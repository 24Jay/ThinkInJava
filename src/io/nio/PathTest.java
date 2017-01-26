package io.nio;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class PathTest
{

	public static void main(String[] args)
	{
		// Path path = FileSystems.getDefault().getPath("home");
		/**
		 * The following method is shorthand for the upper method
		 */
//		Path path = Paths.get("..");
		Path path = Paths.get("home/jay");
		System.out.println("Path : " + path.toAbsolutePath() + ", files : " + path.getNameCount());

		System.out.format("toString: %s%n", path.toString());
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(0));
		System.out.format("getNameCount: %d%n", path.getNameCount());
		System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
		System.out.format("getParent: %s%n", path.getParent());
		System.out.format("getRoot: %s%n", path.getRoot());

		System.out.println("URL = "+path.toUri());
		
		
		betweenPaths();
	}
	
	public static void betweenPaths()
	{
		Path p1 = Paths.get("home/jay/Linux");
		Path p2 = Paths.get("jay/workspace/HelloWorld");
		Path p3 = Paths.get("JavaIO");
		
		Path p1_to_p2 = p1.resolve(p2);
		Path p1_to_p3 = p1.resolve(p3);
		Path p2_to_p3 = p2.resolve(p3);
		System.out.println("p1_to_p2 = "+p1_to_p2);
		System.out.println("p1_to_p3 = "+p1_to_p3);
		System.out.println("p2_to_p3 = "+p2_to_p3);
		
		System.out.println("COmpare Path = "+ p1.compareTo(p2));
		System.out.println("Path_1 = "+ p1.getNameCount()+", Path_2 = "+p2.getNameCount());
	}
	
}
