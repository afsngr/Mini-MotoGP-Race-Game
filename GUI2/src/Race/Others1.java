package Race;


import java.awt.Color;

import javax.swing.JLabel;

public class Others1 extends JLabel implements Runnable  {
	
	public int x = 620;
	public int y = 615;
	public int inc = 1;
	public Others1() {
		setLayout(null);
		setBackground(Color.black);
		setOpaque(true);
		setVisible(true);
		setBounds(x, y, 25,100);
	}
	public void run() {
		
		
		try {
			while (true) {
				if (y + inc > 900) {
					inc = inc * -1;
				} 
				y = y - inc;
				setBounds(x, y, 25, 100);
				Thread.sleep(100);
				repaint();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
}

