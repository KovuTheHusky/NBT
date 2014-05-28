package com.codeski.nbt.tags;

public class NBTInteger extends NBT {
	Integer payload;

	public NBTInteger(String name, int payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Integer getPayload() {
		return payload;
	}

	public void setPayload(int payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Integer] " + name + ": " + payload;
		else
			return "[Integer] null: " + payload;
	}
}
