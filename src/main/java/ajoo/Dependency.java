package ajoo;

/**
 * getArgument负责解析一个函数参数，组件告诉Dependency对象，我需要给第3个参数，类型为String的解析依赖。于是就调用 
getArgument(2, String.class)。 

getProperty负责解析一个用某个key来标识的属性。比如一个javabean的property。 

那两个verify是只取得解析到的那个符合要求的组件类型，但是并不实际创建对象。 
 * @author jingjliu
 *
 */
public interface Dependency{ 
  Object getArgument(int i, Class type); 
  Class verifyArgument(int i, Class type); 
  Object getProperty(Object key, Class type); 
  Class verifyProperty(Object key, Class type); 
}