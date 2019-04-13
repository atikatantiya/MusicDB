import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Songs extends JFrame {

	private JPanel contentPane2;
	private JTextField txtEnterSongTo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Songs frame3 = new Songs();
					frame3.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Songs() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		txtEnterSongTo = new JTextField();
		txtEnterSongTo.setText("Enter song to search for");
		txtEnterSongTo.setBounds(10, 13, 318, 20);
		panel.add(txtEnterSongTo);
		txtEnterSongTo.setColumns(10);
		
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
		
		JLabel lblClickOnThe = new JLabel("Click on the song");
		lblClickOnThe.setBounds(338, 88, 90, 14);
		panel.add(lblClickOnThe);
		
		JLabel lblToKnowMore = new JLabel("to know more");
		lblToKnowMore.setBounds(338, 105, 65, 14);
		panel.add(lblToKnowMore);
		
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
