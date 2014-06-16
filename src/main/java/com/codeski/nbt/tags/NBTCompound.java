package com.codeski.nbt.tags;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class NBTCompound extends NBT implements Set<NBT> {
	private Set<NBT> payload;

	public NBTCompound(String name, Set<NBT> payload) {
		super(name);
		this.payload = payload;
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

	public NBT get(String name) {
		for (NBT e : this.getPayload())
			if (e.getName().equals(name))
				return e;
		return null;
	}

	@Override
	public Set<NBT> getPayload() {
		return payload;
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

	public void setPayload(Set<NBT> payload) {
		this.payload = payload;
	}

	@Override
	public int size() {
		return this.getPayload().size();
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
