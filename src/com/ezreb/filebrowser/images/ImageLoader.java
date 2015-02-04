package com.ezreb.filebrowser.images;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static void loadImages() {
		ARROW_UP = loadImage("arrow.gif");
		SCRIPT = loadImage("script.png");
		FILE = loadImage("file.jpg");
		FOLDER = loadImage("folder.png");
		TEXT = loadImage("text.png");
	}
	public static Image loadImage(String name) {
		String path = "/com/ezreb/filebrowser/images/";
		InputStream inim = ImageLoader.class.getResourceAsStream(path+name);
		Image im = null;
		try {
			im = ImageIO.read(inim);
			return im;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			inim.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Image ARROW_UP;
	public static Image SCRIPT;
	public static Image FILE;
	public static Image FOLDER;
	public static Image TEXT;
}
