package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.phys.AABB;

public class TransparentTile extends Tile {
	
	protected TransparentTile(int id, int tex) {
		super(id, tex);
	}
	
	public AABB getAABB(int integer1, int integer2, int integer3) {
		return null;
	}

	public boolean blocksLight() {
		return false;
	}

	public boolean isSolid() {
		return false;
	}
}
