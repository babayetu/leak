package zzm.ch8;

import java.io.Serializable;

public class Overload {
	public static void sayHello(Object a) {
		System.out.println("hello object");
	}
	public static void sayHello(int a) {
		System.out.println("hello int");
	}
	public static void sayHello(long a) {
		System.out.println("hello long");
	}
	public static void sayHello(Character a) {
		System.out.println("hello Character");
	}
	public static void sayHello(char a) {
		System.out.println("hello char");
	}
	public static void sayHello(char... a) {
		System.out.println("hello char...");
	}
	public static void sayHello(Serializable a) {
		System.out.println("hello Serializable");
	}
	public static void main(String[] args) {
		sayHello('a');
	}
	
}