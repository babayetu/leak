package sizeof;

import net.sourceforge.sizeof.SizeOf;

public class SizeOfDemo {
	public static void main(String[] args) {
		System.out.println("Integer: \t\t"+SizeOf.sizeOf(1));
		System.out.println("Char: \t\t"+SizeOf.sizeOf('a'));
		System.out.println("Long: \t\t"+SizeOf.sizeOf(1L));
		System.out.println("Double: \t\t"+SizeOf.sizeOf(1.0D));
	}
}