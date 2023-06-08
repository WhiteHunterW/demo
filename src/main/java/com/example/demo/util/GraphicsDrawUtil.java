package com.example.demo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

public class GraphicsDrawUtil {

    private static final String FONT_FAMILY = "黑体";

    public static void graphicsDraw(String backGroundImgUrl, String imgUrl, String[] texts) {
        try {
            BufferedImage targetImg = ImageIO.read(new URL(backGroundImgUrl));
            int imgWidth = targetImg.getWidth();
            int imgHeight = targetImg.getHeight();
            BufferedImage bufferedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = bufferedImage.createGraphics();
            // 绘制背景图
            g.drawImage(targetImg, 0, 0, imgWidth, imgHeight, null);
            g.dispose();

            // 添加楼盘图片
            g = (Graphics2D) bufferedImage.getGraphics();
            BufferedImage image = ImageIO.read(new URL(imgUrl));
            // g.drawImage(image, 100, 200, image.getWidth(), image.getHeight(), null);// 原始大小
            g.drawImage(image, 100, 200, 540, 960, null); // 拉升
            g.dispose();

            // 文本字体大小为60
            Font font = new Font(FONT_FAMILY, Font.PLAIN, 60);
            // 填充文本
            for (int i = 0; i < texts.length; i++) {
                g = (Graphics2D) bufferedImage.getGraphics(); //这行不能少
                g.setFont(font);
                g.setColor(Color.WHITE);
                String text = texts[i];
                int[] textSize = getContentSize(font, text);
                int leftMargin = 200 + (imgWidth - textSize[0]) / 2;
                int topMargin = 500 + i * 100 + textSize[1];

                g.drawString(text, leftMargin, topMargin);
                g.dispose();
            }

            // 水印文字
            g = (Graphics2D) bufferedImage.getGraphics();
            g.setFont(font);
            // g.setColor(Color.WHITE);
            // g.setColor(new Color(255,255,255));
            g.setColor(hex2Color("#ffffff"));
            g.rotate(Math.toRadians(45), 500, 500); // 倾斜度, 以（500,500为中心旋转45度）
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
            g.drawString("水印示例-50%", 500, 500);
            g.dispose();

            // 水印图片
            BufferedImage waterImage = ImageIO.read(new URL("https://img.ixintu.com/upload/jpg/20210525/ba30aff29828236ff032dfb6aa82d884_71276_800_856.jpg"));
            g = (Graphics2D) bufferedImage.getGraphics();
            g.rotate(1, 1000, 1000); // 倾斜度, 以（1000,1000）为中心旋转）
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.2f));
            g.drawImage(waterImage, 1000, 1000,waterImage.getWidth(), waterImage.getHeight(), null);
            g.dispose();

            // 保存文件
            FileOutputStream outImgStream = new FileOutputStream("/Users/wenzeng/Desktop/PPT/" + UUID.randomUUID() + ".jpg");
            ImageIO.write(bufferedImage, "jpg", outImgStream);

            // TODO 上传OBS
            // TODO 删除本地文件
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    /**
     * 获取文本的长度，字体大小不同，长度也不同
     *
     * @param font
     * @param content
     * @return {宽度，高度}
     */
    public static int[] getContentSize(Font font, String content) {
        int[] contentSize = new int[2];
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        Rectangle rec = font.getStringBounds(content, frc).getBounds();
        contentSize[0] = (int) rec.getWidth();
        contentSize[1] = (int) rec.getHeight();
        return contentSize;
    }

    /**
     * 16进制颜色字符串转换成rgb
     * @param hexStr
     * @return rgb
     */
    public static Color hex2Color(String hexStr){
        if(hexStr != null && !"".equals(hexStr) && hexStr.length() == 7){
            int[] rgb = new int[3];
            rgb[0] = Integer.valueOf(hexStr.substring( 1, 3 ), 16);
            rgb[1] = Integer.valueOf(hexStr.substring( 3, 5 ), 16);
            rgb[2] = Integer.valueOf(hexStr.substring( 5, 7 ), 16);
            return new Color(rgb[0],rgb[1],rgb[2]);
        }
        return Color.WHITE;
    }

    public static void main(String[] args) throws IOException {
        //背景图片
        String backGroundImgUrl = "https://picnew13.photophoto.cn/20181214/dahongxiqingzhongguofengzhunianbeijingsucai-31180992_1.jpg";
        //楼盘照片
        String imgUrl = "https://img7-build.jiwu.com/album/auto/2020/07/30/034215863288.jpg";
        //文件输出路径
        String outPath = "/Users/wenzeng/Desktop/PPT/temp.jpg";
        //添加文本
        String[] texts = new String[]{"我是一只小小小小鸟", "想要飞却怎么也飞不高", "哈哈哈哈"};
        //输出图片
        graphicsDraw(backGroundImgUrl, imgUrl, texts);

    }
}