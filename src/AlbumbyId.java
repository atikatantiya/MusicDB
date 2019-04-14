import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
					 AlbumbyId frame10 = new AlbumbyId("album1",1,"null",1);
						frame10.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlbumbyId(String s,int ch,String s2,int usr) {
		setTitle("Album Details");
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
		
		JLabel lblname = new JLabel("Name");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setBounds(10, 27, 121, 35);
		panel.add(lblname);
		lblname.setFont(new Font("Courier", Font.BOLD, 14));
		
		JLabel lblartist = new JLabel("Artist");
		lblartist.setHorizontalAlignment(SwingConstants.CENTER);
		lblartist.setBounds(10, 89, 121, 35);
		panel.add(lblartist);
		lblartist.setFont(new Font("Courier", Font.BOLD, 14));
		
		JLabel lblrelease = new JLabel("Release Date");
		lblrelease.setHorizontalAlignment(SwingConstants.CENTER);
		lblrelease.setBounds(10, 149, 121, 35);
		panel.add(lblrelease);
		lblrelease.setFont(new Font("Courier", Font.BOLD, 14));
		
		JLabel namel = new JLabel("");
		namel.setBounds(155, 27, 134, 35);
		panel.add(namel);
		namel.setForeground(Color.GRAY);
		
		JLabel artistl = new JLabel("");
		artistl.setBounds(155, 89, 134, 35);
		panel.add(artistl);
		artistl.setForeground(Color.GRAY);
		
		JLabel releasel = new JLabel("");
		releasel.setBounds(155, 149, 134, 35);
		panel.add(releasel);
		releasel.setForeground(Color.GRAY);
		
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("SELECT alb_name,release_date FROM album where alb_name = '" + s + "'");
			
			if (!rs.next()) {										
				System.out.println("System error");
			} else {				
				rs.beforeFirst();
				while (rs.next()) {	
					 namel.setText(rs.getString(1));
					 releasel.setText(String.valueOf(rs.getDate(2)));
				}
			}		
			
			rs = stmt.executeQuery("SELECT art_name FROM album,artist where alb_name = '" + s + "' and album.artist_id = artist.artist_id");
			if (!rs.next()) {										
				System.out.println("System error");
			} else {				
				rs.beforeFirst();
				while (rs.next()) {	
					 artistl.setText(rs.getString(1));
				}
			}
			
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		JButton btnViewSongs = new JButton("View Songs");
		btnViewSongs.setBounds(322, 89, 106, 23);
		panel.add(btnViewSongs);
		
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
					if(ch==1) {
						Albums frame5 = new Albums(usr);
						frame5.setVisible(true);
					}
					else if(ch==2) {
						AlbumsbyArtist frame13 = new AlbumsbyArtist(s2,usr);
						frame13.setVisible(true);
					}
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnViewSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					SongsbyAlbum frame14 = new SongsbyAlbum(s,ch,s2,usr);
					frame14.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
