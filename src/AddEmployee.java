import java.sql.*;
import java.awt.print.*;

import javax.swing.*;	

import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class AddEmployee implements ActionListener
{
JLabel name,salary,address,workbasis,post,empid,mno;
JTextField nametxt,salarytxt,addresstxt,workbasistxt,posttxt,empidtxt,mnotxt;
JPanel p1,p2;
JButton Save,print,back;
JFrame Aemp;
Statement st;
Connection con;
JComboBox workbasiscb;

String a[]= {"Weekly","Monthly"};




public AddEmployee()
{
	
	DB_conn obj=new DB_conn();
	
		try{
String url="jdbc:oracle:thin:@localhost:1521:xe";
  			Class.forName("oracle.jdbc.driver.OracleDriver");
  			 con=DriverManager.getConnection(url,obj.username(),obj.pass());   			
  		 st=con.createStatement();
}
catch(Exception e)
{
}
	
	
	Aemp = new JFrame("emp");
	Aemp.setLayout(new FlowLayout());
	
	
	workbasiscb = new JComboBox<String>(a);
	
	
	empid= new JLabel("Employee ID");
	name= new JLabel("Employee Name");
	salary= new JLabel("Salary Fixed");
	address= new JLabel("Address");
	post= new JLabel("Work to do");
	workbasis= new JLabel("working parameters");
	 mno = new JLabel("Mobile No.");
	 
	 empidtxt=new JTextField(15);
	 empidtxt.setText("E-");
	 nametxt=new JTextField(15);
	 salarytxt=new JTextField(15);
	 addresstxt=new JTextField(15);
	 workbasiscb = new JComboBox<String>(a);
	 posttxt=new JTextField(15);
	 
	 mnotxt=new JTextField(15);
	 
	 
	 p1=new JPanel();
	 p1.setLayout(new GridLayout(7,2));
	 
	 
	 p2=new JPanel();
	 p2.setLayout(new FlowLayout());
	 
	 Save= new JButton("Enter",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
	  Save.addActionListener(this);
	  
	  print = new JButton("Print",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
	  print.addActionListener(this);
	  
	  back = new JButton("Main menu",new ImageIcon(Login.class.getResource("images/back.png"))); 
	  back.addActionListener(this);
	  
	  
	 
	 p1.add(empid);
	 p1.add(empidtxt);
	 p1.add(name);
	 p1.add(nametxt);
	 p1.add(salary);
	 p1.add(salarytxt);
	 p1.add(address);
	 p1.add(addresstxt);
	 p1.add(post);
	 p1.add(posttxt);
	 p1.add(workbasis);
	 p1.add(workbasiscb );
	 p1.add(mno);
	 p1.add(mnotxt);
	 
	 p2.add(Save);
	 p2.add(print);
	 p2.add(back);
	 
	 Aemp.add(p1);
	Aemp.add(p2);
	
	Aemp.setSize(400, 300);
	Aemp.setResizable(false);
	  
	  Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
      Aemp.setLocation((screen.width-500)/2,((screen.height-500)/2));
      Aemp.setVisible(true);
	 
}

void insert()
{
	try {
		
		 String ab= (String)workbasiscb.getSelectedItem();
		
	 String p="insert into emp values('"+empidtxt.getText()+"','"+nametxt.getText()+"','"+salarytxt.getText()+"','"+addresstxt.getText()+"','"+posttxt.getText()+"','"+ab+"','"+mnotxt.getText()+"')";
		st.executeUpdate(p);                                        
			 
        JOptionPane.showMessageDialog(null,"record updated");
        
        con.close();
	 }
	 catch(Exception e)
	 {
		 JOptionPane.showMessageDialog(null,"record not updated"+e);
	 }
}

public void actionPerformed(ActionEvent ae) {
	 Object source= ae.getSource();
	 if(source==Save)
	 {
		 insert();
		 } 
	 
	 if(source==print)
	 {
	 		

		

		 			   
		 	 	 }
	 if(source==back)
	 {
		new MainMenu();
		Aemp.dispose();
	 } 
}
public static void main(String[] args)
{
	new AddEmployee();
}
}
