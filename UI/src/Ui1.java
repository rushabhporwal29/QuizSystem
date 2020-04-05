import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Ui1 implements ActionListener{
	JFrame frame;
	JPanel panel;
	JLabel lcreateuser,lcreatepass,lconfirmpass,lmobile,lemail;
	JTextField tusername,tmobile,temail;
	JPasswordField password,confirmpassword;
	JButton button;
	Ui1(){
		frame=new JFrame("sign up window");
		panel=new JPanel();
		tusername=new JTextField(10);
		tmobile=new JTextField(10);
		temail=new JTextField(25);
		password=new JPasswordField(10);
		confirmpassword=new JPasswordField(10);
		lcreateuser=new JLabel("create new user name:");
		lcreatepass=new JLabel("create password:");
		lconfirmpass=new JLabel("confirm password:");
		lmobile=new JLabel("Mobile : ");
		lemail=new JLabel("E-mail : ");
		button=new JButton("create account");
		button.addActionListener(this);
		panel.add(lcreateuser);
		panel.add(tusername);
		panel.add(lcreatepass);
		panel.add(password);
		panel.add(lconfirmpass);
		panel.add(confirmpassword);
		panel.add(lmobile);
		panel.add(tmobile);
		panel.add(lemail);
		panel.add(temail);
		panel.add(button);
		frame.add(panel);
		frame.setSize(350,350);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new Ui1();
	}
	@Override
	public void actionPerformed(ActionEvent obj) {
		String dusername="",dpassword="",cpassword="",mobile,email;
		dusername=tusername.getText();
		dpassword= String.valueOf(password.getPassword());
		cpassword= String.valueOf(confirmpassword.getPassword());
		mobile=tmobile.getText();
		email=temail.getText();
		if(obj.getSource()==button) {
			if(dpassword.equals(cpassword)) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "vinithande4", "Swarali123");
					System.out.println("Connection Success ...");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from form");
					PreparedStatement pr = con.prepareStatement("insert into form values(?,?,?,?)");
					pr.setString(1, dusername);
					pr.setString(2, dpassword);
					pr.setString(3,mobile);
					pr.setString(4,email);
					pr.execute();
					JOptionPane.showMessageDialog(frame, "Account Created!!");
					JOptionPane.showMessageDialog(frame, "Welcome to registration page!!");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Password Mismatch !!!");
			}
		}
	}
}
