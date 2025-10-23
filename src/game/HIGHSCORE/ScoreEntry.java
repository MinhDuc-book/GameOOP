package game.HIGHSCORE;

public class ScoreEntry implements Comparable<ScoreEntry> {
    private String playerName;
    private int score;

    public ScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(ScoreEntry other) {
        return Integer.compare(other.score, this.score);
    }

    @Override
    public String toString() {
        return playerName + ":" + score;
    }

    public static ScoreEntry fromString(String line) {
        String[] parts = line.split(":");
        return new ScoreEntry(parts[0], Integer.parseInt(parts[1]));
    }
}
