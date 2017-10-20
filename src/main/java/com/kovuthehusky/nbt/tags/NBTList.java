package com.kovuthehusky.nbt.tags;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A list of tag payloads, without repeated tag types or any tag names.
 */
public final class NBTList extends NBT<List<NBT<?>>> implements List<NBT<?>> {
    public static final byte TYPE = 9;
    private final byte listType;

    public NBTList(String name, byte type, List<NBT<?>> payload) {
        super(name, payload);
        listType = type;
    }

    @Override
    public void add(int index, NBT<?> element) {
        this.getPayload().add(index, element);
    }

    @Override
    public boolean add(NBT<?> e) {
        return this.getPayload().add(e);
    }

    @Override
    public boolean addAll(Collection<? extends NBT<?>> c) {
        return this.getPayload().addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends NBT<?>> c) {
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
        if (!(obj instanceof NBTList))
            return false;
        NBTList that = (NBTList) obj;
        if (this.size() != that.size())
            return false;
        for (int i = 0; i < this.size(); ++i)
            if (!this.get(i).equals(that.get(i)))
                return false;
        return this.getName() == null && that.getName() == null || this.getName().equals(that.getName());
    }

    @Override
    public NBT<?> get(int index) {
        return this.getPayload().get(index);
    }

    @Override
    public int getLength() {
        int length = new NBTByte(null, (byte) 0).getLength() + new NBTInteger(null, 0).getLength();
        if (this.getName() != null)
            length += 3 + (short) this.getName().getBytes(NBT.CHARSET).length;
        for (NBT<?> e : this.getPayload())
            length += e.getLength();
        return length;
    }

    public byte getListType() {
        return listType;
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
    public Iterator<NBT<?>> iterator() {
        return this.getPayload().iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.getPayload().lastIndexOf(o);
    }

    @Override
    public ListIterator<NBT<?>> listIterator() {
        return this.getPayload().listIterator();
    }

    @Override
    public ListIterator<NBT<?>> listIterator(int index) {
        return this.getPayload().listIterator(index);
    }

    @Override
    public NBT<?> remove(int index) {
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
    public NBT<?> set(int index, NBT<?> element) {
        return this.getPayload().set(index, element);
    }

    @Override
    public int size() {
        return this.getPayload().size();
    }

    @Override
    public List<NBT<?>> subList(int fromIndex, int toIndex) {
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
        String str = "\"" + this.getName() + "\":[";
        if (!this.getPayload().isEmpty()) {
            for (NBT<?> e : this.getPayload())
                str += e.toJSON() + ",";
            str = str.substring(0, str.length() - 1);
        }
        str += "]";
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
        for (NBT<?> e : this.getPayload())
            str += e.toXML();
        str += "</" + this.getClass().getSimpleName() + ">";
        return str;
    }

    @Override
    protected void writePayload(ByteBuffer bytes) {
        bytes.put(this.getListType());
        bytes.putInt(this.getPayload().size());
        for (NBT<?> e : this.getPayload())
            bytes.put(e.toNBT());
    }
}
