
import java.sql.*;
import java.awt.print.*;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;


public class Bills implements ActionListener
{
	

	
	JLabel title,pid,pnm,address,mno,pdeals,onm,itm,qty,mrp,total,billid,Month;
	JTextField pidtxt,pnmtxt,addresstxt,mnotxt,pdealstxt,onmtxt,itmtxt,qtytxt,mrptxt,totaltxt,billidtxt,month;
   JFrame jbill;
   JPanel p1,p1_p1,p1_p2,p1_p3;
   JPanel p2,p2_p1,p2_p2,pn;
   JPanel p3,p3_p1,p3_p2,p3_p3;
   DefaultTableModel tmodel;
	JTable table;
	JButton submit,enter,print,back,load;
	JLabel totalcost,disc,tdisc,nettot;
	JTextField totalcosttxt,disctxt,tdisctxt,nettottxt;
	
	String s;
	
	int tot=0;
	
	int a = 0,b,c;
	
	JComboBox <String>itembox;
	
	String i[]=new String[1000];
	
	 Statement st;
	  Connection con;
	  
DB_conn obj=new DB_conn();

public Bills()
   {
		try{
String url="jdbc:oracle:thin:@localhost:1521:xe";
  			Class.forName("oracle.jdbc.driver.OracleDriver");
  			 con=DriverManager.getConnection(url,obj.username(),obj.pass());   			
  		 st=con.createStatement();
  		 
  		 ResultSet rs=st.executeQuery("select * from items");
  		 for(int a=0;rs.next();a++)
  			 
  		 {
  			 
  			 i[a]= rs.getString(1);
  			
  		
  			
  		 }
		}
		catch(Exception e)
		{
			
		}
	   
		 itembox = new JComboBox<String>(i);
	itembox.addActionListener(
			new ActionListener()
			 {
			public void actionPerformed(ActionEvent ae) 
			{
				 s=(String)itembox.getSelectedItem();
				try{
					
					   ResultSet rs=st.executeQuery("select * from items where itemname='"+s+"'");
						while(rs.next())
						{
							
								mrptxt.setText(rs.getString(4));
								a=Integer.parseInt(rs.getString(4));
					
						}	
			
			}
				catch(Exception e)
				{
					
				}
			}
			}
			);
		
	   jbill=new JFrame();
		jbill.setLayout(new GridLayout(3,1));
		 Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	      jbill.setLocation((screen.width-800)/2,((screen.height-500)/2));
		//jbill.setLocationRelativeTo(null);
	    jbill.setSize(900, 500);
		jbill.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p1=new JPanel();
		p1.setLayout(new GridLayout(3,1));
		
		
		p1_p1=new JPanel(new FlowLayout());
		title=new JLabel("Billing Section");
		title.setFont(new Font("Serif", Font.HANGING_BASELINE,35));
		p1_p1.add(title);
		
				
		p1_p2=new JPanel(new GridLayout(2,6));
		p1_p3=new JPanel(new FlowLayout());
		
		pid=new JLabel("Party id");
		pnm=new JLabel("Party Name");
		address=new JLabel("Address");
		mno=new JLabel("MobileNo.");
		pdeals=new JLabel("Party Deals");
		onm=new JLabel("Owner Name");
		
		pidtxt=new JTextField(15);
		pnmtxt=new JTextField(15);
		
		addresstxt=new JTextField(15);
		mnotxt=new JTextField(15);
		pdealstxt=new JTextField(15);
		onmtxt=new JTextField(15);
		
		p1_p2.add(pid);
		p1_p2.add(pidtxt);
		p1_p2.add(pnm);
		p1_p2.add(pnmtxt);
		p1_p2.add(address);
		p1_p2.add(addresstxt);
		p1_p2.add(mno);
		p1_p2.add(mnotxt);
		p1_p2.add(pdeals);
		p1_p2.add(pdealstxt);
		p1_p2.add(onm);
		p1_p2.add(onmtxt);
		
		
		 load=new JButton("Load Data",new ImageIcon(Bills.class.getResource("images/back.png")));
		 load.addActionListener(this);
		 
		 p1_p3.add(load);
		
		p1.add(p1_p1);
		p1.add(p1_p2);
		p1.add(p1_p3);
	    jbill.add(p1);	
	    
		
		 
		 
	    
	p2=new JPanel();
	p2.setLayout(new GridLayout(1,2));
	
	p2_p1=new JPanel(new GridLayout(7,2));
	billid=new JLabel("Enter A bill id");
	itm=new JLabel("Item Name");
	qty=new JLabel("Quantity");
	mrp=new JLabel("MRP");
	total=new JLabel("Total");
	
	billidtxt=new JTextField(20);
	itmtxt=new JTextField(20);
	qtytxt=new JTextField(20);
	mrptxt=new JTextField(20);
	totaltxt=new JTextField(20);
	
	pn=new JPanel(new GridLayout(1,2));

	 Month=new JLabel("Month,Yr");
	 month=new JTextField();
	 
	 submit=new JButton("Submit",new ImageIcon(Bills.class.getResource("images/back.png")));
	 submit.addActionListener(this);
	 
	 totaltxt.setEditable(false);
	 
	 p2_p1.add(billid);
	 p2_p1.add(billidtxt);
	 p2_p1.add(itm);
	p2_p1.add(itembox);
	p2_p1.add(qty);
	p2_p1.add(qtytxt);
	p2_p1.add(mrp);
	p2_p1.add(mrptxt);
	p2_p1.add(total);
	p2_p1.add(totaltxt);
	p2_p1.add(Month);
	p2_p1.add(month);
	pn.add(submit);
	 p2_p1.add(pn);

	
	p2_p2=new JPanel(new FlowLayout());
	
	tmodel = new DefaultTableModel();
    table = new JTable(tmodel);
    SetColHeader();
   p2_p2.add(new JScrollPane(table));
	p2.add(p2_p1);
	p2.add(p2_p2);
	
	jbill.add(p2);
	
   
	totalcost=new JLabel("Total Cost");
	disc=new JLabel("Discount(%)");
	tdisc=new JLabel("Total Discount");
	nettot=new JLabel("Net Total");
	
	totalcosttxt=new JTextField(20);
	disctxt=new JTextField(20);
	tdisctxt=new JTextField(20);
	nettottxt=new JTextField(20);
	
	p3=new JPanel(new GridLayout(3,1));
	
	p3_p1=new JPanel(new GridLayout(2,2));
	p3_p2=new JPanel(new GridLayout(2,2));
	
	totalcosttxt.setEditable(false);
	nettottxt.setEditable(false);
	tdisctxt.setEditable(false);
	
	p3_p1.add(totalcost);
	p3_p1.add(totalcosttxt);
	p3_p1.add(disc);
	p3_p1.add(disctxt);
	
	
	p3_p2.add(tdisc);
	p3_p2.add(tdisctxt);
	p3_p2.add(nettot);
	p3_p2.add(nettottxt);
	

	

	 
	 enter=new JButton("Enter",new ImageIcon(Bills.class.getResource("images/back.png")));
	 enter.addActionListener(this);
	 
	 print=new JButton("print",new ImageIcon(Bills.class.getResource("images/back.png")));
	 print.addActionListener(this);
	 
	 back=new JButton("Back",new ImageIcon(Bills.class.getResource("images/back.png")));
	 back.addActionListener(this);
	 
	 p3_p3=new JPanel(new FlowLayout());
	 p3_p3.setLayout(new FlowLayout());
	 
	// p3_p3.add(load);
	// p3_p3.add(submit);
	 p3_p3.add(enter);
	 p3_p3.add(print);
	 p3_p3.add(back);
	 
	 p3.add(p3_p1);
	 p3.add(p3_p2);
	 p3.add(p3_p3);
	 
	 jbill.add(p3);
	
	jbill.setVisible(true);
	
	
		
   }
   
   
   
