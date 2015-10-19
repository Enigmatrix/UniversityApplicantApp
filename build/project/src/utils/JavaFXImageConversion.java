package utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

//from github
public class JavaFXImageConversion {
	public static Image getJavaFXImage(byte[] rawPixels) {
		ByteArrayInputStream in = new ByteArrayInputStream(rawPixels);
		BufferedImage read = null;
		try {
			read = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SwingFXUtils.toFXImage(read, null);
	}

	public static Byte[] fileToBytes(File f) {
		if(f== null) return null;
		try {

			BufferedImage originalImage = ImageIO.read(f);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "png", baos);
			baos.flush();
			return toByteArray(baos.toByteArray());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static byte[] getBytesFromJavaFXImage(Image img) {
		BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, "png", s);
			s.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s.toByteArray();
	}

	public static Byte[] toByteArray(byte[] bytes) {
		Byte[] data = new Byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			data[i] = Byte.valueOf(bytes[i]);
		}
		return data;
	}

	public static byte[] toPrimitiveByteArray(Byte[] bytes) {
		byte[] data = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			data[i] = bytes[i];
		}
		return data;
	}

}