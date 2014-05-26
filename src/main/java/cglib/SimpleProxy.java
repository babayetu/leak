package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class SimpleProxy {
	public static void main(String[] args) {
		SimpleProxy sp = new SimpleProxy();
		Object retObject = sp.createProxy(MyClass.class);
		((MyClass)retObject).doSth();
	}
	
	/**
     * Create a proxy using NoOp callback. The target class
     * must have a default zero-argument constructor.
     *
     * @param targetClass the super class of the proxy
     * @return a new proxy for a target class instance
     */
    public Object createProxy(Class<?> targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(NoOp.INSTANCE);
        return enhancer.create();
   }
    
    public SimpleProxy() {}
}