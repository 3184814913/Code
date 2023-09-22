package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

//设置开始界面的UI
public class Login extends JFrame implements ActionListener{
    File file1 = new File("图标.jpg");
    File file2 = new File("背景原始.jpg");
    public Login() {
        //设置窗口
        this.setResizable(false);//不可改变大小
        this.setTitle(" 俄 罗 斯 方 块 ");//标题
        this.setBounds(200, 100, 400, 650);//大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置上边框窗口关闭操作，设置窗口在关闭时默认退出程序。
        this.setIconImage(new ImageIcon(file1.getAbsolutePath()).getImage());//上边框窗口小图标
        this.setLayout(null);//使用绝对定位方式来放置上边框窗口中的组件

        //设置按钮格式
        JButton button = new JButton("开 始 游 戏");
        button.setContentAreaFilled(false);//设置按钮透明
        button.setBorder(null);//取消边框
        button.setFont(new Font("幼圆",Font.BOLD,20));//设置字体、大小、加粗
        button.setForeground(Color.GRAY);//设置字体颜色
        Color color = new Color(236, 241, 242);//设置按钮的颜色
        button.setBounds(240, 260, 140, 40);//设置按钮的大小
        this.add(button);//添加到窗口中

        JButton button1 = new JButton("继 续 游 戏");
        button1.setContentAreaFilled(false);//设置按钮透明
        button1.setBorder(null);//取消边框
        button1.setFont(new Font("幼圆",Font.BOLD,20));
        button1.setForeground(Color.GRAY);
        button1.setBounds(240, 300, 140, 40);
        this.add(button1);

        //设置背景图片
        ImageIcon background = new ImageIcon(file2.getAbsolutePath());//将file2所代表的文件路径转换为背景图片
        JLabel Bg = new JLabel(background);//将background图标设置为bg标签的内容
        Bg.setBounds(0, 0, 400, 600);//设置大小
        this.add(Bg);//添加到界面

        this.setVisible(true);//可视化

        new rePlay();//启动背景音乐

        button.addActionListener(this);//添加事件监听器
        button1.addActionListener(this);
    }


    String bt1;
    @Override
    public void actionPerformed(ActionEvent e) {
        bt1 = e.getActionCommand();//通过e.getActionCommand()方法获取到事件的动作命令，并将其赋值给bt变量
        Thread t1 = new Thread(this::run1);//创建一个新的线程，并将当前对象this作为参数传入。将当前类中的run1方法作为线程的任务
        t1.start();//通过调用该线程的start()方法启动线程的执行
        this.setVisible(false);

    }
    //游戏模式选择
    public void run1() {
        if(Objects.equals(bt1, "开 始 游 戏")){
            Difficulty difficulty = new Difficulty();//将Difficulty实例化，即跳转到选择难度的界面
        }
        else if(Objects.equals(bt1, "继 续 游 戏")){
            try {
                Tertris tertris = new Tertris();
                Arr.load1();
                tertris.game_begin();//启动游戏
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
        }
    }
}


