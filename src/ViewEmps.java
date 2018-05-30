import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;

class ViewEmps extends JFrame implements ActionListener
{
  Statement st;
  Connection con;
 JTable table;

  //Table

   DefaultTableModel tmodel;
   Container cpane;
   JButton show,back;
JPanel p1;

DB_conn obj=new DB_conn();

  ViewEmps()
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

 setTitle("JTable Demo");
        setSize(500,500);
        setResizable(false);
cpane = getContentPane();
cpane.setLayout(new FlowLayout());

        //components

show = new JButton("Refresh",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
show.addActionListener(this);

back = new JButton("Back",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
back.addActionListener(this);


p1.add(show);
p1.add(back);
p1.setSize(2,2);
        tmodel = new DefaultTableModel();
        table = new JTable(tmodel);
        SetColHeader();
cpane.add(new JScrollPane(table));
cpane.add(p1);

Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
setLocation((screen.width-500)/2,((screen.height-500)/2));

setVisible(true);
//RetrieveData();
//RetrieveData();

  }
private void SetColHeader()
  {
    tmodel.addColumn("E_ID");
    tmodel.addColumn("E_name");
 tmodel.addColumn("sal");
  tmodel.addColumn("address");
  tmodel.addColumn("post");
  tmodel.addColumn("work_basis");
  tmodel.addColumn("mno");

  }
 private void RetrieveData ()
  {
    try
    {
    	  System.out.println("1");
      int row = tmodel.getRowCount();
     
 while(row > 0)
      {
	  System.out.println("2");
        row--;
        tmodel.removeRow(row);

      }

      //execute query
      ResultSet rs = st.executeQuery("select * from employees");
      System.out.println("3");

      //get metadata
      ResultSetMetaData md = rs.getMetaData();
      int colcount = md.getColumnCount();
      System.out.println("4");
      Object[] data = new Object[colcount];
      //extracting data

      System.out.println("5");
      while (rs.next())
      {
        for (int i=1; i<=colcount; i++)
        {
          data[i-1] = rs.getString(i);
          System.out.println("");
        }
        tmodel.addRow(data);
      }
    }
    catch(Exception e) {JOptionPane.showMessageDialog(null,e);  }
  }  
 
 public void actionPerformed(ActionEvent ae) {
	 Object source= ae.getSource();
	 if(source==show)
	 {
		
		 RetrieveData();
	 }
	 if(source==back)
	 {
		
		 dispose();
		 new MainMenu();
	 }
	 
	 }
 
 
public static void main(String[] args)
{

new ViewEmps();
}


}