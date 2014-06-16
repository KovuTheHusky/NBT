package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

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
	public byte[] toNBT() {
		int bytesForName = 0;
		byte[] name = null;
		short length = 0;
		if (this.name != null) {
			name = this.name.getBytes(Charset.forName("UTF-8"));
			length = (short) name.length;
			bytesForName = 2 + length;
		}
		byte[] value = payload.getBytes(Charset.forName("UTF-8"));
		short lengthValue = (short) value.length;
		ByteBuffer bb = ByteBuffer.allocate(1 + bytesForName + 2 + lengthValue);
		bb.put((byte) 0x8);
		if (this.name != null) {
			bb.putShort(length);
			bb.put(name);
		}
		bb.putShort(lengthValue);
		bb.put(value);
		return bb.array();
	}

	@Override
	public String toString() {
		if (name != null)
			return "[String] " + name + ": " + payload;
		else
			return "[String] null: " + payload;
	}
}
