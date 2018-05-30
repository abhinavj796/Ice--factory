import javax.swing.*;	

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.print.*;

//this class can be used for adding a new icecream product.....

class ItemName implements ActionListener{
	JTextField inms, favs,itytxt, mrps;
	String i,f,m;
	JLabel inm,fav,ity,mrp;
	
	Statement st,crst;
	Connection con;
	JFrame iname;
	JPanel p1,p3;
	JButton enter,print,back;
	
	DB_conn obj=new DB_conn();


	ItemName(){
		

		try{
String url="jdbc:oracle:thin:@localhost:1521:xe";
  			Class.forName("oracle.jdbc.driver.OracleDriver");
  			 con=DriverManager.getConnection(url,obj.username(),obj.pass());   			
  		 st=con.createStatement();
  		crst =con.createStatement();
  		//crst.executeQuery("CREATE TABLE  items (ItemName varchar(30),Flavour varchar(30),ItemType varchar(30),MRP varchar(30))");
}
catch(Exception e)
{

}
	
		 iname = new JFrame("Item Name");
		//iname.setSize(500, 500);
		//iname.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iname.setLayout(new FlowLayout());
		
		p1=new JPanel();
		p1.setLayout(new GridLayout(4,2));
		
		
		inm=new JLabel("Item Name(cup 50 ml) =");
		fav=new JLabel("Flavour(vanilla) =");
		ity=new JLabel("Item Type =");
		mrp=new JLabel("M.R.P(rs.5) =");
		inms=new JTextField(20);
         favs=new JTextField(20);
         itytxt=new JTextField(20);
          mrps=new JTextField(20);
		
		
		
		
		
		
		
		
	
		
		p1.add(inm);
		p1.add(inms);
		p1.add(fav);
		p1.add(favs);
		p1.add(ity);
		p1.add(itytxt);
		p1.add(mrp);
		p1.add(mrps);
		
	
		
	
		
		enter = new JButton("Enter",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
		  enter.addActionListener(this);
		  
		 print = new JButton("Print",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
		  print.addActionListener(this);
		  
		  back = new JButton("Back",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
		  back.addActionListener(this);
		
		  p3=new JPanel();
			p3.setLayout(new FlowLayout());
			  p3.add(enter);
			  p3.add(print);
			  p3.add(back);
			  
			  iname.add(p1);
			  iname.add(p3);
			  Container c=iname.getContentPane();
			  c.setBackground(Color.PINK);	
			  iname.setSize(600, 300);
			  iname.setVisible(true);
			  
			  Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
		        iname.setLocation((screen.width-500)/2,((screen.height-500)/2));	

			
		
	}
	public void actionPerformed(ActionEvent ae) {
		 Object source= ae.getSource();
		 if(source==enter)
		 {
			 try {
				
					//inms, favs,itytxt, mrps;
				 String p="insert into items values('"+inms.getText()+"','"+favs.getText()+"','"+itytxt.getText()+"','"+mrps.getText()+"')";
					st.executeUpdate(p);                                        
					// insert into partyentry values('parth stores','576 sec15','2234432','mr. ramesh');	 
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
		 		

			 	

			 			   
			 	 	 }
		 if(source==back)
		 {
			new MainMenu();
			iname.dispose();
		 } 
		
	 }
	public static void main(String[] args)
	{
	new ItemName();	
		}
	
}