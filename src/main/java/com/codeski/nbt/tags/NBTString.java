package com.codeski.nbt.tags;

public class NBTString extends NBT {
	private String payload;

	public NBTString(String name, String payload) {
		super(name);
		this.payload = payload;
	}

	public short getLength() {
		if (payload.length() > Short.MAX_VALUE)
			System.err.println("Payload length exceeds maximum value of short. " + this);
		return (short) payload.length();
	}

	@Override
	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (name != null)
			return "[String] " + name + ": " + payload;
		else
			return "[String] null: " + payload;
	}
}
