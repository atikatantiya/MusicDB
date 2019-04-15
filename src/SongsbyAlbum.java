import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
//Origin - Album by Id
//On click leads to - Song by Id
//select song.s_name from song,album where album.alb_name = 'album1' and album.album_id = song.album_id;
public class SongsbyAlbum extends JFrame {

	private JPanel contentPane2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SongsbyAlbum frame14 = new SongsbyAlbum("album1",1,"null",1);
					frame14.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SongsbyAlbum(String alb,int ch,String art,int usr) {
		setTitle("Songs in the Album");
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
		
		JLabel namel = new JLabel("");
		namel.setBounds(10, 32, 290, 45);
		panel.add(namel);
		
		DefaultListModel<String> songlist = new DefaultListModel<>(); 
		try {
			songlist.clear();
			Class.forName("oracle.jdbc.driver.OracleDriver");					
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
			
			Statement stmt = con.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY
				);

			ResultSet rs = stmt.executeQuery("SELECT song.s_name FROM song,album WHERE album.alb_name = '" + alb + "' AND album.album_id = song.album_id");
			
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
		            SongbyId frame8 = new SongbyId(o.toString(),3,alb,art,usr);
					frame8.setVisible(true);
					dispose();
		          }
		        }
		      }
		    };
		list.addMouseListener(mouseListener);
        
        JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 70, 455, 200);
		panel.add(scrollPane);
		
		JLabel lblClickOnA = new JLabel("Click on a song to know more");
		lblClickOnA.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickOnA.setBounds(10, 281, 185, 25);
		panel.add(lblClickOnA);
		
		JLabel lblSongsInThe = new JLabel("Songs in the Album");
		lblSongsInThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSongsInThe.setBounds(141, 11, 159, 41);
		panel.add(lblSongsInThe);
		
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
						AlbumbyId frame10 = new AlbumbyId(alb,1,"null",usr);
						frame10.setVisible(true);
					}
					else if(ch==2){
						AlbumbyId frame10 = new AlbumbyId(alb,2,art,usr);
						frame10.setVisible(true);
					}
					
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
