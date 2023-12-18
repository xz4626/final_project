package xingchen.model;

import jaco.mp3.player.MP3Player;

import java.io.File;
import java.net.URL;


public class Music {

    public void music() {
        URL musicUrl = Music.class.getClassLoader().getResource("xingchen/model/背景音乐.mp3");
        if (musicUrl == null) {
            System.out.println("音乐文件未找到");
            return;
        }

        MP3Player player = new MP3Player(musicUrl);
        player.play();
        player.setRepeat(true);
    }


}
