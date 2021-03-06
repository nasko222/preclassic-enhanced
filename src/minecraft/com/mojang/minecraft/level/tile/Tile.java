package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.Tesselator;
import com.mojang.minecraft.particle.Particle;
import com.mojang.minecraft.particle.ParticleEngine;
import com.mojang.minecraft.phys.AABB;
import java.util.Random;

public class Tile {
	public static final Tile[] tiles = new Tile[256];
	public static final Tile empty = null;
	public static final Tile rock = new Tile(1, 1);
	public static final Tile grass = new GrassTile(2);
	public static final Tile dirt = new DirtTile(3, 2);
	public static final Tile cobblestone = new Tile(4, 5);
	public static final Tile wood = new Tile(5, 4);
	public static final Tile goldOre = new Tile(6, 6);
	public static final Tile ironOre = new Tile(7, 7);
	public static final Tile coalOre = new Tile(8, 8);
	public static final Tile diamondOre = new Tile(9, 9);
	public static final Tile redstoneOre = new Tile(10, 10);
	public static final Tile lapisOre = new Tile(11, 11);
	public static final Tile emeraldOre = new Tile(12, 12);
	public static final Tile2 log = new Tile2(13, 14, 13, 13);
	public static final Tile sapling1 = new Bush(14, 15);
	public static final Tile sapling2 = new Bush(15, 95);
	public static final Tile sapling3 = new Bush(16, 111);
	public static final Tile flower1 = new Bush(17, 31);
	public static final Tile flower2 = new Bush(18, 63);
	public static final Tile mushroom1 = new Bush(19, 47);
	public static final Tile mushroom2 = new Bush(20, 79);
	public static final Tile sand1 = new Tile(21, 16);
	public static final Tile sand2 = new Tile(22, 32);
	public static final Tile sandstone1 = new Tile(23, 48);
	public static final Tile2 sandstone2 = new Tile2(24, 48, 64, 48);
	public static final Tile2 sandstone3 = new Tile2(25, 48, 49, 48);
	public static final Tile2 sandstone4 = new Tile2(26, 48, 65, 48);
	public static final Tile gravel1 = new Tile(27, 17);
	public static final Tile gravel2 = new Tile(28, 33);
	public static final Tile mossStone = new Tile(29, 18);
	public static final Tile mossStoneBricks = new Tile(30, 34);
	public static final Tile crackStoneBricks = new Tile(31, 35);
	public static final Tile2 bookcase = new Tile2(32, 20, 21, 20);
	public static final Tile ironBlock = new Tile(33, 22);
	public static final Tile goldBlock = new Tile(34, 23);
	public static final Tile diamondBlock = new Tile(35, 24);
	public static final Tile emeraldBlock = new Tile(36, 25);
	public static final Tile redstoneBlock = new Tile(37, 26);
	public static final Tile lapisBlock = new Tile(38, 27);
	public static final Tile2 nasko = new Tile2(39, 43, 44, 44);
	public static final Tile leaf1 = new Tile(40, 67);
	public static final Tile leaf2 = new Tile(41, 66);
	public static final Tile netherrack = new Tile(42, 36);
	public static final Tile soulsand = new Tile(43, 37);
	public static final Tile glowstone = new Tile(44, 38);
	public static final Tile endstone = new Tile(45, 39);
	public static final Tile techblock = new Tile(46, 50);
	public static final Tile redstoneLampOff = new Tile(47, 51);
	public static final Tile redstoneLampOn = new Tile(48, 52);
	public static final Tile commandBlock = new Tile(49, 53);
	public static final Tile2 tnt = new Tile2(50, 61, 60, 61);
	
	public int tex;
	public final int id;

	
	
	protected Tile(int id) {
		tiles[id] = this;
		this.id = id;
	}

	public Tile(int id, int tex) {
		this(id);
		this.tex = tex;
	}

