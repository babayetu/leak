package machinelearning;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.misc.Signal;
import sun.net.www.content.audio.wav;

public class Pla {
	private final static Color frontColorBlue = Color.BLUE;
	private final static Color frontColorRed = Color.RED;
	private final static int Radius = 5;
	private int CanvasWidth = 500;
	private int CanvasHeight = 500;

	private DataPoint[] data = new DataPoint[] {
			new DataPoint(new XYPair(13, 12), -1),
			new DataPoint(new XYPair(41, 56), -1),
			new DataPoint(new XYPair(107, 123), -1),
			new DataPoint(new XYPair(203, 302), -1),
			new DataPoint(new XYPair(153, 99), -1),
			new DataPoint(new XYPair(251, 198), 1), };
	// 两维x,y, 则x0=1, x1=x, x2=y 共三个变量
	private double[] x = new double[] { 1, 0, 0 };

	// 所以w向量也是3维
	private double[] w = new double[] { 0, 0, 0 };

	private double drawPoint1, drawPoint2;

	private int Signal(double value) {
		if (value > 0) {
			return 1;
		} else if (value < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	private double InnerProduct() {
		return x[0] * w[0] + x[1] * w[1] + x[2] * w[2];
	}

	public static void main(String[] args) {
		Pla runPla = new Pla();
		DrawPanel frame = runPla.new DrawPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		int i = 0;
		while (i < runPla.data.length) {
			runPla.x[1] = runPla.data[i].getPos().getX();
			runPla.x[2] = runPla.data[i].getPos().getY();
			if (runPla.Signal(runPla.InnerProduct()) == runPla.data[i]
					.getSign()) {
				// continue find next point
//				System.out.println("sign is equal. i=" + i + " x="
//						+ runPla.x[1] + " y=" + runPla.x[2]);
				i++;
			} else {
				// find a point with wrong partition
//				System.out.println("sign is unequal. i=" + i + " x="
//						+ runPla.x[1] + " y=" + runPla.x[2]);
				runPla.w[0] = runPla.w[0] + runPla.data[i].getSign()
						* runPla.x[0];
				runPla.w[1] = runPla.w[1] + runPla.data[i].getSign()
						* runPla.x[1];
				runPla.w[2] = runPla.w[2] + runPla.data[i].getSign()
						* runPla.x[2];
//				System.out.println("w0=" + runPla.w[0] + " w1=" + runPla.w[1]
//						+ " w2=" + runPla.w[2]);
				i = 0;
			}
		}

		System.out.println("w0=" + runPla.w[0] + " w1=" + runPla.w[1] + " w2="
				+ runPla.w[2]);
		
		System.out.println("point 4 value:" + (runPla.w[0] + runPla.data[4].getPos().getX() * runPla.w[1] + 
				runPla.data[4].getPos().getY() * runPla.w[2]));
		
		System.out.println("point 5 value:" + (runPla.w[0] + runPla.data[5].getPos().getX() * runPla.w[1] + 
				runPla.data[5].getPos().getY() * runPla.w[2]));
		// w0=-2741.0 w1=37.0 w2=-14.0
		// 我们得到的公式是37.0 * x + (-14) * y + (-2741.0) = 0 直线方程
		// 画成直线的方法是，找到画图轴xy上的两点，然后连起来

		if (Math.abs(runPla.w[1]) > 0.1) {
			// 求y=500时x的值
			runPla.drawPoint1 = (runPla.w[0] +  frame.getHeight() * runPla.w[2]) / ((-1) * runPla.w[1]);
			//求y=0, x值
			runPla.drawPoint2 = runPla.w[0] / ((-1) *runPla.w[1]);
		} else {
			// w[1] ==0 that means the line is vertial to one axis
			runPla.drawPoint1 = 0.0;
			// w[1] ==0 that means the line is vertial to one axis
			runPla.drawPoint2 = 0.0;
		}

		System.out.println("drawPoint1:(" + runPla.drawPoint1
				+ ",500) drawPoint2:(" + runPla.drawPoint2+",0)");

		
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
			
			System.out.println("CanvasHeight:"+CanvasHeight+" CanvasWidth:"+CanvasWidth);
			
			XYPair tmp = null;
			for (int i = 0; i < data.length; i++) {
				tmp = XYPair.convertOriginPoint2LeftCorner(data[i].getPos(),
						CanvasHeight);
				if (data[i].getSign() == 1) {
					g.setColor(frontColorBlue); // 设置蓝色
					g.drawString(String.valueOf(i), tmp.getX(), tmp.getY());
					g.drawOval(tmp.getX(), tmp.getY(), Radius, Radius);
					g.fillOval(tmp.getX(), tmp.getY(), Radius, Radius);
				} else if (data[i].getSign() == -1) {
					g.setColor(frontColorRed); // 设置红色
					g.drawString(String.valueOf(i), tmp.getX(), tmp.getY());
					g.drawOval(tmp.getX(), tmp.getY(), Radius, Radius);
					g.fillOval(tmp.getX(), tmp.getY(), Radius, Radius);
				}
			}
			
			// draw line
			XYPair origin1,convertPoint1,origin2,convertPoint2;

			origin1 = new XYPair((int)drawPoint1, getHeight());
			convertPoint1 = XYPair.convertOriginPoint2LeftCorner(origin1,
						getHeight());

			origin2 = new XYPair((int)drawPoint2, 0);
			convertPoint2 = XYPair.convertOriginPoint2LeftCorner(origin2,
						getHeight());
			System.out.println(convertPoint1.getX()+ " "+ convertPoint1.getY()+
					 " "+ convertPoint2.getX()+ " "+ convertPoint2.getY());
			g.drawLine(convertPoint1.getX(),convertPoint1.getY(),convertPoint2.getX(),convertPoint2.getY());

			
			// g.drawLine(drawPoint1.getX(),drawPoint1.getY(),drawPoint2.getX(),drawPoint2.getY());
			// g.drawLine(0, 0, getWidth() / 2, getHeight() / 2);
			// g.setColor(frontColorBlue);
			// g.drawLine(55, 455, 0, 455);

		}
	}
}