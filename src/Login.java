import javax.swing.*;	
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public  class Login implements ActionListener{
	String psw;
	String nm;

	int pass;
JLabel Title;
	 JLabel jlabusern;
 JLabel timage;
 protected JLabel jlabpassw;
 JFrame jlogin;
JTextField jtxt1;
JPasswordField jtxt2;
protected JButton loginbtn;
JButton exitbtn;
JPanel panel1;
JPanel panel2;
JPanel panel3;
JPanel panel4;
JCheckBox cb,signup;


JComboBox jcb1;
//String[] UserType = { "Admin","Local" };
public String loginname;
public String loginpass;
String url;
Connection con;
Statement crst,st,login;
DB_conn obj=new DB_conn();



int gateway=0;

public Login() {

	try {
		url="jdbc:oracle:thin:@localhost:1521:xe";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection(url,obj.username(),obj.pass()); 
		crst=con.createStatement();
		crst.executeQuery("CREATE TABLE login(Username varchar(30),Password varchar(30))");
	}
	catch(Exception e) {

	}
	try {
		login=con.createStatement();
  		String user1="insert into login values('admin','admin')";
		login.executeUpdate(user1);
	}
	catch(Exception e) {
	
	}
	panel1=new JPanel();
	panel1.setLayout(new FlowLayout());
	timage =  new JLabel(new ImageIcon(Login.class.getResource("images/login.jpg")));
	 panel1.add(timage);
	
	 panel2=new JPanel();
	panel2.setLayout(new FlowLayout());
	Title = new JLabel("Welcome To Ice-Berry");
	panel2.add(Title);
	panel3=new JPanel();
	panel3.setLayout(new GridLayout(3,2));
	
	
	panel4=new JPanel();
	panel4.setLayout(new FlowLayout());
	
  jlogin = new JFrame("ICEBERRY");

  jlogin.setLayout(new FlowLayout());

  
jlogin.setSize(500,350);
  jlogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  
  jlabusern=new JLabel("Enter The Username");
  jtxt1=new JTextField(15);
  jlabpassw=new JLabel("Enter The Password");
 jtxt2=new JPasswordField(15);
 
loginbtn = new JButton("Login",new ImageIcon(Login.class.getResource("images/emp1.png"))); 
loginbtn.addActionListener(this);
exitbtn=new JButton("Exit");
exitbtn.addActionListener(this);
//jcb=new JComboBox(); 
panel3.add(jlabusern);
panel3.add(jtxt1); 
panel3.add(jlabpassw);
panel3.add(jtxt2);



  cb = new JCheckBox("I Accept The Terms And Conditions*");

  panel3.add(cb);


jcb1=new JComboBox();
jcb1.addItem("Admin");
jcb1.addItem("Local");

  
 panel4.add(loginbtn);
 panel4.add(exitbtn);
//jcb = new JComboBox<String>(UserType);
//jcb.addActionListener(this);
panel3.add(jcb1);
jlogin.add(panel1);
jlogin.add(panel2);
jlogin.add(panel3);
jlogin.add(panel4);

Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
jlogin.setLocation((screen.width-500)/2,((screen.height-280)/2));	
jlogin.setLocationRelativeTo(null);


jlogin.setVisible(true);





	}
public void LoginQuery()
{

	loginname = jtxt1.getText().trim();
	loginpass = jtxt2.getText();
   Jdbc_Conn();
   
}




public   void Jdbc_Conn() {
	
	
	try {
		   			
			Statement st=con.createStatement();

			ResultSet rs = null;
			if(jcb1.getSelectedItem().equals("Admin"))
			{
				
			
					rs=st.executeQuery("select * from login");   			
			}
			else if(jcb1.getSelectedItem().equals("Local"))
			{
				rs=st.executeQuery("select * from login");
			}
			
			while(rs.next())
			{
				
				nm=rs.getString(1);
				psw=rs.getString(2);
				
			if(loginname.equals(nm)&& loginpass.equals(psw))
			{
				gateway=1;
				
			}
				 
			
	}
					
					if(gateway==1)
					{
						JOptionPane.showMessageDialog(loginbtn,"Login Successful",null, JOptionPane.INFORMATION_MESSAGE);
						new MainMenu();
						jlogin.dispose();	
					}
					else
					{
						JOptionPane.showMessageDialog(loginbtn,"Login Unsuccessful",null, JOptionPane.ERROR_MESSAGE);	
					}
					con.close();
	}
	catch (Exception ex) {
		JOptionPane.showMessageDialog(null,ex);
	
	}
      }
	







 public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		if(source.equals(loginbtn))
		{
        if(cb.isSelected()) 
        {
		    
        	LoginQuery();
		
		
		
	     }
        else {
        	
        	JOptionPane.showMessageDialog(null,"Please Accept the Terms and Coniditions*");
        }

		
		
		
		} 
		else if(source.equals(exitbtn))
		{
			JOptionPane.showMessageDialog(null, "Thank You!","ICE-BERRY",JOptionPane.YES_OPTION);
			System.exit(0);
			
		}
		
		
	}
 

 


public static void main(String args[]) {
	
	SplashScreen FormSplash = new SplashScreen();
	  Thread ThFormSplash = new Thread(FormSplash);
	ThFormSplash.start();
	ProgressBar pb=new ProgressBar();
	
	
	    while(!FormSplash.isShowing())
	    {
	      try
	      {
	        
	        Thread.sleep(3200);
	      }
	      catch(InterruptedException e)
	      {
	      }
	    }
	 FormSplash.dispose();
	pb.dispose();


		SwingUtilities.invokeLater(new Runnable() {
		public void run() {
	
			new Login();

}
});
}

}