import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class ArtistbyId extends JFrame {

	private JPanel contentPane2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArtistbyId frame9 = new ArtistbyId("artist1");
					frame9.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ArtistbyId(String s) {
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
		
		JLabel namel = new JLabel("");
		namel.setBounds(10, 32, 290, 50);
		panel.add(namel);
		
		JLabel biol = new JLabel("");
		biol.setBounds(10, 128, 290, 64);
		panel.add(biol);
		
		JButton btnSongs = new JButton("List Songs");
		btnSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSongs.setBounds(326, 29, 102, 23);
		panel.add(btnSongs);
		
		JButton btnAlbums = new JButton("List Albums");
		btnAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlbums.setBounds(326, 118, 102, 23);
		panel.add(btnAlbums);
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
					Artists frame4 = new Artists();
					frame4.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println(s);

	}
}
