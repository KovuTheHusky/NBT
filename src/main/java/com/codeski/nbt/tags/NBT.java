package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Base abstract class that all subclasses extend upon. More information can be found at the article for NBT format on Minecraft Wiki:<br>
 * <a href="http://minecraft.gamepedia.com/NBT_format">http://minecraft.gamepedia.com/NBT_format</a>
 */
public abstract class NBT<T> {
	/**
	 * Constants representing the types defined by the NBT specification.
	 */
	public static final int END = 0, BYTE = 1, SHORT = 2, INTEGER = 3, LONG = 4, FLOAT = 5, DOUBLE = 6, BYTE_ARRAY = 7, STRING = 8, LIST = 9, COMPOUND = 10, INTEGER_ARRAY = 11;
	/**
	 * The charset to use for reading a String from an NBT file.
	 */
	protected static final Charset CHARSET = Charset.forName("UTF-8");
	/**
	 * The name of this named binary tag.
	 */
	protected String name;
	/**
	 * The payload of this named binary tag.
	 */
	protected T payload;

	protected NBT(String name, T payload) {
		this.name = name;
		this.payload = payload;
	}

	/**
	 * Compares this object to the specified object.
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NBT))
			return false;
		NBT<?> nbt = (NBT<?>) obj;
		return this.getName() == null && nbt.getName() == null || this.getName().equals(nbt.getName()) && this.getPayload().equals(nbt.getPayload());
	}

	/**
	 * Get the length of this tag in bytes as an <code>Integer</code>. Includes its type and name if it's not in a list.
	 */
	public abstract int getLength();

	/**
	 * Get the name of this tag as a <code>String</code>. This will be <code>null</code> if this tag is in a list.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the payload of this tag as the type specified in its subclass.
	 */
	public T getPayload() {
		return this.payload;
	}

	/**
	 * Get the type of this tag as a <code>Byte</code>.
	 */
	public abstract byte getType();

	/**
	 * Replaces the name of this tag with the <code>String</code> specified.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Replaces the payload of this tag with the <code>Object</code> specified.
	 */
	public void setPayload(T payload) {
		this.payload = payload;
	}

	/**
	 * Returns a <code>String</code> object representing this tag's value as JSON.
	 */
	public String toJSON() {
		if (this.getName() != null)
			return "\"" + this.getName() + "\": " + (this instanceof NBTString ? "\"" + this.getPayload() + "\"" : this.getPayload());
		else
			return this instanceof NBTString ? "\"" + this.getPayload() + "\"" : this.getPayload().toString();
	}

	/**
	 * Returns a <code>Byte[]</code> representing this tag's value as NBT.
	 */
	public byte[] toNBT() {
		ByteBuffer bytes = ByteBuffer.allocate(this.getLength());
		if (this.getName() != null)
			this.writeName(bytes);
		this.writePayload(bytes);
		return bytes.array();
	}

	/**
	 * Returns a <code>String</code> object representing this tag's value.
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.getName() != null)
			return this.getClass().getSimpleName() + " Name:\"" + this.getName() + "\" Payload:" + this.getPayload();
		else
			return this.getClass().getSimpleName() + " Payload:" + this.getPayload();
	}

	/**
	 * Returns a <code>String</code> object representing this tag's value as XML.
	 */
	public String toXML() {
		if (this.getName() != null)
			return "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\" payload=\"" + this.getPayload() + "\" />";
		else
			return "<" + this.getClass().getSimpleName() + " payload=\"" + this.getPayload() + "\" />";
	}

	private void writeName(ByteBuffer bytes) {
		bytes.put(this.getType());
		byte[] name = this.getName().getBytes(Charset.forName("UTF-8"));
		bytes.putShort((short) name.length);
		bytes.put(name);
	}

	protected abstract void writePayload(ByteBuffer bytes);
}
