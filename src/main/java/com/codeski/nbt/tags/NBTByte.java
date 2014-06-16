package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTByte extends NBT {
	private Byte payload;
	private final byte type = 1;

	public NBTByte(String name, byte payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public Byte getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return type;
	}

	public void setPayload(byte payload) {
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
			bytesForName = 1 + 2 + length;
		}
		ByteBuffer bb = ByteBuffer.allocate(bytesForName + 1);
		if (this.name != null) {
			bb.put((byte) 0x1);
			bb.putShort(length);
			bb.put(name);
		}
		bb.put(payload);
		return bb.array();
	}

	@Override
	public String toString() {
		if (name != null)
			return "[Byte] " + name + ": " + payload;
		else
			return "[Byte] null: " + payload;
	}
}
