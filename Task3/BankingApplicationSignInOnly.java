import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingApplicationSignInOnly extends JFrame {
    private Map<String, Double> accountBalances = new HashMap<>();
    private Map<String, List<String>> userTransactions = new HashMap<>();
    private String currentUser = null;

    private JTextField accountNumberField;
    private JPasswordField pinField;
    private JButton signInButton;

    public BankingApplicationSignInOnly() {
        setTitle("Banking Application Sign In Only");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        accountNumberField = new JTextField(10);
        pinField = new JPasswordField(10);
        signInButton = new JButton("Sign In");

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Account Number: "));
        panel.add(accountNumberField);
        panel.add(new JLabel("PIN: "));
        panel.add(pinField);
        panel.add(signInButton);

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void signIn() {
        String enteredAccountNumber = accountNumberField.getText();
        char[] enteredPINChars = pinField.getPassword();
        String enteredPIN = new String(enteredPINChars);

        if ("123456".equals(enteredAccountNumber) && "7890".equals(enteredPIN)) {
            currentUser = enteredAccountNumber;
            openMainApplicationFrame();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid account number or PIN. Please try again.");
        }
    }

    private void openMainApplicationFrame() {
        new MainApplicationFrame(accountBalances, userTransactions, currentUser);
        this.dispose();  // Close the current frame after opening the new one
    }

    private void clearFields() {
        accountNumberField.setText("");
        pinField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankingApplicationSignInOnly();
            }
        });
    }
}
