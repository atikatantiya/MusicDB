import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Artists extends JFrame {

	private JPanel contentPane2;
	private JTextField txtArtist;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Artists frame4 = new Artists(1);
					frame4.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Artists(int usr) {
		setTitle("Artists");
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

		JButton btnSearch = new JButton("Search");

		btnSearch.setBounds(384, 11, 90, 25);
		panel.add(btnSearch);
		
		txtArtist = new JTextField();
		txtArtist.setText("Enter artist to search for");
		txtArtist.setBounds(10, 13, 364, 20);
		panel.add(txtArtist);
		txtArtist.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(384, 420, 90, 25);
		panel.add(btnBack);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 420, 83, 25);
		panel.add(btnLogout);
		
		/*JLabel label = new JLabel("");
		label.setBounds(10, 59, 318, 157);
		panel.add(label);*/
		
		JLabel lblClickOnA = new JLabel("Click on an artist to know more");
		lblClickOnA.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickOnA.setBounds(10, 356, 364, 25);
		panel.add(lblClickOnA);
		
		DefaultListModel<String> artlist = new DefaultListModel<>(); 
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("SELECT art_name FROM artist");
			
			if (!rs.next()) {										
				System.out.println("System error");
			} else {
				
				rs.beforeFirst();
				while (rs.next()) {	
					artlist.addElement(rs.getString(1)); 
				}
			}					
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
		 
        JList<String> list = new JList<>(artlist);
        list.setBounds(10, 46, 364, 300);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list);
		
		MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList<?> theList = (JList<?>) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		            Object o = theList.getModel().getElementAt(index);
		            ArtistbyId frame9 = new ArtistbyId(o.toString(),usr);
					frame9.setVisible(true);
					dispose();
		          }
		        }
		      }
		    };
		list.addMouseListener(mouseListener);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 45, 364, 300);
		panel.add(scrollPane);
		
		btnBack.addActionListener(new ActionListener() {
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
		
		DefaultListModel<String> searchlist = new DefaultListModel<>();
		JList<String> list2 = new JList<>(searchlist);
		list2.setBounds(10, 46, 364, 300);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list2);
		list2.setVisible(false);
		
		JScrollPane scrollPane2 = new JScrollPane(list2);
		scrollPane2.setBounds(10, 45, 364, 300);
		panel.add(scrollPane2);
		scrollPane2.setVisible(false);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String search = txtArtist.getText();					
					searchlist.clear();
					Class.forName("oracle.jdbc.driver.OracleDriver");					
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
					
					Statement stmt = con.createStatement(
						    ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY
						);

					ResultSet rs = stmt.executeQuery("SELECT art_name FROM artist where art_name = '" + search + "'");
					
					if (!rs.next()) {										
						System.out.println("Artist not found");
						searchlist.addElement("Artist not found");
						
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
						            ArtistbyId frame9 = new ArtistbyId(o.toString(),usr);
									frame9.setVisible(true);
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
	}
}
