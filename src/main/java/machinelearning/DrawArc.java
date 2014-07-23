package machinelearning;

import javax.swing.JFrame;  
import javax.swing.JPanel;  
import java.awt.Color;  
import java.awt.Graphics;  
 
public class DrawArc extends JFrame {  
  public DrawArc() {  
    setTitle("画弧形");  
    getContentPane().add(new ArcsPanel());  
  }  
 
  /** 主方法 */ 
  public static void main(String[] args) {  
    DrawArc frame = new DrawArc();  
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    frame.setSize(250, 300);  
    frame.setLocationRelativeTo(null);  
    frame.setVisible(true);  
  }  
}  
 
// 在面板上画弧形的类  
class ArcsPanel extends JPanel {  
  protected void paintComponent(Graphics g) {  
    super.paintComponent(g);  
    g.setColor(Color.BLUE); //设置弧形的颜色为蓝色  
 
    int i=0;  
    int xCenter = getWidth() / 2;  
    int yCenter = getHeight() / 2;  
    int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);  
 
    int x = xCenter - radius;  
    int y = yCenter - radius; 
    
    //g.drawLine(x, y, x+20, y+20);
    //g.drawLine(0, 0, xCenter, yCenter);
    //g.drawLine(xCenter, yCenter,getWidth(),getHeight());
    XYPair a = new XYPair(10,10);
    XYPair b = XYPair.convertOriginPoint2LeftCorner(a, getHeight());
    g.drawOval(b.getX(), b.getY(), 5, 5);
    g.fillOval(b.getX(), b.getY(), 5, 5);
 
  }  
  
  
}  