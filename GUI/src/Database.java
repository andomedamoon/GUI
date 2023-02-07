import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Database {

    public static ArrayList<Player> players;

    public Database() {
        players = new ArrayList<Player>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayer() {
        return players;
    }

    public int getPlayersAmounts() {
        return players.size();
    }

    public void saveToFile(File file) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        PlayersPointComparator playersPointComparator = new PlayersPointComparator();

        Player[] players = getPlayer().toArray(new Player[getPlayersAmounts()]);

        Arrays.sort(players, playersPointComparator);

        objectOutputStream.writeObject(players);
        objectOutputStream.close();
    }

    public void loadFromFile(File file) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        try {
            Player[] previousPlayers = (Player[])objectInputStream.readObject();

            players.clear();

            players.addAll(Arrays.asList(previousPlayers));

//            players.sort((o1, o2) -> {
//                if (o1.getPoints() > o2.getPoints()) {
//                    return -1;
//                } else if (o1.getPoints() < o2.getPoints()) {
//                    return 1;
//                } else {
//                    if (o1.getDays() > o2.getDays()) {
//                        return 1;
//                    } else if (o1.getDays() < o2.getDays()) {
//                        return -1;
//                    } else {
//                       return o1.getName().compareTo(o2.getName());
//                    }
//                }
//            });





        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        objectInputStream.close();
    }

}
