package io.nio;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateReadDirectory
{
	public static void main(String[] args)
	{
		createDirectory();
		listDirectory();
		for (FileStore store : FileSystems.getDefault().getFileStores())
		{
			System.out.println(store.name());
		}
	}

	private static void createDirectory()
	{
		Iterable<Path> ite = FileSystems.getDefault().getRootDirectories();
		for (Path p : ite)
		{
			System.err.println("p = " + p);
		}
		Path path = Paths.get("/home/jay/myfile/file");
		try
		{
			Files.createDirectories(path);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private static void listDirectory()
	{
		Path dir = Paths.get("/home/jay");

		Filter<Path> filter = new Filter<Path>()
		{
			@Override
			public boolean accept(Path entry) throws IOException
			{
				return Files.isDirectory(entry);
			}
		};

		// try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir,
		// ".*"))
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter))
		{
			for (Path file : stream)
			{
				System.out.println(file.getFileName());
			}
		}
		catch (IOException | DirectoryIteratorException x)
		{
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(x);
		}

	}

}
