import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Songs extends JFrame {

	private JPanel contentPane2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Songs frame2 = new Songs();
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
	public Songs() {
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

				

		JButton btnSearch = new JButton("Search for songs");

		btnSearch.setBounds(154, 171, 117, 25);
		panel.add(btnSearch);

		

		JLabel lblData = new JLabel("");
		lblData.setBounds(79, 214, 297, 25);
		panel.add(lblData);
		
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
