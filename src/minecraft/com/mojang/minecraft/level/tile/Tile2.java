package com.mojang.minecraft.level.tile;

public class Tile2 extends Tile {
	
	private int texture1;
	private int texture2;
	
	protected Tile2(int id, int texture1, int texture2, int particleTex) {
		super(id);
		this.texture1 = texture1;
		this.texture2 = texture2;
		this.tex = particleTex;
	}

	protected int getTexture(int face) {
		if (face == 0 || face == 1) {
			return texture1;
		} else {
			return texture2;
		}
	}
}
