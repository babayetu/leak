package cglib;

import net.sf.cglib.proxy.Enhancer;

public class LoaderBean {
	private String loaderName;
	private int loaderValue;
	private PropertyBean propertyBean;
	public LoaderBean(){
		this.loaderName="loaderNameA";
		this.loaderValue=123;
		this.propertyBean=createPropertyBean();
	}
	protected PropertyBean createPropertyBean(){
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(PropertyBean.class);
		return (PropertyBean)enhancer.create(PropertyBean.class,new ConcreteClassLazyLoader());
	}
	/**
	 * @return the loaderName
	 */
	public String getLoaderName() {
		return loaderName;
	}
	/**
	 * @param loaderName the loaderName to set
	 */
	public void setLoaderName(String loaderName) {
		this.loaderName = loaderName;
	}
	/**
	 * @return the loaderValue
	 */
	public int getLoaderValue() {
		return loaderValue;
	}
	/**
	 * @param loaderValue the loaderValue to set
	 */
	public void setLoaderValue(int loaderValue) {
		this.loaderValue = loaderValue;
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