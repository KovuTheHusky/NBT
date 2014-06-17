package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public abstract class NBT {
	public static final int END = 0, BYTE = 1, SHORT = 2, INTEGER = 3, LONG = 4, FLOAT = 5, DOUBLE = 6, BYTE_ARRAY = 7, STRING = 8, LIST = 9, COMPOUND = 10, INTEGER_ARRAY = 11;
	public static final int LENGTH = -1;
	public static final byte TYPE = -1;
	protected String name;

	public NBT(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getName().equals(((NBT) obj).getName()) && this.getPayload().equals(((NBT) obj).getPayload());
	}

	public abstract int getLength();

	public String getName() {
		return name;
	}

	public abstract Object getPayload();

	public abstract byte getType();

	public void setName(String name) {
		this.name = name;
	}

	public void setPayload(Object payload) {
		// Make this abstract and implement in subclasses!
	}

	public String toJSON() {
		if (this.getName() != null)
			return "\"" + this.getName() + "\": " + (this instanceof NBTString ? "\"" + this.getPayload() + "\"" : this.getPayload());
		else
			return this instanceof NBTString ? "\"" + this.getPayload() + "\"" : this.getPayload().toString();
	}

	public byte[] toNBT() {
		ByteBuffer bytes = ByteBuffer.allocate(this.getLength());
		if (this.getName() != null)
			this.writeName(bytes);
		this.writePayload(bytes);
		return bytes.array();
	}

	public String toXML() {
		if (this.getName() != null)
			return "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\" payload=\"" + this.getPayload() + "\" />";
		else
			return "<" + this.getClass().getSimpleName() + " payload=\"" + this.getPayload() + "\" />";
	}

	public void writeName(ByteBuffer bytes) {
		bytes.put(this.getType());
		byte[] name = this.getName().getBytes(Charset.forName("UTF-8"));
		bytes.putShort((short) name.length);
		bytes.put(name);
	}

	public abstract void writePayload(ByteBuffer bytes);
}
