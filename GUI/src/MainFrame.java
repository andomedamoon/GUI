import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame implements ActionListener {

    JButton newGameButton;
    JButton highScoreButton;
    JButton exitButton;


    public MainFrame() {
        super("MENU");

        setBounds(650, 250, 600, 500);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        newGameButton = new JButton();
        newGameButton.setText("NEW GAME");
        newGameButton.setBounds(220, 100, 150, 60);
        newGameButton.addActionListener(this);

        highScoreButton = new JButton();
        highScoreButton.setText("HIGH SCORE");
        highScoreButton.setBounds(220, 175, 150, 60);
        highScoreButton.addActionListener(this);

        exitButton = new JButton();
        exitButton.setText("EXIT");
        exitButton.setBounds(220, 250, 150, 60);
        exitButton.addActionListener(this);

        add(newGameButton);
        add(highScoreButton);
        add(exitButton);
    }

    //Tę metodę zaczerpnąłem z tego filmu: https://www.youtube.com/watch?v=hFv2Uay0qj0
    public void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if (clicked == newGameButton) {
            close();
            DifficultyLevel level = new DifficultyLevel();
        } else if (clicked == highScoreButton) {
            HighScore highScore = new HighScore();
        } else if (clicked == exitButton) {
            System.exit(0);
        }

    }
}
