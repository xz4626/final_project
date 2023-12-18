package xingchen.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

import java.sql.*;

public class Start {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });
    }
    private static void initializeDatabase() {
        String url = "jdbc:sqlite:final project/userandpassword.sqlite";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    String sql = "CREATE TABLE IF NOT EXISTS users (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "username TEXT NOT NULL UNIQUE," +
                            "password TEXT NOT NULL)";
                    stmt.execute(sql);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static class LoginWindow extends JFrame {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton loginButton;
        private JButton registerButton;

        public LoginWindow() {
            setTitle("登录");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            usernameField = new JTextField();
            passwordField = new JPasswordField();
            loginButton = new JButton("登录");
            registerButton = new JButton("注册");

            add(usernameField);
            add(passwordField);
            add(loginButton);
            add(registerButton);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    // 实现登录逻辑
                    // 如果登录成功:
                    dispose();
                    startGame();
                }
            });

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RegisterWindow registerWindow = new RegisterWindow();
                    registerWindow.setVisible(true);
                }
            });
        }

        private void startGame() {
            try {
                new YangLeGeYang(2).setVisible(true);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    private static class RegisterWindow extends JFrame {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton registerButton;

        public RegisterWindow() {
            setTitle("注册");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            usernameField = new JTextField();
            passwordField = new JPasswordField();
            registerButton = new JButton("注册");

            add(usernameField);
            add(passwordField);
            add(registerButton);

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    // 实现注册逻辑
                    // 例如，将用户名和密码存储到数据库
                    registerUser(username, password);
                }
            });
        }

        private void registerUser(String username, String password) {
            String url = "jdbc:sqlite:final project/userandpassword.sqlite";
            String sql = "INSERT INTO users(username, password) VALUES(?,?)";

            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "注册失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
