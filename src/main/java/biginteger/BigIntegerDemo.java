package biginteger;

import java.math.BigInteger;

public class BigIntegerDemo {
	public static void main(String[] args) {
		BigInteger bi = new BigInteger("13");
		System.out.println(bi.isProbablePrime(5));
	}
}