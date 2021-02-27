package PokemonGame;

import javax.sound.sampled.*;

import java.io.*;
import java.net.URL;


public class Music {

    private static String[] attackSounds = new String[] {"/Music/Attacks/Barrier.wav", "/Music/Attacks/Clamp.wav", "/Music/Attacks/DoubleKickSingle.wav",
            "/Music/Attacks/EggBomb1.wav", "/Music/Attacks/Explosion.wav", "/Music/Attacks/HyperBeamLaser.wav", "/Music/Attacks/IcePunch.wav",
            "/Music/Attacks/Lick.wav", "/Music/Attacks/LightScreenSingle.wav", "/Music/Attacks/Psywave1.wav", "/Music/Attacks/Sharpen.wav",
            "/Music/Attacks/WaterGun.wav"};

    private Clip clip;

    public Music(String filename) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        URL url = this.getClass().getResource(filename);

        AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(inputStream);
    }

    public static String getRandomAttackSound() {
        int i = Pokemon.randomNum(0, attackSounds.length - 1);
        return attackSounds[i];
    }

    public void start() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void reset() {
        clip.setMicrosecondPosition(0);
    }

}