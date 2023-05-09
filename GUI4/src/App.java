import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class App {
    private static final int ROCK = 0;
    private static final int PAPER = 1;
    private static final int SCISSORS = 2;

    private JFrame frame;
    private JLabel resultLabel;
    private int userScore;
    private int computerScore;

    public App() {
        frame = new JFrame("Rock Paper Scissors Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);

        Icon scis = new ImageIcon("scis.png");
        Icon paper = new ImageIcon("paper.jpg");
        Icon rock = new ImageIcon("rock.jpg");
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 50, 50));
        JButton rockButton = new JButton(rock);
        JButton paperButton = new JButton(paper);
        JButton scissorsButton = new JButton(scis);
        ;

        rockButton.addActionListener(new ButtonListener(ROCK));
        paperButton.addActionListener(new ButtonListener(PAPER));
        scissorsButton.addActionListener(new ButtonListener(SCISSORS));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        resultLabel = new JLabel("Choose your move to start the game!", JLabel.CENTER);
        resultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 48));

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        private int userChoice;

        public ButtonListener(int choice) {
            userChoice = choice;
        }

        public void actionPerformed(ActionEvent e) {
            int computerChoice = new Random().nextInt(3);
            String result = "";

            if (userChoice == computerChoice) {
                result = "It's a tie!";
            } else if ((userChoice == ROCK && computerChoice == SCISSORS) ||
                    (userChoice == PAPER && computerChoice == ROCK) ||
                    (userChoice == SCISSORS && computerChoice == PAPER)) {
                result = "You win!";
                userScore++;
            } else {
                result = "Computer wins!";
                computerScore++;
            }
            resultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
            resultLabel.setText(String.format("You chose %s, computer chose %s. %s\nYour score: %d, Computer score: %d",
                    getChoiceString(userChoice), getChoiceString(computerChoice), result, userScore, computerScore));
        }

        private String getChoiceString(int choice) {
            switch (choice) {
                case ROCK:
                    return "Rock";
                case PAPER:
                    return "Paper";
                case SCISSORS:
                    return "Scissors";
                default:
                    return "";
            }
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
