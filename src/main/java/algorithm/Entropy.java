package algorithm;
/**
 * http://users.aims.ac.za/~mackay/sorting/sorting.html
 * 信息熵计算,log2为底，结果为bit
 * loge为底，结果nat
 * log10为底，结果为dit
 * 
 * @author jingjliu
 *
 */
public class Entropy {
	final static double LogBase10Value2 = Math.log10(2);  //log10(2)
	
	public static double calc(double[] prob) {
		//输入概率数组，计算出这一次的信息熵
		double sum =0.0;
		double tolerance = 0.01;
		
		
		for (int i = 0; i < prob.length; i++) {
			sum += prob[i];
		}
		
		//如果总概率大于1.01,输入有误
		if (Math.abs(sum - 1) > tolerance ) {
			throw new IllegalArgumentException();
		}
		
		double expect = 0.0;
		for (int i = 0; i < prob.length; i++) {
			expect += prob[i] * Math.log10(prob[i]) / LogBase10Value2;
		}
		
		return (-1)*expect;
	}
	
	public static void main(String[] args) {
		//double[] prob = new double[]  {1.0/3.0, 2.0/3.0};  //0.9182958340544894 bit
		//double[] prob = new double[]  {1.0/4.0, 3.0/4.0};  //0.8112781244591328 bit
		double[] prob = new double[]  {1.0/2.0, 1.0/2.0};  // 1.0 bit
		System.out.println(Entropy.calc(prob));
	}
}