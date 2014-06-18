package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * An array of integers with a maximum of ~2,147,483,647 elements.
 */
public class NBTIntegerArray extends NBT implements List<Integer> {
	private List<Integer> payload;

	public NBTIntegerArray(String name, List<Integer> payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public void add(int index, Integer element) {
		this.getPayload().add(index, element);
	}

	@Override
	public boolean add(Integer e) {
		return this.getPayload().add(e);
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		return this.getPayload().addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Integer> c) {
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
	public boolean equals(Object obj) {
		for (int i = 0; i < this.size(); ++i)
			if (!this.get(i).equals(((NBTIntegerArray) obj).get(i)))
				return false;
		return this.getName() == null && ((NBT) obj).getName() == null || this.getName().equals(((NBT) obj).getName());
	}

	@Override
	public Integer get(int index) {
		return this.getPayload().get(index);
	}

	@Override
	public int getLength() {
		int length = new NBTInteger(null, 0).getLength() + this.getPayload().size() * new NBTInteger(null, 0).getLength(); // Make static getLength() and possibly rename instanced to getSize().
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		return length;
	}

	@Override
	public List<Integer> getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return NBT.INTEGER_ARRAY;
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
	public Iterator<Integer> iterator() {
		return this.getPayload().iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.getPayload().lastIndexOf(o);
	}

	@Override
	public ListIterator<Integer> listIterator() {
		return this.getPayload().listIterator();
	}

	@Override
	public ListIterator<Integer> listIterator(int index) {
		return this.getPayload().listIterator(index);
	}

	@Override
	public Integer remove(int index) {
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
	public Integer set(int index, Integer element) {
		return this.getPayload().set(index, element);
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (List<Integer>) payload;
	}

	@Override
	public int size() {
		return this.getPayload().size();
	}

	@Override
	public List<Integer> subList(int fromIndex, int toIndex) {
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
		for (int e : this.getPayload())
			str += e + ", ";
		str = str.substring(0, str.length() - 2);
		str += " ]";
		return str;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i : this.getPayload())
			sb.append(',').append(i);
		if (this.getName() != null)
			return this.getClass().getSimpleName() + " Name:\"" + this.getName() + "\" Payload:" + sb.substring(1);
		else
			return this.getClass().getSimpleName() + " Payload:" + sb.substring(1);
	}

	@Override
	public String toXML() {
		String str = "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\">";
		for (int e : this.getPayload())
			str += "<NBTInteger payload=\"" + e + "\" />";
		str += "</" + this.getClass().getSimpleName() + ">";
		return str;
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		bytes.putInt(this.getPayload().size());
		for (int i : this.getPayload())
			bytes.putInt(i);
	}
}
