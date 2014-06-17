package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.codeski.nbt.Main;

public class NBTList extends NBT {
	public static final byte TYPE = 9;
	private NBT[] payload;

	public NBTList(String name, NBT[] payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public int getLength() {
		int length = NBTByte.LENGTH + NBTInteger.LENGTH;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		for (NBT e : this.getPayload())
			length += e.getLength();
		return length;
	}

	@Override
	public NBT[] getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (NBT[]) payload;
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
		sb.append("[" + this.getClass().getSimpleName() + "] Name:\"" + name + "\" " + this.getPayload().length + " entries...\n");
		Main.tabs += "  ";
		for (NBT nbt : payload)
			sb.append(Main.tabs + nbt + "\n");
		Main.tabs = Main.tabs.substring(0, Main.tabs.length() - 2);
		sb.append(Main.tabs + "[/" + this.getClass().getSimpleName() + "]\n");
		return sb.substring(0, sb.length() - 1);
	}

	@Override
	public String toXML() {
		String str = "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\">";
		for (NBT e : this.getPayload())
			str += e.toXML();
		str += "</" + this.getClass().getSimpleName() + ">";
		return str;
	}

	@Override
	public void writePayload(ByteBuffer bytes) {
		bytes.put(this.getPayload()[0].getType());
		bytes.putInt(this.getPayload().length);
		for (NBT e : this.getPayload())
			bytes.put(e.toNBT());
	}
}
