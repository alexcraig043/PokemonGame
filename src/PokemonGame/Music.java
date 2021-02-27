package PokemonGame;

import javax.sound.sampled.*;

import java.io.*;
import java.net.URL;


public class Music {

    private static String[] attackSounds = new String[] {"src/Music/Attacks/Barrier.wav", "src/Music/Attacks/Clamp.wav", "src/Music/Attacks/DoubleKickSingle.wav",
            "src/Music/Attacks/EggBomb1.wav", "src/Music/Attacks/Explosion.wav", "src/Music/Attacks/HyperBeamLaser.wav", "src/Music/Attacks/IcePunch.wav",
            "src/Music/Attacks/Lick.wav", "src/Music/Attacks/LightScreenSingle.wav", "src/Music/Attacks/Psywave1.wav", "src/Music/Attacks/Sharpen.wav",
            "src/Music/Attacks/WaterGun.wav"};

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