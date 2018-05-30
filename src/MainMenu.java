import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//Main includes Detail About Employees PayRoll
class ImagePanel2 extends JPanel{
	
	public void paintComponent(Graphics g){
		Image image=new ImageIcon(MainMenu.class.getResource("images/ice-creamlogo.jpg")).getImage();
		g.drawImage(image,10,10,this);
	}
}

class MainMenu extends JFrame implements ActionListener {
    JFrame jf;

    JMenuBar mb;
    JMenu pent,inm,sitm,biing,acc,othrs;
    JMenuItem srm,sfg,psr,ppr,ss,exp,addparty,additem,addbill,viewitm,viewemp,viewparty,addemp,logout,Testing,deldb;
    
    MainMenu() {
        jf=new JFrame("Adminstrator login(ICE BERRY)");

		jf.setBackground(Color.PINK);    
    mb=new JMenuBar();

        
        pent=new JMenu("Party Entry");
        addparty=new JMenuItem("Add a Party");
        viewparty=new JMenuItem("View Patries");
        
        inm=new JMenu("Item Name");
        additem=new JMenuItem("Add New Item");
        viewitm=new JMenuItem("View Items Avalible");
        
        sitm=new JMenu("Stock Item");
        biing=new JMenu("Billing");
        acc=new JMenu("Accounts");
        othrs=new JMenu("Others");
        deldb=new JMenuItem("Delete Database And Exit");
        deldb.setForeground(Color.MAGENTA);
        Testing=new JMenuItem("Test My Software");
        Testing.setForeground(Color.BLUE);
        Testing.setSelected(true);
        srm=new JMenuItem("Stock of Raw Material");
        sfg=new JMenuItem("Stock of Finished Goods");
        
        psr=new JMenuItem("Partywise Sales Register");
        ppr=new JMenuItem("Partywise purchase Register");
     
        
        ss=new JMenuItem("Staff Section");
        viewemp=new JMenuItem("View Employees");
        addemp=new JMenuItem("Add Employees");
        
        exp=new JMenuItem("Expenses");
        logout=new JMenuItem("Log Out");
        logout.setForeground(Color.RED);
     
        
        addbill=new JMenuItem("Make a Bill");
        viewitm=new JMenuItem("View Items Avalible");
        
       pent.add(addparty);
       pent.addSeparator();
       pent.add(viewparty);
       
       inm.add(additem);
       inm.addSeparator();
       inm.add(viewitm);
       
       sitm.add(srm);
       sitm.addSeparator();
       sitm.add(sfg);
       
       biing.add(addbill);
       
       acc.add(psr);
       acc.addSeparator();
       acc.add(ppr);
      
       othrs.add(Testing);
       othrs.add(ss);
       othrs.addSeparator();
       othrs.add(viewemp);
       othrs.addSeparator();
       othrs.add(addemp);
       othrs.addSeparator();
       othrs.add(exp);
       othrs.addSeparator();
       othrs.add(logout);
       othrs.addSeparator();
       othrs.add(deldb);
      
        GridBagLayout gb= new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		jf.getContentPane().setLayout(gb);
		gbc.weightx=1;
    	gbc.weighty=7;
    	gbc.fill=GridBagConstraints.BOTH;

		ImagePanel2 m=new ImagePanel2();
    	gbc.gridx=0;
    	gbc.gridy=0;
    	gb.setConstraints(m,gbc);
    	jf.getContentPane().add(m);

    	
        mb.add(pent);
        mb.add(inm);
        mb.add(sitm);
        mb.add(biing);
        mb.add(acc);
        mb.add(othrs);
        
        addparty.addActionListener(this);
        viewparty.addActionListener(this);
        additem.addActionListener(this);
        viewitm.addActionListener(this);
        srm.addActionListener(this);
        
        sfg.addActionListener(this);
        addbill.addActionListener(this);
        psr.addActionListener(this);
        
        ppr.addActionListener(this);
        Testing.addActionListener(this);
        ss.addActionListener(this);
        viewemp.addActionListener(this);
        addemp.addActionListener(this);
        exp.addActionListener(this);
        logout.addActionListener(this);
        deldb.addActionListener(this);
        jf.setResizable(false);     
        jf.setJMenuBar(mb);
       jf.setBounds(20,20,375,430);
        jf.setVisible(true);
        
        Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
        jf.setLocation((screen.width-500)/2,((screen.height-500)/2));	

    }
    public void actionPerformed(ActionEvent ae) {
        Object o=ae.getSource();
        if(o==addparty) {
            new Partyenrty();
            jf.dispose();
        }
        if(o==viewparty) {
            new ViewParties();
            jf.dispose();
        }
        if(o==additem){
        	new ItemName();
        	jf.dispose();
        }
        if(o==viewitm)
        {
        new ViewItemName();
        jf.dispose();
        }
        if(o==srm) {
        	new Stockrawmat();
        	jf.dispose();
        }
        if(o==sfg) {
        	new Stockfingoods();
        	jf.dispose();
        }
        if(o==addbill) {
            new Bills();
            jf.dispose();
        }
        if(o==psr) {
       new Partywisesales();
       jf.dispose();
        }
        
        
        if(o==ppr) {
         new Partywisepurchase();
         jf.dispose();
        }
       
        
        if(o==ss) {
            new Staffsection();
            jf.dispose();
        }
        if(o==viewemp) {
            new ViewEmps();
            jf.dispose();
        }
        if(o==addemp) {
            new AddEmployee();
            jf.dispose();
        }
        
        if(o==exp)
        {
      	
        	new Expenses();
        	
        	jf.dispose();
        }
        
       
        if(o==logout)
        {    
           
        	new Login();
        	jf.dispose();
        }
        if(o==deldb) {
        	Test t1=new Test();
            t1.delete();
        	new Login();
        	jf.dispose();
        }
        
       if(o==Testing) {
          new TestingPurpose();
       }
        
    }
    
   public static void main(String s[]) {
        new MainMenu();
      
        
    }
}