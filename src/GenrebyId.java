import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

//SHOWS THE SONGS OF THE GENRE DIRECTLY.

public class GenrebyId extends JFrame {

	private JPanel contentPane2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 GenrebyId frame11 = new GenrebyId("genre1");
						frame11.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GenrebyId(String s) {
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
		
		JLabel songs = new JLabel("");
		songs.setBounds(10, 11, 318, 205);
		panel.add(songs);
		
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
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					Genres frame6 = new Genres();
					frame6.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
