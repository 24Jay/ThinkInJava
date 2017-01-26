package io.nio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TryWithResource
{

	public static void main(String[] args)
	{
		Charset charset = Charset.forName("UTF-8");// ("US-ASCII");
		String s = " The try-with-resources statement has the advantage that the "
				+ "compiler automatically generates the code to close the resource(s)"
				+ " when no longer required.爱慕你的美丽，假意或真心";
		Path path = Paths.get("/home/jay/workspace/JavaIO/love.txt");

		/**
		 * The try-with-resources statement has the advantage that the compiler
		 * automatically generates the code to close the resource(s) when no
		 * longer required.
		 */
		try (java.io.BufferedWriter writer = Files.newBufferedWriter(path, charset))
		{
			writer.write(s, 0, s.length());
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x.getMessage());
		}
	}
}
