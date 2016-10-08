package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Homepage extends JFrame implements ActionListener,FocusListener,WindowListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTabbedPane tab;
	JScrollPane inbox,spam,sent;
	JLabel fn,ln,user,label;
	JComboBox menu;
	JButton compose;
	String u;
	private JScrollPane starred;
	private JScrollPane draft;
	String inset[];
	int inid[],seid[],spid[],stid[],drid[];
	private JTable inboxtb,senttb,spamtb,starredtb,drafttb;
	String intb[][],setb[][],sptb[][],sttb[][],drtb[][];
	String cols[]={"Subject", "Sender", "Date", "Time", "Read Status"};
	
	
	public Homepage(String username) 
	{
		setResizable(false);
		u=username;
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		ImageIcon i=new ImageIcon(getClass().getResource("image/logo2 (3).jpg"));
		JLabel im=new JLabel(i);
		im.setBackground(Color.WHITE);
		im.setBounds(-50,-42,323,210);
		getContentPane().add(im);
		
		ImageIcon ib=new ImageIcon(getClass().getResource("image/bg1.jpg"));
		
		tab = new JTabbedPane(JTabbedPane.LEFT);
		tab.setBounds(0, 168, 1362, 406);
		getContentPane().add(tab);
		
		
		addInbox();
		addSent();
		addSpam();
		addStarred();
		addDraft();
		
		fn = new JLabel();
		fn.setFont(new Font("Segoe Script", Font.BOLD, 21));
		fn.setBounds(272, 21, 237, 39);
		getContentPane().add(fn);
		
		ln = new JLabel();
		ln.setFont(new Font("Segoe Script", Font.BOLD, 21));
		ln.setBounds(513, 21, 237, 39);
		getContentPane().add(ln);
		
		user = new JLabel();
		user.setBounds(276, 71, 122, 14);
		getContentPane().add(user);
		
		label = new JLabel("Connecting you with trust");
		label.setFont(new Font("Segoe Script", Font.ITALIC, 17));
		label.setBounds(20, 119, 293, 44);
		getContentPane().add(label);
		
		menu = new JComboBox();
		menu.setForeground(Color.BLACK);
		menu.setFont(new Font("Poor Richard", Font.PLAIN, 20));
		menu.setBackground(Color.WHITE);
		menu.setModel(new DefaultComboBoxModel(new String[] {username, "Details", "Privacy Policy", "Log Out"}));
		menu.setBounds(1163, 0, 199, 60);
		getContentPane().add(menu);
		menu.addActionListener(this);
		
		compose = new JButton("COMPOSE     MAIL");
		compose.setForeground(Color.WHITE);
		compose.setBackground(new Color(0, 191, 255));
		compose.setFont(new Font("Times New Roman", Font.BOLD, 20));
		compose.setBounds(310, 128, 270, 23);
		getContentPane().add(compose);
		JLabel imb=new JLabel(ib);
		imb.setBackground(Color.WHITE);
		imb.setBounds(-10,-42,1362,792);
		getContentPane().add(imb);
		compose.addActionListener(this);
		
		try 
		{
			String query="select firstname,lastname,username from information where username=?";
			java.sql.Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,username);
			ResultSet rs=ps.executeQuery();
			int c=0;
			while(rs.next())
			{
				fn.setText(rs.getString("firstname"));
				ln.setText(rs.getString("lastname"));
				user.setText(username);
				
			}
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();			
		}
	
		
		
		setSize(getMaximumSize());
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new Homepage("Gaurav");
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object o=e.getSource();
		if(o==compose)
		{
			new Compose(u);
		}
		else if(o==menu)
		{
			int c=menu.getSelectedIndex();
			switch(c)
			{
			case 1:
				{
					new Describe(u);
					break;
				}
			case 2:
				{
					new Privacy(u);
					break;
				}
			case 3:
				{
					dispose();
					new login();
					break;
				}
			}
		}
	}
	void addInbox()
	{
		try 
		{
			String sin="select * from mails where spam=0 and receiver=? and starred=0 ";
			java.sql.Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(sin);
			ps.setString(1, u);
			ResultSet rs=ps.executeQuery();
			rs.last();
			int n=rs.getRow();
			rs.beforeFirst();
			intb=new String[n][5];
			
			inid=new int[n];
			int x=0,c=0,r=0;
			while(rs.next())
			{
				System.out.println(intb[r][c++]=rs.getString("subject"));
				System.out.println(intb[r][c++]=rs.getString("sender"));
				System.out.println(intb[r][c++]=rs.getString("date"));
				System.out.println(intb[r][c++]=rs.getString("time"));
				if(rs.getInt("mread")==0)
				{
					intb[r][c++]="UNREAD";
				}
				else
				{
					intb[r][c++]="READ";
				}
				inid[r]=rs.getInt("id");
				c=0;
				r++;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		
		inbox = new JScrollPane();
		tab.addTab("Inbox", null, inbox, null);
		tab.setForegroundAt(0, SystemColor.desktop);
		tab.setBackgroundAt(0, Color.CYAN);
		inboxtb = new JTable(intb,cols);
		inboxtb.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent me)
			{
				getID(1);
			}
		});
		inboxtb.setShowGrid(false);
		inboxtb.getColumnModel().getColumn(0).setResizable(false);
		inboxtb.getColumnModel().getColumn(1).setResizable(false);
		inboxtb.getColumnModel().getColumn(2).setResizable(false);
		inboxtb.getColumnModel().getColumn(3).setResizable(false);
		inboxtb.getColumnModel().getColumn(4).setResizable(false);
		inbox.setViewportView(inboxtb);
		
		
	}
	void addSent()
	{
		sent = new JScrollPane();
		tab.addTab("Sent Mails", null, sent, null);
		tab.setBackgroundAt(1, Color.GREEN);

		try 
		{
			String sin="select * from mails where spam=0 and sender=? and starred=0 and receiver is not null";
			java.sql.Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(sin);
			ps.setString(1, u);
			ResultSet rs=ps.executeQuery();
			rs.last();
			int n=rs.getRow();
			rs.beforeFirst();
			setb=new String[n][5];
			
			seid=new int[n];
			int x=0,c=0,r=-1;
			while(rs.next())
			{
				if(rs.getString("receiver")==null)
				{
				}
				else
				{
					r++;
					setb[r][c++]=rs.getString("subject");
					setb[r][c++]=rs.getString("receiver");
					setb[r][c++]=rs.getString("date");
					setb[r][c++]=rs.getString("time");
					if(rs.getInt("mread")==0)
					{
						setb[r][c++]="UNREAD";
					}
					else
					{
						setb[r][c++]="READ";
					}
					seid[r]=rs.getInt("id");
					c=0;
				}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String colss[]={"Subject", "Receiver", "Date", "Time", "Read Status"};
		senttb = new JTable(setb,colss);
		senttb.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent me)
			{
				getID(2);
			}
		});
		senttb.setShowGrid(false);
		senttb.getColumnModel().getColumn(0).setResizable(false);
		senttb.getColumnModel().getColumn(1).setResizable(false);
		senttb.getColumnModel().getColumn(2).setResizable(false);
		senttb.getColumnModel().getColumn(3).setResizable(false);
		senttb.getColumnModel().getColumn(4).setResizable(false);
		sent.setViewportView(senttb);
	}
	
	void addSpam()
	{
		spam = new JScrollPane();
		tab.addTab("Spam Mails", null, spam, null);
		tab.setForegroundAt(2, Color.WHITE);
		tab.setBackgroundAt(2, Color.RED);
		

		try 
		{
			String sin="select * from mails where spam=1 and receiver=?";
			java.sql.Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(sin);
			ps.setString(1, u);
			ResultSet rs=ps.executeQuery();
			rs.last();
			int n=rs.getRow();
			rs.beforeFirst();
			sptb=new String[n][5];
			
			spid=new int[n];
			int c=0,r=0;
			while(rs.next())
			{
				sptb[r][c++]=rs.getString("subject");
				sptb[r][c++]=rs.getString("sender");
				sptb[r][c++]=rs.getString("date");
				sptb[r][c++]=rs.getString("time");
				if(rs.getInt("mread")==0)
				{
					sptb[r][c++]="UNREAD";
				}
				else
				{
					sptb[r][c++]="READ";
				}
				spid[r]=rs.getInt("id");
				c=0;
				r++;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spamtb = new JTable(sptb,cols);
		spamtb.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent me)
			{
				getID(3);
			}
		});
		spamtb.setShowGrid(false);
		spamtb.getColumnModel().getColumn(0).setResizable(false);
		spamtb.getColumnModel().getColumn(1).setResizable(false);
		spamtb.getColumnModel().getColumn(2).setResizable(false);
		spamtb.getColumnModel().getColumn(3).setResizable(false);
		spamtb.getColumnModel().getColumn(4).setResizable(false);
		spam.setViewportView(spamtb);
	}
	
	void addStarred()
	{
		starred = new JScrollPane();
		tab.addTab("Starred Mails", null, starred, null);
		tab.setBackgroundAt(3, Color.YELLOW);
		

		
		try 
		{
			String sin="select * from mails where spam=0 and receiver=? and starred=1";
			java.sql.Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(sin);
			ps.setString(1, u);
			ResultSet rs=ps.executeQuery();
			rs.last();
			int n=rs.getRow();
			rs.beforeFirst();
			sttb=new String[n][5];
			
			stid=new int[n];
			int c=0,r=0;
			while(rs.next())
			{
				sttb[r][c++]=rs.getString("subject");
				sttb[r][c++]=rs.getString("sender");
				sttb[r][c++]=rs.getString("date");
				sttb[r][c++]=rs.getString("time");
				if(rs.getInt("mread")==0)
				{
					sttb[r][c++]="UNREAD";
				}
				else
				{
					sttb[r][c++]="READ";
				}
				stid[r]=rs.getInt("id");
				c=0;
				r++;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		starredtb = new JTable(sttb,cols);
		starredtb.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent me)
			{
				getID(4);
			}
		});
		starredtb.setShowGrid(false);
		starredtb.getColumnModel().getColumn(0).setResizable(false);
		starredtb.getColumnModel().getColumn(1).setResizable(false);
		starredtb.getColumnModel().getColumn(2).setResizable(false);
		starredtb.getColumnModel().getColumn(3).setResizable(false);
		starredtb.getColumnModel().getColumn(4).setResizable(false);
		starred.setViewportView(starredtb);
	}
	
	void addDraft()
	{
		draft = new JScrollPane();
		tab.addTab("Drafts", null, draft, null);
		tab.setBackgroundAt(4, Color.ORANGE);
		

		try 
		{
			String sin="select * from mails where spam=0 and sender=? and starred=0 and receiver is null";
			java.sql.Connection con=MyConnection.connect();
			PreparedStatement ps=con.prepareStatement(sin);
			ps.setString(1, u);
			ResultSet rs=ps.executeQuery();
			rs.last();
			int n=rs.getRow();
			rs.beforeFirst();
			drtb=new String[n][3];
			
			drid=new int[n];
			int c=0,r=0;
			while(rs.next())
			{
				if(rs.getString("receiver")==null)
				{
					drtb[r][c++]=rs.getString("subject");
					drtb[r][c++]=rs.getString("date");
					drtb[r][c++]=rs.getString("time");
					drid[r]=rs.getInt("id");
					c=0;
					r++;
			
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		String colsss[]={"Subject", "Date", "Time"};
		drafttb = new JTable(drtb,colsss);
		drafttb.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent me)
			{
				getID(5);
			}
		});
		drafttb.setShowGrid(false);
		draft.setViewportView(drafttb);
		
	}
	
	void getID(int n)
	{
		switch(n)
		{
		case 1:
		{
			int r = inboxtb.getSelectedRow();
			System.out.println("Row Number:"+r);
			new Mail(inid[r]);
			break;
		}
		case 2:
		{
			int r = senttb.getSelectedRow();
			System.out.println("Row Number:"+r);
			new Mail(seid[r]);
			break;
		}
		case 3:
		{
			int r = spamtb.getSelectedRow();
			System.out.println("Row Number:"+r);
			new Mail(spid[r]);
			break;
		}
		case 4:
		{
			int r = starredtb.getSelectedRow();
			System.out.println("Row Number:"+r);
			new Mail(stid[r]);
			break;
		}
		case 5:
		{
			int r = drafttb.getSelectedRow();
			System.out.println("Row Number:"+r);
			new Compose(u,drid[r]);
			break;
		}
		
		}
	}

	@Override
	public void windowOpened(WindowEvent e) 
	{	
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{	
	}

	@Override
	public void windowClosed(WindowEvent e) 
	{	
	}

	@Override
	public void windowIconified(WindowEvent e) 
	{
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) 
	{
		
	}

	@Override
	public void windowActivated(WindowEvent e) 
	{
		remove(inboxtb);
		remove(senttb);
		remove(spamtb);
		remove(starredtb);
		remove(drafttb);
		addInbox();
		addSent();
		addSpam();
		addStarred();
		addDraft();
		setVisible(false);
		setVisible(true);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) 
	{
		
	}
}
