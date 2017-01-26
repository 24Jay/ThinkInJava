package io.nio;


/**
 * 注意import static
 */
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperations
{

	public static void main(String[] args)
	{
		deleteFile();
		copyFile();
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
	
	private static void deleteFile()
	{
//		Path path = Paths.get("/home/jay/tst.txt");
//		Path path = Paths.get("/home/jay/test.txt");
		Path path = Paths.get("/home/jay/test");
		try
		{
			Files.delete(path);
		}
		catch (IOException e)
		{
			// e.printStackTrace();
			System.out.println("Exception = " + e.getMessage());
			System.out.println("Exception = " + e);
		}
	}

}
