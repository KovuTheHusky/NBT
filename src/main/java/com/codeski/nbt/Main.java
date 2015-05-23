package com.codeski.nbt;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.codeski.nbt.tags.NBTCompound;

/**
 * Class allows for GUI and CLI interaction for converting files from NBT to NBT, JSON, or XML.
 */
public class Main
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Console console = System.console();
		if (console != null)
			if (args.length == 2) {
				// Read in a user specified file...
				File in = new File(args[0]);
				System.out.println("Reading " + in.getCanonicalPath() + "...");
				NBTCompound root = new NBTReader(in).read();
				// Write the file to NBT, JSON, or XML...
				File out = new File(args[1]);
				String extension = args[1].substring(args[1].lastIndexOf('.') + 1);
				System.out.println("Writing " + out.getCanonicalPath() + "...");
				NBTWriter nw = new NBTWriter(out);
				if (extension.equalsIgnoreCase("dat"))
					nw.writeNBT(root);
				else if (extension.equalsIgnoreCase("json"))
					nw.writeJSON(root);
				else if (extension.equalsIgnoreCase("xml"))
					nw.writeXML(root);
				else {
					System.out.println("File extension was not dat, json, or xml. Writing NBT by default.");
					nw.writeNBT(root);
				}
			} else
				System.out.println("Usage: java -jar " + new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName() + " <file-to-read> <file-to-write>");
		else if (!GraphicsEnvironment.isHeadless()) {
			JFileChooser fc = new JFileChooser();
			FileFilter nbt = new FileNameExtensionFilter("Named Binary Tag (.dat)", "dat");
			fc.addChoosableFileFilter(nbt);
			int ret = fc.showOpenDialog(null);
			if (ret == JFileChooser.APPROVE_OPTION) {
				File in = fc.getSelectedFile();
				NBTCompound root = new NBTReader(in).read();
				FileFilter json = new FileNameExtensionFilter("JavaScript Object Notation (.json)", "json");
				fc.addChoosableFileFilter(json);
				FileFilter xml = new FileNameExtensionFilter("Extensible Markup Language (.xml)", "xml");
				fc.addChoosableFileFilter(xml);
				fc.setFileFilter(fc.getAcceptAllFileFilter());
				ret = fc.showSaveDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					File out = fc.getSelectedFile();
					NBTWriter nw = new NBTWriter(out);
					if (fc.getFileFilter().equals(nbt))
						nw.writeNBT(root);
					else if (fc.getFileFilter().equals(json))
						nw.writeJSON(root);
					else if (fc.getFileFilter().equals(xml))
						nw.writeXML(root);
					else {
						String ext = out.getName().substring(out.getName().lastIndexOf('.') + 1, out.getName().length());
						if (ext.equalsIgnoreCase("dat"))
							nw.writeNBT(root);
						else if (ext.equalsIgnoreCase("json"))
							nw.writeJSON(root);
						else if (ext.equalsIgnoreCase("xml"))
							nw.writeXML(root);
						else
							nw.writeNBT(root); // Default case...
					}
				}
			}
		}
	}
}
