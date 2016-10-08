package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Color;

public class Compose extends JDialog implements ActionListener,FocusListener
{
	private JTextField receiver;
	private JTextField sub;
	JButton draft,cancel,send;
	JEditorPane mail;
	String sender;
	int mailno,c=0;
	
	/**
	 * @wbp.parser.constructor
	 */
	public Compose(String current)
	{
		setResizable(false);
		setModal(true);
		sender=current;
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);
		setTitle("Compose Mail");
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTo.setBounds(10, 188, 34, 25);
		getContentPane().add(lblTo);
		
		receiver = new JTextField();
		receiver.setBackground(SystemColor.controlHighlight);
		receiver.setBounds(82, 190, 389, 22);
		getContentPane().add(receiver);
		receiver.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject :");
		lblSubject.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSubject.setBounds(10, 228, 71, 25);
		getContentPane().add(lblSubject);
		
		sub = new JTextField();
		sub.setBackground(SystemColor.controlHighlight);
		sub.setColumns(10);
		sub.setBounds(82, 230, 389, 22);
		getContentPane().add(sub);
		
		JLabel lblMessage = new JLabel("Message :");
		lblMessage.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMessage.setBounds(10, 264, 114, 25);
		getContentPane().add(lblMessage);
		
		mail = new JEditorPane();
		mail.setBackground(SystemColor.controlHighlight);
		mail.setBounds(10, 300, 461, 297);
		getContentPane().add(mail);
		
		send = new JButton("Send Mail");
		send.setBackground(Color.GREEN);
		send.setBounds(10, 621, 123, 23);
		getContentPane().add(send);
		send.addActionListener(this);
		send.setDefaultCapable(true);
		
		draft = new JButton("Save To Draft");
		draft.setBackground(Color.ORANGE);
		draft.setBounds(167, 621, 123, 23);
		getContentPane().add(draft);
		draft.addActionListener(this);
		
		cancel = new JButton("Cancel");
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Color.RED);
		cancel.setBounds(322, 621, 123, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(this);
		
		ImageIcon ii=new ImageIcon(getClass().getResource("image/logo2 (3).jpg"));
		JLabel img=new JLabel(ii);
		img.setBounds(66,-39,303,281);
		getContentPane().add(img);
		
		setSize(497,694);
		setVisible(true);

	}
	public Compose(String current,int n)
	{
		c=1;
		mailno=n;
		setResizable(false);
		setModal(true);
		sender=current;
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);
		setTitle("Compose Mail");
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTo.setBounds(10, 188, 34, 25);
		getContentPane().add(lblTo);
		
		receiver = new JTextField();
		receiver.setBackground(SystemColor.controlHighlight);
		receiver.setBounds(82, 190, 389, 22);
		getContentPane().add(receiver);
		receiver.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject :");
		lblSubject.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSubject.setBounds(10, 228, 71, 25);
		getContentPane().add(lblSubject);
		
		sub = new JTextField();
		sub.setBackground(SystemColor.controlHighlight);
		sub.setColumns(10);
		sub.setBounds(82, 230, 389, 22);
		getContentPane().add(sub);
		
		JLabel lblMessage = new JLabel("Message :");
		lblMessage.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMessage.setBounds(10, 264, 114, 25);
		getContentPane().add(lblMessage);
		
		mail = new JEditorPane();
		mail.setBackground(SystemColor.controlHighlight);
		mail.setBounds(10, 300, 461, 297);
		getContentPane().add(mail);
		
		send = new JButton("Send Mail");
		send.setBackground(Color.GREEN);
		send.setBounds(10, 621, 123, 23);
		getContentPane().add(send);
		send.addActionListener(this);
		send.setDefaultCapable(true);
		
		draft = new JButton("Save To Draft");
		draft.setBackground(Color.ORANGE);
		draft.setBounds(167, 621, 123, 23);
		getContentPane().add(draft);
		draft.addActionListener(this);
		
		cancel = new JButton("Cancel");
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Color.RED);
		cancel.setBounds(322, 621, 123, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(this);
		
		ImageIcon ii=new ImageIcon(getClass().getResource("image/logo2 (3).jpg"));
		JLabel img=new JLabel(ii);
		img.setBounds(66,-39,303,281);
		getContentPane().add(img);
		
		try
		{
			String s="select * from mails where id=?";
			Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(s);
			ps.setInt(1, mailno);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				sub.setText(rs.getString("subject"));
				mail.setText(rs.getString("email"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		setSize(497,694);
		setVisible(true);

	}
	public static void main(String args[])
	{
		new Compose("Gandhi");
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) 
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj=e.getSource();
		String rec=receiver.getText();
		java.util.Date date=new java.util.Date();
		SimpleDateFormat fd=new SimpleDateFormat ("yyyy.MM.dd");
		SimpleDateFormat ft=new SimpleDateFormat ("hh:mm:ss");
		String d=(fd.format(date)).toString();
		String t=(ft.format(date)).toString();
		int id=genID();
		String subject=sub.getText();
		String m=mail.getText();
		if(obj==send)
		{
			if(c==1)
			{
				try 
				{
					String search="delete from mails where id=?";
					Connection con=MyConnection.connect();
					PreparedStatement ps=con.prepareStatement(search);
					ps.setInt(1, mailno);
					ps.executeUpdate();
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try 
			{
				String search="select * from information where username=?";
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(search);
				ps.setString(1, rec);
				ResultSet rs=ps.executeQuery();
				if(!rs.next())
				{
					JOptionPane.showMessageDialog(null,"Receiver doesnot exist");
				}
				else
				{
					String input="insert into mails(id,sender,receiver,subject,email,date,time,starred,spam,mread)values(?,?,?,?,?,?,?,0,0,0)";
					if(m.equals(""))
					{
						JOptionPane.showMessageDialog(null,"Cannot send a Blank mail");
					}
					else if(subject.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Cannot send a mail without a Subject");
					}
					else
					{
						PreparedStatement pi=con.prepareStatement(input);
						pi.setInt(1,id);
						pi.setString(2,sender);
						pi.setString(3,rec);
						pi.setString(4,subject);
						pi.setString(5,m);
						pi.setString(6,d);
						pi.setString(7,t);
						pi.executeUpdate();
						JOptionPane.showMessageDialog(null,"Mail successfully sent");
						dispose();
					}
				}
			}
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(obj==draft)
		{
			if(c==1)
			{
				try 
				{
					String search="delete from mails where id=?";
					Connection con=MyConnection.connect();
					PreparedStatement ps=con.prepareStatement(search);
					ps.setInt(1, mailno);
					ps.executeUpdate();
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try 
			{
				Connection con=MyConnection.connect();
				String input="insert into mails(id,sender,subject,email,date,time,starred,spam,mread)values(?,?,?,?,?,?,0,0,1)";
				if(subject.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please add subject to the mail");
				}
				else
				{
					PreparedStatement pi=con.prepareStatement(input);
					pi.setInt(1,id);
					pi.setString(2,sender);
					pi.setString(3, subject);
					pi.setString(4,m);
					pi.setString(5,d);
					pi.setString(6,t);
					pi.executeUpdate();
					JOptionPane.showMessageDialog(null,"Draft successfully added !");
					dispose();
				}
			} 
			catch (HeadlessException e1) 
			{
				e1.printStackTrace();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(obj==cancel)
		{
			dispose();
		}
	}
	int genID()
	{
		int id=0;;
		String s="select max(id) from mails";
		try
		{
			Connection con=MyConnection.connect();
			PreparedStatement ps = con.prepareStatement(s);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				id=rs.getInt(1);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		++id;
		return id;
	}

}