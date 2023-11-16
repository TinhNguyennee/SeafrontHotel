package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class test extends JFrame {
    public test() {
        setTitle("Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.RED);
        panel1.setBounds(0, 0, 300, 300);
        layeredPane.add(panel1, JLayeredPane.DEFAULT_LAYER);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLUE);
        panel2.setBounds(29, 25, 221, 225);
        layeredPane.add(panel2, JLayeredPane.PALETTE_LAYER);
        panel1.setLayout(null);
        

        JButton button1 = new JButton("Button 1");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Chặn sự kiện click chuột trên button ở panel 2
                e.consume();
            }
        });
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("hello");
        	}
        });
        button1.setBounds(10, 66, 75, 23);
        panel1.add(button1);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(10, 100, 75, 22);
        panel1.add(comboBox);

        JButton button2 = new JButton("Button 2");
        button2.setBounds(10, 10, 80, 30);
        panel2.add(button2);

        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("hello");
        	}
        });


        setContentPane(layeredPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            test frame = new test();
            frame.setVisible(true);
        });
    }
}