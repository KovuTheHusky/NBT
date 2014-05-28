package com.codeski.nbt.tags;

public class NBTCompound extends NBT {
	NBT[] payload;

	public NBTCompound(String name, NBT[] payload) {
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
		sb.append("[Compound] " + name + ": " + (payload.length - 1) + " entries\n");
		for (NBT nbt : payload)
			sb.append(nbt + "\n");
		return sb.substring(0, sb.length() - 1);
	}
}
