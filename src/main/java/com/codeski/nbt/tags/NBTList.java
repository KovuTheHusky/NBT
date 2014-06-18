package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NBTList extends NBT implements List<NBT> {
	public static final byte TYPE = 9;
	private List<NBT> payload;

	public NBTList(String name, List<NBT> payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public void add(int index, NBT element) {
		this.getPayload().add(index, element);
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
		return this.getPayload().addAll(index, c);
	}

	@Override
	public void clear() {
		this.getPayload().clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.getPayload().contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.getPayload().containsAll(c);
	}

	@Override
	public NBT get(int index) {
		return this.getPayload().get(index);
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
	public List<NBT> getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return TYPE;
	}

	@Override
	public int indexOf(Object o) {
		return this.getPayload().indexOf(o);
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
		return this.getPayload().lastIndexOf(o);
	}

	@Override
	public ListIterator<NBT> listIterator() {
		return this.getPayload().listIterator();
	}

	@Override
	public ListIterator<NBT> listIterator(int index) {
		return this.getPayload().listIterator(index);
	}

	@Override
	public NBT remove(int index) {
		return this.getPayload().remove(index);
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
		return this.getPayload().set(index, element);
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (List<NBT>) payload;
	}

	@Override
	public int size() {
		return this.getPayload().size();
	}

	@Override
	public List<NBT> subList(int fromIndex, int toIndex) {
		return this.getPayload().subList(fromIndex, toIndex);
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
		String str = "\"" + this.getName() + "\": [ ";
		for (NBT e : this.getPayload())
			str += e.toJSON() + ", ";
		str = str.substring(0, str.length() - 2);
		str += " ]";
		return str;
	}

	@Override
	public String toString() {
		if (this.getName() != null)
			return this.getClass().getSimpleName() + " Name:\"" + this.getName() + "\" " + this.getPayload().size() + " entries";
		else
			return this.getClass().getSimpleName() + " " + this.getPayload().size() + " entries";
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
		bytes.put(this.getPayload().get(0).getType());
		bytes.putInt(this.getPayload().size());
		for (NBT e : this.getPayload())
			bytes.put(e.toNBT());
	}
}
