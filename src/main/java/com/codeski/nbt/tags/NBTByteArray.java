package com.codeski.nbt.tags;

public class NBTByteArray extends NBT {
	byte[] payload;

	public NBTByteArray(String name, byte[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (byte b : payload)
			sb.append(b).append(' ');
		if (name != null)
			return "[Byte Array] " + name + ": " + sb.substring(0, sb.length() - 1);
		else
			return "[Byte Array] null: " + sb.substring(0, sb.length() - 1);
	}
}
