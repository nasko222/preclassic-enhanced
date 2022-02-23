package com.mojang.minecraft;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Textures {
	private static HashMap idMap = new HashMap();

	public static int loadTexture(String resourceName, int mode) {
		try {
			if(idMap.containsKey(resourceName)) {
				return ((Integer)idMap.get(resourceName)).intValue();
			} else {
				IntBuffer e = BufferUtils.createIntBuffer(1);
				e.clear();
				GL11.glGenTextures(e);
				int id = e.get(0);
				idMap.put(resourceName, Integer.valueOf(id));
				System.out.println(resourceName + " -> " + id);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, mode);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, mode);
				BufferedImage img = ImageIO.read(Textures.class.getResourceAsStream(resourceName));
				int w = img.getWidth();
				int h = img.getHeight();
				ByteBuffer pixels = BufferUtils.createByteBuffer(w * h * 4);
				int[] rawPixels = new int[w * h];
				img.getRGB(0, 0, w, h, rawPixels, 0, w);

				for(int i = 0; i < rawPixels.length; ++i) {
					int a = rawPixels[i] >> 24 & 255;
					int r = rawPixels[i] >> 16 & 255;
					int g = rawPixels[i] >> 8 & 255;
					int b = rawPixels[i] & 255;
					rawPixels[i] = a << 24 | b << 16 | g << 8 | r;
				}

				pixels.asIntBuffer().put(rawPixels);
				GLU.gluBuild2DMipmaps(3553, 6408, w, h, 6408, 5121, pixels);
				return id;
			}
		} catch (IOException var14) {
			throw new RuntimeException("!!");
		}
	}
}
