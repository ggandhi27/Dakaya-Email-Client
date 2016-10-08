package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class About extends JDialog implements ActionListener
{
	JButton btnClose = new JButton("Close");
	public About()
	{
		setModal(true);
		setResizable(false);
		getContentPane().setLayout(null);
		
		btnClose.setBounds(148, 258, 136, 23);
		getContentPane().add(btnClose);
		
		JLabel label = new JLabel("");
		label.setBounds(47, 66, 46, 14);
		getContentPane().add(label);
		
		JLabel lblTheDeveloperTo = new JLabel("The  developer   of   this   project   is ");
		lblTheDeveloperTo.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		lblTheDeveloperTo.setBounds(25, 93, 424, 23);
		getContentPane().add(lblTheDeveloperTo);
		
		JLabel lblGauravGandhi = new JLabel("Gaurav Gandhi");
		lblGauravGandhi.setFont(new Font("Vladimir Script", Font.BOLD, 35));
		lblGauravGandhi.setBounds(108, 127, 249, 72);
		getContentPane().add(lblGauravGandhi);
		btnClose.addActionListener(this);
		setTitle("About the Developers");
		setSize(465,321);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnClose)
		{
			dispose();
		}
	}
}
