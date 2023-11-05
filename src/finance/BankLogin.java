package finance;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BankLogin extends JFrame implements ActionListener {
    private JTextField loginUsernameField, createEmailAddressField;
    private JPasswordField loginPasswordField;
    private JButton loginButton, forgotLoginButton;

    private JTextField createUsernameField;
    private JPasswordField createPasswordField;
    private JButton createButton, deleteButton;

    private JTabbedPane tabbedPane;

    private Map<String, String> userDatabase; // In-memory user database (not secure)

    public BankLogin() {
        setTitle("BANK LOGIN AND CREATE ACCOUNT" + "     DATE: " + new Date());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255,255,173));//red,green,blue==white
        //setFont(new Font("MV Boli",Font.PLAIN,16));

        ImageIcon image = new ImageIcon("C:\\Users\\Paul\\CBFAcademy\\currency_symbol.png");
        setIconImage(image.getImage());//get icon of image

        tabbedPane = new JTabbedPane();

        // Initialize the in-memory user database (username -> password)
        userDatabase = new HashMap<>();
        userDatabase.put("myusername", "mypassword");

        // Login Page
        Border border = BorderFactory.createLineBorder(Color.GREEN);
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2));

        JLabel loginUsernameLabel = new JLabel("Username:");
        JLabel loginPasswordLabel = new JLabel("Password:");
        JCheckBox showPasswordCheckbox = new JCheckBox("Show Password");
        JLabel forgotPasswordLabel;
        loginUsernameField = new JTextField(20);
        loginPasswordField = new JPasswordField(20);
        forgotLoginButton = new JButton("ForgotPassword");
        loginButton = new JButton("Login");


        loginButton.addActionListener(this);
        forgotLoginButton.addActionListener(this);

        loginUsernameField.setBackground(Color.LIGHT_GRAY);
        loginUsernameField.setForeground(Color.BLACK);//set font colour
        loginUsernameField.setFont(new Font("MV Boli",Font.PLAIN,16));
        loginUsernameField.setOpaque(true);
        loginUsernameField.setBorder(border);
        loginUsernameField.setBounds(20, 40, 80, 30);

        loginPasswordField.setBackground(Color.LIGHT_GRAY);
        loginPasswordField.setForeground(Color.BLACK);//set font colour
        loginPasswordField.setFont(new Font("MV Boli",Font.PLAIN,16));
        loginPasswordField.setOpaque(true);
        loginPasswordField.setBorder(border);
        loginPasswordField.setBounds(20, 40, 80, 30);

        forgotLoginButton.setBackground(Color.LIGHT_GRAY);
        forgotLoginButton.setForeground(Color.BLACK);//set font colour
        //forgotLoginButton.setFont(new Font("MV Boli",Font.PLAIN,16));
        forgotLoginButton.setOpaque(true);
        forgotLoginButton.setBorder(border);
        forgotLoginButton.setBounds(20, 40, 80, 30);

        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setForeground(Color.BLACK);//set font colour
        //loginButton.setFont(new Font("MV Boli",Font.PLAIN,16));
        loginButton.setOpaque(true);
        loginButton.setBorder(border);
        loginButton.setBounds(20, 40, 80, 30);

        showPasswordCheckbox.addActionListener(e -> {
            JCheckBox cb = (JCheckBox) e.getSource();
            loginPasswordField.setEchoChar(cb.isSelected() ? 0 : '\u2022');
        });

