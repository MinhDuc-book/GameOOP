package game.HIGHSCORE;

public class ScoreEntry implements Comparable<ScoreEntry> {
    private int score;

    public ScoreEntry(int score) {
        this.score = score;
    }


    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(ScoreEntry other) {
        return Integer.compare(other.score, this.score);
    }

}
