import javax.swing.*; 
import java.awt.*;    
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxCalculator extends JFrame {

    private JLabel welcomeLabel;
    private JLabel incomeLabel;
    private JLabel ageLabel;
    private ButtonGroup ageGroup;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JRadioButton jRadioButton3;
    private JTextField incomeField;
    private JButton calculateButton;
    private JButton cancelButton;
    private JLabel resultLabel;
  

    /**
     * 
     */
    public TaxCalculator() {
        setTitle("Tax Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 500);
        setLayout(new FlowLayout());
        

        welcomeLabel = new JLabel("Calculate your income tax for 2023/2024");
        ageLabel = new JLabel("How old are you ?");
        ageGroup = new ButtonGroup();
        jRadioButton1 = new JRadioButton("Under 65");
        jRadioButton2 = new JRadioButton("65-75");
        jRadioButton3 = new JRadioButton("Above 75");
        incomeLabel = new JLabel("Enter Your Annual Income:");
        incomeField = new JTextField(15);
        calculateButton = new JButton("Calculate");
        cancelButton = new JButton("Cancel");
        resultLabel = new JLabel();
        
        // Add Action Listeners to the buttons and text fields:
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTax();
            }
            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelCalculation();
            }
        });

        add(welcomeLabel);
        add(ageLabel);
        ageGroup.add(jRadioButton1);
        ageGroup.add(jRadioButton2);
        ageGroup.add(jRadioButton3);
        add(jRadioButton1);
        add(jRadioButton2);
        add(jRadioButton3);
        add(incomeLabel);
        add(incomeField);
        add(calculateButton);
        add(cancelButton);
        add(resultLabel);
       
    }
private void calculateTax() {
    try {
        double income = Double.parseDouble(incomeField.getText());

        int age = 0;
        if (jRadioButton1.isSelected()) {
            age = 65; // Set the age value according to the age group
        } else if (jRadioButton2.isSelected()) {
            age = 75; // Set the age value according to the age group
        } else if (jRadioButton3.isSelected()) {
            age = 76; // Set the age value according to the age group
        }

        double taxObligation = calculateTaxObligation(income, age);
        resultLabel.setText("Tax Obligation: R" + taxObligation);

        if (income < 0) {
            throw new IllegalArgumentException("Amount of money cannot be negative.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid income.");
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(resultLabel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private void cancelCalculation() {
        incomeField.setText("");
        resultLabel.setText("");
    }

    /**
     * @param income
     * @return
     */


 private double calculateTaxObligation(double income, int age) {
    double taxObligation = 0;

    if (income <= 100) {
        return taxObligation;
    } else if (income <= 237100) {
        taxObligation = income * 0.18;
    } else if (income <= 370500) {
        taxObligation = 42678 + (income - 237100) * 0.26;
    } else if (income <= 512800) {
        taxObligation = 77362 + (income - 370500) * 0.31;
    } else if (income <= 673000) {
        taxObligation = 121475 + (income - 512800) * 0.36;
    } else if (income <= 857900) {
        taxObligation = 179147 + (income - 673000) * 0.39;
    } else if (income <= 1817001) {
        taxObligation = 251258 + (income - 857900) * 0.41;
    } else if (income >= 1817001) {
        taxObligation = 644489 + (income - 1817001) * 0.45;
    } else {
        taxObligation = 0;
    }

    // Apply tax rebate based on age group
    if (jRadioButton1.isSelected()) { 
        taxObligation = taxObligation - 17235;
    } else if (jRadioButton3.isSelected()) { 
        taxObligation = taxObligation -  9444; 
    } else if (jRadioButton3.isSelected()){
        taxObligation =  taxObligation - 3145;
    }

    return taxObligation;
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TaxCalculator taxCalculator = new TaxCalculator();
                taxCalculator.setVisible(true);
            }
        });
    }
}
