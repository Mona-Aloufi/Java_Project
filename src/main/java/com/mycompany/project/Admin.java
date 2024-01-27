package com.mycompany.project;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Admin extends JFrame {

    private JLabel l1, l2;
    private JTextField t1;
    private JTextArea t2;
    private JButton b1, b2, b3, b4;
    private JMenuBar m;

    public Admin() {
        setTitle("Text Editor");
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1 = new JLabel("File");
        m = new JMenuBar();
        m.add(l1);
        l2 = new JLabel("Titel:");
        t1 = new JTextField(20);
        JPanel p = new JPanel();
        p.add(l2);
        p.add(t1);
        JPanel p1 = new JPanel(new GridLayout(2,1));
        p1.add(m);
        p1.add(p);
        
        
        t2 = new JTextArea();
        
        t2.setPreferredSize(new Dimension(300, 300));
        b1 = new JButton("Save");
        b2 = new JButton("Search");
        b3 = new JButton("Clear");
        b4 = new JButton("Statistics");
        JPanel p2 = new JPanel();
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        
        JPanel j = (JPanel) getContentPane();
        j.add(p1, BorderLayout.NORTH);
        j.add(t2, BorderLayout.CENTER);
        j.add(p2, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        b1.addActionListener(new ButtonListener());
        b4.addActionListener(new StatisticListener());
        b2.addActionListener(new ButtonListener());
        b3.addActionListener(new ButtonListener());

    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = t1.getText();
            String content = t2.getText();
            JButton bt = (JButton) e.getSource();
            try {
                if (bt == b1) {
                    // Create a PrintWriter to write to a file with the specified title
                    PrintWriter writer = new PrintWriter(new File(title + ".txt"));
                    writer.println(content); // Write the content to the file
                    writer.close();

                    // Show a success message to the user
                    JOptionPane.showMessageDialog(null, "File saved successfully!");
                } else if (bt == b3) {
                    t1.setText("");
                    t2.setText("");
                } else {
                    searchListener s = new searchListener("Search Page");
                }

            } catch (IOException E) {
                JOptionPane.showMessageDialog(null, " I/O Exception" + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public class searchListener extends JFrame implements ActionListener {
        private JLabel l1,l2;
        private JTextField t1,t2;
        private JTextArea ta;
        private JButton b1,b2,b3;
        
        public searchListener(String title){
            super(title);
            this.setLocation(400,400);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel p=(JPanel)this.getContentPane();
            
            l1 = new JLabel("File to search");
            t1= new JTextField(30);
            JPanel p1=new JPanel();
            p1.add(l1);
            p1.add(t1);
            
            l2=new JLabel("Keywords");
            t2=new JTextField(20);
            b1=new JButton("Search");
            b2=new JButton("Clear");
            b3=new JButton("cancel");
            JPanel p2=new JPanel();
            p2.add(l2);
            p2.add(t2);
            p2.add(b1);
            p2.add(b2);
            p2.add(b3);
            
            ta=new JTextArea(15,50);
            JPanel p3=new JPanel();
            p3.add(ta);
            
            
            p.add(p1,BorderLayout.NORTH);
            p.add(p2,BorderLayout.CENTER);
            p.add(p3,BorderLayout.SOUTH);
            
            this.setVisible(true);
            pack();
            
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
        }
        
        @Override
public void actionPerformed(ActionEvent e) {
    String file = t1.getText();
    String keyword, line;
    int c = 0, i;
    String words[] = null;
    keyword = t2.getText();
    JButton bt = (JButton) e.getSource();
    try {
        if (bt == b1) {
            BufferedReader sr = new BufferedReader(new FileReader(file + ".txt"));
            while ((line = sr.readLine()) != null) {
                words = line.split(" ");
                for (i = 0; i < words.length; i++) {
                    try {
                        int keywordInt = Integer.parseInt(keyword);
                        // If successful, compare as integers
                        if (keywordInt == Integer.parseInt(words[i])) {
                            ta.append(line + "\n");
                            break;
                        }
                    } catch (NumberFormatException ex) {
                        // If parsing as an integer fails, compare as strings
                        if (keyword.equals(words[i])) {
                            ta.append(line + "\n");
                            break;
                        }
                    }
                }
                if (ta.getText().isEmpty()) {
                   ta.setText("< No result! >");
            }
            }
            sr.close(); // Close the BufferedReader after use
        } else if (bt == b2) {
            t1.setText("");
            t2.setText("");
            ta.setText("");
            t1.requestFocus();
            t2.requestFocus();
            ta.requestFocus();
        } else {
            dispose();
        }
    } catch (FileNotFoundException o) {
        JOptionPane.showMessageDialog(null, "File " + file + " is not found!");
    } catch (IOException m) {
        m.printStackTrace();
    }
}
    }

    
    
    public class StatisticListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Statistics s = new Statistics();
        }
    }
    }


