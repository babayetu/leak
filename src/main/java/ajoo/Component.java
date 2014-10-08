package ajoo;

/**
 * getType()用来返回这个Component生成的对象的类型。 
create用来创建这个对象。 
verify用来保证这个对象可以被创建。
 * @author jingjliu
 *
 */
public interface Component{ 
  Class getType(); 
  Object create(Dependency dep); 
  Class verify(Dependency dep); 
}