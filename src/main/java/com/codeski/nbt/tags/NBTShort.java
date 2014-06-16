package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTShort extends NBT {
	private Short payload;

	public NBTShort(String name, short payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Short getPayload() {
		return payload;
	}

	public void setPayload(short payload) {
		this.payload = payload;
	}

	@Override
	public byte[] toNBT() {
		int bytesForName = 0;
		byte[] name = null;
		short length = 0;
		if (this.name != null) {
			name = this.name.getBytes(Charset.forName("UTF-8"));
			length = (short) name.length;
			bytesForName = 2 + length;
		}
		ByteBuffer bb = ByteBuffer.allocate(1 + bytesForName + 2);
		bb.put((byte) 0x2);
		if (this.name != null) {
			bb.putShort(length);
			bb.put(name);
		}
		bb.putShort(payload);
		return bb.array();
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Short] " + name + ": " + payload;
		else
			return "[Short] null: " + payload;
	}
}
