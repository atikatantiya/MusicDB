import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Cart extends JFrame {

	private JPanel contentPane2;
	private JTextField txtSong;
	
	String pur_id,pur_id2,addCart,pitem_id;
	int total_cost;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart frame7 = new Cart(1);
					frame7.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Cart(int usr) {
		setTitle("Cart");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane2);
		contentPane2.setLayout(null);

		JPanel panel = new JPanel();
		setBounds(450, 150, 500, 495);
		panel.setBounds(0, 0, 484, 456);
		contentPane2.add(panel);
		panel.setLayout(null);				
		
		JLabel lblTotal = new JLabel("Total Cost");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(344, 104, 130, 25);
		panel.add(lblTotal);
		lblTotal.setFont(new Font("Corbel", Font.BOLD, 15));
		
		JLabel costl = new JLabel("");
		costl.setHorizontalAlignment(SwingConstants.CENTER);
		costl.setBounds(354, 140, 113, 25);
		panel.add(costl);
		//costl.setForeground(Color.GRAY);

		JButton btnDelete = new JButton("Delete from Cart");

		btnDelete.setBounds(344, 11, 130, 25);
		panel.add(btnDelete);
		
		JButton btnHome = new JButton("Continue Shopping");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHome.setBounds(331, 422, 143, 23);
		panel.add(btnHome);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 422, 89, 23);
		panel.add(btnLogout);
		
		txtSong = new JTextField();
		txtSong.setText("Enter name of song");
		txtSong.setBounds(10, 13, 324, 20);
		panel.add(txtSong);
		txtSong.setColumns(10);
		
		DefaultListModel<String> cartlist = new DefaultListModel<>();
		DefaultListModel<String> pricelist = new DefaultListModel<>();
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("SELECT purchase_id FROM purchase where user_id='" + usr + "'");
			if (!rs.next()) {										
				cartlist.addElement("No items in the cart");
				total_cost = 0;
			}
			else {
				pur_id = rs.getString(1);
				
				ResultSet rs5 = stmt.executeQuery("Select sum(i_price) from purchased_item where purchase_id='" + pur_id + "'");
				if(!rs5.next()) {
					System.out.println("System error");
				}
				else {
					total_cost = rs5.getInt(1);
				}
				ResultSet rs2 = stmt.executeQuery("select song.s_name,purchased_item.i_price from song,purchased_item where song.song_id = purchased_item.song_id and purchased_item.purchase_id ='" + pur_id + "'");
				if (!rs2.next()) {										
					System.out.println("System error");
				} 
				else {
					rs2.beforeFirst();
					while (rs2.next()) {	
						addCart = rs2.getString(1) ;
						cartlist.addElement(rs2.getString(1));
						pricelist.addElement(String.valueOf(rs2.getInt(2)));
					}				
				}		
			}						
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
		 
        JList<String> list = new JList<>(cartlist);
        list.setBounds(10, 45, 274, 320);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list);
        
        JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 45, 274, 320);
		panel.add(scrollPane);	
		costl.setText(String.valueOf(total_cost));
		
		JList<String> list2 = new JList<>(pricelist);
        list2.setBounds(282, 45, 50, 320);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list2);
        
        JScrollPane scrollPane2 = new JScrollPane(list2);
		scrollPane2.setBounds(282, 45, 50, 320);
		panel.add(scrollPane2);	
		
		JLabel lblData = new JLabel("");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setBounds(344, 252, 130, 36);
		panel.add(lblData);
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {				
					Main frame2 = new Main(usr);
					frame2.setVisible(true);
					dispose();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
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
		
		
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sname = txtSong.getText();			
					
					Class.forName("oracle.jdbc.driver.OracleDriver");					
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
					
					Statement stmt = con.createStatement(
						    ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY
						);

					ResultSet rs = stmt.executeQuery("SELECT purchase_id FROM purchase where user_id='" + usr + "'");
					DefaultListModel<String> cartlist = new DefaultListModel<>();
					if (!rs.next()) {	
						System.out.println("Invalid Choice");
						lblData.setText("Invalid Choice");
					} 
					else {						
						pur_id2 = rs.getString(1);
						ResultSet rs2 = stmt.executeQuery("Select p_id,i_price from purchased_item,song where purchased_item.song_id = song.song_id and song.s_name='" + sname + "'");
						if(!rs2.next()) {
							System.out.println("Invalid Choice!");
							lblData.setText("Invalid Choice");
						}
						else {
							pitem_id = rs2.getString(1);
							ResultSet rs5 = stmt.executeQuery("Select sum(i_price) from purchased_item where purchase_id='" + pur_id2 + "'");
							if(!rs5.next()) {
								System.out.println("System error");
								lblData.setText("System error");
							}
							else {
								total_cost = rs5.getInt(1);
								ResultSet rs6 = stmt.executeQuery("Update purchase set total_cost=" + total_cost + "where purchase_id='" + pur_id2 + "'");
								if(!rs6.next()) {
									System.out.println("System error");	
									lblData.setText("System error");
								}
								else {
									ResultSet rs3 = stmt.executeQuery("Delete from purchased_item where p_id='" + pitem_id + "'");
									if(!rs3.next()) {
										cartlist.addElement("System error");
										lblData.setText("System error");
									}
									else {
										System.out.println("Deleted");
										lblData.setText("Deleted");
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
