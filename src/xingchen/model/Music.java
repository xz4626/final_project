package xingchen.model;

import jaco.mp3.player.MP3Player;

import java.io.File;

public class Music {

    public void music(){

        File file = new File("model\\背景音乐.mp3");
        MP3Player player = new MP3Player(file);

        player.play();
        player.setRepeat(true);


    }


}
