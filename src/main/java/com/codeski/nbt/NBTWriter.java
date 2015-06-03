package com.codeski.nbt;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import com.codeski.nbt.tags.NBT;

/**
 * Class for writing NBT structures as NBT, JSON, or XML.
 */
public class NBTWriter {
	private final File file;

	public NBTWriter(File file) throws FileNotFoundException {
		this.file = file;
	}

	/**
	 * Writes the tag specified and its children as JSON text.
	 *
	 * @param root
	 *            The root tag to write to JSON.
	 */
	public void writeJSON(NBT<?> root) {
		try (PrintWriter out = new PrintWriter(file)) {
			out.println("{ " + root.toJSON() + " }");
		} catch (IOException e) {
			System.err.println("There was an error writing the data to JSON text.");
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Writes the tag specified and its children as NBT binary data.
	 *
	 * @param root
	 *            The root tag to write to NBT.
	 */
	public void writeNBT(NBT<?> root) {
		this.writeNBT(root, true);
	}

	public void writeNBT(NBT<?> root, boolean compressed) {
		if (compressed)
			try (DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)))) {
				out.write(root.toNBT());
			} catch (IOException e) {
				System.err.println("There was an error writing the data to NBT binary data.");
				e.printStackTrace(System.err);
			}
		else
			try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
				out.write(root.toNBT());
			} catch (IOException e) {
				System.err.println("There was an error writing the data to NBT binary data.");
				e.printStackTrace(System.err);
			}
	}

	/**
	 * Writes the tag specified and its children as XML text.
	 *
	 * @param root
	 *            The root tag to write to XML.
	 */
	public void writeXML(NBT<?> root) {
		try (PrintWriter out = new PrintWriter(file)) {
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + root.toXML());
		} catch (IOException e) {
			System.err.println("There was an error writing the data to XML text.");
			e.printStackTrace(System.err);
		}
	}
}
