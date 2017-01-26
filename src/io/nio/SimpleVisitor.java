package io.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SimpleVisitor<T> extends SimpleFileVisitor<T>
{
	@Override
	public FileVisitResult visitFile(T file, BasicFileAttributes attrs) throws IOException
	{
		if (attrs.isSymbolicLink())
		{
			System.out.format("Symbolic link: %s ", file);
		}
		else if (attrs.isRegularFile())
		{
			System.out.format("Regular file: %s ", file);
		}
		else
		{
			System.out.format("Other: %s ", file);
		}
		System.out.println("(" + attrs.size() + "bytes)");
		return FileVisitResult.CONTINUE;
	}
}
