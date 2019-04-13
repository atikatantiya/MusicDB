import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class AlbumbyId extends JFrame {

	private JPanel contentPane2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 AlbumbyId frame10 = new AlbumbyId("album1");
						frame10.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlbumbyId(String s) {
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
		songs.setBounds(10, 11, 318, 35);
		panel.add(songs);
		
		JLabel albuml = new JLabel("");
		albuml.setBounds(10, 57, 318, 35);
		panel.add(albuml);
		
		JLabel artistl = new JLabel("");
		artistl.setBounds(10, 103, 318, 35);
		panel.add(artistl);
		
		JLabel releasel = new JLabel("");
		releasel.setBounds(10, 149, 318, 35);
		panel.add(releasel);
		
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
					Albums frame5 = new Albums();
					frame5.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
