package cglib;

import net.sf.cglib.proxy.LazyLoader;

public class ConcreteClassLazyLoader implements LazyLoader{
	/**
	 *  public java.lang.Object loadObject() throws java.lang.Exception
     *  Return the object which the original method invocation should be dispatched. 
     *  Called as soon as the first lazily-loaded method in the enhanced instance is invoked. 
     *  The same object is then used for every future method call to the proxy instance.
	 */
	public Object loadObject() throws Exception {
		System.out.println("LazyLoader loadObject() ...");
		PropertyBean bean=new PropertyBean();
		bean.setPropertyName("lazy-load object propertyName!");
		bean.setPropertyValue(11);
		return bean;
	}
}