package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTIntegerArray extends NBT {
	public static final byte TYPE = 11;
	private int[] payload;

	public NBTIntegerArray(String name, int[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = NBTInteger.LENGTH + this.getPayload().length * NBTInteger.LENGTH;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		return length;
	}

	@Override
	public int[] getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
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

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.putInt(this.getPayload().length);
		for (int i : this.getPayload())
			bytes.putInt(i);
	}
}
