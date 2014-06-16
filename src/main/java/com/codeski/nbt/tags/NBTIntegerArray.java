package com.codeski.nbt.tags;

public class NBTIntegerArray extends NBT {
	private int[] payload;

	public NBTIntegerArray(String name, int[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int[] getPayload() {
		return payload;
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
}
