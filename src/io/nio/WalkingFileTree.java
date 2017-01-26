package io.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.stream.Stream;

public class WalkingFileTree
{

	public static void main(String[] args)
	{
		FileVisitor<Path> visitor = new FileVisitor<Path>()
		{

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
					throws IOException
			{
				// if (attrs.isSymbolicLink())
				// {
				// System.out.format("Symbolic link: %s ", dir);
				// }
				// else if (attrs.isRegularFile())
				// {
				// System.out.format("Regular file: %s ", dir);
				// }
				// else
				// {
				// System.out.format("Other: %s ", dir);
				// }
				// System.out.println("(" + attrs.size() + "bytes)");
				System.out.print(dir.getFileName());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
					throws IOException
			{
				if (attrs.isSymbolicLink())
				{
					// System.out.format("Symbolic link: %s ", file);
					System.out.print(file.getFileName());
				}
				else if (attrs.isRegularFile())
				{
					// System.out.format("Regular file: %s ", file);
					System.out.print(file.getFileName());
				}
				else
				{
					// System.out.format("Other: %s ", file);
					System.out.print(file.getFileName());
				}
				// System.out.println("(" + attrs.size() + "bytes)");
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
			{
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
			{
				return FileVisitResult.CONTINUE;
			}
		};

		/***
		 * 
		 */
		Path path = Paths.get("/home/jay/Linux/");
		try
		{
			/*
			 * SimpleVisitor<Path> simple = new SimpleVisitor<Path>();
			 * Files.walkFileTree(path, simple);
			 */
			Stream<Path> stream = Files.list(path);
//			System.out.println("$$$$$$$$$$$$$$$$$$$$ = " + stream.iterator());
			Iterator<Path> it = stream.iterator();
			while (it.hasNext())
			{
				System.out.println(it.next().getFileName());
			}
			// Files.walkFileTree(path, visitor);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}