package cglib;


import java.lang.reflect.Method;
 
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;
 
 
public class CallbackFilterDemo {
    public static void main(String[] args) {
        Callback[] callbacks = new Callback[] {
            new MethodInterceptorImpl(), NoOp.INSTANCE
        };
         
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyClass.class);
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new CallbackFilterImpl());
         
        MyClass myClass = (MyClass) enhancer.create();
         
        // use NoOp.INSTANCE
        myClass.method();
        // use MethodInterceptorImpl
        myClass.method1();
    }
    
    /**
     * return index of Callback[]
     * @author jingjliu
     *
     */
    private static class CallbackFilterImpl implements CallbackFilter {
        public int accept(Method method) {
            if (method.getName().equals("method"))
                return 1;
            else
                return 0;
        }
         
    }
     
    private static class MethodInterceptorImpl implements MethodInterceptor {
        public Object intercept(Object obj, Method method, Object[] args,
                MethodProxy proxy) throws Throwable {
            System.err.println("Before invoke " + method);
            Object result = proxy.invokeSuper(obj, args);
            System.err.println("After invoke" + method);
            return result;
        }
         
    }
}