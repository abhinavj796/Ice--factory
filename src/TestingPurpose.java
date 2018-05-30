import java.awt.EventQueue;

import java.awt.event.*;



import java.awt.*;


import javax.swing.*;


public class TestingPurpose implements ActionListener {

	private JFrame frame;
	JButton create,delete;
	JLabel mainlabel ;
	JPanel panel;
	JButton Exit;
	TestingPurpose(){
	
		frame = new JFrame();
		frame.setBounds(100, 100, 456, 102);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		frame.setUndecorated(true);
		mainlabel = new JLabel("Test Our HardWork(Automated Database Creation)");
		panel.add(mainlabel);
	
		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		
		create= new JButton("Create Database");
		create.addActionListener(this);
		panel1.add(create);
		
	delete= new JButton("Delete Databases");
	delete.addActionListener(this);
		panel1.add(delete);
		frame.getContentPane().add(panel1);
		
		Exit = new JButton("Exit");
		panel1.add(Exit);
		Exit.addActionListener(this);
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screen.width-500)/2,((screen.height-280)/2));	
			frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		if(source==create) {
			new Test();
			JOptionPane.showMessageDialog(null, "Database Created Successfully","Creation",JOptionPane.INFORMATION_MESSAGE);
		}
		if(source==delete) {
			Test t=new Test();
			t.delete();
			JOptionPane.showMessageDialog(null, "Database Deleted Successfully","Deletion",JOptionPane.WARNING_MESSAGE);
		}
		if(source==Exit) {
		//	System.exit(0);
			frame.dispose();
			
		}
		
	}
	
	public static void main(String[] args) {
		
	new TestingPurpose();	

	
	}

	
	


}
