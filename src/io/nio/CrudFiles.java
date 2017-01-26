package io.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;


/**
 * 注意import static的使用
 */
import static java.nio.file.StandardCopyOption.*;

public class CrudFiles
{
	public static void main(String[] args)
	{
		/**
		 * input
		 */
		readAllBytes();
		bufferedReader();
		streamInput();

		/**
		 * output
		 */
		streamOutput();
		byteChannelRead();
		byteChannelWrite();

		createFile();
		createTempFile();
	}

	private static void createTempFile()
	{
		Path path = Paths.get("/home/jay/");
		try
		{
			Path tempFile = Files.createTempFile(path, "myapp", null);
			// A file will be created at the default location.
			// Path tempFile = Files.createTempFile(null, ".myapp");
			System.out.format("The temporary file" + " has been created: %s%n", tempFile);
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x);
		}
	}

	private static void createFile()
	{
		Path file = Paths.get("./createFile");
		try
		{
			// Create the empty file with default permissions, etc.
			Files.createFile(file);
		}
		catch (FileAlreadyExistsException x)
		{
			System.err.format("file named %s" + " already exists%n", file);
		}
		catch (IOException x)
		{
			// Some other sort of failure, such as permissions.
			System.err.format("createFile error: %s%n", x);
		}
	}

	private static void byteChannelWrite()
	{

		// Create the set of options for appending to the file.
		Set<OpenOption> options = new HashSet<OpenOption>();
		options.add(StandardOpenOption.APPEND);
		options.add(StandardOpenOption.CREATE);

		// Create the custom permissions attribute.
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-r-----");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

		// Convert the string to a ByteBuffer.
		String s = "Hello World! This is a channel writer.";
		byte data[] = s.getBytes();
		ByteBuffer bb = ByteBuffer.wrap(data);

		Path file = Paths.get("./permissions.log");

		try (SeekableByteChannel sbc = Files.newByteChannel(file, options, attr))
		{
			sbc.write(bb);
			System.out.println("*********** = " + bb);
		}
		catch (IOException x)
		{
			System.out.println("Exception thrown: " + x);
		}
	}

	private static void byteChannelRead()
	{
		Path p = Paths.get("./logfile.txt");
		// Defaults to READ
		try (SeekableByteChannel sbc = Files.newByteChannel(p))
		{
			ByteBuffer buf = ByteBuffer.allocate(50);

			// Read the bytes with the proper encoding for this platform. If
			// you skip this step, you might see something that looks like
			// Chinese characters when you expect Latin-style characters.
			String encoding = System.getProperty("file.encoding");
			while (sbc.read(buf) > 0)
			{
				buf.rewind();
				System.out.println(Charset.forName(encoding).decode(buf));
				buf.flip();
			}
		}
		catch (IOException x)
		{
			System.out.println("caught exception: " + x);
		}
	}

	private static void streamOutput()
	{
		// Convert the string to a
		// byte array.
		String s = "Hello World! ";
		byte data[] = s.getBytes();
		Path p = Paths.get("./logfile.txt");

		try (OutputStream out = new BufferedOutputStream(
				Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND)))
		{
			out.write(data, 0, data.length);
		}
		catch (IOException x)
		{
			System.err.println(x);
		}
	}

	private static void streamInput()
	{
		Charset charset = Charset.forName("UTF-8");
		Path file = Paths.get("/home/jay/test/linux_command");
		try (InputStream in = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
		{
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				System.out.println(line);
			}
		}
		catch (IOException x)
		{
			System.err.println(x);
		}
	}

	private static void bufferedReader()
	{
		Charset charset = Charset.forName("UTF-8");
		Path file = Paths.get("/home/jay/test/linux_command");
		try (java.io.BufferedReader reader = Files.newBufferedReader(file, charset))
		{
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				System.out.println(line);
			}
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x);
		}
	}

	private static void readAllBytes()
	{
		Path path = Paths.get("/home/jay/test/linux_command");
		try
		{
			byte[] fileBytes = Files.readAllBytes(path);
			for (int i = 0; i < fileBytes.length; i++)
			{
				System.out.println((char) fileBytes[i]);
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void copyFile()
	{
		Path initialPath = Paths.get("/home/jay/Linux/linux_command");
		Path path = Paths.get("/home/jay/test/linux_command");
		try
		{
			System.out.println("Move file : " + Files.copy(initialPath, path, REPLACE_EXISTING));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
