import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.Font;
public class SongbyId extends JFrame {

	private JPanel contentPane2;
	String pur_id,p_id,song_id;
	int song_price,total_cost;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SongbyId frame8 = new SongbyId("Breakthru",1,"null","null",1);
					frame8.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SongbyId(String s,int ch,String s2,String s3,int usr) {
		setTitle("Song Details");
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
		
		JButton btnBuy = new JButton("Buy Now");
		btnBuy.setBounds(374, 19, 100, 25);
		panel.add(btnBuy);
		
		JLabel lblartist = new JLabel("Artist");
		lblartist.setHorizontalAlignment(SwingConstants.CENTER);
		lblartist.setBounds(22, 63, 121, 35);
		panel.add(lblartist);
		lblartist.setFont(new Font("Corbel", Font.BOLD, 15));
		
		JLabel lblalbum = new JLabel("Album");
		lblalbum.setHorizontalAlignment(SwingConstants.CENTER);
		lblalbum.setBounds(22, 122, 121, 35);
		panel.add(lblalbum);
		lblalbum.setFont(new Font("Corbel", Font.BOLD, 15));
		
		JLabel lblgenre = new JLabel("Genre");
		lblgenre.setHorizontalAlignment(SwingConstants.CENTER);
		lblgenre.setBounds(22, 186, 121, 35);
		panel.add(lblgenre);
		lblgenre.setFont(new Font("Corbel", Font.BOLD, 15));
		
		JLabel lblprice = new JLabel("Price");
		lblprice.setHorizontalAlignment(SwingConstants.CENTER);
		lblprice.setBounds(22, 248, 121, 35);
		panel.add(lblprice);
		lblprice.setFont(new Font("Corbel", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(172, 63, 168, 35);
		panel.add(scrollPane);
		
		JLabel artl = new JLabel("");
		scrollPane.setViewportView(artl);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(172, 122, 168, 35);
		panel.add(scrollPane_1);
		
		JLabel albl = new JLabel("");
		scrollPane_1.setViewportView(albl);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(172, 186, 168, 35);
		panel.add(scrollPane_2);
		
		JLabel genl = new JLabel("");
		scrollPane_2.setViewportView(genl);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(172, 248, 168, 35);
		panel.add(scrollPane_3);
		
		JLabel pricel = new JLabel("");
		scrollPane_3.setViewportView(pricel);		
		
		JLabel lblSongDetails = new JLabel("");
		lblSongDetails.setFont(new Font("Corbel", Font.BOLD, 13));
		lblSongDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblSongDetails.setBounds(138, 11, 159, 41);
		panel.add(lblSongDetails);
		
        try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);
			
			lblSongDetails.setText(s);

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
						Songs frame3 = new Songs(usr);
						frame3.setVisible(true);
					}
					else if(ch==2){
						SongsbyArtist frame12 = new SongsbyArtist(s2,usr);
						frame12.setVisible(true);
					}
					else if(ch==3) {
						if(s3.equals("null")) {
							SongsbyAlbum frame14 = new SongsbyAlbum(s2,1,s3,usr);
							frame14.setVisible(true);
						}
						else {
							SongsbyAlbum frame14 = new SongsbyAlbum(s2,2,s3,usr);
							frame14.setVisible(true);
						}
						
					}
					else if(ch==4) {
						GenrebyId frame11 = new GenrebyId(s2,usr);
						frame11.setVisible(true);
					}	
					else if(ch==5) {
						Main frame2 = new Main(usr);
						frame2.setVisible(true);
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
					Class.forName("oracle.jdbc.driver.OracleDriver");					
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
					
					Statement stmt = con.createStatement(
						    ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY
						);

					ResultSet rs = stmt.executeQuery("SELECT * FROM purchase where user_id = '" + usr + "'");
					//doesn't exist
					if (!rs.next()) {
						
						ResultSet rs2 = stmt.executeQuery("INSERT INTO purchase(user_id,p_date,total_cost) VALUES(" + usr + ",sysdate,0)");						
						if(!rs2.next()) {
							System.out.println("System error");
						}
						//inserted into purchase
						else {
							ResultSet rs3 = stmt.executeQuery("SELECT song_id,s_price from song where s_name ='" + s + "'" );							
							if(!rs3.next()) {
								System.out.println("System error");
							}
							//song details found
							else {
								song_id = rs3.getString(1);
								song_price = rs3.getInt(2);		
								
								ResultSet rs4 = stmt.executeQuery("Select purchase_id from purchase where user_id='" + usr +"'");
								if(!rs4.next()) {
									System.out.println("System error");
								}
								else {
									pur_id = rs4.getString(1);
								}
								
								rs4 = stmt.executeQuery("Insert into purchased_item(purchase_id,song_id,i_price) values(" + pur_id + "," + song_id + "," + song_price + ")");
								if(!rs4.next()) {
									System.out.println("System error");
								}
								//inserted into purchased_item
								else {
									ResultSet rs5 = stmt.executeQuery("Select sum(i_price) from purchased_item where purchase_id='" + pur_id + "'");
									if(!rs5.next()) {
										System.out.println("System error");
									}
									else {
										total_cost = rs5.getInt(1);
										ResultSet rs6 = stmt.executeQuery("Update purchase set total_cost=" + total_cost + "where purchase_id='" + pur_id + "'");
										if(!rs6.next()) {
											System.out.println("System error");
										}
										else {
											System.out.println("Successfully added to cart");
											Cart frame7 = new Cart(usr);
											frame7.setVisible(true);
											dispose();
										}
									}
								}
							}
						}						
					} 
					//exists
					else {				
						ResultSet rs3 = stmt.executeQuery("SELECT song_id,s_price from song where s_name ='" + s + "'" );							
						if(!rs3.next()) {
							System.out.println("System error");
						}
						//found song details
						else {
							song_id = rs3.getString(1);
							song_price = rs3.getInt(2);		
							
							ResultSet rs4 = stmt.executeQuery("Select purchase_id from purchase where user_id='" + usr +"'");
							if(!rs4.next()) {
								System.out.println("System error");
							}
							//found purchase id
							else {
								pur_id = rs4.getString(1);
							}
							
							rs4 = stmt.executeQuery("Insert into purchased_item(purchase_id,song_id,i_price) values(" + pur_id + "," + song_id + "," + song_price + ")");
							if(!rs4.next()) {
								System.out.println("System error");
							}
							//inserted into purchased item
							else {
								ResultSet rs5 = stmt.executeQuery("Select sum(i_price) from purchased_item where purchase_id='" + pur_id + "'");
								if(!rs5.next()) {
									System.out.println("System error");
								}
								else {
									total_cost = rs5.getInt(1);
									ResultSet rs6 = stmt.executeQuery("Update purchase set total_cost=" + total_cost + "where purchase_id='" + pur_id + "'");
									if(!rs6.next()) {
										System.out.println("System error");
									}
									else {
										System.out.println("Successfully added to cart");
										Cart frame7 = new Cart(usr);
										frame7.setVisible(true);
										dispose();
									}
								}
								
							}							
						}
					}							
					
					con.close();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
