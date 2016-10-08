package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Describe extends JDialog implements ActionListener
{

	String username;
	private JTextField ftf;
	private JTextField ltf;
	JLabel img,im,lblName,fn,lblLastName,lblLn,lblUserName,lblUn,lblDob,dob,lblCountry,country,lblGender,gen;
	JButton fedit,ledit,cedit,close;
	JComboBox cbox;
	String user;
	private JButton fok;
	private JButton lok;
	private JButton cok;
	private JButton fcancel;
	private JButton lcancel;
	private JButton ccancel;
	
	public Describe(String u) 
	{
		user=u;
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setResizable(false);
		setModal(true);
		username=u;
		getContentPane().setLayout(null);
		
		ImageIcon ii=new ImageIcon(getClass().getResource("image/message_icon-1.png"));
		img=new JLabel(ii);
		img.setBounds(-4,134,210,302);
		getContentPane().add(img);
		
		ImageIcon i=new ImageIcon(getClass().getResource("image/logo2 (3).jpg"));
		im=new JLabel(i);
		im.setBackground(Color.WHITE);
		im.setBounds(-14,-38,256,196);
		getContentPane().add(im);
		
		lblName = new JLabel("First Name  :-");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblName.setBounds(265, 23, 122, 26);
		getContentPane().add(lblName);
		
		fn = new JLabel();
		fn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		fn.setBounds(397, 23, 273, 26);
		getContentPane().add(fn);
		
		lblLastName = new JLabel("Last Name  :-");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblLastName.setBounds(265, 60, 122, 26);
		getContentPane().add(lblLastName);
		
		lblLn = new JLabel();
		lblLn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblLn.setBounds(397, 60, 273, 26);
		getContentPane().add(lblLn);
		
		lblUserName = new JLabel("Username  :-");
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblUserName.setBounds(265, 97, 122, 26);
		getContentPane().add(lblUserName);
		
		lblUn = new JLabel();
		lblUn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUn.setBounds(397, 97, 273, 26);
		getContentPane().add(lblUn);
		
		lblDob = new JLabel("D.O.B. :-");
		lblDob.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDob.setBounds(265, 134, 122, 26);
		getContentPane().add(lblDob);
		
		dob = new JLabel();
		dob.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		dob.setBounds(397, 134, 273, 26);
		getContentPane().add(dob);
		
		lblCountry = new JLabel("Country :-");
		lblCountry.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCountry.setBounds(265, 171, 122, 26);
		getContentPane().add(lblCountry);
		
		country = new JLabel();
		country.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		country.setBounds(397, 171, 256, 26);
		getContentPane().add(country);
		
		lblGender = new JLabel("Gender :-");
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblGender.setBounds(265, 208, 122, 26);
		getContentPane().add(lblGender);
		
		gen = new JLabel();
		gen.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		gen.setBounds(397, 208, 273, 26);
		getContentPane().add(gen);
		
		close = new JButton("Close");
		close.setForeground(new Color(255, 255, 255));
		close.setBackground(new Color(255, 0, 0));
		close.setBounds(279, 314, 170, 23);
		getContentPane().add(close);
		close.addActionListener(this);
		
		fedit = new JButton("Edit");
		fedit.setForeground(new Color(255, 255, 255));
		fedit.setBackground(new Color(51, 153, 255));
		fedit.setBounds(690, 27, 89, 23);
		getContentPane().add(fedit);
		fedit.addActionListener(this);
		
		ledit = new JButton("Edit");
		ledit.setBackground(new Color(102, 153, 255));
		ledit.setForeground(new Color(255, 255, 255));
		ledit.setBounds(691, 61, 89, 23);
		getContentPane().add(ledit);
		ledit.addActionListener(this);
		
		cedit = new JButton("Edit");
		cedit.setForeground(new Color(255, 255, 255));
		cedit.setBackground(new Color(102, 153, 255));
		cedit.setBounds(691, 175, 89, 23);
		getContentPane().add(cedit);
		cedit.addActionListener(this);
		
		cbox = new JComboBox();
		cbox.setBounds(398, 176, 230, 20);
		getContentPane().add(cbox);
		cbox.setModel(new DefaultComboBoxModel(new String[] {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo, Republic of the", "Congo, Democratic Republic of the", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "St. Kitts and Nevis", "St. Lucia", "St. Vincent and The Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom (UK)", "United States of America (USA)", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City (Holy See)", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"}));
		cbox.setVisible(false);
		
		ftf = new JTextField();
		ftf.setBounds(397, 28, 219, 20);
		getContentPane().add(ftf);
		ftf.setColumns(10);
		ftf.setVisible(false);
		
		ltf = new JTextField();
		ltf.setColumns(10);
		ltf.setBounds(397, 65, 219, 20);
		getContentPane().add(ltf);
		ltf.setVisible(false);
		
		fok = new JButton("ok");
		fok.setBackground(new Color(51, 255, 102));
		fok.setBounds(651, 27, 55, 23);
		getContentPane().add(fok);
		fok.setVisible(false);
		fok.addActionListener(this);
		
		lok = new JButton("ok");
		lok.setBackground(new Color(51, 255, 102));
		lok.setBounds(651, 60, 55, 23);
		getContentPane().add(lok);
		lok.setVisible(false);
		lok.addActionListener(this);
		
		cok = new JButton("ok");
		cok.setBackground(new Color(51, 255, 102));
		cok.setBounds(651, 175, 55, 23);
		getContentPane().add(cok);
		cok.setVisible(false);
		cok.addActionListener(this);
		
		fcancel = new JButton("cancel");
		fcancel.setForeground(new Color(255, 255, 255));
		fcancel.setBackground(new Color(255, 0, 0));
		fcancel.setBounds(716, 27, 89, 23);
		getContentPane().add(fcancel);
		fcancel.setVisible(false);
		fcancel.addActionListener(this);
		
		lcancel = new JButton("cancel");
		lcancel.setForeground(Color.WHITE);
		lcancel.setBackground(Color.RED);
		lcancel.setBounds(716, 60, 89, 23);
		getContentPane().add(lcancel);
		lcancel.setVisible(false);
		lcancel.addActionListener(this);
		
		ccancel = new JButton("cancel");
		ccancel.setForeground(Color.WHITE);
		ccancel.setBackground(Color.RED);
		ccancel.setBounds(716, 175, 89, 23);
		getContentPane().add(ccancel);
		ccancel.setVisible(false);
		ccancel.addActionListener(this);
		
		
		try 
		{
			Connection con=MyConnection.connect();
			String s="select * from information where username=?";
			PreparedStatement ps=con.prepareStatement(s);
			ps.setString(1, user);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				fn.setText(rs.getString("firstname"));
				lblLn.setText(rs.getString("lastname"));
				lblUn.setText(rs.getString("username"));
				dob.setText(rs.getString("dob"));
				country.setText(rs.getString("country"));
				setTitle("Details of "+rs.getString("firstname")+" "+rs.getString("lastname"));
				gen.setText(rs.getString("gender"));
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setSize(815,447);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new Describe("Gaurav");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj=e.getSource();
		if(obj==fedit)
		{
			fedit.setVisible(false);
			fok.setVisible(true);
			fcancel.setVisible(true);
			ftf.setVisible(true);
			ftf.setEditable(true);
			fn.setVisible(false);
			ftf.setText(fn.getText());
		}
		else if(obj==ledit)
		{
			ledit.setVisible(false);
			lok.setVisible(true);
			lcancel.setVisible(true);
			ltf.setVisible(true);
			ltf.setEditable(true);
			lblLn.setVisible(false);
			ltf.setText(fn.getText());
		}
		else if(obj==cedit)
		{
			cbox.setVisible(true);
			country.setVisible(false);
			cedit.setVisible(false);
			cok.setVisible(true);
			ccancel.setVisible(true);
		}
		else if(obj==fok)
		{
			String f=ftf.getText();
			if(f.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please enter your name");
			}
			else
			{
				try 
				{
					Connection con=MyConnection.connect();
					String s="update information set firstname=? where username=?";
					PreparedStatement ps=con.prepareStatement(s);
					ps.setString(1, f);
					ps.setString(2, user);
					ps.executeUpdate();
				}
				catch (SQLException ei) 
				{
					// TODO Auto-generated catch block
					ei.printStackTrace();
				}

				fn.setText(f);
				fok.setVisible(false);
				fcancel.setVisible(false);
				fedit.setVisible(true);
				ftf.setVisible(false);
				fn.setVisible(true);
				setTitle("Details of "+fn.getText()+" "+lblLn.getText());
			}
		}
		else if(obj==lok)
		{
			String l=ltf.getText();
			if(l.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please enter your name");
			}
			else
			{
				try 
				{
					Connection con=MyConnection.connect();
					String s="update information set lastname=? where username=?";
					PreparedStatement ps=con.prepareStatement(s);
					ps.setString(1, l);
					ps.setString(2, user);
					ps.executeUpdate();
				}
				catch (SQLException ei) 
				{
					// TODO Auto-generated catch block
					ei.printStackTrace();
				}

				lblLn.setText(l);
				lok.setVisible(false);
				lcancel.setVisible(false);
				ledit.setVisible(true);
				ltf.setVisible(false);
				lblLn.setVisible(true);
				setTitle("Details of "+fn.getText()+" "+lblLn.getText());
			}
		}
		else if(obj==fcancel)
		{
			ftf.setVisible(false);
			fn.setVisible(true);
			fok.setVisible(false);
			fcancel.setVisible(false);
			fedit.setVisible(true);
		}
		else if(obj==lcancel)
		{
			ltf.setVisible(false);
			lblLn.setVisible(true);
			lok.setVisible(false);
			lcancel.setVisible(false);
			ledit.setVisible(true);
		}
		else if(obj==cok)
		{
			String c=cbox.getSelectedItem().toString();
			try 
			{
				Connection con=MyConnection.connect();
				String s="update information set country=? where username=?";
				PreparedStatement ps=con.prepareStatement(s);
				ps.setString(1, c);
				ps.setString(2, user);
				ps.executeUpdate();
			}
			catch (SQLException ei) 
			{
				// TODO Auto-generated catch block
				ei.printStackTrace();
			}

			country.setText(c);
			cok.setVisible(false);
			ccancel.setVisible(false);
			cedit.setVisible(true);
			cbox.setVisible(false);
			country.setVisible(true);

		}
		if(obj==ccancel)
		{
			cok.setVisible(false);
			ccancel.setVisible(false);
			cedit.setVisible(true);
			cbox.setVisible(false);
			country.setVisible(true);
		}
		if(obj==close)
		{
			dispose();
		}
	}
}
