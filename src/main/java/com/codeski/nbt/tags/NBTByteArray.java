package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTByteArray extends NBT {
	private byte[] payload;
	private final byte type = 7;

	public NBTByteArray(String name, byte[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public byte[] getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return type;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	@Override
	public String toJSON() {
		String str = "\"" + this.getName() + "\": [ ";
		for (byte e : this.getPayload())
			str += e + ", ";
		str = str.substring(0, str.length() - 2);
		str += " ]";
		return str;
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
		ByteBuffer bb = ByteBuffer.allocate(bytesForName + 4 + payload.length);
		if (this.name != null) {
			bb.put((byte) 0x7);
			bb.putShort(length);
			bb.put(name);
		}
		bb.putInt(payload.length);
		bb.put(payload);
		return bb.array();
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

	@Override
	public String toXML() {
		String str = "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\">";
		for (byte e : this.getPayload())
			str += "<NBTByte payload=\"" + e + "\" />";
		str += "</" + this.getClass().getSimpleName() + ">";
		return str;
	}
}
