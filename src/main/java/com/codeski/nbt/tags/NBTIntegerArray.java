package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTIntegerArray extends NBT {
	private int[] payload;
	private final byte type = 11;

	public NBTIntegerArray(String name, int[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int[] getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return type;
	}

	public void setPayload(int[] payload) {
		this.payload = payload;
	}

	@Override
	public String toJSON() {
		String str = "\"" + this.getName() + "\": [ ";
		for (int e : this.getPayload())
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
		ByteBuffer bb = ByteBuffer.allocate(bytesForName + 4 + 4 * payload.length);
		if (this.name != null) {
			bb.put((byte) 0xB);
			bb.putShort(length);
			bb.put(name);
		}
		bb.putInt(payload.length);
		for (int i : payload)
			bb.putInt(i);
		return bb.array();
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

	@Override
	public String toXML() {
		String str = "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\">";
		for (int e : this.getPayload())
			str += "<NBTInteger payload=\"" + e + "\" />";
		str += "</" + this.getClass().getSimpleName() + ">";
		return str;
	}
}
