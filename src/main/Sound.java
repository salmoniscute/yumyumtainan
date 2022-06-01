package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/sound/theme.wav");
        soundURL[1] = getClass().getResource("/sound/option.wav");
        soundURL[2] = getClass().getResource("/sound/mouse.wav");
        soundURL[3] = getClass().getResource("/sound/openDoor1.wav");
        soundURL[4] = getClass().getResource("/sound/openDoor2.wav");
        soundURL[5] = getClass().getResource("/sound/closeDoor.wav");
        soundURL[6] = getClass().getResource("/sound/cashier.wav");
        soundURL[7] = getClass().getResource("/sound/makeMoney.wav");
        soundURL[8] = getClass().getResource("/sound/Attack.wav");
        soundURL[9] = getClass().getResource("/sound/gameOver.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();

    }

    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}