	public void render(Tesselator t, Level level, int layer, int x, int y, int z) {
		float c1 = 1.0F;
		float c2 = 0.8F;
		float c3 = 0.6F;
		if(this.shouldRenderFace(level, x, y - 1, z, layer)) {
			t.color(c1, c1, c1);
			this.renderFace(t, x, y, z, 0);
		}

		if(this.shouldRenderFace(level, x, y + 1, z, layer)) {
			t.color(c1, c1, c1);
			this.renderFace(t, x, y, z, 1);
		}

		if(this.shouldRenderFace(level, x, y, z - 1, layer)) {
			t.color(c2, c2, c2);
			this.renderFace(t, x, y, z, 2);
		}

		if(this.shouldRenderFace(level, x, y, z + 1, layer)) {
			t.color(c2, c2, c2);
			this.renderFace(t, x, y, z, 3);
		}

		if(this.shouldRenderFace(level, x - 1, y, z, layer)) {
			t.color(c3, c3, c3);
			this.renderFace(t, x, y, z, 4);
		}

		if(this.shouldRenderFace(level, x + 1, y, z, layer)) {
			t.color(c3, c3, c3);
			this.renderFace(t, x, y, z, 5);
		}

	}

	private boolean shouldRenderFace(Level level, int x, int y, int z, int layer) {
		return !level.isSolidTile(x, y, z) && level.isLit(x, y, z) ^ layer == 1;
	}

	protected int getTexture(int face) {
		return this.tex;
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
		float y1 = (float)y + 1.0F;
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

	public void renderFaceNoTexture(Tesselator t, int x, int y, int z, int face) {
		float x0 = (float)x + 0.0F;
		float x1 = (float)x + 1.0F;
		float y0 = (float)y + 0.0F;
		float y1 = (float)y + 1.0F;
		float z0 = (float)z + 0.0F;
		float z1 = (float)z + 1.0F;
		if(face == 0) {
			t.vertex(x0, y0, z1);
			t.vertex(x0, y0, z0);
			t.vertex(x1, y0, z0);
			t.vertex(x1, y0, z1);
		}

		if(face == 1) {
			t.vertex(x1, y1, z1);
			t.vertex(x1, y1, z0);
			t.vertex(x0, y1, z0);
			t.vertex(x0, y1, z1);
		}

		if(face == 2) {
			t.vertex(x0, y1, z0);
			t.vertex(x1, y1, z0);
			t.vertex(x1, y0, z0);
			t.vertex(x0, y0, z0);
		}

		if(face == 3) {
			t.vertex(x0, y1, z1);
			t.vertex(x0, y0, z1);
			t.vertex(x1, y0, z1);
			t.vertex(x1, y1, z1);
		}

		if(face == 4) {
			t.vertex(x0, y1, z1);
			t.vertex(x0, y1, z0);
			t.vertex(x0, y0, z0);
			t.vertex(x0, y0, z1);
		}

		if(face == 5) {
			t.vertex(x1, y0, z1);
			t.vertex(x1, y0, z0);
			t.vertex(x1, y1, z0);
			t.vertex(x1, y1, z1);
		}

	}

	public final AABB getTileAABB(int x, int y, int z) {
		return new AABB((float)x, (float)y, (float)z, (float)(x + 1), (float)(y + 1), (float)(z + 1));
	}

	public AABB getAABB(int x, int y, int z) {
		return new AABB((float)x, (float)y, (float)z, (float)(x + 1), (float)(y + 1), (float)(z + 1));
	}

	public boolean blocksLight() {
		return true;
	}

	public boolean isSolid() {
		return true;
	}

	public void tick(Level level, int x, int y, int z, Random random) {
	}

	public void destroy(Level level, int x, int y, int z, ParticleEngine particleEngine) {
		byte SD = 4;

		for(int xx = 0; xx < SD; ++xx) {
			for(int yy = 0; yy < SD; ++yy) {
				for(int zz = 0; zz < SD; ++zz) {
					float xp = (float)x + ((float)xx + 0.5F) / (float)SD;
					float yp = (float)y + ((float)yy + 0.5F) / (float)SD;
					float zp = (float)z + ((float)zz + 0.5F) / (float)SD;
					particleEngine.add(new Particle(level, xp, yp, zp, xp - (float)x - 0.5F, yp - (float)y - 0.5F, zp - (float)z - 0.5F, this.tex));
				}
			}
		}

	}
}
