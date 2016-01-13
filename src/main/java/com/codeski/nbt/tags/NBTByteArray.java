package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * An array of bytes with a maximum of ~2,147,483,647 elements.
 */
public final class NBTByteArray extends NBT<List<Byte>> implements List<Byte> {
    public NBTByteArray(String name, List<Byte> payload) {
        super(name, payload);
    }

    @Override
    public boolean add(Byte e) {
        return this.getPayload().add(e);
    }

    @Override
    public void add(int index, Byte element) {
        this.getPayload().add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends Byte> c) {
        return this.getPayload().addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Byte> c) {
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
        if (!(obj instanceof NBTByteArray))
            return false;
        NBTByteArray that = (NBTByteArray) obj;
        if (this.size() != that.size())
            return false;
        for (int i = 0; i < this.size(); ++i)
            if (!this.get(i).equals(that.get(i)))
                return false;
        return this.getName() == null && that.getName() == null || this.getName().equals(that.getName());
    }

    @Override
    public Byte get(int index) {
        return this.getPayload().get(index);
    }

    @Override
    public int getLength() {
        int length = new NBTInteger(null, 0).getLength() + this.getPayload().size();
        if (this.getName() != null)
            length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
        return length;
    }

    @Override
    public byte getType() {
        return NBT.BYTE_ARRAY;
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
    public Iterator<Byte> iterator() {
        return this.getPayload().iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.getPayload().lastIndexOf(o);
    }

    @Override
    public ListIterator<Byte> listIterator() {
        return this.getPayload().listIterator();
    }

    @Override
    public ListIterator<Byte> listIterator(int index) {
        return this.getPayload().listIterator(index);
    }

    @Override
    public Byte remove(int index) {
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
    public Byte set(int index, Byte element) {
        return this.getPayload().set(index, element);
    }

    @Override
    public int size() {
        return this.getPayload().size();
    }

    @Override
    public List<Byte> subList(int fromIndex, int toIndex) {
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
        if (!this.getPayload().isEmpty()) {
            for (byte e : this.getPayload())
                str += e + ", ";
            str = str.substring(0, str.length() - 2);
        }
        str += " ]";
        return str;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : this.getPayload())
            sb.append(',').append(b);
        if (this.getName() != null)
            return this.getClass().getSimpleName() + " Name:\"" + this.getName() + "\" Payload:" + sb.substring(1);
        else
            return this.getClass().getSimpleName() + " Payload:" + sb.substring(1);
    }

    @Override
    public String toXML() {
        String str = "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\">";
        for (byte e : this.getPayload())
            str += "<NBTByte payload=\"" + e + "\" />";
        str += "</" + this.getClass().getSimpleName() + ">";
        return str;
    }

    @Override
    protected void writePayload(ByteBuffer bytes) {
        bytes.putInt(this.getPayload().size());
        for (byte b : this.getPayload())
            bytes.put(b);
    }
}
