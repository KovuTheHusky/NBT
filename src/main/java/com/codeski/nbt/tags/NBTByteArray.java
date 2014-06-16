package com.codeski.nbt.tags;

public class NBTByteArray extends NBT {
	private byte[] payload;

	public NBTByteArray(String name, byte[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public byte[] getPayload() {
		return payload;
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
