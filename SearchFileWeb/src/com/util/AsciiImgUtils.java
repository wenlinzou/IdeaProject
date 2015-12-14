package com.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class AsciiImgUtils {
    public static String _BitmapConvert(String imgpath) {
        StringBuffer _sb = new StringBuffer();
        File imgfile = new File(imgpath);
        //原始
        char[] charset = { 'M', '8', '0', 'V', '1', 'i', ':', '*', '|', '.', ' ' };
//	    char[] charset = { 'M', '8', '0', 'V', '1', 'i', ':', '*', '|', '+', ' ' };
//	    char[] charset = { '.', ',', '*', '+', '=', '&', '$', '@', '#', ' ' };
        try {
            BufferedImage buff = ImageIO.read(imgfile);

            int bitmapH = buff.getHeight();
            int bitmapW = buff.getWidth();

            for (int y = 0; y < bitmapH; y++) {
                for (int x = 0; x < bitmapW; x++) {
                    int rgb = buff.getRGB(x, y);
                    Color c = new Color(rgb);
                    int cc = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                    _sb.append(charset[(int) ((cc * 10 - 1) / 255)]);
                }
                _sb.append("\r\n<br/>");
            }
        } catch (Exception e) {
        }
        return _sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(_BitmapConvert("e:/test.jpg"));
    }
}
