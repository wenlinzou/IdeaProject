package com.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class CaptureScreen {

	public boolean captureScreen(String fileName) throws Exception {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		File file = new File(fileName);
		String jpgName = file.getName();
		boolean canScreen = ImageIO.write(image, "png", new File("f:/txt/"+jpgName+".jpg"));
		return canScreen;
		
	}

}
