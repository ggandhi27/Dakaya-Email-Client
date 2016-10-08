package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class Mail extends JDialog implements ActionListener
{

	JLabel sender,subject,from;
	JButton forward,send,draft,close,spam,unspam,star,unstar;
	JTextPane email;
	int mailno;
	private JTextField subtf;
	private JTextField fromtf;
	String current;
	
	public Mail(int id) 
	{
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);
		mailno=id;
		
		from = new JLabel("From :");
		from.setFont(new Font("Times New Roman", Font.BOLD, 20));
		from.setBounds(10, 11, 72, 32);
		getContentPane().add(from);
		
		JLabel lblsubject = new JLabel("Subject :");
		lblsubject.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblsubject.setBounds(10, 50, 83, 32);
		getContentPane().add(lblsubject);
		
		sender = new JLabel();
		sender.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		sender.setBounds(92, 11, 332, 32);
		getContentPane().add(sender);
		
		subject = new JLabel();
		subject.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		subject.setBounds(92, 50, 332, 32);
		getContentPane().add(subject);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 414, 306);
		getContentPane().add(scrollPane);
		
		email = new JTextPane();
		email.setEditable(false);
		scrollPane.setViewportView(email);
		
		forward = new JButton("Forward");
		forward.setForeground(new Color(255, 255, 255));
		forward.setBackground(SystemColor.textHighlight);
		forward.setBounds(125, 467, 192, 23);
		getContentPane().add(forward);
		forward.addActionListener(this);
		
		close = new JButton("Close");
		close.setForeground(new Color(255, 255, 255));
		close.setBackground(new Color(255, 0, 0));
		close.setBounds(125, 501, 192, 23);
		getContentPane().add(close);
		close.addActionListener(this);
		
		send = new JButton("Send");
		send.setBackground(new Color(51, 255, 51));
		send.setEnabled(false);
		send.setBounds(10, 467, 192, 23);
		getContentPane().add(send);
		send.setVisible(true);
		send.setVisible(false);
		send.addActionListener(this);
		
		draft = new JButton("Save as Draft");
		draft.setBackground(new Color(255, 204, 0));
		draft.setEnabled(false);
		draft.setBounds(247, 467, 192, 23);
		getContentPane().add(draft);
		draft.addActionListener(this);
		
		subtf = new JTextField();
		subtf.setBounds(92, 56, 332, 23);
		getContentPane().add(subtf);
		subtf.setColumns(10);
		subtf.setVisible(false);
		
		fromtf = new JTextField();
		fromtf.setBounds(92, 17, 332, 23);
		getContentPane().add(fromtf);
		fromtf.setColumns(10);
		
		spam = new JButton("SPAM");
		spam.setForeground(new Color(255, 255, 255));
		spam.setBackground(new Color(255, 0, 0));
		spam.setBounds(10, 433, 149, 23);
		getContentPane().add(spam);
		spam.addActionListener(this);
		
		unspam = new JButton("UNSPAM");
		unspam.setBackground(new Color(51, 255, 0));
		unspam.setBounds(10, 433, 149, 23);
		getContentPane().add(unspam);
		unspam.addActionListener(this);
		
		star = new JButton("STAR");
		star.setBackground(new Color(255, 255, 51));
		star.setBounds(292, 433, 132, 23);
		getContentPane().add(star);
		star.addActionListener(this);
		
		unstar = new JButton("UNSTAR");
		unstar.setBackground(new Color(51, 255, 255));
		unstar.setBounds(292, 433, 132, 23);
		getContentPane().add(unstar);
		unstar.addActionListener(this);
		
		fromtf.setVisible(false);
		
		draft.setVisible(true);
		draft.setVisible(false);
		
		try 
		{
			String s="select * from mails where id=?";
			String re="update mails set mread=1 where id=?";
			Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(s);
			PreparedStatement pr=con.prepareStatement(re);
			ps.setInt(1, mailno);
			pr.setInt(1, mailno);
			pr.executeUpdate();
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				setTitle(rs.getString("sender")+" : "+rs.getString("subject"));
				sender.setText(rs.getString("sender"));
				subject.setText(rs.getString("subject"));
				subtf.setText(rs.getString("subject"));
				email.setText(rs.getString("email"));
				current=rs.getString("receiver");
				if(rs.getInt("spam")==0)
				{
					spam.setVisible(true);
					unspam.setVisible(false);
				}
				else
				{
					unspam.setVisible(true);
					spam.setVisible(false);
				}
				if(rs.getInt("starred")==0)
				{
					star.setVisible(true);
					unstar.setVisible(false);
				}
				else
				{
					unstar.setVisible(true);
					star.setVisible(false);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		setSize(455,629 );
		setVisible(true);
		
	}

	public static void main(String[] args) 
	{
		new Mail(4);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj=e.getSource();
		String rec=fromtf.getText();
		java.util.Date date=new java.util.Date();
		SimpleDateFormat fd=new SimpleDateFormat ("yyyy.MM.dd");
		SimpleDateFormat ft=new SimpleDateFormat ("hh:mm:ss");
		String d=(fd.format(date)).toString();
		String t=(ft.format(date)).toString();
		String m=fromtf.getText();
		String su=subtf.getText();
		int id=genID();
		if(obj==forward)
		{
			email.setEditable(true);
			email.setEnabled(true);
			send.setEnabled(true);
			send.setVisible(true);
			draft.setEnabled(true);
			draft.setVisible(true);
			forward.setVisible(false);
			subject.setVisible(false);
			subtf.setVisible(true);
			from.setText("To");
			sender.setVisible(false);
			fromtf.setVisible(true);
			spam.setVisible(false);
			unspam.setVisible(false);
			unstar.setVisible(false);
			star.setVisible(false);
			
		}
		else if(obj==close)
		{
			dispose();
		}
		else if(obj==send)
		{
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
					else if(su.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Cannot send a mail without a Subject");
					}
					else
					{
						PreparedStatement pi=con.prepareStatement(input);
						pi.setInt(1,id);
						pi.setString(2,current);
						pi.setString(3,m);
						pi.setString(4,su);
						pi.setString(5,email.getText());
						pi.setString(6,d);
						pi.setString(7,t);
						pi.executeUpdate();
						JOptionPane.showMessageDialog(null,"Mail successfully sent");
						dispose();
					}
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
		else if(obj==draft)
		{
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
					pi.setString(2,current);
					pi.setString(3, su);
					pi.setString(4,email.getText());
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
		else if(obj==spam)
		{
			spam.setVisible(false);
			unspam.setVisible(true);
			String s="update mails set spam=1 where id=?";
			try 
			{
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(s);
				ps.setInt(1, mailno);
				ps.executeUpdate();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(obj==unspam)
		{
			unspam.setVisible(false);
			spam.setVisible(true);
			String s="update mails set spam=0 where id=?";
			try 
			{
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(s);
				ps.setInt(1, mailno);
				ps.executeUpdate();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(obj==star)
		{
			star.setVisible(false);
			unstar.setVisible(true);
			String s="update mails set starred=1 where id=?";
			try 
			{
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(s);
				ps.setInt(1, mailno);
				ps.executeUpdate();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(obj==unstar)
		{
			unstar.setVisible(false);
			star.setVisible(true);
			String s="update mails set starred=0 where id=?";
			try 
			{
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(s);
				ps.setInt(1, mailno);
				ps.executeUpdate();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
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
		e.printStackTrace();
		}
		++id;
		return id;
	}
}
