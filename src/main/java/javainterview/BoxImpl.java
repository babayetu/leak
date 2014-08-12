package javainterview;

import java.util.ArrayList;


public class BoxImpl<T> implements Box<T> {
	private T al;
	
    public static<T> Box<T> make() {
        return new BoxImpl<T>();
    }

	@Override
	public T get() {
		return al;
	}

	@Override
	public void put(T element) {
		// 不知道构造函数是什么，无法new 或者clone的，如有必要只能借助于ArrayList, HashSet这种来put进去
		al = element;
	}    
    
}