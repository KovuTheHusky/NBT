package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Base abstract class that all subclasses extend upon. More information can be found at the article for NBT format on Minecraft Wiki:<br>
 * <a href="http://minecraft.gamepedia.com/NBT_format">http://minecraft.gamepedia.com/NBT_format</a>
 */
public abstract class NBT<T> {
	/**
	 * The <code>Charset</code> to use for reading a <code>String</code> from an NBT file.
	 */
	public static final Charset CHARSET = Charset.forName("UTF-8");
	/**
	 * Constants representing the types defined by the NBT specification.
	 */
	public static final int END = 0, BYTE = 1, SHORT = 2, INTEGER = 3, LONG = 4, FLOAT = 5, DOUBLE = 6, BYTE_ARRAY = 7, STRING = 8, LIST = 9, COMPOUND = 10, INTEGER_ARRAY = 11;
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
		if (!(obj instanceof NBT<?>))
			return false;
		NBT<?> that = (NBT<?>) obj;
		return this.getName() == null && that.getName() == null || this.getName().equals(that.getName()) && this.getPayload().equals(that.getPayload());
	}

	/**
	 * Get the length of this tag in bytes as an <code>Integer</code>. Includes its type and name if it's not in a list.
	 *
	 * @return The length of this tag.
	 */
	public abstract int getLength();

	/**
	 * Get the name of this tag as a <code>String</code>. This will be <code>null</code> if this tag is in a list.
	 *
	 * @return The name of this tag.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the payload of this tag as the type specified in its subclass.
	 *
	 * @return The payload of this tag.
	 */
	public T getPayload() {
		return this.payload;
	}

	/**
	 * Get the type of this tag as a <code>Byte</code>.
	 *
	 * @return The type of this tag.
	 */
	public abstract byte getType();

	/**
	 * Replaces the name of this tag with the <code>String</code> specified.
	 *
	 * @param name
	 *            The name of this tag.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Replaces the payload of this tag with the <code>Object</code> specified.
	 *
	 * @param payload
	 *            The payload of this tag.
	 */
	public void setPayload(T payload) {
		this.payload = payload;
	}

	/**
	 * Returns a <code>String</code> object representing this tag's value as JSON.
	 *
	 * @return A JSON representation of this tag.
	 */
	public String toJSON() {
		if (this.getName() != null)
			return "\"" + this.getName() + "\": " + (this instanceof NBTString ? "\"" + this.getPayload() + "\"" : this.getPayload());
		else
			return this instanceof NBTString ? "\"" + this.getPayload() + "\"" : this.getPayload().toString();
	}

	/**
	 * Returns a <code>Byte[]</code> representing this tag's value as NBT.
	 *
	 * @return An NBT representation of this tag.
	 */
	public byte[] toNBT() {
		ByteBuffer bytes = ByteBuffer.allocate(this.getLength());
		this.writeTag(bytes);
		return bytes.array();
	}

	/**
	 * Returns a <code>String</code> object representing this tag's value.
	 *
	 * @return A String representation of this tag.
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
	 *
	 * @return An XML representation of this tag.
	 */
	public String toXML() {
		if (this.getName() != null)
			return "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\" payload=\"" + this.getPayload() + "\" />";
		else
			return "<" + this.getClass().getSimpleName() + " payload=\"" + this.getPayload() + "\" />";
	}

	private void writeTag(ByteBuffer bytes) {
		if (this.getName() != null) {
			bytes.put(this.getType());
			byte[] name = this.getName().getBytes(NBT.CHARSET);
			bytes.putShort((short) name.length);
			bytes.put(name);
		}
		this.writePayload(bytes);
	}

	protected abstract void writePayload(ByteBuffer bytes);
}
