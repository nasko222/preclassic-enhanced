package com.mojang.minecraft.character;

public class ZombieModel {
	public Cube head = new Cube(0, 0);
	public Cube head2 = new Cube(0, 0);
	public Cube head3 = new Cube(0, 0);
	public Cube head4 = new Cube(0, 0);
	public Cube head5 = new Cube(0, 0);
	public Cube head6 = new Cube(0, 0);
	public Cube head7 = new Cube(0, 0);
	public Cube head8 = new Cube(0, 0);
	public Cube head9 = new Cube(0, 0);

	public ZombieModel() {
		this.head.addBox(-8.0F, 8.0F, -8.0F, 16, 16, 16);
		this.head2.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
		this.head3.addBox(-8.0F, -24.0F, -8.0F, 16, 16, 16);
		this.head4.addBox(8.0F, 8.0F, -8.0F, 16, 16, 16);
		this.head5.addBox(8.0F, -8.0F, -8.0F, 16, 16, 16);
		this.head6.addBox(8.0F, -24.0F, -8.0F, 16, 16, 16);
		this.head7.addBox(-24.0F, 8.0F, -8.0F, 16, 16, 16);
		this.head8.addBox(-24.0F, -8.0F, -8.0F, 16, 16, 16);
		this.head9.addBox(-24.0F, -24.0F, -8.0F, 16, 16, 16);
	}

	public void render(float time) {
		this.head.render();
		this.head2.render();
		this.head3.render();
		this.head4.render();
		this.head5.render();
		this.head6.render();
		this.head7.render();
		this.head8.render();
		this.head9.render();
	}
}
