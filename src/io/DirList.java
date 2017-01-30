package io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.FilterInputStream;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList
{
	public static void main(final String[] ar)
	{
		File file = new File(".");
		String[] list;
		if (ar.length == 0)
			list = file.list();
		else
		{
			list = file.list(new FilenameFilter()
			{
				Pattern pattern = Pattern.compile(ar[0]);
				@Override
				public boolean accept(File dir, String name)
				{
					return pattern.matcher(name).matches();
				}
			});
		}
		
		Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
		
		for(String item:list)
			System.out.println(item);
	}

}
