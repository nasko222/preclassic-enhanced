package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.Tesselator;

public class TileY extends Tile {
	
	private float height;
	
	protected TileY(int id, int tex, float height) {
		super(id, tex);
		this.height = height;
	}
	
	public void renderFace(Tesselator t, int x, int y, int z, int face) {
		int tex = this.getTexture(face);
		float u0 = (float)(tex % 16) / 16.0F;
		float u1 = u0 + 0.0624375F;
		float v0 = (float)(tex / 16) / 16.0F;
		float v1 = v0 + 0.0624375F;
		float x0 = (float)x + 0.0F;
		float x1 = (float)x + 1.0F;
		float y0 = (float)y + 0.0F;
		float y1 = (float)y + height;
		float z0 = (float)z + 0.0F;
		float z1 = (float)z + 1.0F;
		if(face == 0) {
			t.vertexUV(x0, y0, z1, u0, v1);
			t.vertexUV(x0, y0, z0, u0, v0);
			t.vertexUV(x1, y0, z0, u1, v0);
			t.vertexUV(x1, y0, z1, u1, v1);
		}

		if(face == 1) {
			t.vertexUV(x1, y1, z1, u1, v1);
			t.vertexUV(x1, y1, z0, u1, v0);
			t.vertexUV(x0, y1, z0, u0, v0);
			t.vertexUV(x0, y1, z1, u0, v1);
		}

		if(face == 2) {
			t.vertexUV(x0, y1, z0, u1, v0);
			t.vertexUV(x1, y1, z0, u0, v0);
			t.vertexUV(x1, y0, z0, u0, v1);
			t.vertexUV(x0, y0, z0, u1, v1);
		}

		if(face == 3) {
			t.vertexUV(x0, y1, z1, u0, v0);
			t.vertexUV(x0, y0, z1, u0, v1);
			t.vertexUV(x1, y0, z1, u1, v1);
			t.vertexUV(x1, y1, z1, u1, v0);
		}

		if(face == 4) {
			t.vertexUV(x0, y1, z1, u1, v0);
			t.vertexUV(x0, y1, z0, u0, v0);
			t.vertexUV(x0, y0, z0, u0, v1);
			t.vertexUV(x0, y0, z1, u1, v1);
		}

		if(face == 5) {
			t.vertexUV(x1, y0, z1, u0, v1);
			t.vertexUV(x1, y0, z0, u1, v1);
			t.vertexUV(x1, y1, z0, u1, v0);
			t.vertexUV(x1, y1, z1, u0, v0);
		}

	}
}
