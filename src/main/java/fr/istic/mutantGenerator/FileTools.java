package fr.istic.mutantGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public final class FileTools {

	public FileTools() {
	}

	public static void copy(String src, String dst) {
		File source = new File(src);
		File destination = new File(dst);
		FileInputStream sourceFile = null;
		FileOutputStream destinationFile = null;
		try {
			destination.createNewFile();
			sourceFile = new FileInputStream(source);
			destinationFile = new java.io.FileOutputStream(destination);
			byte buffer[] = new byte[512 * 1024];
			int nbRead;
			while ((nbRead = sourceFile.read(buffer)) != -1) {
				destinationFile.write(buffer, 0, nbRead);
			}
			sourceFile.close();
			destinationFile.close();
		} catch (Exception e) {
		}
	}

	public static void copyFolderToFolder(String currentFolder, String relatedPath, String sourceFolder, String destinationFolder) {
		File current = new File(currentFolder);
		if (current.isDirectory()) {
			File[] list = current.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					File tf = new File(sourceFolder + relatedPath + "/" + list[i].getName());
					File pf = new File(destinationFolder + relatedPath + "/" + list[i].getName());
					if (tf.isDirectory() && !pf.exists()) {
						pf.mkdir();
						copyFolderToFolder(tf.getAbsolutePath(), relatedPath
								+ "/" + tf.getName(), sourceFolder,
								destinationFolder);
					} else if (tf.isDirectory() && pf.exists()) {
						copyFolderToFolder(tf.getAbsolutePath(), relatedPath
								+ tf.getName(), sourceFolder,
								destinationFolder);
					} else if (tf.isFile()) {
						copy(sourceFolder + relatedPath + "/"
								+ list[i].getName(), destinationFolder
								+ relatedPath + "/" + list[i].getName());
					}
				}
			}
		}
	}
	
	public static void deleteDirectoryContent(String path) {
		File file = new File(path);
		for(File child : file.listFiles()) {
			deleteDirectory(child);
		}
	}
	
	public static void deleteDirectory(File path) {
		if(path.isDirectory()) {
			for(File child : path.listFiles()) {
				deleteDirectory(child);
			}
			path.delete();
		} else {
			path.delete();
		}
	}
}