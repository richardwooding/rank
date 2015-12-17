package co.richardwooding.rank.tests;

import co.richardwooding.rank.Result;
import co.richardwooding.rank.ResultFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by richardwooding on 2015/12/18.
 */
@Test
public class ResultTestSuite {

    @Test
    void tieTest() throws ResultFormatException {
        Result result = Result.parse("Lions 3,Snakes 3");
        Assert.assertEquals(result.isTie(), true);
    }

    @Test(expectedExceptions = ResultFormatException.class)
    void malformattedTest() throws ResultFormatException {
        Result result = Result.parse("not a score");
    }

    @Test
    void winnerLoserValuesTest() throws ResultFormatException {
        Result result = Result.parse("Tarantulas 3, Snakes 1");

        Assert.assertEquals(result.getHigherScore().getTeam(), "Tarantulas");
        Assert.assertEquals(result.getHigherScore().getPoints(), 3);
        Assert.assertEquals(result.getLowerScore().getTeam(), "Snakes");
        Assert.assertEquals(result.getLowerScore().getPoints(), 1);
    }

    @Test
    void winnerLoserValuesReversedTest() throws ResultFormatException {
        Result result = Result.parse("Snakes 1, Tarantulas 3");

        Assert.assertEquals(result.getHigherScore().getTeam(), "Tarantulas");
        Assert.assertEquals(result.getHigherScore().getPoints(), 3);
        Assert.assertEquals(result.getLowerScore().getTeam(), "Snakes");
        Assert.assertEquals(result.getLowerScore().getPoints(), 1);
    }
    @Test
    void whitespacesTest() throws ResultFormatException {
        Result result = Result.parse("  Lions 3 , Snakes 1  ");

        Assert.assertEquals(result.getHigherScore().getTeam(), "Lions");
        Assert.assertEquals(result.getHigherScore().getPoints(), 3);
        Assert.assertEquals(result.getLowerScore().getTeam(), "Snakes");
        Assert.assertEquals(result.getLowerScore().getPoints(), 1);
    }

}
