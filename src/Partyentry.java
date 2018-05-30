import javax.swing.*;	

import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;

class Partyenrty implements ActionListener{
	JPanel p1,p2,p3;
	JLabel title,pno,pnm,add,mno,pfl,onm;
	JTextField pnotxt,pnmtxt,addtxt,mnotxt,onmtxt;
	JComboBox <String>pf;
	String a[]= {"Sales","Purchase"};
	JButton enter,print,back;
	JFrame pent;
	  Statement st;
	  Connection con;
	  
	 DB_conn obj=new DB_conn();
	public Partyenrty()
	{
		
		try{
String url="jdbc:oracle:thin:@localhost:1521:xe";
   			Class.forName("oracle.jdbc.driver.OracleDriver");
   			 con=DriverManager.getConnection(url,obj.username(),obj.pass());   			
   		 st=con.createStatement();
}
catch(Exception e)
{
}

		  pent= new JFrame("Party Entry");
		  pent.setLayout(new FlowLayout());
	
		  
		  pno = new JLabel("Party Id");
		pnm = new JLabel("Party name");
		add=new JLabel("Address");
		mno=new JLabel("Mobile No.");
		pfl=new JLabel("Party Deals");
		onm=new JLabel("Owner name");
		
		pnotxt=new JTextField(15);
		  pnmtxt=new JTextField(15);
		  addtxt=new JTextField(15);
		  mnotxt=new JTextField(15);
		  
		  pf = new JComboBox<String>(a);
		  onmtxt=new JTextField(15);
		  
		
		  
		  
		  enter = new JButton("Enter",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
		  enter.addActionListener(this);
		  
		  print = new JButton("Print",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
		  print.addActionListener(this);
		  
		  back = new JButton("Back",new ImageIcon(Login.class.getResource("images/back.png"))); 
		  back.addActionListener(this);
		  
		  p2=new JPanel();
		  p2.setLayout(new GridLayout(6,2));
		  
		  p2.add(pno);
		  p2.add(pnotxt);
		  p2.add(pnm);
		p2.add(pnmtxt);
		p2.add(add);
		p2.add(addtxt);
		p2.add(mno);
		p2.add(mnotxt);
		p2.add(pfl);
		p2.add(pf);
		p2.add(onm);
		p2.add(onmtxt);
		 
		p3=new JPanel();
		p3.setLayout(new FlowLayout());
		  p3.add(enter);
		  p3.add(print);
		  p3.add(back);
		  
		  
		  p1=new JPanel();
		  p1.setLayout(new FlowLayout());
		  
		 p1.add(title = new JLabel("PARTY ENTRY"));
	
		  
		  pent.add(p1);
		  pent.add(p2);
		  pent.add(p3);
		
		pent.setSize(400, 300);
		
		 Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	       pent.setLocation((screen.width-500)/2,((screen.height-500)/2));
	       pent.setBackground(Color.PINK);
	       pent.setResizable(false);
	       pent.setVisible(true);
	}
	 public void actionPerformed(ActionEvent ae) {
		 Object source= ae.getSource();
		 if(source==enter)
		 {
			insert(); 
		 }
		 if(source==print)
	 	 {
		 		

			

			 			   
			 	 	 }
		 if(source==back)
		 {
			new MainMenu();
			pent.dispose();
		
		 } 
	 }
	 public void insert()
	 {
		 
		 try {
			 String ab= (String)pf.getSelectedItem();
			 String p="insert into partyentry values('"+pnotxt.getText()+"','"+pnmtxt.getText()+"','"+addtxt.getText()+"','"+mnotxt.getText()+"','"+ab+"','"+onmtxt.getText()+"')";
			st.executeUpdate(p);                                         
            JOptionPane.showMessageDialog(null,"record updated");
            
            con.close();
		 }
		 catch(Exception e)
		 {
			 JOptionPane.showMessageDialog(null,"record not updated"+e);
		 }
		 }
	 
public static void main(String[] s)
{
	new Partyenrty();
}
}
