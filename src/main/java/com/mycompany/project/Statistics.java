
package com.mycompany.project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Statistics extends JFrame {

    private JLabel L1;
    private JTextField T1;
    private JButton B1, B2, B3;

    public Statistics() {
        setTitle("Statistics");
        setLocation(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        L1 = new JLabel("File for Statistics");
        T1 = new JTextField(20);
        B1 = new JButton("Calculate");
        B2 = new JButton("Clear");
        B3 = new JButton("Cancel");
        JPanel P = (JPanel) this.getContentPane();
        P.setLayout(new FlowLayout(FlowLayout.LEFT));
        P.add(L1);
        P.add(T1);
        P.add(B1);
        P.add(B2);
        P.add(B3);
        pack();
        setVisible(true);

        B1.addActionListener(new CalculateListener());
        B2.addActionListener(new ClearListener());
        B3.addActionListener(new CancelListener());

    }//constructer end

    public class CalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String filename = T1.getText();//
            String line, arr[];
            String name, degree;
            int linenumberGET = 0;//linenumber Grater than or equal 10
            int linenumberLT = 0;//linenumber less than 10
            //int linenumber=0;
            //int degree;
            try {
                BufferedReader br = new BufferedReader(new FileReader(filename + ".txt"));
                while ((line = br.readLine()) != null) {
                    arr = line.split(" ");
                    name = arr[0];
                    degree = arr[1];
                    int number = Integer.parseInt(degree);

                    if (number >= 10) {
                        linenumberGET++;
                    } else if (number < 10) {
                        linenumberLT++;
                    }
                }//while end

                JOptionPane.showMessageDialog(null, "Number of lines with numbers>=" + "10:" + linenumberGET
                        + "\nNumber of lines with numbers<" + "10:" + linenumberLT, "Statistics", JOptionPane.INFORMATION_MESSAGE);

                br.close();

            }//try end 
            catch (FileNotFoundException E) {
                JOptionPane.showMessageDialog(null, "The file " + filename + " is not found", "Message", JOptionPane.ERROR_MESSAGE);
                //System.out.println("The file" +filename+"is not found");
            } catch (IOException E) {
                System.out.println("Exception of E/S" + E.getMessage());
            }
        }
    }

    public class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            T1.setText("");
        }
    }

    public class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

}//end Statistic class
