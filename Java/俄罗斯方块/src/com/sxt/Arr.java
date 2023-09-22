package com.sxt;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.*;

public class Arr {
    //将游戏数据写入到save.tet中
    public static void save1() throws IOException {
        File file1 = new File("save.txt");//创建文档
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("save.txt"));

        writer.write(Tertris.isrunning + "\n");
        writer.write(Tertris.rect + "\n");
        writer.write(Tertris.time + "\n");
        writer.write(Tertris.x + "\n");
        writer.write(Tertris.y + "\n");
        writer.write(Tertris.score + "\n");
        writer.write(Tertris.upbond + "\n");
        writer.write(Tertris.rate + "\n");
        for(int i=0;i<Tertris.data.length;i++){
            for(int j=0;j<Tertris.data[i].length;j++){
                writer.write(Tertris.data[i][j]+" ");
            }
            writer.write("\n");
        }
        writer.close();
    }

    //读取save.txt的游戏数据
    public static void load1() throws IOException{
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("save.txt"));
        Tertris.isrunning = Boolean.parseBoolean(reader.readLine());
        Tertris.rect = Integer.parseInt(reader.readLine());
        Tertris.time = Integer.parseInt(reader.readLine());
        Tertris.x = Integer.parseInt(reader.readLine());
        Tertris.y = Integer.parseInt(reader.readLine());
        Tertris.score = Integer.parseInt(reader.readLine());
        Tertris.upbond = Integer.parseInt(reader.readLine());
        Tertris.rate = Double.parseDouble(reader.readLine());
        for(int i=0;i<Tertris.data.length;i++){
            String s=reader.readLine();
            String[] ss=s.split(" ");//用空格进行拆分
            for(int j=0;j<Tertris.data[i].length;j++){
                Tertris.data[i][j]= Integer.parseInt(ss[j]);
            }
        }
    }
}
