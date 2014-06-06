package cglib;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ReflectDemo {
	public static void main(String[] args) {
		// test reflect
				ConcreteClassNoInterface ccni = new ConcreteClassNoInterface();
				try {
					Method method2 = ccni.getClass().getMethod("getConcreteMethodB",new Class[] { int.class });
					System.out.println(method2.invoke(ccni, new Object[] { 100 }));
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}