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
					Main frame2 = new Main();
					frame2.setVisible(true);
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
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					

					// Get driver class
					Class.forName("oracle.jdbc.driver.OracleDriver");

					// step2 create the connection object
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM",
							"atika123");

					// create statement
					Statement stmt = con.createStatement(
						    ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY
						);

					ResultSet rs = stmt.executeQuery(
							"SELECT * FROM song");
					if (!rs.next()) {										
						System.out.println("Incorrect credientials");
					} else {
						// reset to first row
						rs.beforeFirst();
						while (rs.next()) {
							//System.out.println("Not empty");
							System.out.println(rs.getInt(1) + " " + rs.getString(2));
							// lblData.setText(rs.getString("phone_num") + " " + rs.getString ("name"));
						}
					}

					//con.commit();
					con.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
}
