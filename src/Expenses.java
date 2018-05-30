import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;

class Expenses implements ActionListener
{
	  Statement st;
	  Connection con;
	
	JLabel title,exp,cost,month;
	JTextField exptxt,costtxt,monthtxt;
	JPanel p1,p2,p3,p4;
	JButton show,print,add,back;
	JFrame jf;
	DefaultTableModel tmodel;
	JTable table;
	
	DB_conn obj=new DB_conn();

  Expenses()
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
	   
	   title=new JLabel("Expenses");
		title.setFont(new Font("Serif", Font.HANGING_BASELINE, 45));
		p1.add(title);
		
		exp=new JLabel("Expense");
		cost=new JLabel("Cost");
		month=new JLabel("Month,Yr");
		
		exptxt=new JTextField(15);
		costtxt=new JTextField(15);
	   monthtxt=new JTextField(15);
		
	
		 p2.add(exp);
		 p2.add(exptxt);
		 p2.add(cost);
		 p2.add(costtxt);
		 p2.add(month);
		 p2.add(monthtxt);

		// p2.add(load);
		
		
		tmodel = new DefaultTableModel();
	    table = new JTable(tmodel);
	    SetColHeader();
	   p3.add(new JScrollPane(table));
	   
		 add=new JButton("ADD Data",new ImageIcon(Expenses.class.getResource("images/back.png")));
		 add.addActionListener(this);
		
	   show=new JButton("Search",new ImageIcon(Expenses.class.getResource("images/back.png")));
	   show.addActionListener(this);
		print=new JButton("Print",new ImageIcon(Expenses.class.getResource("images/print.png")));
		print.addActionListener(this);
		back=new JButton("Back",new ImageIcon(Expenses.class.getResource("images/back.png")));
		back.addActionListener(this);
		
		
		
		p4.add(add);
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
	     tmodel.addColumn("Expense");
	     tmodel.addColumn("Cost");
	     tmodel.addColumn("Month,Year");
	    
	 
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
       ResultSet rs = st.executeQuery("Select * from expenses where month='"+monthtxt.getText()+"'");

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
     
     catch(Exception e) {		JOptionPane.showMessageDialog(null,e);  }
   }
   
   void insert()
   {
	try
	{
		String a="insert into expenses values('"+exptxt.getText()+"','"+costtxt.getText()+"','"+monthtxt.getText()+"')";
		st.executeUpdate(a);
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
 	 if(source==add)
 	 {
 		
 		 insert();
 	 }
 	 if(source==show)
 	 {
 		
 		 RetrieveData();
 	 }
 	 if(source==back)
	 {
		new MainMenu();
		jf.dispose();
	 }
 	 }
   
   public static void main(String[] args)
   {
	   new Expenses();
	   
   }
   
   
   
   
}