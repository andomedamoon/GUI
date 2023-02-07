import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HighScore extends JFrame {

    JList<Player> ranking = new JList<>();
    DefaultListModel<Player> model = new DefaultListModel<>();
    Database database;
    File file = new File("high_score.txt");


    public HighScore() {
        super("RANKING");
        try {

            database = new Database();
            database.loadFromFile(file);


            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setBounds(250, 250, 500, 350);
            setVisible(false);
            setLocationRelativeTo(null);
            setVisible(true);
            setLayout(new BorderLayout());

            ranking.setModel(model);
            model.addAll(Database.players);

            add(ranking);
            add(new JScrollPane(ranking), BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
