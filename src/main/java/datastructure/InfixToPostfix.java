package datastructure;

import util.Print;
import algorithm.BSTNode;

/**
 * 整数中缀表达式到后缀表达式的转换
 * 算法如下：
 * 1.如果是数字，直接输出
 * 2.如果是操作符 +, -, *, /, %, (, )
 *   +,- 优先级 1
 *   乘/ % 优先级2
 *   ( ) 优先级3
 * 2.1. 栈空，入栈
 * 2.2. 弹出栈中优先级大于等于目前操作符的，且不是(的，当前操作符入栈
 * 2.3. ( 直接入栈
 * 2.4  ) 弹出所有栈中操作符，一直弹到(。 ( )本身不输出
 * 
 *  3. 扫描完毕，弹出栈中剩余操作符，输出
 * 
 * @author jingjliu
 *
 */
public class InfixToPostfix {
	private MyLinkedList<Character> stack = new MyLinkedList<Character>();
	
	public static int priority(char oper) {		
		if (oper == '+' || oper == '-' ) {
			return 1;
		} else if (oper == '*' || oper == '/' || oper == '%') {
			return 2;
		} else if (oper == '(') {
			return 3;
		} else if (oper == ')') {
			return 4;
		} else {
			return -1;
		}
	}
	
	public static int priority(String oper) {		
		if (oper.equals("+") || oper.equals("-") ) {
			return 1;
		} else if (oper.equals("*") || oper.equals("/") || oper.equals("%")) {
			return 2;
		} else if (oper.equals("(")) {
			return 3;
		} else if (oper.equals(")")) {
			return 4;
		} else {
			return -1;
		}
	}
	
	public char[] transform(char[] input) {
		if (input.length == 0 ) {
			return input;
		}
		
		//指向下一个output输出的位置
		int cursor = 0;
		
		//输出数组将过滤掉()，所以需要减掉()的个数
		int effectCharNum = 0;
		for (int i =0;i<input.length;i++) {
			if (input[i] != '(' && input[i] != ')') {
				effectCharNum++;
			}
		}
		char[] output= new char[effectCharNum];
		
		//O(N)
		for (int i=0; i<input.length;i++) {
			// Step 1
			int pri = priority(input[i]);
			if (pri < 0 ) {
				output[cursor] = input[i];
				cursor++;
			} else  {
				
				//Step 2.3
				if (pri == 3) {
					// (
					stack.push(input[i]);
					continue;
				}
				
				//Step 2.4
				if (pri == 4) {
					// )
					while (stack.top() != null && stack.top() != '(') {
						output[cursor] = stack.pop();
						cursor++;
					}
					stack.pop();
					continue;
				}
				
				//Step 2.1
				if (stack.isEmpty()) {
					stack.push(input[i]);
				} else {
					//Step 2.2
					while (!stack.isEmpty() && priority(stack.top()) >= pri && priority(stack.top()) != 3) { //第三个判断条件不是(
						output[cursor] = stack.pop();
						cursor++;
					}
					stack.push(input[i]);
				}				
			}
		}
		
		//Step 3
		while (!stack.isEmpty()) {
			output[cursor] = stack.pop();
			cursor++;
		}
		
		return output;
	}

	/**
	 * 使用String[]储存后缀表达式，可以做到多位数字的存储。也可以完成float，double等数据的存储
	 * 这点是char[]做不到的
	 * @param sInput
	 * @return
	 */
	public String[] transform(String sInput) {
		if (sInput.length() == 0 ) {
			return null;
		}
		
		char[] input = sInput.toCharArray();
		
		MyArrayList<String> output= new MyArrayList<String>();
		
		StringBuffer sb = new StringBuffer();
		
		//O(N)
		for (int i=0; i<input.length;i++) {
			// Step 1
			int pri = priority(input[i]);
			if (pri < 0 ) {
				sb.append(input[i]);
			} else  {
				//扫描到操作符，如果StringBuffer里面不为空，则输出一个数字
				if (sb.length() > 0) {
					output.add(sb.toString());
					sb.delete(0, sb.length());
				}
				
				//Step 2.3
				if (pri == 3) {
					// (
					stack.push(input[i]);
					continue;
				}
				
				//Step 2.4
				if (pri == 4) {
					// )
					while (stack.top() != null && stack.top() != '(') {
						//操作符总是占String[]的一个元素
						output.add(String.valueOf(stack.pop()));
					}
					stack.pop();
					continue;
				}
				
				//Step 2.1
				if (stack.isEmpty()) {
					stack.push(input[i]);
				} else {
					//Step 2.2
					while (!stack.isEmpty() && priority(stack.top()) >= pri && priority(stack.top()) != 3) { //第三个判断条件不是(
						output.add(String.valueOf(stack.pop()));
					}
					stack.push(input[i]);
				}				
			}
		}
		
		//Step 3
		//最后一个数字需要被输出，不然就没有输出机会了，因为是最后一个数字，之后没有操作符了，
		//没有触发的机会   P68/81 Java数据结构
		if (sb.length() > 0) {
			output.add(sb.toString());
			sb.delete(0, sb.length());
		}
		while (!stack.isEmpty()) {
			output.add(String.valueOf(stack.pop()));
		}
		
		//return output.toArray();
		//System.out.println(output.generateArray(new String[output.size()]) instanceof Object[]);   //true
		//System.out.println(output.generateArray(new String[output.size()]) instanceof String[]);   // true
		
		return output.generateArray(new String[output.size()]);
	}
	
	//后缀表达式生成表达式操作树，即一个二分查找树，
	//这个例子演示最大的用途不是用来搜索，而是编译器生成表达式操作树
	public static BSTNode transformToTree(String[] postfixExp) {
		if (postfixExp.length == 0) {
			return null;
		}
		
		MyLinkedList<BSTNode> aStack = new MyLinkedList<BSTNode>();
		for (int i = 0; i < postfixExp.length; i++) {
			if (priority(postfixExp[i]) == -1) {
				//是操作数，而不是操作符
				aStack.push(new BSTNode(postfixExp[i]));				
			} else {
				//是操作符
				BSTNode oper = new BSTNode(postfixExp[i]);
				BSTNode right = aStack.pop();
				BSTNode left = aStack.pop();
				oper.setLeft(left);
				oper.setRight(right);
				aStack.push(oper);
			}
		}
		
		return aStack.pop();
	}
	
	public static void main(String[] args) {
		InfixToPostfix itp = new InfixToPostfix();
		
//		System.out.println((int)'0');  //48
//		System.out.println((int)'9');  //57
		
//		String[] oa = new String[] {"aaa","bbb"};
//		Object[] sa = (Object[])oa;
		
		String expr = "1+2*3+(4*5+6)*7";
		//abc*+de*f+g*+
		//char[] output = itp.transform(expr.toCharArray());
		
		//System.out.println(String.valueOf(output));
		String[] output = itp.transform(expr);
		for (String o : output) {
			System.out.println(o);
		}
		
		System.out.println();
		new Print().midOrderBST(transformToTree(output),0);
		
//		BSTNode root = transformToTree(output);
//		System.out.println(root.getValue());
//		System.out.println(root.getLeft().getValue());
//		System.out.println(root.getRight().getValue());
//		System.out.println(root.getLeft().getLeft().getValue());
//		System.out.println(root.getLeft().getRight().getValue());
	}
}