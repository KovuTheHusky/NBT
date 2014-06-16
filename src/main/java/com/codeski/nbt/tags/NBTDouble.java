package com.codeski.nbt.tags;

public class NBTDouble extends NBT {
	private Double payload;

	public NBTDouble(String name, double payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Double getPayload() {
		return payload;
	}

	public void setPayload(double payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Double] " + name + ": " + payload;
		else
			return "[Double] null: " + payload;
	}
}
