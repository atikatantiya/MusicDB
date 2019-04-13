import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Artists extends JFrame {

	private JPanel contentPane2;
	private JTextField textField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Artists frame4 = new Artists();
					frame4.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Artists() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane2);
		contentPane2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 438, 261);
		contentPane2.add(panel);
		panel.setLayout(null);						

		JButton btnSearch = new JButton("Search");

		btnSearch.setBounds(338, 11, 90, 25);
		panel.add(btnSearch);
		
		textField = new JTextField();
		textField.setBounds(10, 13, 318, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(339, 227, 89, 23);
		panel.add(btnBack);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 227, 89, 23);
		panel.add(btnLogout);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 59, 318, 157);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("View More");
		lblNewLabel.setBounds(338, 88, 90, 83);
		panel.add(lblNewLabel);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {				
					Main frame2 = new Main();
					frame2.setVisible(true);
					dispose();					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Home frame = new Home();
					frame.setVisible(true);
					dispose();
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
}
