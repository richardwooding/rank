package co.richardwooding.rank.tests;

import co.richardwooding.rank.Rank;
import co.richardwooding.rank.Result;
import co.richardwooding.rank.ResultFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by richardwooding on 2015/12/17.
 */
@Test
public class RankTestSuite {

    @Test
    void firstPlace() throws ResultFormatException {
        Rank rank = new Rank();
        rank.addResult(Result.parse("Lions 3, Snakes 3"))
            .addResult(Result.parse("Tarantulas 1, FC Awesome 0"))
            .addResult(Result.parse("Lions 1, FC Awesome 1"))
            .addResult(Result.parse("Tarantulas 3, Snakes 1"))
            .addResult(Result.parse("Lions 4, Grouches 0"));

        Map.Entry<String, Integer> firstPlace = rank.getSortedRankings().findFirst().get();

        Assert.assertEquals(firstPlace.getKey(), "Tarantulas");
        Assert.assertEquals(firstPlace.getValue(), new Integer(6));
    }

    @Test
    void testLoser() throws ResultFormatException  {
        Rank rank = new Rank();
        rank.addResult(Result.parse("Lions 3, Snakes 3"))
                .addResult(Result.parse("Tarantulas 1, FC Awesome 0"))
                .addResult(Result.parse("Lions 1, FC Awesome 1"))
                .addResult(Result.parse("Tarantulas 3, Snakes 1"))
                .addResult(Result.parse("Lions 4, Grouches 0"));

        Map.Entry<String, Integer> lastPlace = rank.getSortedRankings().reduce((previous, current) -> current).get();

        Assert.assertEquals(lastPlace.getKey(), "Grouches");
        Assert.assertEquals(lastPlace.getValue(), new Integer(0));
    }


}
