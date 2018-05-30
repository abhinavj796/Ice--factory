import javax.swing.*;

import java.awt.*;

import javax.swing.border.LineBorder;

public class SplashScreen extends JWindow implements Runnable
{
	public void run()
	{
		JLabel SplashScreenLabel = new JLabel(new ImageIcon(SplashScreen.class.getResource("images/mainimg1.jpg")));
		Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
		Color cl = new Color (255,200, 120);
		SplashScreenLabel.setBorder (new LineBorder(cl, 10));

		getContentPane().add(SplashScreenLabel,BorderLayout.CENTER);

		setSize(540,405);
		setLocation((screen.width-500)/2,(screen.height-400)/2);
		show();
	}
	public static void main(String[]args)
	{
		SplashScreen FormSplash = new SplashScreen();
		  Thread ThFormSplash = new Thread(FormSplash);
		ThFormSplash.start();
		//ProgressBar pb=new ProgressBar();
		
		
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
	}
}