package com.ezreb.filebrowser;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.ezreb.filebrowser.images.ImageLoader;

public class FileBrowser {

	public static void main(String[] args) {
		FileBrowser fb = new FileBrowser();
		try {
			File output = fb.run();
			System.out.println("output: "+output.getAbsolutePath());
			Desktop.getDesktop().open(output);
		} catch(NullPointerException | IOException npe) {
			
		}
	}
	public File run() {
		ImageLoader.loadImages();
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
		File retVal = mw.getSelectedFile();
		return retVal;
	}

}
