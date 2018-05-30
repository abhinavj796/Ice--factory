import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;

class Partywisepurchase implements ActionListener
{
	  Statement st;
	  Connection con;
	
	JLabel title,pid,pnm,address,mno,pdeals,onm;
	JTextField pidtxt,pnmtxt,addresstxt,mnotxt,pdealstxt,onmtxt;
	JPanel p1,p2,p3,p4;
	JButton show,print,load,back,l2;
	JFrame jf;
	DefaultTableModel tmodel;
	JTable table;
	
	
DB_conn obj=new DB_conn();





	
   Partywisepurchase()
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
	   p4.setLayout(new FlowLayout());
	   
	   title=new JLabel("Partywise Purchase Raw Material");
		title.setFont(new Font("Serif", Font.HANGING_BASELINE, 45));
		p1.add(title);
		
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
		
	
		 p2.add(pid);
		 p2.add(pidtxt);
		 p2.add(pnm);
		 p2.add(pnmtxt);
		 p2.add(address);
		 p2.add(addresstxt);
		 p2.add(mno);
		 p2.add(mnotxt);
		 p2.add(pdeals);
		 p2.add(pdealstxt);
		 p2.add(onm);
		 p2.add(onmtxt);
		// p2.add(load);
		
		
		tmodel = new DefaultTableModel();
	    table = new JTable(tmodel);
	    SetColHeader();
	   p3.add(new JScrollPane(table));
	   
		 load=new JButton("Load",new ImageIcon(Partywisepurchase.class.getResource("images/back.png")));
		 load.addActionListener(this);
		
	   show=new JButton("Show",new ImageIcon(Partywisepurchase.class.getResource("images/back.png")));
	   show.addActionListener(this);
		print=new JButton("Print",new ImageIcon(Partywisepurchase.class.getResource("images/print.png")));
		print.addActionListener(this);
		back=new JButton("Back",new ImageIcon(Partywisepurchase.class.getResource("images/back.png")));
		back.addActionListener(this);
		
		
		
		p4.add(load);
		p4.add(show);
		p4.add(print);
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
     tmodel.addColumn("ITEM NAME");
     tmodel.addColumn("ITEM Type");
     tmodel.addColumn("MRP");
     tmodel.addColumn("Purchase Rate");
     tmodel.addColumn("Total Product");
     tmodel.addColumn("Net Cost");


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
       ResultSet rs = st.executeQuery("Select * from rawmaterials where partyid='"+pidtxt.getText()+"'");

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
     
     catch(Exception e) {JOptionPane.showMessageDialog(null,e);   }
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
  
  public void actionPerformed(ActionEvent ae) {
 	 Object source= ae.getSource();
 	 if(source==load)
 	 {
 		
 		 loaddata();
 	 }
 	 if(source==show)
 	 {
 		
 		 RetrieveData();
 	 }
 	 
 	  if(source==print)
 	 {
 		
 	 }
 	 
	 if(source==back)
 	 {
 		
 		 jf.dispose();
 		 new MainMenu();
 	 }
 	 }
   
   public static void main(String[] args)
   {
	   new Partywisepurchase();
	   
   }
   
   
   
   
}