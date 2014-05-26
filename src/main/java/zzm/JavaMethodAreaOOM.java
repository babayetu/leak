package zzm;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author jingjliu
 *
 */
public class JavaMethodAreaOOM {
	public static void main(String[] args) throws Exception {
			while (true) {
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(OOMObject.class);
				enhancer.setUseCache(false);
				enhancer.setCallback(new MethodInterceptor() {
					
					public Object intercept(Object object, Method method, Object[] args,
							MethodProxy proxy) throws Throwable {
						// TODO Auto-generated method stub
						return proxy.invokeSuper(object, args);					
					}
				});
				enhancer.create();
			}		
	}
	
	static class OOMObject {
		
	}
}