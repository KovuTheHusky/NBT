package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public abstract class NBT {
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

	public abstract void setPayload(Object payload);

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

	@Override
	public String toString() {
		if (name != null)
			return "[" + this.getClass().getSimpleName() + "] " + this.getName() + ": " + this.getPayload();
		else
			return "[" + this.getClass().getSimpleName() + "] null: " + this.getPayload();
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
