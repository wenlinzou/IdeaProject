package com.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import com.bean.IDCodeStyle;

public class IDCodeUtils {
	private IDCodeStyle iword;
	
	public IDCodeUtils(){
		super();
		
	}
	public IDCodeUtils(IDCodeStyle iword){
		this.iword = iword;
	}
	//添加一个通用的设置验证码的一个function
	public String getWordSetCheckStyle(){
		//1设置背景颜色
		setWordBackgroundColor(iword);
		//2设置边框
		setWordBorder(iword);
		//3画干扰线
		drawWordLine(iword);
		//4设置随机数Graphics2D g,Color fontColor, Font wordFont
		String word4 = drawWordMandomNum(iword);
		
		return word4;
	}
	private void setWordBackgroundColor(IDCodeStyle iword) {
		iword.getG().setColor(iword.getBackColor());
		iword.getG().fillRect(0, 0, iword.getWidth(), iword.getHeight());
	}
	private void setWordBorder(IDCodeStyle iword) {
		iword.getG().setColor(iword.getBorderColor());
		iword.getG().drawRect(1, 1, iword.getWidth()-2, iword.getHeight()-2);
	}
	private void drawWordLine(IDCodeStyle iword) {
		iword.getG().setColor(iword.getLineColor());
		for (int i = 0; i < 4; i++) {
			int x1 = new Random().nextInt(iword.getWidth());
			int y1 = new Random().nextInt(iword.getHeight());
			
			int x2 = new Random().nextInt(iword.getWidth());
			int y2 = new Random().nextInt(iword.getHeight());
			
			iword.getG().drawLine(x1, y1, x2, y2);
		}
	}
	//------------------------
	public String drawWordMandomNum(IDCodeStyle iword) {
//		g.setColor(Color.BLACK);
//		g.setFont(new Font("黑体",Font.BOLD,23));
		Graphics2D g = (Graphics2D) iword.getG();
		iword.setG(g);
		g.setColor(iword.getFontColor());
		g.setFont(iword.getWordFont());
		
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz0123456789";
		int x  = 5;
		StringBuilder tempCode = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			int degree = new Random().nextInt()%30;
			String ch = base.charAt(new Random().nextInt(base.length()))+"";
			
			tempCode.append(ch);
			//设置旋转的幅度
			g.rotate(degree*Math.PI/180, x, 22);
			g.drawString(ch,x,22);
			g.rotate(-degree*Math.PI/180,x,22);
			x+=30;
		}
		return tempCode.toString();		
	}
	
	
}