   private void SetColHeader()
   {
	   tmodel.addColumn("Party ID");
     tmodel.addColumn("Bill ID");
     tmodel.addColumn("ITEM NAME");
     tmodel.addColumn("Quantity");
     tmodel.addColumn("MRP");
     tmodel.addColumn("Net Cost");
     tmodel.addColumn("Date");
 

   }
   
   private void RetrieveData()
   {
     try
     {
       int row = tmodel.getRowCount();
    
  while(row > 0)
       {

         row--;
         tmodel.removeRow(row);

       }

       //execute query
       ResultSet rs = st.executeQuery("Select * from bills where billid='"+billidtxt.getText()+"'");

       //get metadata
       ResultSetMetaData md = rs.getMetaData();
       int colcount = md.getColumnCount();

       Object[] data = new Object[colcount];
       //extracting data


       while (rs.next())
       {
         for (int i=1; i<=colcount; i++)
         {
           data[i-1] = rs.getString(i);

         }
         tmodel.addRow(data); 
       }
      
       
     /* while (rs.next())
       {
     	 String a=rs.getString(1);
     	 String b=rs.getString(2);
     	  System.out.println(rs.getString(1)+rs.getString(2));
     	  
     	 tmodel.addRow(new Object[]{a,b});
       }
     */
       
     }
     
     catch(Exception e) {JOptionPane.showMessageDialog(null,e);  }
   }
   
