package io.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipCompress implements Serializable
{
	private static String[] files =
		{ "./src/io/compress/GZipCompress.java", "./src/io/compress/ZipCompress.java" };

	public static void main(String[] ar) throws IOException
	{
		FileOutputStream out = new FileOutputStream("test.zip");
		CheckedOutputStream checked = new CheckedOutputStream(out, new Adler32());
		System.out.println("Before Writing : CheckSum="+checked.getChecksum().getValue());
		ZipOutputStream zout = new ZipOutputStream(checked);
		zout.setComment("A test of Java Zipping!");
		BufferedOutputStream buf = new BufferedOutputStream(zout);

		for (String f : files)
		{
			BufferedReader reader = new BufferedReader(new FileReader(f));
			zout.putNextEntry(new ZipEntry(f));
			int c;
			while ((c = reader.read()) != -1)
			{
				buf.write(c);
			}
			reader.close();
			buf.flush();
		}
		buf.close();
		System.out.println("After Writing : CheckSum="+checked.getChecksum().getValue());
	
		System.out.println("******************************");
		
		/**
		 * Read zipfile
		 */
		FileInputStream in = new FileInputStream("test.zip");
		CheckedInputStream csum = new CheckedInputStream(in, new Adler32());
		System.out.println("Before Reading : CheckSum="+csum.getChecksum().getValue());
		ZipInputStream zin = new ZipInputStream(csum);
		BufferedInputStream bis = new BufferedInputStream(zin);
		ZipEntry ent;
		while((ent=zin.getNextEntry())!=null)
		{
			int x;
			while((x=bis.read())!=-1)
				System.out.print((char)x);
		}
		System.out.println("\nAfter Reading : CheckSum="+csum.getChecksum().getValue());
		bis.close();
		
		/**
		 * another way to read zipfile
		 */
		ZipFile zf = new ZipFile("test.zip");
		Enumeration<ZipEntry> e = (Enumeration<ZipEntry>) zf.entries();
		
		while(e.hasMoreElements())
		{
			ZipEntry en = (ZipEntry) e.nextElement();
			System.out.println("FIle = "+en);
		}
	}

}
