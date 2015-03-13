package com.ezreb.filebrowser;

import java.io.File;
import com.ezreb.filebrowser.images.ImageLoader;

public class FileBrowser {

//	public static void main(String[] args) {
//		FileBrowser fb = new FileBrowser();
//		try {
//			File output = fb.run();
//			System.out.println("output: "+output.getAbsolutePath());
//			Desktop.getDesktop().open(output);
//		} catch(NullPointerException | IOException npe) {
//			
//		}
//	}
	/**
	 * Very simple to use. This method opens up a filebrowser window, and once a file is selected, the window closes and the file object is returned.
	 * @return the chosen File
	 */
	public File run() {
		ImageLoader.loadImages();
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
		File retVal = mw.getSelectedFile();
		return retVal;
	}

}