   public void actionPerformed(ActionEvent ae) {
	 	 Object source= ae.getSource();
	 	 if(source==load)
	 	 {
	 		
	 		 loaddata();
	 		 
			   
	 	 }
	 	 
	 	 if(source==print)
	 	 {
	 		



			   
	 	 }
	 	
	 	 if(source==submit)
	 	 {
	 		 
	 		
	 		
	 		  b=a*Integer.parseInt(qtytxt.getText());
			   totaltxt.setText(""+b);
			   tot=tot+b;
			   c=tot;
			   totalcosttxt.setText("");
			   totalcosttxt.setText(""+tot);
			   insertdata();
			   
			   RetrieveData(); 
			   
			   
	 	 }
	 	 if(source==enter)
	 	 {
	 		 
	 		 
	 		  float nd,nc;
		 		 a=Integer.parseInt(disctxt.getText());
		 		 nd=((float)tot/100)*a;
		 		 nc=tot-nd;
		 		tdisctxt.setText(""+nd);
		 		nettottxt.setText(""+nc);
	 		
	 		 try
	 		
	 		 
	    	 {
	    		 String p="insert into bills2 values('"+billidtxt.getText()+"','"+totalcosttxt.getText()+"','"+disctxt.getText()+"','"+tdisctxt.getText()+"','"+nettottxt.getText()+"')";
					st.executeUpdate(p); 
	    	 }
	    	 catch(Exception e) 
	    	 {
	    		 JOptionPane.showMessageDialog(null,e);
	    	 }
	 		
	 	 }
	 	 
	 	 if(source==back)
		 {
			new MainMenu();
			jbill.dispose();
		 }
	 	
	 	 }
   void loaddata()
   {
	  
	   try {
		  
		   ResultSet rs=st.executeQuery("select * from partyentry where partyid='"+pidtxt.getText()+"'");
		while(rs.next())
		{
			
				pnmtxt.setText(rs.getString(2));
				addresstxt.setText(rs.getString(3));
				mnotxt.setText(rs.getString(4));
				pdealstxt.setText(rs.getString(5));
				onmtxt.setText(rs.getString(6));
		}	
		   
		
	   }
	   
	 
	   catch(Exception e)
		{
		   JOptionPane.showMessageDialog(null,e);
		}
   }
 
     void insertdata()
     {
    	 try
    	 {
    		 String p="insert into bills values('"+pidtxt.getText()+"','"+billidtxt.getText()+"','"+s+"','"+qtytxt.getText()+"','"+mrptxt.getText()+"','"+totaltxt.getText()+"','"+month.getText()+"')";
				st.executeUpdate(p); 
    	 }
    	 catch(Exception e) 
    	 {
    		 JOptionPane.showMessageDialog(null,e);
    	 }
     }
   public static void main(String[]args)
   {
	   new Bills();
   }
}
