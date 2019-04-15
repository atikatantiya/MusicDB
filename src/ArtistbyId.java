import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

//select count(song_id) from song where album_id in (select album_id from album,artist where artist.artist_id = album.artist_id and art_name = 'artist1');
public class ArtistbyId extends JFrame {

	private JPanel contentPane2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArtistbyId frame9 = new ArtistbyId("artist1",1);
					frame9.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ArtistbyId(String s,int usr) {
		setTitle("Artist Details");
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
		
		JButton btnSongs = new JButton("List Songs");		
		btnSongs.setBounds(366, 125, 108, 23);
		panel.add(btnSongs);
		
		JButton btnAlbums = new JButton("List Albums");		
		btnAlbums.setBounds(366, 207, 108, 23);
		panel.add(btnAlbums);
		
		JLabel lblname = new JLabel("Name");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setBounds(10, 119, 108, 35);
		panel.add(lblname);
		lblname.setFont(new Font("Corbel", Font.BOLD, 15));
		
		JLabel lblbio = new JLabel("Bio");
		lblbio.setHorizontalAlignment(SwingConstants.CENTER);
		lblbio.setBounds(10, 186, 108, 35);
		panel.add(lblbio);
		lblbio.setFont(new Font("Corbel", Font.BOLD, 15));
		//namel.setForeground(Color.GRAY);
		
		JLabel lblArtistDetails = new JLabel("Artist Details");
		lblArtistDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistDetails.setBounds(141, 11, 159, 41);
		panel.add(lblArtistDetails);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(141, 186, 208, 61);
		panel.add(scrollPane);
		
		JLabel biol = new JLabel("");
		scrollPane.setViewportView(biol);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(140, 119, 209, 35);
		panel.add(scrollPane_1);
		
		JLabel namel = new JLabel("");
		scrollPane_1.setViewportView(namel);
		
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
					Artists frame4 = new Artists(usr);
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
					SongsbyArtist frame12 = new SongsbyArtist(s,usr);
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
					AlbumsbyArtist frame13 = new AlbumsbyArtist(s,usr);
					frame13.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
