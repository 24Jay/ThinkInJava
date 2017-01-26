package io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * 根据自己的理解写的遍历文件的方法
 * 
 * @author jay
 *
 */
public class ListDirectory
{
	private static int count = 0;

	private static double totalSize = 0;

	private static void listAll(int depth, Path path)
	{
		try
		{
			Stream<Path> stream = Files.list(path);
			Iterator<Path> it = stream.iterator();
			totalSize += Files.size(path);
//			System.out.println(path);
			while (it.hasNext())
			{
				Path file = it.next();
				if (Files.isDirectory(file))
				{
					listAll(depth + 1, file);
				}
				else if (!Files.isDirectory(file))
				{
					for (int i = 0; i < path.toString().length(); i++)
					{
						//System.out.print(" ");
					}
					count++;
					totalSize += Files.size(file);
					System.out.println(file.getFileName() + "( Size=" + Files.size(file) + " )");
				}

			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		listAll(1, Paths.get("/home/jay/Linux"));
		System.out.println("Total Count of Files = " + count);
		System.out.println("Total Size of Files = " + totalSize);
	}
}
