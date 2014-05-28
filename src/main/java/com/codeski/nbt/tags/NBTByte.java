package com.codeski.nbt.tags;

public class NBTByte extends NBT {
	Byte payload;

	public NBTByte(String name, byte payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Byte getPayload() {
		return payload;
	}

	public void setPayload(byte payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Byte] " + name + ": " + payload;
		else
			return "[Byte] null: " + payload;
	}
}