// Set the initial state of the checkbox and password visibility
        showPasswordCheckbox.setSelected(false); // Password is initially hidden
        loginPasswordField.setEchoChar('\u2022'); // Use a bullet character to hide the password

        loginPanel.add(loginUsernameLabel);
        loginPanel.add(loginUsernameField);
        loginPanel.add(loginPasswordLabel);
        loginPanel.add(loginPasswordField);
        loginPanel.add(new JLabel());
        loginPanel.add(showPasswordCheckbox);
        loginPanel.add(forgotLoginButton);
        loginPanel.add(loginButton);
        //loginPanel.add(new JLabel());



        // Create Account Page
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new GridLayout(5, 2));

        JLabel createUsernameLabel = new JLabel("New Username:");
        JLabel createEmailAddressLabel = new JLabel("E-mail Address:");
        JLabel createPasswordLabel = new JLabel("New Password:");
        JCheckBox showPasswordCheckbox2 = new JCheckBox("Show Password");
        createUsernameField = new JTextField(20);
        createEmailAddressField = new JTextField(50);
        createPasswordField = new JPasswordField(20);
        createButton = new JButton("Create Account");
        deleteButton = new JButton("Delete Account");


        createButton.addActionListener(this);
        deleteButton.addActionListener(this);

        createUsernameField.setBackground(Color.LIGHT_GRAY);
        createUsernameField.setForeground(Color.BLACK);//set font colour
        createUsernameField.setFont(new Font("MV Boli",Font.PLAIN,16));
        createUsernameField.setOpaque(true);
        createUsernameField.setBorder(border);
        createUsernameField.setBounds(20, 40, 80, 30);

        createEmailAddressField.setBackground(Color.LIGHT_GRAY);
        createEmailAddressField.setForeground(Color.BLACK);//set font colour
        createEmailAddressField.setFont(new Font("MV Boli",Font.PLAIN,16));
        createEmailAddressField.setOpaque(true);
        createEmailAddressField.setBorder(border);
        createEmailAddressField.setBounds(20, 40, 80, 30);

        createPasswordField.setBackground(Color.LIGHT_GRAY);
        createPasswordField.setForeground(Color.BLACK);//set font colour
        createPasswordField.setFont(new Font("MV Boli",Font.PLAIN,16));
        createPasswordField.setOpaque(true);
        createPasswordField.setBorder(border);
        createPasswordField.setBounds(20, 40, 80, 30);

        createButton.setBackground(Color.LIGHT_GRAY);
        createButton.setForeground(Color.BLACK);//set font colour
        createButton.setOpaque(true);
        createButton.setBorder(border);
        createButton.setBounds(20, 40, 80, 30);

        deleteButton.setBackground(Color.LIGHT_GRAY);
        deleteButton.setForeground(Color.BLACK);//set font colour
        deleteButton.setOpaque(true);
        deleteButton.setBorder(border);
        deleteButton.setBounds(20, 40, 80, 30);


        showPasswordCheckbox2.addActionListener(e -> {
            JCheckBox cb = (JCheckBox) e.getSource();
            createPasswordField.setEchoChar(cb.isSelected() ? 0 : '\u2022');
        });

        // Set the initial state of the checkbox and password visibility
        showPasswordCheckbox2.setSelected(false); // Password is initially hidden
        createPasswordField.setEchoChar('\u2022'); // Use a bullet character to hide the password



        createAccountPanel.add(createUsernameLabel);
        createAccountPanel.add(createUsernameField);
        createAccountPanel.add(createEmailAddressLabel);
        createAccountPanel.add(createEmailAddressField);
        createAccountPanel.add(createPasswordLabel);
        createAccountPanel.add(createPasswordField);
        createAccountPanel.add(new JLabel());
        createAccountPanel.add(showPasswordCheckbox2);
        createAccountPanel.add(createButton);
        createAccountPanel.add(deleteButton);

//        createAccountPanel.add(new JLabel());


        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Create Account", createAccountPanel);

        add(tabbedPane);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String enteredUsername = loginUsernameField.getText();
            String enteredPassword = new String(loginPasswordField.getPassword());//char[] enteredPassword = loginPasswordField.getPassword();

            if (!isPasswordComplex(enteredPassword)) {
                JOptionPane.showMessageDialog(this, "Password does not meet complexity requirements.");
                return; // Don't proceed with login if the password is not complex enough
            }


            if (checkLogin(enteredUsername, new String(enteredPassword))) {
                JOptionPane.showMessageDialog(this, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Please check your username and password.");
            }

            loginUsernameField.setText("");
            loginPasswordField.setText("");
        } else if (e.getSource() == createButton) {
            String newUsername = createUsernameField.getText();
            String newPassword = new String(createPasswordField.getPassword());//char[] newPassword = createPasswordField.getPassword();

            if (!isPasswordComplex(newPassword)) {
                JOptionPane.showMessageDialog(this, "Password does not meet complexity requirements.");
                return; // Don't proceed with account creation if the password is not complex enough
            }

            if (createAccount(newUsername, new String(newPassword))) {
                JOptionPane.showMessageDialog(this, "Account created!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create an account.");
            }

            createUsernameField.setText("");
            createPasswordField.setText("");
        }
        if (e.getSource() == deleteButton) {
            String usernameToDelete = createUsernameField.getText();
            if (deleteUser(usernameToDelete)) {
                JOptionPane.showMessageDialog(this, "Account deleted!");
            }
            createUsernameField.setText("");
            createPasswordField.setText("");
        }

    }

    private boolean checkLogin(String username, String password) {
        String storedPassword = userDatabase.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    private boolean createAccount(String username, String password) {
        if (userDatabase.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists.");
            return false;
        }
        if (!isPasswordComplex(password)) {
            JOptionPane.showMessageDialog(this, "Password does not meet complexity requirements.");
            return false;
        }

        userDatabase.put(username, password);
        return true;
    }
    private boolean isPasswordComplex(String password) {
        // Check if the password meets complexity requirements
        return password.matches(".*[A-Z].*") && // At least one uppercase letter
                password.matches(".*[a-z].*") && // At least one lowercase letter
                password.matches(".*\\d.*") &&   // At least one digit
                password.length() >= 8;         // Minimum length (adjust as needed)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankLogin loginSystem = new BankLogin();
            loginSystem.setVisible(true);
        });
    }
    private boolean deleteUser(String username) {
        if (userDatabase.containsKey(username)) {
            userDatabase.remove(username);
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "User not found.");
            return false;
        }
    }

}
