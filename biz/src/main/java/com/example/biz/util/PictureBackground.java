package com.example.biz.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author wenzeng
 * @date 2025/2/10
 */
public class PictureBackground {

    public static void main(String[] args) {
        try {
            // 读取证件照
            File file = new File("/Users/wenzeng/Desktop/WechatIMG6431.jpg");
            BufferedImage image = ImageIO.read(file);

            // 获取图像的宽度和高度
            int width = image.getWidth();
            int height = image.getHeight();

            // 定义白色和蓝色
            Color white = new Color(255, 255, 255);
            Color blue = new Color(60, 140, 220);

            // 遍历每个像素点
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color pixelColor = new Color(image.getRGB(x, y));

                    // 如果像素是接近白色的，替换成蓝色
                    if (isWhite(pixelColor)) {
                        image.setRGB(x, y, blue.getRGB());
                    }
                }
            }

            // 保存修改后的图片
            File outputFile = new File("/Users/wenzeng/Desktop/output.jpg");
            ImageIO.write(image, "jpg", outputFile);

            System.out.println("背景已成功替换为蓝色！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 判断是否为接近白色的颜色
    public static boolean isWhite(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        // 白色的RGB值大致为(255, 255, 255)，如果差距较小，就认为是白色
        return (Math.abs(red - 255) < 30) && (Math.abs(green - 255) < 30) && (Math.abs(blue - 255) < 30);
    }
}
