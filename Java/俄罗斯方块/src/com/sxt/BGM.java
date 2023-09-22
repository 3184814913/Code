package com.sxt;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import java.io.FileInputStream;
import java.io.IOException;

public class BGM implements Runnable{
    private final String file;
    private AdvancedPlayer player = null;//空播放器
    private Thread thread = null;//空线程，实例化可用
    public BGM(String file) {
        this.file = file;
    }

    public void run() {
        createPlayer();// 每次开始需要重新创建AdvancedPlayer，否则会报错
        play();
    }

    //线程实例化
    public void start() {
        thread = new Thread(this, "Player thread");
        thread.start();
    }
    //音乐关闭设置
    public void stop() {
        player.stop();
        thread = null;
    }
    //播放音乐
    protected void play() {
        try {
            player.play();
        } catch (JavaLayerException ex) {
            System.err.println("Problem playing audio: " + ex);
        }
    }

    //播放设置
    protected void createPlayer() {
        try {
            player = new AdvancedPlayer(new FileInputStream(file));// 这里设置一个监听器，来监听停止事件
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {// 当播放完毕后,会触发该事件,再次调用start()
                    start();
                }
            });
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }
}


