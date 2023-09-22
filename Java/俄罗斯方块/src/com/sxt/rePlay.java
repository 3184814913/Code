package com.sxt;

import java.io.File;

public class rePlay {
    public rePlay() {
        File file3 = new File("音乐合集.mp3");//file实例化
        BGM bgm = new BGM(file3.getAbsolutePath());//用所播放音乐的相对路径创建BGM的新对象
        bgm.start();//启动背景音乐
        try {
            Thread.sleep(2000);//打开窗口时延长2秒播放
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

