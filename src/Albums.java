import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class Albums extends JFrame {

	private JPanel contentPane2;
	private JTextField txtSearch;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Albums frame5 = new Albums(1);
					frame5.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Albums(int usr) {
		setTitle("Albums");
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
		
		txtSearch = new JTextField();
		txtSearch.setText("Enter album to search for");
		txtSearch.setBounds(10, 13, 364, 20);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(384, 420, 90, 25);
		panel.add(btnBack);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 420, 83, 25);
		panel.add(btnLogout);
		
		/*JLabel label = new JLabel("");
		label.setBounds(10, 59, 318, 157);
		panel.add(label);*/
		
		JLabel lblClickOnA = new JLabel("Click on an album to know more");
		lblClickOnA.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickOnA.setBounds(10, 356, 364, 25);
		panel.add(lblClickOnA);
		
		DefaultListModel<String> alblist = new DefaultListModel<>(); 
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("SELECT alb_name FROM album");
			
			if (!rs.next()) {										
				System.out.println("System error");
			} else {
				
				rs.beforeFirst();
				while (rs.next()) {	
					alblist.addElement(rs.getString(1)); 
				}
			}					
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
		 
        JList<String> list = new JList<>(alblist);
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
		            AlbumbyId frame10 = new AlbumbyId(o.toString(),1,"null",usr);
					frame10.setVisible(true);
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
					String search = txtSearch.getText();					
					searchlist.clear();
					Class.forName("oracle.jdbc.driver.OracleDriver");					
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
					
					Statement stmt = con.createStatement(
						    ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY
						);

					ResultSet rs = stmt.executeQuery("SELECT alb_name FROM album where alb_name = '" + search + "'");
					
					if (!rs.next()) {										
						System.out.println("Album not found");
						searchlist.addElement("Album not found");
						
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
						            AlbumbyId frame10 = new AlbumbyId(o.toString(),1,"null",usr);
									frame10.setVisible(true);
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
