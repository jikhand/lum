package com.set.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageUpload {

	public static void decoder(String base64Image, String pathFile) {
		pathFile = pathFile.replace(" ", "");
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			imageOutFile.write(imageByteArray);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
	}

}