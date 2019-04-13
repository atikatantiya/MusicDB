import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
public class SongbyId extends JFrame {

	private JPanel contentPane2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SongbyId frame8 = new SongbyId("song1",1,"null","null");
					frame8.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SongbyId(String s,int ch,String s2,String s3) {
		setTitle("Song Details");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane2);
		contentPane2.setLayout(null);
		
		//System.out.println(s);

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
		
		JButton btnBuy = new JButton("Buy Now");
		btnBuy.setBounds(339, 11, 89, 23);
		panel.add(btnBuy);
		
		JLabel lblartist = new JLabel("Artist");
		lblartist.setHorizontalAlignment(SwingConstants.CENTER);
		lblartist.setBounds(23, 32, 100, 35);
		panel.add(lblartist);
		lblartist.setFont(new Font("Courier", Font.BOLD, 14));
		
		JLabel lblalbum = new JLabel("Album");
		lblalbum.setHorizontalAlignment(SwingConstants.CENTER);
		lblalbum.setBounds(23, 78, 100, 35);
		panel.add(lblalbum);
		lblalbum.setFont(new Font("Courier", Font.BOLD, 14));
		
		JLabel lblgenre = new JLabel("Genre");
		lblgenre.setHorizontalAlignment(SwingConstants.CENTER);
		lblgenre.setBounds(23, 123, 100, 35);
		panel.add(lblgenre);
		lblgenre.setFont(new Font("Courier", Font.BOLD, 14));
		
		JLabel lblprice = new JLabel("Price");
		lblprice.setHorizontalAlignment(SwingConstants.CENTER);
		lblprice.setBounds(23, 169, 100, 35);
		panel.add(lblprice);
		lblprice.setFont(new Font("Courier", Font.BOLD, 14));		
		
		JLabel pricel = new JLabel("");
		pricel.setHorizontalAlignment(SwingConstants.CENTER);
		pricel.setBounds(166, 169, 116, 35);
		panel.add(pricel);
		pricel.setForeground(Color.GRAY);
		
		JLabel genl = new JLabel("");
		genl.setHorizontalAlignment(SwingConstants.CENTER);
		genl.setBounds(166, 123, 116, 35);
		panel.add(genl);
		genl.setForeground(Color.GRAY);
		
		JLabel albl = new JLabel("");
		albl.setHorizontalAlignment(SwingConstants.CENTER);
		albl.setBounds(166, 78, 116, 35);
		panel.add(albl);
		albl.setForeground(Color.GRAY);
		
		JLabel artl = new JLabel("");
		artl.setHorizontalAlignment(SwingConstants.CENTER);
		artl.setBounds(166, 32, 116, 35);
		panel.add(artl);
		artl.setForeground(Color.GRAY);
		
		JLabel lblName = new JLabel("");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(48, 0, 281, 23);
		panel.add(lblName);
		lblName.setFont(new Font("Courier", Font.BOLD, 14));
		lblName.setText(s);
		
        try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("SELECT s_price FROM song where s_name = '" + s + "'");
			
			if (!rs.next()) {										
				System.out.println("System error");
			} else {				
				rs.beforeFirst();
				while (rs.next()) {	
					 pricel.setText(String.valueOf(rs.getInt(1)));
				}
			}		
			
			rs = stmt.executeQuery("SELECT alb_name FROM album,song where s_name = '" + s + "' and album.album_id = song.album_id");
			if (!rs.next()) {										
				System.out.println("System error");
			} else {				
				rs.beforeFirst();
				while (rs.next()) {	
					 albl.setText(rs.getString(1));
				}
			}
			
			rs = stmt.executeQuery("SELECT g_name FROM genre,song where s_name = '" + s + "' and genre.genre_id = song.genre_id");
			if (!rs.next()) {										
				System.out.println("System error");
			} else {				
				rs.beforeFirst();
				while (rs.next()) {	
					 genl.setText(rs.getString(1));
				}
			}
			
			rs = stmt.executeQuery("SELECT art_name FROM artist,album,song where s_name = '" + s + "' and album.album_id = song.album_id and album.artist_id = artist.artist_id");
			if (!rs.next()) {										
				System.out.println("System error");
			} else {				
				rs.beforeFirst();
				while (rs.next()) {	
					 artl.setText(rs.getString(1));
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
						Songs frame3 = new Songs();
						frame3.setVisible(true);
					}
					else if(ch==2){
						SongsbyArtist frame12 = new SongsbyArtist(s2);
						frame12.setVisible(true);
					}
					else if(ch==3) {
						if(s3.equals("null")) {
							SongsbyAlbum frame14 = new SongsbyAlbum(s2,1,s3);
							frame14.setVisible(true);
						}
						else {
							SongsbyAlbum frame14 = new SongsbyAlbum(s2,2,s3);
							frame14.setVisible(true);
						}
						
					}
					else if(ch==4) {
						GenrebyId frame11 = new GenrebyId(s2);
						frame11.setVisible(true);
					}					
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
