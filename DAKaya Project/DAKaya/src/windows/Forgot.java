package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;



public class Forgot extends JDialog implements ActionListener
{
	private JTextField user;
	JButton submit,cancel;

	public Forgot() 
	{
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		setTitle("DAKaya Forgot password");
		setAlwaysOnTop(true);
		getContentPane().setLayout(null);
		
		JLabel lblForgotYourPassword = new JLabel("Forgot your password?");
		lblForgotYourPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblForgotYourPassword.setBounds(284, 27, 160, 31);
		getContentPane().add(lblForgotYourPassword);
		
		JLabel lblNoIssues = new JLabel("No issues . We'll help you to fix that.");
		lblNoIssues.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNoIssues.setBounds(284, 57, 297, 31);
		getContentPane().add(lblNoIssues);
		
		JLabel lblEnterYourUser = new JLabel("Enter your user name here :");
		lblEnterYourUser.setBounds(284, 109, 171, 14);
		getContentPane().add(lblEnterYourUser);
		
		user = new JTextField();
		user.setBounds(284, 131, 171, 20);
		getContentPane().add(user);
		user.setColumns(10);
		
		submit = new JButton("Submit");
		submit.setBounds(279, 178, 89, 23);
		getContentPane().add(submit);
		submit.addActionListener(this);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(408, 178, 89, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(this);
		
		ImageIcon i=new ImageIcon(getClass().getResource("image/logo2 (3).jpg"));
		JLabel im=new JLabel(i);
		im.setBackground(Color.WHITE);
		im.setBounds(0,-25,274,266);
		getContentPane().add(im);
		
		setSize(589,268);
		setVisible(true);
		
	}

//	public static void main(String[] args) 
//	{
//		new Forgot();
//	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object s=e.getSource();
		if(s==submit)
		{
			System.out.println("Submit");
			String u=user.getText();
			try 
			{
				String query="select * from information where username=?";
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1,u);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					dispose();
					new ChangePassword(u);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Username doesnot exists");
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(s==cancel)
		{
			System.out.println("Cancel");
			dispose();
		}
	}
}
