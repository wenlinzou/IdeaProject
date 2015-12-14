package com.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class PhotoUtils {
	public static void saveToFile(String destUrl) {
		System.out.println("PhotoUtils-desturl:"+destUrl);
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        try {
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            String filename = FileUtils.getFilePathFileName(destUrl, "/", ".");

            String suffix = FileUtils.getFileSuffix(destUrl, ".");
            
//            String newFilePath = System.getProperty("user.dir") + "/delete/" + filename + "_Copy_ucandelete." + suffix;
//            String newFilePath = tomcat + "/webapps/deletephoto/" + filename + "_Copy_ucandelete." + suffix;
            String newFilePath = destUrl+"/" + filename + "_Copy_ucandelete." + suffix;
            System.out.println("newFilePath:"+newFilePath);
            
            fos = new FileOutputStream(newFilePath);
            System.out.println("photo:" + newFilePath);
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.flush();
        } catch (IOException e) {
        } catch (ClassCastException e) {
        } finally {
            try {
                fos.close();
                bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
            } catch (NullPointerException e) {
            }
        }
    }
	
	private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

    public static void readUrlPhoto(String urlPath) throws IOException {
        URL url = new URL(urlPath);
        BufferedImage bufferedImage = ImageIO.read(url);
        BufferedImage grayImage =
                new BufferedImage(bufferedImage.getWidth(),
                        bufferedImage.getHeight(),
                        bufferedImage.getType());

        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                final int color = bufferedImage.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                ;
//                System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                grayImage.setRGB(i, j, newPixel);
            }
        }
        String copyname = FileUtils.getFilePathFileName(urlPath, "/", ".");
        String filepath = FileUtils.getFilepath(urlPath, "/");
        String suffix = FileUtils.getFileSuffix(urlPath, ".");

        File newFile = new File(filepath + copyname + "Copy" + "." + suffix);
//        File newFile = new File(System.getProperty("user.dir") + "/ok.jpg");
//        ImageIO.write(grayImage, "jpg", newFile);
        System.out.println(newFile.length());
//        ImageIO.write(grayImage, suffix, newFile);
    }

    public static void newGrayImage(String filePath) throws IOException {
//        BufferedImage bufferedImage = ImageIO.read(new File(System.getProperty("user.dir" + "/test.jpg")));
        BufferedImage bufferedImage = ImageIO.read(new File(filePath));
        BufferedImage grayImage =
                new BufferedImage(bufferedImage.getWidth(),
                        bufferedImage.getHeight(),
                        bufferedImage.getType());

        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                final int color = bufferedImage.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                ;
//                System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                grayImage.setRGB(i, j, newPixel);
            }
        }
        String copyname = FileUtils.getFilePathFileName(filePath, "/", ".");
        String filepath = FileUtils.getFilepath(filePath, "/");
        String suffix = FileUtils.getFileSuffix(filePath, ".");
        String photoFilePath = filepath + copyname + "Copy" + "." + suffix;
        File newFile = new File(photoFilePath);
        System.out.println("newGrayImage:"+newFile.getAbsolutePath() + "\nphotoFilePath:" + photoFilePath);
//        File newFile = new File(System.getProperty("user.dir") + "/ok.jpg");
//        ImageIO.write(grayImage, "jpg", newFile);
        ImageIO.write(grayImage, suffix, newFile);
    }
}
