import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;

class Partywisesales implements ActionListener
{
	  Statement st;
	  Connection con;
	
	JLabel title,pid,pnm,address,mno,onm,billid;
	JTextField pidtxt,pnmtxt,addresstxt,mnotxt,onmtxt,billidtxt;
	JPanel p1,p2,p3,p4;
	JButton show,print,load,back,l2;
	JFrame jf;
	DefaultTableModel tmodel;
	JTable table;
	
	JLabel totalcost,disc,tdisc,nettot;
	JTextField totalcosttxt,disctxt,tdisctxt,nettottxt;
	
	DB_conn obj=new DB_conn();
	
   Partywisesales()
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
	   
	   
	   jf=new JFrame();
	   jf.setSize(900, 500);
	 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new GridLayout(4,1));
		 Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
	      jf.setLocation((screen.width-800)/2,((screen.height-500)/2));
	      
	      
	   p1=new JPanel();
	   p1.setLayout(new FlowLayout());
	   
	   p2=new JPanel();
	   p2.setLayout(new GridLayout(6,2));
	   
	   p3=new JPanel();
	   p3.setLayout(new GridLayout());
	   
	   p4=new JPanel();
	   p4.setLayout(new GridLayout(6,2));
	   
	   title=new JLabel("Partywise Sales Register");
		title.setFont(new Font("Serif", Font.HANGING_BASELINE, 45));
		p1.add(title);
		
		pid=new JLabel("Party id");
		pnm=new JLabel("Party Name");
		address=new JLabel("Address");
		mno=new JLabel("MobileNo.");
		
		onm=new JLabel("Owner Name");
		billid=new JLabel("Enter A Valid Bill id");
		
		pidtxt=new JTextField(15);
		pnmtxt=new JTextField(15);
		addresstxt=new JTextField(15);
		mnotxt=new JTextField(15);
		
		onmtxt=new JTextField(15);
		billidtxt=new JTextField(15);
		
		 p2.add(billid);
		 p2.add(billidtxt);
		 p2.add(pid);
		 p2.add(pidtxt);
		 p2.add(pnm);
		 p2.add(pnmtxt);
		 p2.add(address);
		 p2.add(addresstxt);
		 p2.add(mno);
		 p2.add(mnotxt);
	
		 p2.add(onm);
		 p2.add(onmtxt);
		 
		
		// p2.add(load);
		
		
		tmodel = new DefaultTableModel();
	    table = new JTable(tmodel);
	    SetColHeader();
	   p3.add(new JScrollPane(table));
	   
	   
	   
	   totalcost=new JLabel("Total Cost");
		disc=new JLabel("Discount(%)");
		tdisc=new JLabel("Total Discount");
		nettot=new JLabel("Net Total");
		
		totalcosttxt=new JTextField(20);
		disctxt=new JTextField(20);
		tdisctxt=new JTextField(20);
		nettottxt=new JTextField(20);
		
		
		
		
		p4.add(totalcost);
		p4.add(totalcosttxt);
		p4.add(disc);
		p4.add(disctxt);
		
		
		p4.add(tdisc);
		p4.add(tdisctxt);
		p4.add(nettot);
		p4.add(nettottxt);
	   
		 load=new JButton("Load",new ImageIcon(Partywisesales.class.getResource("images/back.png")));
		 load.addActionListener(this);
		
	   show=new JButton("Show",new ImageIcon(Partywisesales.class.getResource("images/back.png")));
	   show.addActionListener(this);
		print=new JButton("Print",new ImageIcon(Partywisesales.class.getResource("images/print.png")));
		print.addActionListener(this);
		back=new JButton("Back",new ImageIcon(Partywisesales.class.getResource("images/back.png")));
		back.addActionListener(this);
		
	
		
		p4.add(totalcost);
		p4.add(totalcosttxt);
		p4.add(disc);
		p4.add(disctxt);
		
		
		p4.add(tdisc);
		p4.add(tdisctxt);
		p4.add(nettot);
		p4.add(nettottxt);
		
		p4.add(load);
	//	p4.add(show);
		//p4.add(print);
		p4.add(back);
		
		
		jf.add(p1);
		jf.add(p2);
		jf.add(p3);
		jf.add(p4);

jf.setVisible(true);
			
	}
   private void SetColHeader()
   {
	   tmodel.addColumn("Party ID");
	     tmodel.addColumn("Bill ID");
	     tmodel.addColumn("ITEM NAME");
	     tmodel.addColumn("Quantity");
	     tmodel.addColumn("MRP");
	     tmodel.addColumn("Net Cost");
	     tmodel.addColumn("Date(Month,Yr)");
	 
   }
   private void RetrieveData ()
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
      
       
   
       
     }
     
     catch(Exception e) {JOptionPane.showMessageDialog(null,e);  }
   }
   
   void loaddata()
   {
	  
	   try {
		   
		   ResultSet rs=st.executeQuery("select partyid from bills where billid='"+billidtxt.getText()+"'");
		while(rs.next())
		{
			
				pidtxt.setText(rs.getString(1));
		}	
		   
	   }
	   catch(Exception e)
		{
		   JOptionPane.showMessageDialog(null,e);
		}
	   
	   
	   try {
		  
		   ResultSet rs=st.executeQuery("select * from partyentry where partyid='"+pidtxt.getText()+"'");
		while(rs.next())
		{
			
				pnmtxt.setText(rs.getString(2));
				addresstxt.setText(rs.getString(3));
				mnotxt.setText(rs.getString(4));
				onmtxt.setText(rs.getString(6));
		}	
		   
	   }
	   catch(Exception e)
		{
		   JOptionPane.showMessageDialog(null,e);
		}
   }
  
  public void actionPerformed(ActionEvent ae) {
 	 Object source= ae.getSource();
 	 if(source==load)
 	 {
 		
 		 loaddata();
 		 RetrieveData();
 		 
 		 try {
 			  
 			   ResultSet rs=st.executeQuery("select * from bills2 where billid='"+billidtxt.getText()+"'");
 			while(rs.next())
 			{
 			
 					totalcosttxt.setText(rs.getString(2));
 					disctxt.setText(rs.getString(3));
 					tdisctxt.setText(rs.getString(4));
 					nettottxt.setText(rs.getString(5));
 			}	
 			   
 		   }
 		   catch(Exception e)
 			{
 			   JOptionPane.showMessageDialog(null,e);
 			}
 		 
 	 }
 	 if(source==show)
 	 {
 		
 		 RetrieveData();
 	 }
 	 if(source==back)
 	 {
 		
 		 jf.dispose();
 		 new MainMenu();
 	 }
 	 }
   
   public static void main(String[] args)
   {
	   new Partywisesales();
	   
   }
   
   
   
   
}