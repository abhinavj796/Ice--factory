
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;

class ViewParties extends JFrame implements ActionListener
{
  Statement st;
  Connection con;
 JTable table;
JButton Show,back;
  //Table

   DefaultTableModel tmodel;
   Container cpane;
   
   DB_conn obj=new DB_conn();

  ViewParties()
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

		Show = new JButton("Refresh",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
	Show.addActionListener(this);
	
	back = new JButton("Back",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
	back.addActionListener(this);
		
 setTitle("JTable Demo");
        setSize(500,500);
        setResizable(false);
cpane = getContentPane();

        //components

cpane.setLayout(new FlowLayout());

        tmodel = new DefaultTableModel();
        table = new JTable(tmodel);
        SetColHeader();
cpane.add(new JScrollPane(table));
cpane.add(Show);
cpane.add(back);
setVisible(true);
//RetrieveData();

Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
setLocation((screen.width-500)/2,((screen.height-500)/2));

  }
private void SetColHeader()
  {
    tmodel.addColumn("Party Id");
    tmodel.addColumn("Party Name");
  tmodel.addColumn("Address");
    tmodel.addColumn("Mobile No.");
    tmodel.addColumn("Party Deals");
    tmodel.addColumn("Owner Name");

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
      ResultSet rs = st.executeQuery("select * from partyentry");

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
 public void actionPerformed(ActionEvent ae) {
	 Object source= ae.getSource();
	 if(source==Show)
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

new ViewParties();
}


}