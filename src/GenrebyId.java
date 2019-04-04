import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

//SHOWS THE SONGS OF THE GENRE DIRECTLY.

public class GenrebyId extends JFrame {

	private JPanel contentPane2;
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
	public GenrebyId() {
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
		
		JButton btnCart = new JButton("Back");
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCart.setBounds(339, 227, 89, 23);
		panel.add(btnCart);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 227, 89, 23);
		panel.add(btnLogout);
		
		JLabel songs = new JLabel("");
		songs.setBounds(10, 11, 318, 205);
		panel.add(songs);

	}
}
