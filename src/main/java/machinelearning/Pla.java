package machinelearning;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pla {
	private final static Color frontColorBlue = Color.BLUE;
	private final static Color frontColorRed = Color.RED;
	private final static int Radius = 5;
	private int CanvasWidth = 500;
	private int CanvasHeight = 500;
	
	private DataPoint[] data = new DataPoint[] {
			new DataPoint(new XYPair(10,10),-1),
			new DataPoint(new XYPair(40,50),-1),
			new DataPoint(new XYPair(100,120),1),
			new DataPoint(new XYPair(200,300),1),
			new DataPoint(new XYPair(150,90),1),
	};
	
	  public static void main(String[] args) {  
		  DrawPanel frame = new Pla().new DrawPanel();  
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		  frame.setSize(500, 500);  
		  frame.setLocationRelativeTo(null);  
		  frame.setVisible(true);
	}
	
	class DrawPanel extends JFrame {
		public DrawPanel() {  
		    setTitle("画点");  
		    getContentPane().add(new PointPanel());  
		}  
	}
	
	 class PointPanel extends JPanel {  
		  protected void paintComponent(Graphics g) {  
		    super.paintComponent(g);  
		    
		    CanvasHeight = getHeight();
		    CanvasWidth = getWidth();
		    
		    XYPair tmp = null; 
		    for (int i =0; i<data.length; i++) {
		    	tmp = XYPair.convertOriginPoint2LeftCorner(data[i].getPos(), CanvasHeight);
		    	if (data[i].getSign() == 1) {
		    		g.setColor(frontColorBlue); //设置蓝色  
		    		g.drawString(String.valueOf(i), tmp.getX(), tmp.getY());
		    		//g.drawOval(tmp.getX(), tmp.getY(), Radius, Radius);
		    		//g.fillOval(tmp.getX(), tmp.getY(), Radius, Radius);
		    	} else if (data[i].getSign() == -1) {
		    		g.setColor(frontColorRed); //设置红色
		    		g.drawString(String.valueOf(i), tmp.getX(), tmp.getY());
		    		//g.drawOval(tmp.getX(), tmp.getY(), Radius, Radius);
		    		//g.fillOval(tmp.getX(), tmp.getY(), Radius, Radius);
		    	}		    	
		    } 
		  } 
	 }
}