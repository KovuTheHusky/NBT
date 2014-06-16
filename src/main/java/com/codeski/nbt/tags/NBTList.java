package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
	public byte[] toNBT() {
		int bytesForName = 0;
		byte[] name = null;
		short length = 0;
		if (this.name != null) {
			name = this.name.getBytes(Charset.forName("UTF-8"));
			length = (short) name.length;
			bytesForName = 1 + 2 + length;
		}
		ByteBuffer bb = ByteBuffer.allocate(bytesForName + 1 + 4);
		if (this.name != null) {
			bb.put((byte) 0x9);
			bb.putShort(length);
			bb.put(name);
		}
		List<byte[]> bal = new ArrayList<byte[]>();
		int bytecount = 0;
		for (NBT e : payload) {
			byte[] eba = e.toNBT();
			bal.add(eba);
			bytecount += eba.length;
		}
		ByteBuffer combo = ByteBuffer.allocate(bytecount);
		for (byte[] append : bal)
			combo.put(append);
		byte[] fin = new byte[bb.array().length + combo.array().length];
		System.arraycopy(bb.array(), 0, fin, 0, bb.array().length);
		System.arraycopy(combo.array(), 0, fin, bb.array().length, combo.array().length);
		return fin;
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
