import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
//Origin - Artist by Id
//On click leads to - Album by Id
//select alb_name from album,artist where album.artist_id = artist.artist_id and artist.art_name='artist1';

public class AlbumsbyArtist extends JFrame {

	private JPanel contentPane2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlbumsbyArtist frame13 = new AlbumsbyArtist("Queen",1);
					frame13.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlbumsbyArtist(String art,int usr) {
		setTitle("Albums by the Artist");
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
		
		DefaultListModel<String> alblist = new DefaultListModel<>(); 
		try {
			alblist.clear();
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("SELECT alb_name FROM album,artist WHERE album.artist_id = artist.artist_id AND artist.art_name='" + art + "'");
			
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
        list.setBounds(10, 70, 455, 200);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list);
		
		MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList<?> theList = (JList<?>) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		            Object o = theList.getModel().getElementAt(index);
		            AlbumbyId frame10 = new AlbumbyId(o.toString(),2,art,usr);
					frame10.setVisible(true);
					dispose();
		          }
		        }
		      }
		    };
		list.addMouseListener(mouseListener);
        
        JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 70, 455, 200);
		panel.add(scrollPane);
		
		JLabel lblAlbumsByThe = new JLabel("Albums by the Artist");
		lblAlbumsByThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbumsByThe.setBounds(157, 11, 159, 41);
		panel.add(lblAlbumsByThe);
		
		JLabel label = new JLabel("Click on an album to know more");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 281, 231, 25);
		panel.add(label);
		
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
					ArtistbyId frame9 = new ArtistbyId(art,usr);
					frame9.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
