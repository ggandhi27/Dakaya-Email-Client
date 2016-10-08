package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class ChangePassword extends JDialog implements ActionListener,FocusListener
{
	private JTextField answer;
	private JPasswordField newpassword;
	JButton change,cancel;
	String ques,uname;
	public ChangePassword(String s) 
	{
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		uname=s;
		getContentPane().setLayout(null);
		
		JLabel lblDakaya = new JLabel("DAKaya");
		lblDakaya.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		lblDakaya.setBounds(277, 11, 165, 53);
		getContentPane().add(lblDakaya);
		
		JLabel label = new JLabel("Connecting you with trust");
		label.setFont(new Font("Segoe Script", Font.ITALIC, 17));
		label.setBounds(277, 43, 293, 44);
		getContentPane().add(label);
		
		try 
		{
			String query="select ques from information where username=?";
			Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,uname);
			System.out.println("username is "+uname);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ques=rs.getString("ques");
				System.out.println("Question :- "+ques);
			}
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		JLabel lblNewLabel = new JLabel(ques);
		lblNewLabel.setBounds(277, 79, 328, 29);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(true);
		
		answer = new JTextField();
		answer.setBounds(279, 119, 299, 20);
		getContentPane().add(answer);
		answer.setColumns(10);
		answer.addFocusListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("Enter new password here :-");
		lblNewLabel_1.setBounds(277, 150, 186, 20);
		getContentPane().add(lblNewLabel_1);
		
		newpassword = new JPasswordField();
		newpassword.setEnabled(false);
		newpassword.setBounds(279, 187, 299, 20);
		getContentPane().add(newpassword);
		newpassword.addFocusListener(this);
		
		change = new JButton("Change");
		change.setBounds(279, 238, 120, 23);
		getContentPane().add(change);
		change.addActionListener(this);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(458, 238, 120, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(this);
		
		ImageIcon i=new ImageIcon(getClass().getResource("image/logo2 (3).jpg"));
		JLabel im=new JLabel(i);
		im.setBounds(0,23,267,277);
		getContentPane().add(im);
		
		setSize(625,350);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj=e.getSource();
		if(obj==change)
		{
			if(newpassword.isEnabled())
			{
				String x= new String(newpassword.getPassword());
				if(x.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter the new password first");
				}
				else
				{
				try 
				{
					String query="update information set password=?  where username=?";
					Connection con=MyConnection.connect();
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1,x);
					ps.setString(2,uname);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Password Updated successfully");
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				dispose();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid Answer");
			}
		}
		else if(obj==cancel)
		{
			dispose();
		}
		
	}
//	public static void main(String args[])
//	{
//		new ChangePassword("Ggandhi");
//	}

	@Override
	public void focusGained(FocusEvent e) 
	{
		Object obj=e.getSource();
		
		if(obj==answer)
		{
			try 
			{
				String s="select answer from information where ques=?";
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(s);
				ps.setString(1,ques);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					if(rs.getString("answer").equals(answer.getText()))
					{
						newpassword.setEnabled(true);
						setVisible(true);
					}
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		Object obj=e.getSource();
		if(obj==answer)
		{
			try 
			{
				String s="select answer from information where ques=?";
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(s);
				ps.setString(1,ques);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					if(rs.getString("answer").equals(answer.getText()))
					{
						newpassword.setEnabled(true);
						setVisible(true);
					}
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
			
		
	}
}
