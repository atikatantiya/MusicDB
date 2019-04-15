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
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane2);
		contentPane2.setLayout(null);

		JPanel panel = new JPanel();
		setBounds(450, 150, 500, 420);
		panel.setBounds(0, 0, 484, 381);
		contentPane2.add(panel);
		panel.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(384, 343, 90, 25);
		panel.add(btnBack);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 343, 83, 25);
		panel.add(btnLogout);
		
		JLabel lblname = new JLabel("Name");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setBounds(22, 63, 121, 35);
		panel.add(lblname);
		lblname.setFont(new Font("Corbel", Font.BOLD, 15));
		
		JLabel lblartist = new JLabel("Artist");
		lblartist.setHorizontalAlignment(SwingConstants.CENTER);
		lblartist.setBounds(22, 122, 121, 35);
		panel.add(lblartist);
		lblartist.setFont(new Font("Corbel", Font.BOLD, 15));
		
		JLabel lblrelease = new JLabel("Release Date");
		lblrelease.setHorizontalAlignment(SwingConstants.CENTER);
		lblrelease.setBounds(22, 186, 121, 35);
		panel.add(lblrelease);
		lblrelease.setFont(new Font("Corbel", Font.BOLD, 15));
		//releasel.setForeground(Color.GRAY);
		
		JButton btnViewSongs = new JButton("View Songs");
		btnViewSongs.setBounds(366, 142, 108, 25);
		panel.add(btnViewSongs);
		
		JLabel lblSongs = new JLabel("Number of Songs ");
		lblSongs.setHorizontalAlignment(SwingConstants.CENTER);
		lblSongs.setFont(new Font("Corbel", Font.BOLD, 15));
		lblSongs.setBounds(22, 248, 121, 35);
		panel.add(lblSongs);
		
		JLabel lblAlbumDetails = new JLabel("Album Details");
		lblAlbumDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbumDetails.setBounds(150, 7, 159, 41);
		panel.add(lblAlbumDetails);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(172, 63, 168, 35);
		panel.add(scrollPane);
		
		JLabel namel = new JLabel("");
		scrollPane.setViewportView(namel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(172, 122, 168, 35);
		panel.add(scrollPane_1);
		
		JLabel artistl = new JLabel("");
		scrollPane_1.setViewportView(artistl);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(172, 186, 168, 35);
		panel.add(scrollPane_2);
		
		JLabel releasel = new JLabel("");
		scrollPane_2.setViewportView(releasel);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(172, 248, 168, 35);
		panel.add(scrollPane_3);
		
		JLabel songsl = new JLabel("");
		scrollPane_3.setViewportView(songsl);
		
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
			
			rs = stmt.executeQuery("SELECT art_name FROM album,artist where alb_name = '" + s + "' and album.artist_id = artist.artist_id");
			if (!rs.next()) {										
				System.out.println("System error");
			} else {				
				rs.beforeFirst();
				while (rs.next()) {	
					 artistl.setText(rs.getString(1));
				}
			}
			
			rs = stmt.executeQuery("select count(song_id) from song where album_id = (select album_id from album where alb_name ='" + s + "')");
			if (!rs.next()) {										
				System.out.println("System error");
			} else {				
				rs.beforeFirst();
				while (rs.next()) {	
					 songsl.setText(String.valueOf(rs.getInt(1)));
				}
			}
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		
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
