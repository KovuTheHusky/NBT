package com.codeski.nbt.tags;

public class NBTShort extends NBT {
	private Short payload;

	public NBTShort(String name, short payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Short getPayload() {
		return payload;
	}

	public void setPayload(short payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Short] " + name + ": " + payload;
		else
			return "[Short] null: " + payload;
	}
}
