package windows;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.security.auth.login.LoginContext;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JDialog implements ActionListener
{
	private JTextField u;
	private JPasswordField p;
	JButton submit = new JButton("Login"),forgot;
	JButton register = new JButton("Click here to register with us");
	JLabel lblUsername = new JLabel("Username");
	JLabel lblPassword = new JLabel("Password");
    JLabel lbl;
	JLabel lblPleaseEnterYour = new JLabel("Please Enter your \r\nusername and password  to login.");
	private final JButton about = new JButton("About the Developers");
	private final JLabel label = new JLabel("Welcome To DAKaya");
	private final JLabel label_1 = new JLabel("Connecting you with trust");

	public login() 
	{
		setResizable(false);
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		lblUsername.setForeground(Color.BLACK);
		
	
		lblUsername.setBounds(245, 181, 70, 25);
		getContentPane().add(lblUsername);
		
		
		lblPassword.setBounds(245, 240, 70, 14);
		getContentPane().add(lblPassword);
		
		u = new JTextField();
		u.setBounds(308, 183, 197, 20);
		getContentPane().add(u);
		u.setColumns(10);
		submit.setBackground(SystemColor.controlHighlight);
		submit.setMnemonic('l');
		
		
		submit.setBounds(245, 289, 110, 23);
		getContentPane().add(submit);
		submit.addActionListener(this);
		
		p = new JPasswordField();
		p.setBounds(308, 237, 197, 20);
		getContentPane().add(p);
		lblPleaseEnterYour.setForeground(Color.BLACK);
		

		lblPleaseEnterYour.setBackground(UIManager.getColor("ScrollBar.thumbShadow"));
		lblPleaseEnterYour.setBounds(245, 123, 319, 31);
		getContentPane().add(lblPleaseEnterYour);
		
		forgot = new JButton("Forgot Password");
		forgot.setBackground(SystemColor.controlHighlight);
		forgot.setMnemonic('f');
		forgot.setBounds(365, 289, 143, 23);
		getContentPane().add(forgot);
		
		getRootPane().setDefaultButton(submit);
		forgot.addActionListener(this);
		register.setBackground(SystemColor.controlHighlight);
		register.setBounds(281, 334, 221, 23);
		getContentPane().add(register);
		register.addActionListener(this);
		about.setBounds(464, 429, 167, 23);
		about.addActionListener(this);
		getContentPane().add(about);
		label.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		label.setBounds(245, 22, 333, 53);
		
		getContentPane().add(label);
		label_1.setFont(new Font("Segoe Script", Font.ITALIC, 17));
		label_1.setBounds(245, 75, 293, 44);
		
		getContentPane().add(label_1);
		setTitle("DAKaya");
		
		ImageIcon ii=new ImageIcon(getClass().getResource("image/logo2 (2).jpg"));
		JLabel img=new JLabel(ii);
		img.setBounds(-27,-15,298,498);
		getContentPane().add(img);
		
		setSize(647,491);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new login();

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object s=e.getSource();
		if(s==register)
		{
			new Register();
		}
		else if(s==forgot)
		{
			setVisible(true);
			setVisible(false);
			new Forgot();
		}
		else if(s==about)
		{
			new About();
		}
		else if(s==submit)
		{
			String uname=u.getText();
			String pname=new String(p.getPassword());
			if(u.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter a username and try again");	
			}
			else
			{
			try {
				String query="select password from information where username=?";
				Connection con=MyConnection.connect();
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1,uname);
				ResultSet rs=ps.executeQuery();
				int c=0;
				while(rs.next())
				{
					System.out.println("password enterred ="+pname);
					System.out.println("db password ="+rs.getString("password"));
					if((rs.getString("password")).equals(pname))
					{
						JOptionPane.showMessageDialog(null,"login successful");
						new Homepage(uname);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Incorrect Password. \nPlease check your username and password and try again");	
					}
					c=1;
				}
				if(c==0)
				{
					JOptionPane.showMessageDialog(null,"Account doesnot exist");	
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
				
			}
			}
		}
	}
}
