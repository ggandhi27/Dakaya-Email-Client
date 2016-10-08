package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Privacy extends JDialog implements ActionListener,FocusListener
{
	String tpass;
	String user;
	private JTextField oldp;
	private JPasswordField newp;
	private JPasswordField cnewp;
	JButton update,cancel;
	int c;
	String p,np;
	
	public Privacy(String u) 
	{
		setTitle("Change Your Password");
		setAlwaysOnTop(true);
		setUndecorated(false);
		setResizable(false);
		setModal(true);
		user=u;
		getContentPane().setLayout(null);
		c=0;
		
		JLabel lblChangeYourPassword = new JLabel("Change your password");
		lblChangeYourPassword.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblChangeYourPassword.setBounds(34, 41, 347, 39);
		getContentPane().add(lblChangeYourPassword);
		
		JLabel lblYourCurrentPassword = new JLabel("Your Current Password :-");
		lblYourCurrentPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYourCurrentPassword.setBounds(34, 118, 191, 23);
		getContentPane().add(lblYourCurrentPassword);
		
		oldp = new JTextField();
		oldp.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent k)
			{
				checkValue();
			}
		});
		oldp.setBounds(235, 121, 249, 20);
		getContentPane().add(oldp);
		oldp.addFocusListener(this);
		
		JLabel lblEnterANew = new JLabel("Enter a new password :-");
		lblEnterANew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterANew.setBounds(34, 168, 191, 23);
		getContentPane().add(lblEnterANew);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm new password :-");
		lblConfirmNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmNewPassword.setBounds(34, 220, 191, 23);
		getContentPane().add(lblConfirmNewPassword);
		
		newp = new JPasswordField();
		newp.setBounds(235, 171, 249, 20);
		getContentPane().add(newp);
		newp.setEditable(false);
		newp.addFocusListener(this);
		newp.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent k)
			{
				checkPass();
			}
		});
		
		cnewp = new JPasswordField();
		cnewp.setBounds(235, 223, 249, 20);
		getContentPane().add(cnewp);
		cnewp.setEditable(false);
		cnewp.addFocusListener(this);
		cnewp.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent k)
			{
				checkPass();
			}
		});
		
		update = new JButton("update password");
		update.setForeground(new Color(255, 255, 255));
		update.setBackground(new Color(0, 153, 255));
		update.setBounds(72, 305, 153, 23);
		getContentPane().add(update);
		update.addActionListener(this);
		update.setEnabled(false);
		
		cancel = new JButton("Cancel");
		cancel.setBackground(new Color(255, 0, 0));
		cancel.setForeground(new Color(255, 255, 255));
		cancel.setBounds(267, 305, 153, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(this);
		
		
		p=new String(newp.getPassword());
		np=new String(cnewp.getPassword());
		try 
		{
			String s="select * from information where username=?";
			Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(s);
			ps.setString(1, user);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				tpass = rs.getString("password");
			}
		}
		catch (SQLException es)
		{
			es.printStackTrace();
		}
		
		setSize(541,414);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new Privacy("gaurav");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj=e.getSource();
		if(obj==cancel)
		{
			dispose();
		}
		if(obj==update)
		{
			try 
			{
				p=new String(newp.getPassword());
				String s="update information set password=? where username=?";
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(s);
				ps.setString(1,p);
				ps.setString(2, user);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Password Updated Successfully");
			}
			catch (SQLException es)
			{
				es.printStackTrace();
			}	
			JOptionPane.showMessageDialog(null, "Password Updated Successfully");
			dispose();
		}
	}

	@Override
	public void focusGained(FocusEvent e) 
	{
		
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		
	}
	public void checkValue()
	{
		String st = oldp.getText();
		if(st.equals(tpass))
		{
			newp.setEnabled(true);
			cnewp.setEnabled(true);
			newp.setEditable(true);
			cnewp.setEditable(true);
		}
	}
	public void checkPass()
	{
		String p=new String(newp.getPassword());
		String cp=new String(cnewp.getPassword());
		if(p.equals(cp))
		{
			update.setEnabled(true);
		}
	}
}
