package com.mycompany.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Project extends JFrame {

    private JButton b1;
    private JButton b2;
    private JLabel l1;
    private JLabel l2;
    private JPasswordField pass1;
    private JTextField t1;

    public Project(String title) {
        super(title);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Login");
        l2 = new JLabel("Password");
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,1));
        p1.add(l1);
        p1.add(l2);

        t1 = new JTextField(15);
        pass1 = new JPasswordField(15);
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2,1));
        p2.add(t1);
        p2.add(pass1);

        b1 = new JButton("Enter");
        b2 = new JButton("Cancel");
        JPanel p3 = new JPanel();
        p3.add(b1);
        p3.add(b2);

        JPanel c = (JPanel) this.getContentPane();
        c.add(p1, BorderLayout.WEST);
        c.add(p2, BorderLayout.EAST);
        c.add(p3, BorderLayout.SOUTH);
        b1.addActionListener(new EnterAcction());
        b2.addActionListener(new cancelAction());
        //this.pack();
        setSize(300,110);
        this.setVisible(true);
    }

    class EnterAcction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection c = DriverManager.getConnection("jdbc:ucanaccess://User_Database1.accdb");
                if (c == null) {
                    System.out.println("No Connection");
                } else {
                    System.out.println("Connection is Ok");
                }
                String login = t1.getText();
                String password = new String(pass1.getPassword());
                String query = "SELECT * FROM Users_DataBase ";
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                boolean matchFound = false;
                while (rs.next()) {
                    if (login.equals(rs.getString(2)) && password.equals(rs.getString(3))) {
                        matchFound = true;
                        break;
                    }

                }
                 if(matchFound){
                    switch (rs.getString(4)) {
                        case "Admin":
                            Admin admin = new Admin();
                            break;
                        case "Simple User":
                            User user = new User();
                            break;
                    }
                    
                }
                 else if (!matchFound) {
                    JOptionPane.showMessageDialog(null, "Check your login and password", "Input Error",
                            JOptionPane.ERROR_MESSAGE);

                }
               
                rs.close();
                stmt.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Exeception : SQL");
                ex.printStackTrace();
            }
        }
    }
    class cancelAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            t1.setText("");
            pass1.setText("");
        }
    }

    public static void main(String[] args) {
        Project c = new Project("login page");
    }
}
