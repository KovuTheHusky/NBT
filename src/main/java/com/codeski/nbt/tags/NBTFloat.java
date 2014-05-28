package com.codeski.nbt.tags;

public class NBTFloat extends NBT {
	Float payload;

	public NBTFloat(String name, float payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Float getPayload() {
		return payload;
	}

	public void setPayload(float payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Float] " + name + ": " + payload;
		else
			return "[Float] null: " + payload;
	}
}
