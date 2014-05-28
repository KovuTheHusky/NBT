package com.codeski.nbt.tags;

public class NBTLong extends NBT {
	Long payload;

	public NBTLong(String name, long payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Long getPayload() {
		return payload;
	}

	public void setPayload(long payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Long] " + name + ": " + payload;
		else
			return "[Long] null: " + payload;
	}
}
