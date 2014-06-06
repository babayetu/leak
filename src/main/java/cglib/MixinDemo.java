package cglib;

import net.sf.cglib.proxy.Mixin;
 
 
public class MixinDemo {
    public static void main(String[] args) {
        Class<?>[] interfaces = new Class[] {MyInterfaceA.class, MyInterfaceB.class};
        Object[] delegates = new Object[] {new MyInterfaceAImpl(), new MyInterfaceBImpl()};
         
        Object o = Mixin.create(interfaces, delegates);
         
        MyInterfaceA a = (MyInterfaceA)o;
        a.methodA();
         
        MyInterfaceB b = (MyInterfaceB)o;
        b.methodB();
    }
}
 
interface MyInterfaceA {
 
    public void methodA();
}
 
interface  MyInterfaceB {
    public void methodB();
}
 
class MyInterfaceAImpl implements MyInterfaceA {
 
    public void methodA() {
        System.out.println("MyInterfaceAImpl.methodA()");
    }
}
 
class MyInterfaceBImpl implements MyInterfaceB {
 
    public void methodB() {
        System.out.println("MyInterfaceBImpl.methodB()");
    }
}