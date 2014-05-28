package com.codeski.nbt.tags;

public class NBTEnd extends NBT {
	public NBTEnd() {
		super(null);
	}

	@Override
	public Object getPayload() {
		return null;
	}

	@Override
	public String toString() {
		return "[End]";
	}
}
