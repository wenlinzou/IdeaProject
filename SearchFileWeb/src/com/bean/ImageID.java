package com.bean;

import java.awt.Graphics;

public class ImageID {
    // 验证码图片的宽度。
    private int width = 60;

    // 验证码图片的高度。
    private int height = 20;

    // 验证码字符个数
    private int codeCount = 4;

    private Graphics g;

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(int codeCount) {
        this.codeCount = codeCount;
    }

}
