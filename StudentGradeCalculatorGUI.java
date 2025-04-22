import javax.swing.*;
import java.awt.event.*;

public class StudentGradeCalculatorGUI extends JFrame {
    private JTextField[] subjectFields;
    private JLabel resultLabel;

    public StudentGradeCalculatorGUI(int numSubjects) {
        setTitle("Student Grade Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        subjectFields = new JTextField[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            JLabel label = new JLabel("Subject " + (i + 1) + " Marks:");
            label.setBounds(30, 30 + (i * 40), 120, 25);
            add(label);

            subjectFields[i] = new JTextField();
            subjectFields[i].setBounds(160, 30 + (i * 40), 150, 25);
            add(subjectFields[i]);
        }

        JButton calculateButton = new JButton("Calculate Grade");
        calculateButton.setBounds(100, 40 * numSubjects + 30, 180, 30);
        add(calculateButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(30, 40 * numSubjects + 70, 320, 100);
        add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });

        setVisible(true);
    }

    private void calculateResults() {
        int total = 0;
        int count = subjectFields.length;

        for (JTextField field : subjectFields) {
            try {
                int mark = Integer.parseInt(field.getText());
                if (mark < 0 || mark > 100) {
                    resultLabel.setText("Enter marks between 0 and 100.");
                    return;
                }
                total += mark;
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers for all fields.");
                return;
            }
        }

        double average = (double) total / count;
        char grade;

        if (average >= 90) grade = 'A';
        else if (average >= 80) grade = 'B';
        else if (average >= 70) grade = 'C';
        else if (average >= 60) grade = 'D';
        else if (average >= 50) grade = 'E';
        else grade = 'F';

        resultLabel.setText("<html>Total Marks: " + total +
                "<br>Average: " + String.format("%.2f", average) +
                "%<br>Grade: " + grade + "</html>");
    }

    public static void main(String[] args) {
        // Customize the number of subjects here
        new StudentGradeCalculatorGUI(5);
    }
}