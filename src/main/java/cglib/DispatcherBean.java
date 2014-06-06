package cglib;

import net.sf.cglib.proxy.Enhancer;


public class DispatcherBean {
	private String name;
	private String value;
	private PropertyBean propertyBean;
	public DispatcherBean(){
		this.name="DispatcherBean";
		this.value="abc";
		this.propertyBean=createDispatcherBean();
	}
	protected PropertyBean createDispatcherBean(){
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(PropertyBean.class);
		return (PropertyBean)enhancer.create(PropertyBean.class,new ConcreteClassDispatcher());
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the propertyBean
	 */
	public PropertyBean getPropertyBean() {
		return propertyBean;
	}
	/**
	 * @param propertyBean the propertyBean to set
	 */
	public void setPropertyBean(PropertyBean propertyBean) {
		this.propertyBean = propertyBean;
	}
    
}