import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Test extends JFrame implements ActionListener {
    private int currentQuestion = 0;
    private ArrayList<Integer> scores = new ArrayList<Integer>();
    private JLabel questionLabel;
    private JRadioButton[] answerButtons;
    private JButton nextButton;
    private JButton finishButton;

    private String[] questions = {
        "Яка столиця України?",
        "Які події привели до Першої світової війни?",
        "У якому році був оголошений Рік пам'яті Голодомору??"
    };

    private String[][] answers = {
        {"Kyiv", "Lviv", "Kharkiv", "Odessa"},
        {"Анексія Австрією Угорщини Боснії і Герцеговини.", "Утворення Трійського союзу між Німеччиною, Австро-Угорщиною та Італією.", "Розрив дипломатичних стосунків між Німеччиною та Францією через кризу у Марокко"},
        {"1932", "1933", "1934", "1935"}
    };

    public Test() {
        super("Tests");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel for question and answers
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionLabel = new JLabel(questions[currentQuestion]);
        questionPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel answerPanel = new JPanel(new GridLayout(0, 1));
        answerButtons = new JRadioButton[answers[currentQuestion].length];
        ButtonGroup answerGroup = new ButtonGroup();
        for (int i = 0; i < answers[currentQuestion].length; i++) {
            answerButtons[i] = new JRadioButton(answers[currentQuestion][i]);
            answerButtons[i].addActionListener(this);
            answerGroup.add(answerButtons[i]);
            answerPanel.add(answerButtons[i]);
        }
        questionPanel.add(answerPanel, BorderLayout.CENTER);

        add(questionPanel, BorderLayout.CENTER);

        // Buttons "Next" and "Finish"
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        finishButton = new JButton("Finish");
        finishButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(nextButton);
        buttonPanel.add(finishButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Processing of answer selection
        if (e.getSource() instanceof JRadioButton) {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            int score = 0;
            if (selectedButton.getText().equals(answers[currentQuestion][0])) {
                score = 5;
            }
            scores.add(score);
        }

        // Processing of "Next" and "Finish" buttons
        if (e.getSource() == nextButton) {
            // Move to the next question
            currentQuestion++;
            if (currentQuestion < questions.length) {
                // Update panel with question and answers
                questionLabel.setText(questions[currentQuestion]);
                for (int i = 0; i < answers[currentQuestion].length; i++) {
                    answerButtons[i].setText(answers[currentQuestion][i]);
                }
            } else {
                // Finish the test
                finish();
            }
        } else if (e.getSource() == finishButton) {
            // Finish the test
            finish();
        }
    }

    private void finish() {
        // Calculation of average score
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }
        double averageScore = (double) totalScore / scores.size();

        // Displaying results
        JOptionPane.showMessageDialog(null, "Your average score is " + averageScore, "Results", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }
    public static void main(String[] args) {
        new Test();
    }
}