package com.codeski.nbt.tags;

public class NBTList extends NBT {
	private NBT[] payload;

	public NBTList(String name, NBT[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public NBT[] getPayload() {
		return payload;
	}

	public void setPayload(NBT[] payload) {
		this.payload = payload;
	}

	@Override
	public String toJSON() {
		String str = "\"" + this.getName() + "\": [ ";
		for (NBT e : this.getPayload())
			str += e.toJSON() + ", ";
		str = str.substring(0, str.length() - 2);
		str += " ]";
		return str;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (NBT nbt : payload)
			sb.append(nbt).append(' ');
		if (name != null)
			return "[List] " + name + ": " + sb.substring(0, sb.length() - 1).replace('\n', ' ');
		else
			return "[List] null: " + sb.substring(0, sb.length() - 1).replace('\n', ' ');
	}

	@Override
	public String toXML() {
		String str = "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\">";
		for (NBT e : this.getPayload())
			str += e.toXML();
		str += "</" + this.getClass().getSimpleName() + ">";
		return str;
	}
}
