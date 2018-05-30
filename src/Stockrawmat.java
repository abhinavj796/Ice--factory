import javax.swing.*;	

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Stockrawmat implements ActionListener{
	JPanel p1,p2,p3;
	JLabel ppnm,inm,ity,mrp,prt,tpd,np,title;
	JTextField ppnmtxt,inmtxt,itytxt,mrptxt,prttxt,tpdtxt,nptxt;
	JButton enter,print,back;
	JFrame srm;
	
	Statement st;
	Connection con;
	
	DB_conn obj=new DB_conn();

	public Stockrawmat()
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
		
		p1=new JPanel();
		p1.setLayout(new FlowLayout());
		
		 p2=new JPanel();
		p2.setLayout(new GridLayout(7,2));
		
		p3=new JPanel();
		p3.setLayout(new FlowLayout());
		
		 srm= new JFrame("stock of raw material");
	  srm.setLayout(new FlowLayout());
		
	 ppnm=new JLabel("Party ID");
		inm= new JLabel("Item name");
		ity= new JLabel("Item type");
		mrp= new JLabel("MRP");
		prt= new JLabel("Purchase rate");
		tpd= new JLabel("Total product");
		np= new JLabel("Net Cost");
		title= new JLabel("STOCK ITEM RAW MATERIALS");
		
	 ppnmtxt=new JTextField(15);
		inmtxt=new JTextField(15);
		itytxt=new JTextField(15);
		mrptxt=new JTextField(15);
		prttxt=new JTextField(15);
		tpdtxt=new JTextField(15);
		nptxt=new JTextField(15);
		
		 enter = new JButton("Enter",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
		  enter.addActionListener(this);
		  
		  print = new JButton("Print",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
		  print.addActionListener(this);

			 back = new JButton("Back",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
			 back.addActionListener(this);
	
		p1.add(title);
		
		p2.add(ppnm);
		p2.add(ppnmtxt);
		p2.add(inm);
		p2.add(inmtxt);
		p2.add(ity);
		p2.add(itytxt);
		p2.add(mrp);
		p2.add(mrptxt);
		p2.add(prt);
		p2.add(prttxt);
		p2.add(tpd);
		p2.add(tpdtxt);
		p2.add(np);
		p2.add(nptxt);
		
		p3.add(enter);
		p3.add(print);
		p3.add(back);
		
		srm.add(p1);
		srm.add(p2);
		srm.add(p3);
		
		srm.setSize(400, 300);
		srm.setVisible(true);
		 Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	       srm.setLocation((screen.width-500)/2,((screen.height-500)/2));
	}
	 public void actionPerformed(ActionEvent ae) {
		 Object source= ae.getSource();
		 if(source==enter)
		 {
			 int a;
			 a=Integer.parseInt(prttxt.getText())*Integer.parseInt(tpdtxt.getText());
			 nptxt.setText(""+a);
			 
			 try {
				                                                 
				 String p="insert into rawmaterial values('"+ppnmtxt.getText()+"','"+inmtxt.getText()+"','"+itytxt.getText()+"','"+mrptxt.getText()+"','"+prttxt.getText()+"','"+tpdtxt.getText()+"','"+nptxt.getText()+"')";
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
			
			srm.dispose();
			 new MainMenu();
		 }
		 
	 }
	 
public static void main(String[] args)
{
new Stockrawmat();	
	}
}

