package com.mojang.minecraft.level;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Tesselator {
	private static final int MAX_MEMORY_USE = 4194304;
	private static final int MAX_FLOATS = 524288;
	private FloatBuffer buffer = BufferUtils.createFloatBuffer(524288);
	private float[] array = new float[524288];
	private int vertices = 0;
	private float u;
	private float v;
	private float r;
	private float g;
	private float b;
	private boolean hasColor = false;
	private boolean hasTexture = false;
	private int len = 3;
	private int p = 0;
	public static Tesselator instance = new Tesselator();

	public void flush() {
		this.buffer.clear();
		this.buffer.put(this.array, 0, this.p);
		this.buffer.flip();
		if(this.hasTexture && this.hasColor) {
			GL11.glInterleavedArrays(GL11.GL_T2F_C3F_V3F, 0, this.buffer);
		} else if(this.hasTexture) {
			GL11.glInterleavedArrays(GL11.GL_T2F_V3F, 0, this.buffer);
		} else if(this.hasColor) {
			GL11.glInterleavedArrays(GL11.GL_C3F_V3F, 0, this.buffer);
		} else {
			GL11.glInterleavedArrays(GL11.GL_V3F, 0, this.buffer);
		}

		GL11.glEnableClientState('\u8074');
		if(this.hasTexture) {
			GL11.glEnableClientState('\u8078');
		}

		if(this.hasColor) {
			GL11.glEnableClientState('\u8076');
		}

		GL11.glDrawArrays(GL11.GL_QUADS, GL11.GL_POINTS, this.vertices);
		GL11.glDisableClientState('\u8074');
		if(this.hasTexture) {
			GL11.glDisableClientState('\u8078');
		}

		if(this.hasColor) {
			GL11.glDisableClientState('\u8076');
		}

		this.clear();
	}

	private void clear() {
		this.vertices = 0;
		this.buffer.clear();
		this.p = 0;
	}

	public void init() {
		this.clear();
		this.hasColor = false;
		this.hasTexture = false;
	}

	public void tex(float u, float v) {
		if(!this.hasTexture) {
			this.len += 2;
		}

		this.hasTexture = true;
		this.u = u;
		this.v = v;
	}

	public void color(float r, float g, float b) {
		if(!this.hasColor) {
			this.len += 3;
		}

		this.hasColor = true;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public void vertexUV(float x, float y, float z, float u, float v) {
		this.tex(u, v);
		this.vertex(x, y, z);
	}

	public void vertex(float x, float y, float z) {
		if(this.hasTexture) {
			this.array[this.p++] = this.u;
			this.array[this.p++] = this.v;
		}

		if(this.hasColor) {
			this.array[this.p++] = this.r;
			this.array[this.p++] = this.g;
			this.array[this.p++] = this.b;
		}

		this.array[this.p++] = x;
		this.array[this.p++] = y;
		this.array[this.p++] = z;
		++this.vertices;
		if(this.vertices % 4 == 0 && this.p >= 524288 - this.len * 4) {
			this.flush();
		}

	}
}
