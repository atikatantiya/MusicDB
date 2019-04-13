import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Cart extends JFrame {

	private JPanel contentPane2;
	private JTextField txtEnterNameOf;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart frame7 = new Cart();
					frame7.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Cart() {
		setTitle("Cart");
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

		JButton btnDelete = new JButton("Delete from Cart");

		btnDelete.setBounds(315, 11, 113, 25);
		panel.add(btnDelete);
		
		JButton btnHome = new JButton("Continue Shopping");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHome.setBounds(305, 227, 123, 23);
		panel.add(btnHome);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 227, 89, 23);
		panel.add(btnLogout);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 59, 418, 157);
		panel.add(label);
		
		txtEnterNameOf = new JTextField();
		txtEnterNameOf.setText("Enter name of song");
		txtEnterNameOf.setBounds(10, 13, 295, 20);
		panel.add(txtEnterNameOf);
		txtEnterNameOf.setColumns(10);
		
		btnHome.addActionListener(new ActionListener() {
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
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
}
