import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Staffsection implements ActionListener {
	JLabel eid;
	JLabel s_name;             
	JLabel wagesbasis;
	JLabel salary;
	
	JLabel leaves;

	
	JLabel stfhead;
	JLabel netsalary;
	
	JTextField Eid;
	JTextField Wagesbasis;
	JTextField S_name;
    JTextField Salary;
	JTextField Leaves;
  
	JTextField Netsalary;
	
	
	JButton enter,print,back,load;
	JButton addemp,viewemp;
	
	JFrame jstaff;
	
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	
	Statement st;
	Connection con;

	String a,b;
	int c;
	
	DB_conn obj=new DB_conn();
	
	int ns;
	GridBagConstraints gbc=new GridBagConstraints();

	public Staffsection() {
		
	
	
			
				try{
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		  			Class.forName("oracle.jdbc.driver.OracleDriver");
		  			 con=DriverManager.getConnection(url,obj.username(),obj.pass());   			
		  		 st=con.createStatement();
		}
		catch(Exception e)
		{
		}
			
		
		jstaff=new JFrame();
		jstaff.setLayout(new GridLayout(3,1));
		jstaff.setSize(500,500);
		jstaff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	
		
		
	panel1=new JPanel(new FlowLayout());
	//panel2.setBackground(Color.pink);
	stfhead=new JLabel("Staff Section");
	stfhead.setFont(new Font("Serif", Font.HANGING_BASELINE, 45));
	gbc.fill=GridBagConstraints.HORIZONTAL;
	
	panel1.add(stfhead);
	jstaff.setResizable(false);
	jstaff.setLocationRelativeTo(null);
	panel2=new JPanel(new GridLayout(7,2));
	
	
	//staff member id
	eid=new JLabel("Staff ID");
	eid.setFont(new Font("Serif", Font.PLAIN, 20));
	panel2.add(eid);
	Eid=new JTextField();
	panel2.add(Eid);
	
	
	
	//staff Name
	s_name=new JLabel("Staff Name");
	s_name.setFont(new Font("Serif", Font.PLAIN, 20));
	panel2.add(s_name);
	S_name=new JTextField();
	panel2.add(S_name);
	
	//Wage Basis
	wagesbasis=new JLabel("Wage Basis");
	wagesbasis.setFont(new Font("Serif", Font.PLAIN, 20));
	panel2.add(wagesbasis);
	Wagesbasis=new JTextField(20);
	panel2.add(Wagesbasis);

	//Salary
	salary=new JLabel("Salary");
	salary.setFont(new Font("Serif", Font.PLAIN, 20));
	panel2.add(salary);
	Salary=new JTextField(15);
	panel2.add(Salary);
	//Month


	
	//Total
	leaves=new JLabel("Leaves Taken");
	leaves.setFont(new Font("Serif", Font.PLAIN, 20));
	panel2.add(leaves);
	Leaves=new JTextField();
	panel2.add(Leaves);
	
	netsalary=new JLabel("Net Salary");
	netsalary.setFont(new Font("Serif", Font.PLAIN, 20));
	panel2.add(netsalary);
	Netsalary=new JTextField();
	panel2.add(Netsalary);
	
	
	

	panel3=new JPanel(new FlowLayout());
	enter=new JButton("Enter",new ImageIcon(Staffsection.class.getResource("images/backup.png") ));
	
	enter.addActionListener(this);
	print=new JButton("Print", new ImageIcon(Staffsection.class.getResource("images/print.png") ) );
	print.addActionListener(this);
	back=new JButton("Back",new ImageIcon(Staffsection.class.getResource("images/back.png") ));
	back.addActionListener(this);
	load=new JButton("Load",new ImageIcon(Staffsection.class.getResource("images/find.gif") ));
	load.addActionListener(this);
	
	addemp=new JButton("Add Employee", new ImageIcon(Staffsection.class.getResource("images/emp1.png") ) );
	addemp.addActionListener(this);
	viewemp=new JButton("View Employee",new ImageIcon(Staffsection.class.getResource("images/emp1.png") ));
	viewemp.addActionListener(this);
	
	
	panel3.add(load);
	panel3.add(enter);
	panel3.add(print);
	panel3.add(back);
	panel3.add(addemp);
	panel3.add(viewemp);
	
	
	jstaff.add(panel1,BorderLayout.NORTH);
	jstaff.add(panel2);
	jstaff.add(panel3);
	jstaff.setVisible(true);
	
	}


public void actionPerformed(ActionEvent ae) {
	 Object source= ae.getSource();
	 if(source==load)
	 {
		try {
			ResultSet rs=st.executeQuery("select * from emp where eid='"+Eid.getText()+"'");   			
   			while(rs.next()){
   		//	String	id=rs.getString(1);
   		
   			//if(id==Eid.getText())
   			//{
   				S_name.setText(rs.getString(2));
   				
   				Salary.setText(rs.getString(3));
   				c=Integer.parseInt(rs.getString(3));
   				
   				Wagesbasis.setText(rs.getString(6));
   				b=rs.getString(6);
   				
   			//}
   			}	
   		}
   			catch(Exception e)
   			{
   			}
		
   					
	 }
	 if(source==enter)
	 {
		
		 int l=Integer.parseInt(Leaves.getText());
			if(b.equals("weekly"))
			{
			
			
			ns=c-(l*(c/7));
		
			}
			if(b.equals("monthly"))
			{
				
				ns=c-(l*(c/30));
			
			}
		 
		 
			Netsalary.setText(""+ns);
	 }
	 if(source==print)
	 {
		 JOptionPane.showMessageDialog(null,"Printer Unavalible",null, JOptionPane.ERROR_MESSAGE); 
	 } 
	 if(source==back)
	 {
		 jstaff.dispose();
		 new MainMenu();
		
	 } 
	 if(source==addemp)
	 {
		 new AddEmployee();
		 jstaff.dispose();
	 }
	 if(source==viewemp)
	 {
		 new ViewEmps();
		 jstaff.dispose();
	 }
}

public static void main(String [] args) {
	new Staffsection();
}
}