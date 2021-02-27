package PokemonGame;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

        BattleManager battle = new BattleManager();
        battle.startBackgroundMusic();

        Pokemon enemy = new Pokemon();
        System.out.println("\n\tAnother trainer is issuing a challenge!\n\n\t"
                + enemy.getName() + " has appeared!" + "\n\n");
        Pokemon player = new Pokemon();
        enemy.displayStats();
        System.out.println("\n\tWhat do you want to name your pokemon?\n");
        player.chooseName();
        System.out.println("\n\tYou choose: " + player.getName() + "!\n\n\t");
        player.displayStats();

        battle.gameLoop(player, enemy);
    }
}