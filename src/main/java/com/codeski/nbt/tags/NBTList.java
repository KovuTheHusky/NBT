package com.codeski.nbt.tags;

public class NBTList extends NBT {
	NBT[] payload;

	public NBTList(String name, NBT[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public NBT[] getPayload() {
		return payload;
	}

	public void setPayload(NBT[] payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (NBT nbt : payload)
			sb.append(nbt).append(' ');
		if (name != null)
			return "[List] " + name + ": " + sb.substring(0, sb.length() - 1).replace('\n', ' ');
		else
			return "[List] null: " + sb.substring(0, sb.length() - 1).replace('\n', ' ');
	}
}
