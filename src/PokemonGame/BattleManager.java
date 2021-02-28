package PokemonGame;

import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.lang.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class BattleManager {
    public static boolean running = true;
    static Scanner in = new Scanner(System.in);

    private Music backGroundMusic = new Music("/Music/background.wav");
    private Music healSound = new Music("/Music/heal.wav");
    private Music cry = new Music("/Music/cry.wav");
    private Music victorySound = new Music("/Music/victory.wav");
    private Music defeatSound = new Music("/Music/defeat.wav");

    public BattleManager() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    }

    public static void startBattle() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
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

    public void gameLoop(Pokemon player, Pokemon enemy) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        while (running) {
            userOption(player, enemy);
            checkWin(player, enemy);
            if (running) {
                enemyDecision(player, enemy);
            }
        }
    }

    public void attack(Pokemon attacker, Pokemon victim) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        generateRandomAttackSound().start();
        String attackType = attacker.getRandomAttack();
        attackType = "**" + attackType + "**";
        int attackAmount = attacker.rollForAttack();
        int defenseAmount = victim.rollForDefense();
        int netAttackAmount = attackAmount - defenseAmount;
        if (netAttackAmount < 0) {
            netAttackAmount = 0;
        }

        victim.updateHP(0, netAttackAmount);
        System.out.println("\n\t" + attacker.getName() + " **ATTACKED** with " + attackType + " dealing " + attackAmount + " damage. " + victim.getName()
                + " **BLOCKED** " + defenseAmount + " damage.");
        System.out.println("\t\t" + victim.getName() + " recieved " + netAttackAmount + " **TOTAL DAMAGE**. " + victim.getName() + "'s current HP is: " + victim.getHP() + ".");
        TimeUnit.SECONDS.sleep(2);
    }

    public void heal(Pokemon healer, int heal) throws InterruptedException {
        healSound.start();
        if (healer.getHP() + heal > healer.getMaxHP()) {
            heal = healer.getMaxHP() - healer.getHP();
            System.out.println("\n\t" + healer.getName() + " cannot heal above maximum HP.");
        }
        healer.updateHP(heal, 0);

        System.out.println("\n\t" + healer.getName() + " **HEALED** " + heal + " hit points. " + healer.getName() + " now has " + healer.getHP() + " hp");
        TimeUnit.SECONDS.sleep(2);
        healSound.reset();
    }

    public void userOption(Pokemon player, Pokemon enemy) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        System.out.println("\n\tWhat would you like to do?");
        System.out.println("\t\t1. Attack!");
        System.out.println("\t\t2. Heal!");
        System.out.println("\t\t3. Run away!\n\t\t");

        String input = in.nextLine();

        if (input.equals("1")) {
            attack(player, enemy);
        } else if (input.equals("2")) {
            heal(player, player.rollForHeal());
        } else if (input.equals("3")) {
            coward(player, enemy);
        } else {
            System.out.println("\n\tInvalid command!");
            userOption(player, enemy);
        }

    }

    public void startBackgroundMusic() {
        backGroundMusic.start();
    }

    public Music generateRandomAttackSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Music attackSound = new Music(Music.getRandomAttackSound());
        return attackSound;
    }

    private void enemyDecision(Pokemon player, Pokemon enemy) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        int superComplexAI = Pokemon.randomNum(1, 4);
        if (enemy.getHP() < enemy.getMaxHP() * .5) {
            superComplexAI = Pokemon.randomNum(1, 2);
        }
        if (superComplexAI == 1) {
            heal(enemy, enemy.rollForHeal());
        } else {
            attack(enemy, player);
            checkLoss(player, enemy);
        }
    }

    private void checkWin(Pokemon player, Pokemon enemy) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (enemy.getHP() <= 0) {
            TimeUnit.SECONDS.sleep(2);
            backGroundMusic.stop();
            System.out.println("\n\n\t" + enemy.getName() + " has collapsed. " + player.getName() + " is victorious!\n");
            System.out.println("\t\t**CONGRATULATIONS YOU WIN**\n");
            victorySound.start();
            playAgain();
        }
    }

    private void checkLoss(Pokemon player, Pokemon enemy) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (player.getHP() <= 0) {
            TimeUnit.SECONDS.sleep(2);
            backGroundMusic.stop();
            System.out.println("\n\n\t" + player.getName() + " has collapsed. " + enemy.getName() + " is victorious!\n");
            System.out.println("\t\t**YOU ARE DEFEATED**\n");
            defeatSound.start();
            playAgain();
        }
    }

    private void coward(Pokemon player, Pokemon enemy) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        backGroundMusic.stop();
        cry.start();
        System.out.println("\n\n\tYou coward! " + player.getName() + " dies anyways! You lose!\n\t\t" + enemy.getName() + " laughs at your miserable defeat.");
        System.out.println("\n\t\t**GAME OVER**\n");
        playAgain();
    }

    public void playAgain() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("\n\tWould you like to play again? (y/n)\n");

        String input = in.nextLine();
        if (input.equals("y")) {
            BattleManager.startBattle();
        } else if (input.equals("n")) {
            running = false;
        } else {
            System.out.println("\n\tInvalid input!");
            playAgain();
        }
    }

}