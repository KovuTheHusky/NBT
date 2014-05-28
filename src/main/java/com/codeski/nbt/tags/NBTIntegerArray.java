package com.codeski.nbt.tags;

public class NBTIntegerArray extends NBT {
	int[] payload;

	public NBTIntegerArray(String name, int[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int[] getPayload() {
		return payload;
	}

	public void setPayload(int[] payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i : payload)
			sb.append(i).append(' ');
		if (name != null)
			return "[Integer Array] " + name + ": " + sb.substring(0, sb.length() - 1);
		else
			return "[Integer Array] null: " + sb.substring(0, sb.length() - 1);
	}
}
