package com.codeski.nbt.tags;

public abstract class NBT {
	protected String name;

	public NBT(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getName().equals(((NBT) obj).getName()) && this.getPayload().equals(((NBT) obj).getPayload());
	}

	public String getName() {
		return name;
	}

	public abstract Object getPayload();

	public void setName(String name) {
		this.name = name;
	}

	public String toJSON() {
		if (this.getName() != null)
			return "\"" + this.getName() + "\": " + (this instanceof NBTString ? "\"" + this.getPayload() + "\"" : this.getPayload());
		else
			return this instanceof NBTString ? "\"" + this.getPayload() + "\"" : this.getPayload().toString();
	}

	public String toXML() {
		if (this.getName() != null)
			return "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\" payload=\"" + this.getPayload() + "\" />";
		else
			return "<" + this.getClass().getSimpleName() + " payload=\"" + this.getPayload() + "\" />";
	}
}
