import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {

    private String name;
    private int points;
    private int days;

    public Player(String name, int points, int days) {
        this.name = name;
        this.points = points;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getDays() {
        return days;
    }

    @Override
    public int compareTo(Player o) {
        if (points > o.points) {
            return -1;
        } else if (points < o.points) {
            return 1;
        } else {
            if (days > o.days) {
                return 1;
            } else if (days < o.days) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "PLAYER: " +
                " NAME: " + name +
                " POINTS: " + points +
                " DAYS: " + days;
    }
}
