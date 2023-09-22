package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class pause extends JFrame implements ActionListener,Runnable {
    File file1 = new File("图标.jpg");
    File file2 = new File("背景原始.jpg");
    public pause(){
        this.setResizable(false);
        this.setTitle(" 暂 停 中 ");
        this.setBounds(300, 150, 400, 650);//设置窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口关闭操作，设置窗口在关闭时默认退出程序。
        this.setIconImage(new ImageIcon(file1.getAbsolutePath()).getImage());//设置图标
        this.setLayout(null);//使用绝对定位方式来放置窗口中的组件

        //暂停时UI的设置
        JButton button1 = new JButton("继 续 游 戏");
        button1.setContentAreaFilled(false);//设置按钮透明
        button1.setBorder(null);//取消边框
        button1.setFont(new Font("幼圆",Font.BOLD,20));
        button1.setForeground(Color.GRAY);
        button1.setBounds(250, 260, 140, 30);
        this.add(button1);

        JButton button2 = new JButton("保 存 游 戏");
        button2.setContentAreaFilled(false);//设置按钮透明
        button2.setBorder(null);//取消边框
        button2.setFont(new Font("幼圆",Font.BOLD,20));
        button2.setForeground(Color.GRAY);
        button2.setBounds(250, 300, 140, 30);
        this.add(button2);

        //添加背景图片
        ImageIcon background = new ImageIcon(file2.getAbsolutePath());//将file2所代表的文件路径转换为背景图标
        JLabel Bg = new JLabel(background);//将background图标设置为bg标签的内容
        Bg.setBounds(0, 0, 400, 600);//图片位置
        this.add(Bg);//将标签添加到窗口里

        this.setVisible(true);
        button1.addActionListener(this);//添加事件监听器，即鼠标点击
        button2.addActionListener(this);
    }

    String bt;//用bt储存事件的动作命令
    @Override
    public void actionPerformed(ActionEvent e) {

        bt = e.getActionCommand();//获取到事件的动作命令，并将其赋值给bt变量

        new Thread(this).start();//创建一个新的线程，并将当前对象this作为参数传入。然后，通过调用该线程的start()方法启动线程的执行
    }


    public void run() {

        Tertris.isrunning =true;//游戏在运行中

        if(bt.equals("继 续 游 戏")){
            Tertris.pause_times++;
            this.setVisible(false);//进行隐藏
            if (Tertris.pause_times == 2) {
                Tertris.game_pause = false;
                Tertris.pause_times = 0;//暂停次数回调到0
                Tertris.label1.setText("游戏状态: 正在进行中!");//改变游戏里提示
            }
        }
        else if(bt.equals("保 存 游 戏")){
            try {
                Arr.save1();//将现在的游戏数据写入文档进行保存
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
