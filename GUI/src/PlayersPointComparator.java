import java.util.Comparator;

public class PlayersPointComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getPoints() > o2.getPoints()) {
            return -1;
        } else if (o1.getPoints() < o2.getPoints()) {
            return 1;
        } else {
            if (o1.getDays() > o2.getDays()) {
                return 1;
            } else if (o1.getDays() < o2.getDays()) {
                return -1;
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        }
    }
}
