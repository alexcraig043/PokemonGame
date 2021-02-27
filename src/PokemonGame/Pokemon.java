package PokemonGame;

import java.util.*;

public class Pokemon {
    static String[] possiblePokemon = {"Charizard", "Gengar", "Bulbasaur", "Squirtle", "Metapod", "Pikachu", "Eevee", "Ditto", "Pidgeot", "Vulpix"};
    static String[] possibleMoves = {"Astral Barrage", "Barrage", "Bite", "Bone Rush", "Power Whip", "Water Pulse", "Hurricane", "Hydro Cannon", "Comet Punch", "Disarming Voice", "Earthquake", "Lick"};

    private String[] moves = new String[3];
    private int defense;
    private int level;
    private int maxAttack;
    private String name;
    private int hp;
    private int maxHP;
    private int maxHeal;

    private String line = "\t----------------------------------------------------------";
    private String barTop = "\t/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\";
    private String barBottom = "\t\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/";

    public Pokemon() {
        name = getSingleRandomPokemon();
        level = randomNum(1, 100);
        defense = randomNum(1, 2) * level;
        maxAttack = randomNum(3, 5) * level;
        maxHeal = randomNum(1, 5) * level;
        hp = randomNum(5, 10) * level;
        maxHP = hp;
        moves = getThreeRandomMoves();
        this.removeMoveDuplicates();

    }

    public void displayStats() {
        System.out.println(barTop);
        System.out.println(barBottom);
        System.out.println("\n\tNAME: " + getName().toUpperCase());
        System.out.println(line);
        System.out.println("\tLEVEL: " + getLevel());
        System.out.println(line);
        System.out.println("\tHP: " + getHP() + "\n\tMAX ATTTACK: " + getMaxAttack() + "\n\tDEFENSE: " + getDefense() + "\n\tMAX HEAL: " + getMaxHeal());
        System.out.println(line);
        System.out.println("\tABILITIES: " + Arrays.toString(getMoves()) + "\n");
        System.out.println(barTop);
        System.out.println(barBottom + "\n\n");
    }

    public static int randomNum(int min, int max) {
        return (int)(Math.random() * (max - min + 1) + min);
    }

    private void removeMoveDuplicates() {
        for(int i = 0; i < getMoves().length; i++) {
            for(int j = i + 1; j < getMoves().length; j++) {
                if(moves[i].equals(moves[j])) {
                    moves[i] = getSingleRandomMove().toUpperCase();
                    removeMoveDuplicates();
                }
            }
        }
    }

    public static String[] getAllPossibleMoves() {
        return possibleMoves;
    }

    public String[] getThreeRandomMoves() {
        String[] threeRandomMoves = new String[3];
        for (int i = 0; i < threeRandomMoves.length; i++) {
            threeRandomMoves[i] = getSingleRandomMove().toUpperCase();
        }
        return threeRandomMoves;
    }

    public String getSingleRandomMove() {
        return possibleMoves[randomNum(0, getAllPossibleMoves().length - 1)];
    }

    public String getRandomAttack() {
        String attackType = getMoves()[Pokemon.randomNum(0, getMoves().length - 1)];
        return attackType;
    }

    public String[] getAllPossiblePokemon() {
        return possiblePokemon;
    }

    public String getSingleRandomPokemon() {
        int i = randomNum(0, possiblePokemon.length - 1);
        return possiblePokemon[i];
    }

    public void updateHP(int heal, int damage) {
        hp += heal;
        hp -= damage;
    }

    public int rollForAttack() {
        return randomNum(0, this.getMaxAttack());
    }

    public int rollForDefense() {
        return randomNum(0, this.getDefense());
    }

    public int rollForHeal() {
        return randomNum(0, this.getMaxHeal());
    }

    public void chooseName() {
        Scanner in = new Scanner(System.in);
        String nameChoice = in.nextLine();
        this.name = nameChoice;
    }

    public String getName() {
        return name;
    }

    public String[] getMoves() {
        return moves;
    }

    public int getLevel() {
        return level;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public int getHP() {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxHeal() {
        return maxHeal;
    }

}
