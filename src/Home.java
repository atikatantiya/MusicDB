import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Home() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 438, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(154, 113, 117, 20);
		panel.add(txtPassword);

		JLabel lblEmail = new JLabel("Email Id");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(154, 12, 158, 25);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(154, 49, 117, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JButton btnSearch = new JButton("Login");

		btnSearch.setBounds(154, 171, 117, 25);
		panel.add(btnSearch);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(154, 85, 133, 17);
		panel.add(lblPassword);

		JLabel lblData = new JLabel("");
		lblData.setBounds(79, 214, 297, 25);
		panel.add(lblData);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String email = txtEmail.getText();
					String password = String.valueOf(txtPassword.getPassword());
					
					Class.forName("oracle.jdbc.driver.OracleDriver");					
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM","atika123");
					
					Statement stmt = con.createStatement(
						    ResultSet.TYPE_SCROLL_INSENSITIVE,
						    ResultSet.CONCUR_READ_ONLY
						);

					ResultSet rs = stmt.executeQuery("SELECT * FROM users where email = '" + email + "' and password = '" + password + "'");
					
					if (!rs.next()) {										
						System.out.println("Incorrect credientials");
					} else {
						
						rs.beforeFirst();
						while (rs.next()) {	
							System.out.println("Successfully logged in");
							System.out.println(rs.getInt(1) + " " + rs.getString(2));					
							
						}
						
						Main frame2 = new Main();
						frame2.setVisible(true);
						dispose();
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
