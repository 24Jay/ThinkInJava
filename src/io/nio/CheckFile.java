package io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

/**
 * !Files.exists(path) is not equivalent to Files.notExists(path). When you are
 * testing a file's existence, three results are possible:
 * 
 * 1, The file is verified to exist. 2, The file is verified to not exist. 3,The
 * file's status is unknown.
 * 
 * This result can occur when the program does not have access to the file.
 * 
 * If both exists and notExists return false, the existence of the file cannot
 * be verified.
 * 
 * @author jay
 *
 */

public class CheckFile
{
	public static void main(String[] args)
	{
		checkAccessibility();
	}

	private static void checkAccessibility()
	{
		Path filePath = Paths.get("/home/jay/Linux/git_command");
		System.out.println("Readable = " + Files.isReadable(filePath));
		System.out.println("Writable = " + Files.isWritable(filePath));
		System.out.println("Executadable = " + Files.isExecutable(filePath));
		try
		{
			System.out.println("Owner = " + Files.getOwner(filePath));
			/**
			 * BasicFileAttributes
			 */
			BasicFileAttributes attribute = Files.readAttributes(filePath,
					BasicFileAttributes.class);
			System.out.println("Size = " + attribute.size());
			System.out.println("CreationTime = " + attribute.creationTime());
			System.out.println("LastMofiedTime = " + attribute.lastModifiedTime());
			System.out.println("FileKey = " + attribute.fileKey());
			System.out.println("IsRegularFile = " + attribute.isRegularFile());

			/**
			 * POSIXFILEAttributes
			 */
			PosixFileAttributes posix = Files.readAttributes(filePath, PosixFileAttributes.class);
			System.out.println("Owner = " + posix.owner());
			System.out.println("Group = " + posix.group());
			System.out.println("Permissions = " + posix.permissions());
			System.out.format("%s %s %s%n", posix.owner().getName(), posix.group().getName(),
					PosixFilePermissions.toString(posix.permissions()));

			/**
			 * Set permissions
			 */
			Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-------");
			FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions
					.asFileAttribute(perms);
			Files.setPosixFilePermissions(filePath, perms);
			System.out.println("Permissionsssssssssssss = " + PosixFilePermissions.toString(posix.permissions()));
			
			
			/**
			 * set owner
			 */
			UserPrincipal owner =filePath.getFileSystem().getUserPrincipalLookupService()
			        .lookupPrincipalByName("root");
			Files.setOwner(filePath, owner);
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			System.out.println("If the same file = " + Files.isSameFile(filePath, filePath));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}