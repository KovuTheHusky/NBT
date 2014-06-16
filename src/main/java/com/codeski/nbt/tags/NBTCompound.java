package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NBTCompound extends NBT implements List<NBT> {
	private List<NBT> payload;
	private final byte type = 10;

	public NBTCompound(String name, List<NBT> payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public void add(int index, NBT element) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean add(NBT e) {
		return this.getPayload().add(e);
	}

	@Override
	public boolean addAll(Collection<? extends NBT> c) {
		return this.getPayload().addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends NBT> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		this.getPayload().clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.getPayload().contains(o);
	}

	public boolean contains(String name) {
		for (NBT e : this.getPayload())
			if (e.getName().equals(name))
				return true;
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.getPayload().containsAll(c);
	}

	@Override
	public NBT get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public NBT get(String name) {
		for (NBT e : this.getPayload())
			if (e.getName().equals(name))
				return e;
		return null;
	}

	@Override
	public List<NBT> getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return type;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return this.getPayload().isEmpty();
	}

	@Override
	public Iterator<NBT> iterator() {
		return this.getPayload().iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<NBT> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<NBT> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NBT remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		return this.getPayload().remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.getPayload().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.getPayload().retainAll(c);
	}

	@Override
	public NBT set(int index, NBT element) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPayload(List<NBT> payload) {
		this.payload = payload;
	}

	@Override
	public int size() {
		return this.getPayload().size();
	}

	@Override
	public List<NBT> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		return this.getPayload().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.getPayload().toArray(a);
	}

	@Override
	public String toJSON() {
		String str = "";
		if (this.getName() != null)
			str += "\"" + this.getName() + "\": ";
		str += "{ ";
		for (NBT e : this.getPayload())
			str += e.toJSON() + ", ";
		str = str.substring(0, str.length() - 2);
		str += " }";
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
		ByteBuffer bb = ByteBuffer.allocate(bytesForName);
		if (this.name != null) {
			bb.put((byte) 0xA);
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
		ByteBuffer combo = ByteBuffer.allocate(bytecount + 1);
		for (byte[] append : bal)
			combo.put(append);
		combo.put((byte) 0x0);
		byte[] fin = new byte[bb.array().length + combo.array().length];
		System.arraycopy(bb.array(), 0, fin, 0, bb.array().length);
		System.arraycopy(combo.array(), 0, fin, bb.array().length, combo.array().length);
		return fin;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Compound] " + name + ": " + this.size() + " entries\n");
		for (NBT nbt : payload)
			sb.append(nbt + "\n");
		return sb.substring(0, sb.length() - 1);
	}

	@Override
	public String toXML() {
		String str = "";
		if (this.getName() != null)
			str += "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\">";
		else
			str += "<" + this.getClass().getSimpleName() + ">";
		for (NBT e : this.getPayload())
			str += e.toXML();
		str += "</" + this.getClass().getSimpleName() + ">";
		return str;
	}
}
