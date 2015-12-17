package co.richardwooding.rank;

/**
 * Created by richardwooding on 2015/12/15.
 */
public class Score {

    private String team;
    private int points;

    public String getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }

    public Score(String team, int points) {
        this.team = team;
        this.points = points;
    }

    @Override
    public String toString() {
        return String.format("%s %s", team, points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (points != score.points) return false;
        return !(team != null ? !team.equals(score.team) : score.team != null);

    }

    @Override
    public int hashCode() {
        int result = team != null ? team.hashCode() : 0;
        result = 31 * result + points;
        return result;
    }
}
