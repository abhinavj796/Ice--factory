
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class ProgressBar extends JFrame implements Runnable
 {
 	JProgressBar pb;
 	Thread t;
 	Container c;
 	ProgressBar(){
 		c=getContentPane();
 		setSize(540,50);
 		setLocation(435,600);
 		t=new Thread(this);
 		
 		pb=new JProgressBar(0,100);	
 	pb.setStringPainted(true);
 	pb.setBackground(Color.PINK);		
 		c.add(pb);
 setUndecorated(true);
 		t.start();
 		setVisible(true);
 	
 	}
 	public void run(){
 		for (int i = 0; i<=100; i++){
 			try {
 // decrease timing
     			Thread.sleep(20);
 			}
 			catch (Exception ex) {
 			}
 			pb.setValue(i);
 		}
 	
 		dispose();		
 	}
 }