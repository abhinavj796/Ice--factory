import javax.swing.*;	

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Stockfingoods implements ActionListener{

	JLabel inm,ity,fla,mrp,qty,tamt;
	
	JTextField i,it,fl,mr,qtytxt,tamtxt;
	
	
JFrame fingoods;
	JPanel p1,p2,p3;
	JButton enter,print,back;
	Statement st;
	Connection con;
	
	DB_conn obj=new DB_conn();
	
Stockfingoods()
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
		
	
	fingoods = new JFrame("Stock Of Finished Goods");
	fingoods.setLayout(new FlowLayout());
	
	p1=new JPanel();
	p1.setLayout(new GridLayout(6,2));
	
	p2=new JPanel();
	p2.setLayout(new FlowLayout());
	
	inm=new JLabel("Item Name=");
	ity=new JLabel("Item Type=");
	fla=new JLabel("Flavour=");
	mrp=new JLabel("MRP=");
	qty=new JLabel("Quantity avalible");
	tamt=new JLabel("Total Amount=");
	

	i=new JTextField(15);
	it=new JTextField(15);
	fl=new JTextField(15);
	mr=new JTextField(15);
	qtytxt=new JTextField(15);
	tamtxt=new JTextField(15);
	
	p1.add(inm);
	p1.add(i);
	p1.add(ity);
	p1.add(it);
	p1.add(fla);
	p1.add(fl);
	p1.add(qty);
	p1.add(qtytxt);
	p1.add(mrp);
	
	p1.add(mr);
	p1.add(tamt);
	p1.add(tamtxt);
	
	enter = new JButton("Enter",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
	  enter.addActionListener(this);
	  
	 print = new JButton("Print",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
	  print.addActionListener(this);
	
	  back = new JButton("Back",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
	  back.addActionListener(this);
	  
	  
	  
	  p2.add(enter);
	  p2.add(print);
	  p2.add(back);
	
	fingoods.add(p1);
	fingoods.add(p2);
	

	 fingoods.setSize(400, 300);
	 fingoods.setVisible(true);
	  
	  Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
       fingoods.setLocation((screen.width-500)/2,((screen.height-500)/2));	
	
}
public void actionPerformed(ActionEvent ae) {
	 Object source= ae.getSource();
	 if(source==enter)
	 {
		 try {
			
			 String p="insert into stockfinished values('"+i.getText()+"','"+it.getText()+"','"+fl.getText()+"','"+mr.getText()+"','"+qtytxt.getText()+"','"+tamtxt.getText()+"')";
				st.executeUpdate(p);                                        
				
	            JOptionPane.showMessageDialog(null,"record updated");
	            
	            con.close();
			 }
			 catch(Exception e)
			 {
				 JOptionPane.showMessageDialog(null,"record not updated"+e);
			 }
			 } 
	 
	 if(source==print)
	 {
		 JOptionPane.showMessageDialog(null,"Printer Unavalible",null, JOptionPane.ERROR_MESSAGE); 
	 }
	 
	 if(source==back)
	 {
		fingoods.dispose();
		new MainMenu();
	 } 
}

public static void main(String[] s)
{
	new Stockfingoods();
}
}
