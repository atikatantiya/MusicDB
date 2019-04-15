import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.plaf.FontUIResource;
public class Main extends JFrame {

	private JPanel contentPane2;
	private JTextField txtEnter;
	
	private static void setFont(FontUIResource myFont) {
	    UIManager.put("CheckBoxMenuItem.acceleratorFont", myFont);
	    UIManager.put("Button.font", myFont);
	    UIManager.put("ToggleButton.font", myFont);
	    UIManager.put("RadioButton.font", myFont);
	    UIManager.put("CheckBox.font", myFont);
	    UIManager.put("ColorChooser.font", myFont);
	    UIManager.put("ComboBox.font", myFont);
	    UIManager.put("Label.font", myFont);
	    UIManager.put("List.font", myFont);
	    UIManager.put("MenuBar.font", myFont);
	    UIManager.put("Menu.acceleratorFont", myFont);
	    UIManager.put("RadioButtonMenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.font", myFont);
	    UIManager.put("RadioButtonMenuItem.font", myFont);
	    UIManager.put("CheckBoxMenuItem.font", myFont);
	    UIManager.put("OptionPane.buttonFont", myFont);
	    UIManager.put("OptionPane.messageFont", myFont);
	    UIManager.put("Menu.font", myFont);
	    UIManager.put("PopupMenu.font", myFont);
	    UIManager.put("OptionPane.font", myFont);
	    UIManager.put("Panel.font", myFont);
	    UIManager.put("ProgressBar.font", myFont);
	    UIManager.put("ScrollPane.font", myFont);
	    UIManager.put("Viewport.font", myFont);
	    UIManager.put("TabbedPane.font", myFont);
	    UIManager.put("Slider.font", myFont);
	    UIManager.put("Table.font", myFont);
	    UIManager.put("TableHeader.font", myFont);
	    UIManager.put("TextField.font", myFont);
	    UIManager.put("Spinner.font", myFont);
	    UIManager.put("PasswordField.font", myFont);
	    UIManager.put("TextArea.font", myFont);
	    UIManager.put("TextPane.font", myFont);
	    UIManager.put("EditorPane.font", myFont);
	    UIManager.put("TabbedPane.smallFont", myFont);
	    UIManager.put("TitledBorder.font", myFont);
	    UIManager.put("ToolBar.font", myFont);
	    UIManager.put("ToolTip.font", myFont);
	    UIManager.put("Tree.font", myFont);
	    UIManager.put("FormattedTextField.font", myFont);
	    UIManager.put("IconButton.font", myFont);
	    UIManager.put("InternalFrame.optionDialogTitleFont", myFont);
	    UIManager.put("InternalFrame.paletteTitleFont", myFont);
	    UIManager.put("InternalFrame.titleFont", myFont);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setFont(new FontUIResource(new Font("Corbel", Font.PLAIN, 15)));
					Main frame2 = new Main(1);
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main(int usr) {
		setTitle("Home Page");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 500, 495);
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane2);
		contentPane2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 484, 456);
		contentPane2.add(panel);
		panel.setLayout(null);						

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(384, 11, 90, 25);
		panel.add(btnSearch);
		
		txtEnter = new JTextField();
		txtEnter.setText("Search for a song, album or artist");
		txtEnter.setBounds(10, 13, 364, 20);
		panel.add(txtEnter);
		txtEnter.setColumns(10);
		
		JButton btnCart = new JButton("Cart");
		btnCart.setBounds(385, 422, 89, 23);
		panel.add(btnCart);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 422, 89, 23);
		panel.add(btnLogout);
		
		JButton btnSongs = new JButton("Songs");
		btnSongs.setBounds(385, 99, 89, 23);
		panel.add(btnSongs);
		
		JButton btnArtists = new JButton("Artists");
		btnArtists.setBounds(385, 163, 89, 23);
		panel.add(btnArtists);
		
		JButton btnAlbums = new JButton("Albums");
		btnAlbums.setBounds(385, 229, 89, 23);
		panel.add(btnAlbums);
		
		JButton btnGenres = new JButton("Genres");
		btnGenres.setBounds(385, 290, 89, 23);
		panel.add(btnGenres);
		
		JLabel lblNewLabel = new JLabel("Here are some song recommendations for you");
		lblNewLabel.setFont(new Font("Corbel", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 47, 318, 25);
		panel.add(lblNewLabel);
		
		
		
		DefaultListModel<String> songlist = new DefaultListModel<>(); 
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("select * from (select s_name from song) where rownum<=100");
			
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
        list.setBounds(10, 80, 364, 300);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list);
		
		MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList<?> theList = (JList<?>) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		            Object o = theList.getModel().getElementAt(index);
		            SongbyId frame8 = new SongbyId(o.toString(),5,"null","null",usr);
					frame8.setVisible(true);
					dispose();
		          }
		        }
		      }
		    };
		list.addMouseListener(mouseListener);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 80, 364, 300);
		panel.add(scrollPane);
		
		DefaultListModel<String> searchlist = new DefaultListModel<>();
		JList<String> list2 = new JList<>(searchlist);
		list2.setBounds(10, 80, 364, 300);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list2);		
		
		JScrollPane scrollPane2 = new JScrollPane(list2);
		scrollPane2.setBounds(10, 80, 364, 300);
		panel.add(scrollPane2);
		
		list2.setVisible(false);
		scrollPane2.setVisible(false);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String srch = txtEnter.getText();
					searchlist.clear();
					Class.forName("oracle.jdbc.driver.OracleDriver");					
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
					
					Statement stmt = con.createStatement(
						    ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY
						);
					
					int f1=1,f2=1,f3=1;
					ResultSet rs = stmt.executeQuery("select s_name from song where s_name='" + srch + "'");
					
					if (!rs.next()) {										
						System.out.println("No song found");
						f1=0;
					} 
					else {						
						rs.beforeFirst();
						while (rs.next()) {	
							searchlist.addElement("Song - " + rs.getString(1)); 
						}
					}	
					
                    rs = stmt.executeQuery("select alb_name from album where alb_name='" + srch + "'");
					
					if (!rs.next()) {										
						System.out.println("No album found");
						f2=0;
					} 
					else {
						
						rs.beforeFirst();
						while (rs.next()) {	
							searchlist.addElement("Album - " + rs.getString(1)); 
						}
					}
					
                    rs = stmt.executeQuery("select art_name from artist where art_name='" + srch + "'");
					
					if (!rs.next()) {										
						System.out.println("No artist found");		
						f3=0;
					} 
					else {
						
						rs.beforeFirst();
						while (rs.next()) {	
							searchlist.addElement("Artist - " + rs.getString(1)); 
						}
					}
					
					if((f1+f2+f3)==0) 
						searchlist.addElement("No matches found");
					
					list.setVisible(false);
					scrollPane.setVisible(false);
					list2.setVisible(true);
					scrollPane2.setVisible(true);
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
		
		btnSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					Songs frame3 = new Songs(usr);
					frame3.setVisible(true);
					dispose();					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		btnArtists.addActionListener(new ActionListener() {
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
		
		btnAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					Albums frame5 = new Albums(usr);
					frame5.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnGenres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {				
					Genres frame6 = new Genres(usr);
					frame6.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					
					Cart frame7 = new Cart(usr);
					frame7.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
