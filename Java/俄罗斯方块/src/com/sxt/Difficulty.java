package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Difficulty extends JFrame implements ActionListener,Runnable {
    File file1 = new File("图标.jpg");//创建图标图片的对象
    File file2 = new File("背景原始.jpg");//创建背景图片的对象
    public Difficulty(){
        this.setResizable(false);
        this.setTitle(" 选 择 难 度 ");
        this.setBounds(250, 100, 400, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口关闭操作，设置窗口在关闭时默认退出程序。
        this.setIconImage(new ImageIcon(file1.getAbsolutePath()).getImage());//设置窗口图标
        this.setLayout(null);//使用绝对定位方式来放置窗口中的组件

        JButton button1 = new JButton("简  单");
        button1.setContentAreaFilled(false);//设置按钮透明
        button1.setBorder(null);//取消边框
        button1.setFont(new Font("幼圆",Font.BOLD,20));//字体设置
        button1.setForeground(Color.GRAY);//字体颜色为棕色
        button1.setBounds(250, 270, 100, 30);//所处位置
        this.add(button1);//添加到窗口

        JButton button2 = new JButton("一  般");
        button2.setContentAreaFilled(false);//设置按钮透明
        button2.setBorder(null);//取消边框
        button2.setFont(new Font("幼圆",Font.BOLD,20));
        button2.setForeground(Color.GRAY);
        button2.setBounds(250, 300, 100, 30);
        this.add(button2);

        JButton button3 = new JButton("困  难");
        button3.setContentAreaFilled(false);//设置按钮透明
        button3.setBorder(null);//取消边框
        button3.setFont(new Font("幼圆",Font.BOLD,20));
        button3.setForeground(Color.GRAY);
        button3.setBounds(250, 330, 100, 30);
        this.add(button3);

        ImageIcon background = new ImageIcon(file2.getAbsolutePath());
        JLabel Bg = new JLabel(background);
        Bg.setBounds(0, 0, 400, 600);
        this.add(Bg);

        this.setVisible(true);//可视化打开

        button1.addActionListener(this);//添加监听事件
        button2.addActionListener(this);
        button3.addActionListener(this);
    }

    String bt;//bt储存事件的动作命令
    @Override
    public void actionPerformed(ActionEvent e) {
        bt = e.getActionCommand();//通过e.getActionCommand()方法获取到事件的动作命令，并将其赋值给bt变量
        new Thread(this).start();//创建一个新的线程，并将当前对象this作为参数传入。然后，通过调用该线程的start()方法启动线程的执行
        this.setVisible(false);//隐藏当前窗口
    }


    public void run() {

        Tertris tertris = new Tertris();
        Tertris.isrunning =true;//

        if(bt.equals("简  单")){
            Tertris.upbond = 350;
            Tertris.rate = 0.5;
        }
        else if(bt.equals("一  般")){
            Tertris.upbond = 250;
            Tertris.rate = 0.75;
        }
        else if(bt.equals("困  难")){
            Tertris.upbond = 150;
            Tertris.rate = 1;
        }

        tertris.game_begin();//选择后启动游戏
    }
}
