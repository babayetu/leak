package datastructure;

/**
 * 算法：
 * 中缀转成后缀表达式已实现，参考InfixToPostfix
 * 1. 把后缀表达式逐个压入stack
 * 1.1 如果不是操作符，压入
 * 1.2 如果是操作符，弹出栈顶两个数字，计算出结果再次压入栈
 * 
 * @author jingjliu
 *
 */
public class Algorithm {
	private MyLinkedList<Integer> stack = new MyLinkedList<Integer>();
	
	private int calculate(String[] postFix) {
		int i=0;
		while (i<postFix.length) {
			int pri = InfixToPostfix.priority(postFix[i]);
			if (pri > 0) {
				//是操作符
				Integer a = stack.pop();
				Integer b = stack.pop();
				Integer c = null;
				
				if (postFix[i].equals("+")) {
					c = a + b;
				} else if (postFix[i].equals("-")) {
					c = a - b;
				} else if (postFix[i].equals("*")) {
					c = a * b;
				} else if (postFix[i].equals("/")) {
					c = a / b;
				} else if (postFix[i].equals("%")) {
					c = a % b;
				} else {	
					throw new IllegalArgumentException();
				}
				
				stack.push(c);
				i++;
			} else {
				//数字直接入栈				
				stack.push(Integer.valueOf(postFix[i]));
				i++;
			}
		}
		
		return stack.pop();
		
	}
	
	public static void main(String[] args) {
		InfixToPostfix itp = new InfixToPostfix();
		Algorithm al = new Algorithm();
				
		String expr = "1+2*3+(4*5+6)*7";

		//去掉()之后，output的长度比原来的expr少几个元素
		String[] output = itp.transform(expr);
				
		System.out.println(String.valueOf(output));
		System.out.println(al.calculate(output));
	}
}