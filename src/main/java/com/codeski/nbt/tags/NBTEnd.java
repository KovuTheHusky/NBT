package com.codeski.nbt.tags;

import java.nio.ByteBuffer;

public class NBTEnd extends NBT {
	public NBTEnd() {
		super(null);
	}

	@Override
	public Object getPayload() {
		return null;
	}

	@Override
	public byte[] toNBT() {
		ByteBuffer bb = ByteBuffer.allocate(1);
		bb.put((byte) 0x0);
		return bb.array();
	}

	@Override
	public String toString() {
		return "[End]";
	}
}
