
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Main extends JFrame {

	private JPanel contentPane2;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Main frame2 = new Main();
					//frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
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
		
		textField = new JTextField();
		textField.setBounds(10, 13, 318, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnCart = new JButton("Cart");
		btnCart.setBounds(339, 227, 89, 23);
		panel.add(btnCart);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 227, 89, 23);
		panel.add(btnLogout);
		
		JButton btnSongs = new JButton("Songs");
		btnSongs.setBounds(339, 59, 89, 23);
		panel.add(btnSongs);
		
		JButton btnArtists = new JButton("Artists");
		btnArtists.setBounds(339, 93, 89, 23);
		panel.add(btnArtists);
		
		JButton btnAlbums = new JButton("Albums");
		btnAlbums.setBounds(339, 127, 89, 23);
		panel.add(btnAlbums);
		
		JButton btnGenres = new JButton("Genres");
		btnGenres.setBounds(339, 168, 89, 23);
		panel.add(btnGenres);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 59, 318, 157);
		panel.add(label);
		
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
		
		btnSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Songs frame3 = new Songs();
					frame3.setVisible(true);
					dispose();
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		btnArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Artists frame4 = new Artists();
					frame4.setVisible(true);
					dispose();
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		btnAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Albums frame5 = new Albums();
					frame5.setVisible(true);
					dispose();
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		btnGenres.addActionListener(new ActionListener() {
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
