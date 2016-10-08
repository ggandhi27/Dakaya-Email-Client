package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollBar;

import java.awt.Toolkit;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register extends JDialog implements ActionListener,FocusListener
{
	private static final long serialVersionUID = 1L;
	private JTextField fn;
	private JTextField ln;
	private JTextField un;
	private JPasswordField pf;
	private JPasswordField pf_1;
	JLabel first,Last,User,pass,cpass,country,date,lblGender;
	JComboBox yr,m,d,Country;
	private JButton submit;
	private JButton cancel;
	private JLabel lblChooseASecurity;
	private JComboBox ques;
	private JLabel lblNewLabel_1;
	private JTextField ans;
	JLabel lblNewLabel = new JLabel("Connecting you with trust");
	JRadioButton male = new JRadioButton("Male");
	JRadioButton female = new JRadioButton("Female");
	JLabel lblWelcomeToDakaya = new JLabel("Welcome To DAKaya");
	String s;
	int c=0;
	public Register() 
	{
		setResizable(false);
		setModal(true);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Gandhi Bhai Java\\DAKAYA.COM\\Images\\logo2 (3).jpg"));
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		first = new JLabel("First Name  :");
		first.setBounds(210, 147, 81, 14);
		getContentPane().add(first);
		
		
		fn = new JTextField();
		fn.setBounds(350, 144, 153, 20);
		getContentPane().add(fn);
		fn.setColumns(10);
		fn.addFocusListener(this);
		
		Last = new JLabel("Last Name  :");
		Last.setBounds(210, 180, 81, 14);
		getContentPane().add(Last);
		
		User = new JLabel("Choose a  username  :");
		User.setBounds(210, 216, 141, 14);
		getContentPane().add(User);
		
		pass = new JLabel("Create Password  :");
		pass.setBounds(210, 249, 117, 14);
		getContentPane().add(pass);
		
		cpass = new JLabel("Confirm Password  :");
		cpass.setBounds(210, 284, 117, 14);
		getContentPane().add(cpass);
		
		country = new JLabel("Country  :");
		country.setBounds(210, 324, 81, 14);
		getContentPane().add(country);
		
		ln = new JTextField();
		ln.setColumns(10);
		ln.setBounds(350, 177, 153, 20);
		getContentPane().add(ln);
		ln.addFocusListener(this);
		
		un = new JTextField();
		un.setColumns(10);
		un.setBounds(350, 213, 153, 20);
		getContentPane().add(un);
		
		Country = new JComboBox();
		Country.setModel(new DefaultComboBoxModel(new String[] {"Select", "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo, Republic of the", "Congo, Democratic Republic of the", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "St. Kitts and Nevis", "St. Lucia", "St. Vincent and The Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom (UK)", "United States of America (USA)", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City (Holy See)", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"}));
		Country.setBounds(350, 321, 153, 20);
		setTitle("Welcome To DAKaya");
		getContentPane().add(Country);
		
		date = new JLabel("Date of Birth :");
		date.setBounds(210, 360, 97, 14);
		getContentPane().add(date);
		
		String months[]={"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		m = new JComboBox();
		m.setModel(new DefaultComboBoxModel(months));
		m.setBounds(350, 357, 79, 20);
		getContentPane().add(m);
		m.addActionListener(this);
		
		
		pf = new JPasswordField();
		pf.setBounds(350, 246, 153, 20);
		getContentPane().add(pf);
		
		pf_1 = new JPasswordField();
		pf_1.setBounds(350, 281, 153, 20);
		getContentPane().add(pf_1);
		
		String year[]={"Year","1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016"};
		yr = new JComboBox();
		yr.setModel(new DefaultComboBoxModel(year));
		yr.setBounds(439, 357, 64, 20);
		getContentPane().add(yr);
		
		
		d = new JComboBox();
		d.setBounds(350, 394, 64, 20);
		
		lblGender = new JLabel("Gender  :");
		lblGender.setBounds(210, 426, 81, 14);
		getContentPane().add(lblGender);
		
		
		male.setBounds(350, 422, 109, 23);
		getContentPane().add(male);
		male.setSelected(true);
		
		female.setBounds(350, 448, 109, 23);
		getContentPane().add(female);
		female.setSelected(true);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(male);
		bg.add(female);
		submit = new JButton("Submit");
		submit.setBackground(new Color(102, 153, 255));
		submit.setBounds(210, 613, 89, 23);
		getContentPane().add(submit);
		submit.addActionListener(this);
		
		cancel = new JButton("Cancel");
		cancel.setForeground(new Color(255, 255, 255));
		cancel.setBackground(new Color(255, 0, 0));
		cancel.setBounds(350, 613, 89, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(this);
		
		lblWelcomeToDakaya.setFont(new Font("Footlight MT Light", Font.BOLD, 30));
		lblWelcomeToDakaya.setBounds(200, 24, 333, 53);
		getContentPane().add(lblWelcomeToDakaya);
		
		
		lblNewLabel.setFont(new Font("Segoe Script", Font.ITALIC, 17));
		lblNewLabel.setBounds(210, 75, 293, 44);
		getContentPane().add(lblNewLabel);
		
		lblChooseASecurity = new JLabel("Choose a security Question to answer :");
		lblChooseASecurity.setBounds(210, 490, 300, 14);
		getContentPane().add(lblChooseASecurity);
		
		ques = new JComboBox();
		ques.setModel(new DefaultComboBoxModel(new String[] {"Select Question", "What is your pet's name?", "What is your Nick name?", "What is the birth place of your mother ?", "What is your favourite color?", "What was your first school name ?"}));
		ques.setBounds(210, 515, 248, 20);
		getContentPane().add(ques);
		
		lblNewLabel_1 = new JLabel("Answer : -\r\n");
		lblNewLabel_1.setBounds(210, 546, 165, 14);
		getContentPane().add(lblNewLabel_1);
		
		ans = new JTextField();
		ans.setBounds(210, 571, 249, 20);
		getContentPane().add(ans);
		ans.setColumns(10);
		
		ans.addFocusListener(this);
		ques.addFocusListener(this);
		yr.addFocusListener(this);
		m.addFocusListener(this);
		d.addFocusListener(this);
		pf.addFocusListener(this);
		pf_1.addFocusListener(this);
		Country.addFocusListener(this);
	
		ImageIcon ii=new ImageIcon(getClass().getResource("image/message_icon-1.png"));
		JLabel img=new JLabel(ii);
		img.setBounds(-31,186,271,702);
		getContentPane().add(img);
		
		ImageIcon i=new ImageIcon(getClass().getResource("image/logo2 (3).jpg"));
		JLabel im=new JLabel(i);
		im.setBounds(-40,-128,298,361);
		getContentPane().add(im);
		
		setSize(611,700);
		setVisible(true);
	}
	public static void main(String[] args) 
	{
		new Register();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object x=e.getSource();
		
		if(x==submit)
		{
			System.out.println("submit is pressed");
			
			if((!fn.getText().equals(""))&&(!ln.getText().equals(""))&&(!un.getText().equals(""))&&(!ans.getText().equals(""))&&(!pf.getPassword().equals(""))&&(!pf_1.getPassword().equals(""))&&(yr.getSelectedIndex()>0)&&(m.getSelectedIndex()>0)&&(d.getSelectedIndex()>0)&&(Country.getSelectedIndex()>0)&&(ques.getSelectedIndex()>0))
			{
				String fname=fn.getText();
				String lname=ln.getText();
				String uname=un.getText();
				String pass=new String(pf.getPassword());
				String cpass = new String(pf_1.getPassword());
				String cou=Country.getSelectedItem().toString();
				String DATE=d.getSelectedItem().toString()+"-"+m.getSelectedIndex()+"-"+yr.getSelectedItem().toString();
				String q=ques.getSelectedItem().toString();
				String answer=ans.getText();
				String gender="";
				if(male.isSelected())
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}
				try {
					String s="Insert into information(username,password,firstname,lastname,country,dob,gender,ques,answer) values(?,?,?,?,?,?,?,?,?)";
					String check="select * from information where username=?";
					Connection con=MyConnection.connect();
					PreparedStatement ps=con.prepareStatement(s);
					PreparedStatement ch=con.prepareStatement(check);
					ch.setString(1,uname);
					ResultSet rs=ch.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null,"User name already exists.");	
					}
					else if((uname.indexOf(' ')>=0)||(uname.indexOf('/')>=0)||(uname.indexOf('?')>=0)||(uname.indexOf(',')>=0))
					{
						JOptionPane.showMessageDialog(null,"Username cannot have Blank spaces");
					}
//					else if(pass.equals(cpass)==false)
//					{
//						System.out.println("Pass = "+pass);
//						System.out.println("Cpass = "+cpass);
//						JOptionPane.showMessageDialog(null, "Password doesn't match");
//					}
					
					else
					{
						ps.setString(1,uname);
						ps.setString(2,pass);
						ps.setString(3,fname);
						ps.setString(4,lname);
						ps.setString(5,cou);
						ps.setString(6,DATE);
						ps.setString(7, gender);
						ps.setString(8,q);
						ps.setString(9,answer);
						ps.executeUpdate();
						int l=uname.length();
						JOptionPane.showMessageDialog(null, "Congratulations! You are successfully \n registered now");
						dispose();
					}
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please fill all the Fields");
			}
		}
		else if(x==cancel)
		{
			dispose();
		}
		
	}
	@Override
	public void focusGained(FocusEvent e) 
	{
		Object obj=e.getSource();
		if(obj==m)
		{
			int index=m.getSelectedIndex();
			String dt1[]={"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
			String dt2[]={"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
			String dt3[]={"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
			String dt4[]={"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
			
			if((index==1)||(index==3)||(index==5)||(index==7)||(index==8)||(index==10)||(index==12))
			{
				c++;
				d.setModel(new DefaultComboBoxModel(dt1));
			}
			else if(index==0)
			{}
			else if(index==2)
			{
				c++;
				if(yr.getSelectedIndex()>0)
				{
					String s=(String)yr.getSelectedItem();
					if(Integer.parseInt(s)%4==0)
					{
						d.setModel(new DefaultComboBoxModel(dt4));
					}
					else
					{
						d.setModel(new DefaultComboBoxModel(dt3));
					}
					
				}
			}
			else
			{
				c++;
				d.setModel(new DefaultComboBoxModel(dt2));
			}
			getContentPane().add(d);
		}
		/*if(obj==fn)
		{
			if(fn.getText().equals(""))
			{
				submit.setEnabled(false);
			}
			else
			{
				submit.setEnabled(true);
			}
		}
		else if(obj==ln)
		{
			if(ln.getText().equals(""))
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==un)
		{
			if(un.getText().equals(""))
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==pf)
		{
			if(pf.getPassword().equals(""))
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==pf_1)
		{
			if(pf_1.getPassword().equals(""))
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==Country)
		{
			if(Country.getSelectedIndex()==0)
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==yr)
		{
			if(yr.getSelectedIndex()==0)
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==d)
		{
			if(d.getSelectedIndex()==0)
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==ques)
		{
			if(ques.getSelectedIndex()==0)
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==ans)
		{
			if(ans.getText().equals(""))
			{
				submit.setEnabled(false);
			}
		}*/
	}
	@Override
	public void focusLost(FocusEvent e)
	{
		Object obj=e.getSource();
		if(obj==m)
		{
			int index=m.getSelectedIndex();
			String dt1[]={"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
			String dt2[]={"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
			String dt3[]={"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
			String dt4[]={"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
			
			if((index==1)||(index==3)||(index==5)||(index==7)||(index==8)||(index==10)||(index==12))
			{
				d.setModel(new DefaultComboBoxModel(dt1));
			}
			else if(index==0)
			{
				submit.setEnabled(false);
			}
			else if(index==2)
			{
				if(yr.getSelectedIndex()>0)
				{
					String s=(String)yr.getSelectedItem();
					if(Integer.parseInt(s)%4==0)
					{
						d.setModel(new DefaultComboBoxModel(dt4));
					}
					else
					{
						d.setModel(new DefaultComboBoxModel(dt3));
					}
					
				}
			}
			else
			{
				d.setModel(new DefaultComboBoxModel(dt2));
			}
		}
		/*if(obj==fn)
		{
			if(fn.getText().equals(""))
			{
				submit.setEnabled(false);
			}
			else
			{
				submit.setEnabled(true);
			}
		}
		else if(obj==ln)
		{
			if(ln.getText().equals(""))
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==un)
		{
			if(un.getText().equals(""))
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==pf)
		{
			if(pf.getPassword().equals(""))
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==pf_1)
		{
			if(pf_1.getPassword().equals(""))
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==Country)
		{
			if(Country.getSelectedIndex()==0)
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==yr)
		{
			if(yr.getSelectedIndex()==0)
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==d)
		{
			if(d.getSelectedIndex()==0)
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==ques)
		{
			if(ques.getSelectedIndex()==0)
			{
				submit.setEnabled(false);
			}
		}
		else if(obj==ans)
		{
			if(ans.getText().equals(""))
			{
				submit.setEnabled(false);
			}
		}*/
	}
}
