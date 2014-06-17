package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NBTByteArray extends NBT {
	public static final byte TYPE = 7;
	private byte[] payload;

	public NBTByteArray(String name, byte[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = NBTInteger.LENGTH + this.getPayload().length;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		return length;
	}

	@Override
	public byte[] getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (byte[]) payload;
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (byte b : payload)
			sb.append(b).append(' ');
		if (name != null)
			return "[" + this.getClass().getSimpleName() + "] " + name + ": " + sb.substring(0, sb.length() - 1);
		else
			return "[" + this.getClass().getSimpleName() + "] null: " + sb.substring(0, sb.length() - 1);
	}

	@Override
	public String toXML() {
		String str = "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\">";
		for (byte e : this.getPayload())
			str += "<NBTByte payload=\"" + e + "\" />";
		str += "</" + this.getClass().getSimpleName() + ">";
		return str;
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.putInt(this.getPayload().length);
		bytes.put(this.getPayload());
	}
}
