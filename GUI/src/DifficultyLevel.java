import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DifficultyLevel extends JFrame implements ActionListener {

    JButton easy;
    JButton medium;
    JButton hard;


    DifficultyLevel() {
        super("DIFFICULTY LEVEL");

        setBounds(650, 250, 600, 500);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        easy = new JButton();
        easy.setText("EASY");
        easy.setBounds(220, 100, 150, 60);
        easy.addActionListener(this);

        medium = new JButton();
        medium.setText("MEDIUM");
        medium.setBounds(220, 175, 150, 60);
        medium.addActionListener(this);

        hard = new JButton();
        hard.setText("HARD");
        hard.setBounds(220, 250, 150, 60);
        hard.addActionListener(this);

        add(easy);
        add(medium);
        add(hard);



    }

    public void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if (clicked == easy) {
            close();
            LevelDataSetter.setAmountValue(30);
            LevelDataSetter.setMulipleValue(1);
            Game newGame = new Game();
        } else if (clicked == medium) {
            close();
            LevelDataSetter.setAmountValue(80);
            LevelDataSetter.setMulipleValue(2);
            Game newGame = new Game();
        } else if (clicked == hard) {
            close();
            LevelDataSetter.setAmountValue(125);
            LevelDataSetter.setMulipleValue(3);
            Game newGame = new Game();
        }
    }
}
