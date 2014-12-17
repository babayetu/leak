package datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 数据结构与算法 java P52
 * @author jingjliu
 *
 */
public class MyArrayList<T> implements Iterable<T> {
	T[] items;
	int size = 0;
	private static int DEFAULTCAPACITY = 10;
	
	
	protected MyArrayList() {
		ensureCapacity(DEFAULTCAPACITY);
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		ensureCapacity(DEFAULTCAPACITY);
	}
	
	private void ensureCapacity(int newCapacity) {
		if (newCapacity < size) {
			return;
		}
		
		T[] old = items;
		T[] newOne = (T[])new Object[newCapacity];
		for (int i =0;i<size; i++) {
			newOne[i] = old[i];
		}	
		items = newOne;
	}
	
	public T get(int idx) {
		return items[idx];
	}
	
	public void set(T value, int idx) {
		items[idx] = value;
	}
	
	public void add(T value) {
		add(size ,value);
	}
	
	public void add(int idx, T value) {
		//size是下一个数组插入位置
		if (size == items.length - 1) {
			ensureCapacity(size * 2 + 1);
		}
		
		for (int i=size;i>idx;i--) {
			items[i+1] = items[i]; 
		}
		items[idx] = value;
		size++;
	}
	
	public T remove(int idx) {
		if (idx >= size) {
			return null;
		}
		if (size == 0) {
			return null;
		}
		
		T oldValue = items[idx];
		for (int i=idx;i<size;i++) {
			items[i] = items[i+1];
		}
		
		size--;
		return oldValue;
	}
	
	//不能叫T[] toArray()，因为List已经有一个函数签名Object[] toArray()了
	//最后出来的结果不是T[]，而是Object[]
	public T[] generateArray(T[] t) {
		if (size>0 && t.length == size) {
			for (int i=0;i<size;i++) {
				t[i] = items[i];
			}
			return t;
		} else {
			return null;
		}		
	}
	
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T> {
		private int pos;
		
		protected ArrayListIterator() {
			pos = 0;
		}

		public int getPos() {
			return pos;
		}

		public void setPos(int pos) {
			this.pos = pos;
		}

		public boolean hasNext() {
			if (getPos() < size) {
				return true;
			} else {
				return false;
			}			
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				return items[pos++];
			}			
		}

		public void remove() {
			MyArrayList.this.remove(--pos);
		}
		
	}
	
}