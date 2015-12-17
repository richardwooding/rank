package co.richardwooding.rank;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by richardwooding on 2015/12/15.
 */
public class Rank {

    private Map<String, Integer> rankings = new HashMap<>();

    public Rank addResult(Result result) {
        if (result.isTie()) {
            increaseScore(result.getHigherScore().getTeam(), 1);
            increaseScore(result.getLowerScore().getTeam(), 1);
        } else {
            increaseScore(result.getHigherScore().getTeam(), 3);
            increaseScore(result.getLowerScore().getTeam(), 0);
        }
        return this;
    }

    private void increaseScore(String team, int increase) {
        Integer score = rankings.get(team);
        rankings.put(team, score == null ? increase : score + increase);
    }

    public Stream<Map.Entry<String, Integer>> getSortedRankings() {
        return rankings.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed());
    }

}
