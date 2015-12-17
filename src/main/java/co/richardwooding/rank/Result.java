package co.richardwooding.rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by richardwooding on 2015/12/15.
 */
public class Result {

    static final Pattern RESULT_PATTERN = Pattern.compile("\\s*([[^,].]+)\\s+(\\d+)\\s*,\\s*([[^,],]+)\\s+(\\d+)\\s*");


    List<Score> scores = new ArrayList<>();

    public Result(Score scoreA, Score scoreB) {
        scores.add(scoreA);
        scores.add(scoreB);
        Collections.sort(scores, Comparator.comparing(Score::getPoints).thenComparing(Score::getTeam).reversed());
    }

    public static Result parse(CharSequence resultChars) throws ResultFormatException {
        Matcher matcher = RESULT_PATTERN.matcher(resultChars);
        if (matcher.matches()) {
            return new Result(new Score(matcher.group(1), Integer.parseInt(matcher.group(2))), new Score(matcher.group(3), Integer.parseInt(matcher.group(4))));
        } else {
            throw new ResultFormatException(String.format("Invalid result format: %s", resultChars));
        }
    }

    public boolean isTie() {
        return scores.get(0).getPoints() == scores.get(1).getPoints();
    }

    public Score getHigherScore() {
        return scores.get(0);
    }

    public Score getLowerScore() {
        return scores.get(1);
    }

    public String toString() {
        return String.format("\\s, \\s", getHigherScore(), getLowerScore());
    }

}
