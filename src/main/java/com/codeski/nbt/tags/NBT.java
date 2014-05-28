package com.codeski.nbt.tags;

public abstract class NBT {
	protected String name;

	public NBT(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract Object getPayload();

	public void setName(String name) {
		this.name = name;
	}
}
