import java.sql.*;
import java.awt.print.*;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;

public class Test {

Connection con;
Statement itemst,billst,loginst,expenst,rawst,empst,partyst,billsecst;
Statement itemin,billin,billsin,expensein,empin,login,partyin,rawin;
Statement delst1,delst2,delst3,delst4,delst5,delst6,delst7,delst8;

DB_conn obj=new DB_conn();

	Test() {
	
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//*******************************TABLE CREATION FOR TESTING***********************************
		
		try {  
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			  			Class.forName("oracle.jdbc.driver.OracleDriver");
			  			 con=DriverManager.getConnection(url,obj.username(),obj.pass());   			
			  		
	}
	catch(Exception e) {

		
	}
		
		try {  ////********FOR ITEM NAME*****************
		
		 			
			  		 itemst=con.createStatement();
			  		itemst.executeQuery("CREATE TABLE  items (ItemName varchar(30),Flavour varchar(30),ItemType varchar(30),MRP varchar(30))");
			  		//butterscoth cone,   ,1*50mL,
	}
	catch(Exception e) {
	//	JOptionPane.showMessageDialog(null,"Item Table already Created");
		
	}
	try { //**************FOR BILLS*******************
		
		   			
		  		 billst=con.createStatement();
		  		billst.executeQuery("CREATE TABLE  bills (PartyID varchar(30),BillID varchar(30),ItemName varchar(30),Quantity varchar(30),MRP varchar(30),Total varchar(30),Month varchar(30))");
		  		
}
catch(Exception e) {
	//JOptionPane.showMessageDialog(null, e);
	
}
	
	
	try {  ////********FOR Employees*****************
	   			
		  		 empst = con.createStatement();
		  		empst.executeQuery("create table emp(EID varchar(30),ENAME varchar(30),Salary varchar(30),Address varchar(30),POST varchar(30),Work_Basis varchar(30),Mobile_number varchar(30))");
		  	
}
catch(Exception e) {
	//JOptionPane.showMessageDialog(null,"Item Table already Created");
	
}
	
	
	try {  ////********for Expenses*****************
		
			
		  		 expenst=con.createStatement();
		  		 expenst.executeQuery("CREATE TABLE  expenses(Expense varchar(30),Cost varchar(30),Month varchar(30))");
		  	
}
catch(Exception e) {
	//JOptionPane.showMessageDialog(null,"Item Table already Created");
	
}
	
	try {  ////********FOR PartyEntry*****************
		
	  			
		  		partyst=con.createStatement();
		  		partyst.executeQuery("CREATE TABLE  partyentry (PartyID varchar(30),PName varchar(30),Address varchar(30),MNO varchar(30),Deals_in varchar(30),Owner_Name varchar(30))");
		  		
}
catch(Exception e) {
	//JOptionPane.showMessageDialog(null,"Item Table already Created");
	
}
	try {  ////********FOR Raw Materials*****************
		
	   			
		  		rawst=con.createStatement();
		  		rawst.executeQuery("CREATE TABLE  RawMaterials (PartyID varchar(30),ITEMName varchar(30),ITEMtype varchar(30),MRP varchar(30),Purchaserate varchar(30),TotalProduct varchar(30),NetCOST varchar(30))");
		  		
}
catch(Exception e) {
	//JOptionPane.showMessageDialog(null,"Raw MaterialsTable already Created");
	
}
try {  ////********FOR BILLS 2 *****************
  			
		  		billsecst=con.createStatement();
		  		billsecst.executeQuery("CREATE TABLE  Bills2 (BillID varchar(30),TotalCost varchar(30),Discount varchar(30),TotalDiscount varchar(30),NetCost varchar(30))");
		  		
}
catch(Exception e) {
	//JOptionPane.showMessageDialog(null,"Item Table already Created");
	
}
//***************************************************************INSERTING VALUES INTO TABLES***********************************************************
	try{  
		
		//////FOR ITEM NAME
  			
  			itemin=con.createStatement();
  		String i1="insert into items values('Cone stw','Strawberry','1*200ml','25')";
  	String i2="insert into items values('Cup van','vanilla','1*100ml','10')";
  		String i3="insert into items values('Butterscotch','mix','1*200ml','50')";
  		String i4="insert into items values('cup(50ml)','Strawberry','1*50ml','5')";
  		String i5="insert into items values('brick bct','black current','1*1000ml','100')";
  		String i6="insert into items values('cup bts(1*50ml)','butter','1*50ml','5')";
  		
  		///***for bill**
  		billin=con.createStatement();
  		String bill1="insert into bills values('s-1234','1212','cup(50)ml','10','5','50','july')";
  		String bill2="insert into bills values('s-1234','1212','cup(50)ml','2','5','10','july')";
  		String bill3="insert into bills values('s-1234','1212','cup(50)ml','12','5','60','july')";
  		String bill4="insert into bills values('s-001','b-02','brick bct','10','100','1000','23-08-16')";
  		String bill5="insert into bills values('s-12','b-01','cup van','10','5','50','sept')";
		itemin.executeUpdate(i1);
		itemin.executeUpdate(i2);
		itemin.executeUpdate(i3);
		itemin.executeUpdate(i4);
		itemin.executeUpdate(i5);
		itemin.executeUpdate(i6);
		billin.executeUpdate(bill1);
  		billin.executeUpdate(bill2);
  		billin.executeUpdate(bill3);
  		billin.executeUpdate(bill4);
  		billin.executeUpdate(bill5);
  		
  		//*************For Bills2********
  		billsin=con.createStatement();
  		String bills1="insert into bills2 values('1212','120','10','12.0','108.0')";
  		String bills2="insert into bills2 values('122','60','5','0.0','60.0')";
  		String bills3="insert into bills2 values('1252','100','5','5.0','95.0')";
  	   billsin.executeUpdate(bills1);
  		billsin.executeUpdate(bills2);
  		billsin.executeUpdate(bills3);
  		
  		//*****For Expenses*********
  		expensein=con.createStatement();
  		String exp1="insert into expenses values('Generator Repair','3000','21-08-2016')";
  	String exp2="insert into expenses values('Electricity bill','10000','22-08-2016')";
  		String exp3="insert into expenses values('Diesel','20000','01-09-2016')";
  		
  	
		expensein.executeUpdate(exp1);
		expensein.executeUpdate(exp2);
		expensein.executeUpdate(exp3);
		
		
		empin=con.createStatement();
  		String emp1="insert into emp values('e-057','Yashima Hooda','20000','Dcrust snp,haryana','Manager','Monthly','9854823658')";
  	String emp2="insert into emp values('e-027','Mayank Mukund','1000','Jharkhand','worker','weekly','922255888')";
  		String emp3="insert into emp values('e-501','Shubham sharma','5000','basai darapur','sweeper','weekly','989989897')";
  		
  	
  		empin.executeUpdate(emp1);
  		empin.executeUpdate(emp2);
  		empin.executeUpdate(emp3);
		
  		
		//For Party Entry//
		partyin=con.createStatement();
  		String p1="insert into partyentry values('s-1234','Shivam Group of co.','delhi','9555536209','sales','Shivam Arora')";
  		String p2="insert into partyentry values('s-001','Abhinav Group of co.','Sonipat','999130365','Purchase','Abhinav Jain')";
  		String p3="insert into partyentry values('s-12','Hooda Conn','Punjab','9416682626','Sales','Yashima Hooda')";
  		partyin.executeUpdate(p1);
  		partyin.executeUpdate(p2);
  		partyin.executeUpdate(p3);
		
  		//For Raw Materials****
  		rawin=con.createStatement();
  		String r1="insert into RawMaterials values('p-001','milk','1*50L','5000','4500','10','45000')";
  	
  		rawin.executeUpdate(r1);
  	
		
		
		con.close();
	}
catch(Exception e)
{

}
	
	
}
	
	public  void delete() {
try {  
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			  			Class.forName("oracle.jdbc.driver.OracleDriver");
			  			 con=DriverManager.getConnection(url,obj.username(),obj.pass());   			
			  		    delst1=con.createStatement();
			  		    delst2=con.createStatement();
			  		    delst3=con.createStatement();
			  		    delst4=con.createStatement();
			  		    delst5=con.createStatement();
			  		    delst6=con.createStatement();
			  		    delst7=con.createStatement();
			  		    delst8=con.createStatement();
			  		  delst8.executeQuery("drop table rawmaterials");
			  		 delst5.executeQuery("drop table bills2"); 
			  		   delst6.executeQuery("drop table expenses");
			  		   delst7.executeQuery("drop table partyentry");
			  		  delst1.executeQuery("drop table items");
			  		   delst2.executeQuery("drop table bills");
			  		   delst3.executeQuery("drop table emp");
			  		   delst4.executeQuery("drop table login");
			  		  
			  		   
			  			con.close();	  		   
	}
	catch(Exception e) {

		
	}
		
	}
	public void createdb() {
		
	}

	public static void main(String[] args) {
		new Test();
	}
}
