package com.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

import com.bean.ImageID;

public class ImageIDUtils {
	// 验证码图片的宽度。
	private int width = 60;

	// 验证码图片的高度。
	private int height = 20;

	// 验证码字符个数
	private int codeCount = 4;

	private int x = 0;

	// 字体高度
	private int fontHeight;

	private int codeY;

	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	protected void init(ImageID imageId) {
		width = imageId.getWidth();
		codeCount = imageId.getCodeCount();
		height =imageId.getHeight();
		x = width / (codeCount + 1);
		fontHeight = height;
		codeY = height;
	}
	public String getImageWords(ImageID imageId) {
		init(imageId);
		// 定义图像buffer
		Graphics2D g = (Graphics2D) imageId.getG();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		g.setColor(getRandColor(220, 250));
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("宋体", Font.BOLD, fontHeight-5);
		// 设置字体。
		g.setFont(font);
		// 画边框。
		// g.setColor(Color.pink);
		// g.drawRect(0, 0, width - 1, height - 1);
		
		
		// 随机产生450个干扰点，使图象中的认证码不易被其它程序探测到。
		g.setColor(getRandColor(120,200));
		for(int i=0;i<550;i++){
		   int x=random.nextInt(width);
		   int y=random.nextInt(height);
		   g.drawOval(x,y,0,0);  
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String strRand = String.valueOf(codeSequence[random.nextInt(62)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			g.setColor(getRandColor(20, 130));
			// 用随机产生的颜色将验证码绘制到图像中。
			g.drawString(strRand, (i + 1) * x - 7, codeY - 5);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		return randomCode.toString();
	}


	/**
	 * 产生随机颜色
	 */
	protected static Color getRandColor(int num1, int num2) {
		Random random = new Random();
		if (num1 > 255)
			num1 = 255;
		if (num2 > 255)
			num2 = 255;
		int r = num1 + random.nextInt(num2 - num1);
		int g = num1 + random.nextInt(num2 - num1);
		int b = num1 + random.nextInt(num2 - num1);
		return new Color(r, g, b);
	}
	/*
	// 随机产生150条干扰线，使图象中的认证码不易被其它程序探测到。
	g.setColor(getRandColor(120, 200));
	for (int i = 0; i < 150; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(10);
		int yl = random.nextInt(10);
		g.drawLine(x, y,x+xl,y+yl);
	}
	*/
}
