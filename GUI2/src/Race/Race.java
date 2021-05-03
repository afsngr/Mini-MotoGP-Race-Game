package Race;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CarProject.Others;

public class Race  extends JFrame implements KeyListener{

	  private ImageIcon player1main = new ImageIcon(getClass().getResource("image1.jpg"));
	  private ImageIcon player1left = new ImageIcon(getClass().getResource("image2.jpg"));
	  private ImageIcon player1right = new ImageIcon(getClass().getResource("image3.jpg"));
	  private ImageIcon player1bomb = new ImageIcon(getClass().getResource("exploding.jpg"));
	  
	  private Image img = player1main.getImage();
	  private Image img2 = player1left.getImage();
	  private Image img3 = player1right.getImage();
	  private Image img4 = player1bomb.getImage();

	  
	  private int xActor=685;
	  private int yActor=615;
	  boolean flag_left=false;
	  boolean flag_right=false;
	  boolean flag_bomb = false;
	  static boolean flag_up = false;
	  static boolean flag_down= false;
	  
	  int x=690;   //starting point of divider lines
	  int y=0,y1=125,y2=250,y3=375,y4=500,y5=625,y6=750,y7=875;
	  int xend=500;    //ending strip
	  int yend=-3000; 
	  
	  Label lose=new Label("GAME OVER");   //labels
	  Label win=new Label("YOU WIN");
	  Font myFont = new Font("Serif", Font.BOLD,30);  //setting font
	  
	  public Race(String s) {
	  	super(s);
	  	
	  	}
	public void moveBox()
	  {
			repaint();
	 }
	
	public void movement() {
	
		
		y+=1;     //divider lines movement
		y1+=1;
		y2+=1;
		y3+=1;
		y4+=1;
		y5+=1;
		y6+=1;
		y7+=1;
		
		if(y==900)     //recycling divider lines
			y=-100;
		if(y1==900)
			y1=-100;
		if(y2==900)
			y2=-100;
		if(y3==900)
			y3=-100;
		if(y4==900)
			y4=-100;
		if(y5==900)
			y5=-100;
		if(y6==900)
			y6=-100;
		if(y7==900)
			y7=-100;
		
		if(yActor==yend)    //you won
		{
			win.setBounds(500,200,400,200);
			win.setForeground(Color.white);
			win.setBackground(Color.black);
			win.setFont(myFont);
			add(win);
		}

		yend=yend+1;	
	}
	
	public void paint(Graphics g) 
	{
		super.paint(g);
		g.setColor(Color.white);
		g.fillRect(0,0,1400,900); 

		g.setColor(Color.orange); //background except road
		g.fillRect(0,0,500,900);
		g.fillRect(900,0,500,900);
		
    	g.setColor(Color.black);
    	g.fillRect(x, y, 10, 75);    //designing divider lines
    	g.fillRect(x,y1,10,75);
    	g.fillRect(x,y2,10,75);
    	g.fillRect(x,y3,10,75);
    	g.fillRect(x,y4,10,75);	
    	g.fillRect(x,y5,10,75);
    	g.fillRect(x,y6,10,75);	
    	g.fillRect(x,y7,10,75);
    	
		g.setColor(Color.black);
		g.fillRect(xend,yend,400,20);  //designing end strip
		
		
		if((flag_left == false && flag_right == false) ) 
			g.drawImage(img,xActor,yActor,null);
		else if(flag_left == true && flag_right == false) {

			g.drawImage(img2,xActor,yActor,null);
		}
		else if(flag_right == true && flag_left == false) {
			
			g.drawImage(img3,xActor,yActor,null);
		}
		
		if(flag_bomb == true) {
			g.drawImage(img4,xActor,yActor,null);
		}


	}
	public void Gui() {
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setSize(1400,900);
		  addKeyListener(this);
		  setVisible(true);
	}
	public void changeLayoutLeft()
	{
		
		xActor = xActor- 4 ;	
		moveBox();

	}
	public void changeLayoutRight()
	{
		
		xActor = xActor + 4 ;
		moveBox();
	}
	public void changeLayoutUp()
	{
		flag_up = true;
		moveBox();
	}
	public void changeLayoutDown()
	{
		flag_down = true;
		moveBox();
	}
	public void keyPressed(KeyEvent event) {
		String whichKey=KeyEvent.getKeyText(event.getKeyCode());
		
		if(flag_bomb == false) {
			
			if(whichKey.compareTo("Left")==0)
			{
				changeLayoutLeft();
				if(xActor<=500)
				{
					flag_bomb = true;
					lose.setBounds(500,200,400,200);  //game over left side
					lose.setForeground(Color.white);
					lose.setBackground(Color.black);
					lose.setFont(myFont);
					add(lose);
					yend=-10000; //finish line goes further away
				}
				flag_right= false;
				flag_left= true;
			
			}else if(whichKey.compareTo("Right")==0){
				changeLayoutRight();
				if((xActor+50)>=900)
				{
					flag_bomb = true;
					lose.setBounds(500,200,400,200);  //game over right side
					lose.setForeground(Color.white);
					lose.setBackground(Color.black);
					lose.setFont(myFont);
					add(lose);
					yend=-10000; //finish line goes further away
				}
				flag_left = false;
				flag_right = true;
			
			}else if(whichKey.compareTo("Up")==0){
			
				changeLayoutUp();
			}else if(whichKey.compareTo("Down")==0){
			
				changeLayoutDown();	
			}else{		
			}	

		}
	}
	public void keyTyped(KeyEvent e) {}


	public void keyReleased(KeyEvent event) {
		
		String whichKey=KeyEvent.getKeyText(event.getKeyCode());
		
		
		if(whichKey.compareTo("Left")==0)
		{
			flag_left = false;
			
		}else if(whichKey.compareTo("Right")==0){
			flag_right = false;
			
		}else if(whichKey.compareTo("Up")==0){
			flag_up = false;
			
		}else if(whichKey.compareTo("Down")==0){
			flag_down = false;
			
		}else{		
		}	
	}

	public static void main(String[] args) throws InterruptedException {
		Race race = new Race("Race");
		JPanel panel = new JPanel();
		Others other1 = new Others();
		panel.add(other1);
		panel.setLayout(null);
		panel.setBounds(0, 0,  1400, 900);
		panel.setPreferredSize(new Dimension(1400, 900));
		race.add(panel);
		race.pack();
		race.Gui();
		Thread t = new Thread(other1);
		t.start();
		while(true) {
			race.movement();
			race.repaint();
			other1.repaint();
			if(flag_up == true && flag_down == false ) {
				Thread.sleep(5);
			}
			else if(flag_down == true  && flag_up == false) {
				Thread.sleep(15);
			}
			else {
				Thread.sleep(7);
			}
		}
	}
	
}