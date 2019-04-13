import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
		setTitle("Artist Details");
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
		
		JButton btnSongs = new JButton("List Songs");		
		btnSongs.setBounds(326, 29, 102, 23);
		panel.add(btnSongs);
		
		JButton btnAlbums = new JButton("List Albums");		
		btnAlbums.setBounds(326, 118, 102, 23);
		panel.add(btnAlbums);
		
		JLabel lblExtra = new JLabel("");
		lblExtra.setBounds(10, 11, 290, 38);
		panel.add(lblExtra);
		
		JLabel lblname = new JLabel("Name");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setBounds(10, 68, 89, 38);
		panel.add(lblname);
		lblname.setFont(new Font("Courier", Font.BOLD, 14));
		
		JLabel lblbio = new JLabel("Bio");
		lblbio.setHorizontalAlignment(SwingConstants.CENTER);
		lblbio.setBounds(10, 128, 89, 64);
		panel.add(lblbio);
		lblbio.setFont(new Font("Courier", Font.BOLD, 14));
		
		JLabel biol = new JLabel("");
		biol.setBounds(133, 128, 167, 64);
		panel.add(biol);
		biol.setForeground(Color.GRAY);
		
		JLabel namel = new JLabel("");
		namel.setBounds(133, 68, 167, 38);
		panel.add(namel);
		namel.setForeground(Color.GRAY);
		
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("SELECT art_name,bio FROM artist where art_name = '" + s + "'");
			
			if (!rs.next()) {										
				System.out.println("System error");
			} else {				
				rs.beforeFirst();
				while (rs.next()) {	
					 namel.setText(rs.getString(1));
					 biol.setText(rs.getString(2));
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
					SongsbyArtist frame12 = new SongsbyArtist(s);
					frame12.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					AlbumsbyArtist frame13 = new AlbumsbyArtist(s);
					frame13.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
