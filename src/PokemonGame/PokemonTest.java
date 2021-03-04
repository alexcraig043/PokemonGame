package PokemonGame;

import org.junit.Assert;
import org.junit.Test;

/**
 * Pokemon Tester.
 *
 * @author MJ Linane
 * @version 1.0
 * @since <pre>Feb 23, 2021</pre>
 */


public class PokemonTest {
    /**
     * Method: getAllPossiblePokemon()
     */
    @Test
    public void testGetAllPossiblePokemon()
    {
        // Has length greater than 1
        Assert.assertTrue("possiblePokemon need to have some Pokemon in the array.", Pokemon.getAllPossiblePokemon().length > 0);

        // Has 10 Possible Pokemon
        Assert.assertEquals("allPossiblePokemon should be an array of 10 Pokemon", 10, Pokemon.getAllPossiblePokemon().length);
    }


    /**
     * Method: getSingleRandomPokemon()
     */
    @Test
    public void testGetSingleRandomPokemon()
    {
        Assert.assertNotEquals("Needs to get a different random pokemon. Try fixing the method or running the test again.", Pokemon.getSingleRandomPokemon(), Pokemon.getSingleRandomPokemon());
        Assert.assertNotEquals("Needs to get a different random pokemon. Try fixing the method or running the test again.", Pokemon.getSingleRandomPokemon(), Pokemon.getSingleRandomPokemon());
        Assert.assertNotEquals("Needs to get a different random pokemon. Try fixing the method or running the test again.", Pokemon.getSingleRandomPokemon(), Pokemon.getSingleRandomPokemon());
    }


    /**
     * Method: getSingleRandomMove()
     */
    @Test
    public void testGetSingleRandomMove()
    {
        Pokemon test1 = new Pokemon();
        Assert.assertNotEquals("Needs to get a different random move. Try running again or fix the error.", test1.getSingleRandomMove(), test1.getSingleRandomMove());
    }


    /**
     * Method: getThreeRandomMoves()
     */
    @Test
    public void testGetThreeRandomMoves()
    {
        // Duplicates Found
        String[] moves = Pokemon.getThreeRandomMoves();
        Assert.assertEquals("Duplicates found. There should only be one of each. Try running again or fix the error.", 1, frequencyInArray(moves, moves[0]));
        Assert.assertEquals("Duplicates found. There should only be one of each. Try running again or fix the error.", 1, frequencyInArray(moves, moves[1]));
        Assert.assertEquals("Duplicates found. There should only be one of each. Try running again or fix the error.", 1, frequencyInArray(moves, moves[2]));
    }

    private int frequencyInArray(String[] arrayToSearch, String stringToFind)
    {
        // Initialize counter to check for duplicates
        int counter = 0;
        // CHECK IF ALREADY IN ARRAY
        for (String tempMove : arrayToSearch)
        {
            if (tempMove.equals(stringToFind))
            {
                counter += 1;
            }
        }

        return counter;
    }
}
