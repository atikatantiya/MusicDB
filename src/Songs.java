import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class Songs extends JFrame {

	private JPanel contentPane2;
	private JTextField txtSong;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Songs frame3 = new Songs();
					frame3.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Songs() {
		setTitle("Songs");
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

		JButton btnSearch = new JButton("Search");

		btnSearch.setBounds(338, 11, 90, 25);
		panel.add(btnSearch);
		
		txtSong = new JTextField();
		txtSong.setText("Enter song to search for");
		txtSong.setBounds(10, 13, 318, 20);
		panel.add(txtSong);
		txtSong.setColumns(10);
		
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
		
		JLabel lblClick = new JLabel("  Click on the ");
		lblClick.setBounds(338, 88, 90, 14);
		panel.add(lblClick);		
		
		JLabel lblSong = new JLabel("  song to ");
		lblSong.setBounds(338, 105, 65, 14);
		panel.add(lblSong);	
		
		JLabel lblKnowMore = new JLabel("  know more");
		lblKnowMore.setBounds(338, 124, 90, 14);
		panel.add(lblKnowMore);
		
		DefaultListModel<String> songlist = new DefaultListModel<>(); 
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("SELECT s_name FROM song");
			
			if (!rs.next()) {										
				System.out.println("System error");
			} else {
				
				rs.beforeFirst();
				while (rs.next()) {	
					songlist.addElement(rs.getString(1)); 
				}
			}					
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
		 
        JList<String> list = new JList<>(songlist);
        list.setBounds(10, 46, 318, 162);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list);
		
		MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList<?> theList = (JList<?>) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		            Object o = theList.getModel().getElementAt(index);
		            SongbyId frame8 = new SongbyId(o.toString(),1,"null","null");
					frame8.setVisible(true);
					dispose();
		          }
		        }
		      }
		    };
		list.addMouseListener(mouseListener);
        
        JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 45, 318, 168);
		panel.add(scrollPane);				
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {				
					Main frame2 = new Main();
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
		
		DefaultListModel<String> searchlist = new DefaultListModel<>();
		JList<String> list2 = new JList<>(searchlist);
        list2.setBounds(10, 46, 318, 162);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list2);
		list2.setVisible(false);
		
		JScrollPane scrollPane2 = new JScrollPane(list2);
		scrollPane2.setBounds(10, 45, 318, 168);
		panel.add(scrollPane2);
		scrollPane2.setVisible(false);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String search = txtSong.getText();					
					searchlist.clear();
					Class.forName("oracle.jdbc.driver.OracleDriver");					
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
					
					Statement stmt = con.createStatement(
						    ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY
						);

					ResultSet rs = stmt.executeQuery("SELECT s_name FROM song where s_name = '" + search + "'");
					
					if (!rs.next()) {										
						System.out.println("Song not found");
						searchlist.addElement("Not found");
						
						list2.setVisible(true);
						list.setVisible(false);
						scrollPane.setVisible(false);
						scrollPane2.setVisible(true);
					} 
					else {						
						rs.beforeFirst();
						while (rs.next()) {								
							System.out.println("Found: " + rs.getString(1));
							searchlist.addElement(rs.getString(1));
						}	
						
						list2.setVisible(true);
						list.setVisible(false);
						
						MouseListener mouseListener2 = new MouseAdapter() {
						      public void mouseClicked(MouseEvent mouseEvent) {
						        JList<?> theList2 = (JList<?>) mouseEvent.getSource();
						        if (mouseEvent.getClickCount() == 2) {
						          int index = theList2.locationToIndex(mouseEvent.getPoint());
						          if (index >= 0) {
						            Object o = theList2.getModel().getElementAt(index);
						            SongbyId frame8 = new SongbyId(o.toString(),1,"null","null");
									frame8.setVisible(true);
									dispose();
						          }
						        }
						      }
						    };
						list2.addMouseListener(mouseListener2);				        
				        
						scrollPane.setVisible(false);
						scrollPane2.setVisible(true);						
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
