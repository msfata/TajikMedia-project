package com.projectdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileDelete {
	public static void delete() throws FileNotFoundException {
		File temp;
		temp = new File(ScreenShot.path);
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(temp, "rw");
			raf.close();
			temp.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
