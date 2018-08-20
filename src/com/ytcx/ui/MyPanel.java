package com.ytcx.ui;

import java.awt.*;
import javax.swing.*;

 
//自定义一个面板类
class MyPanel extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //绘制一张背景图片  2.jpg是图片的路径  自己设定为自己想要添加的图片
        Image image = new ImageIcon("background.jpg").getImage();
        g.drawImage(image, 0, 0, this);
    }
}
 