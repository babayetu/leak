package datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// 书签 P51/64
public class ListDemo {
	private List<Integer> lst;
	private int times;
	
	public void makeList1(List<Integer> lst, int N) {
		lst.clear();
		for (int i=0;i<N;i++) {
			lst.add(i);
		}
	}

	public void makeList2(List<Integer> lst, int N) {
		//在head增加元素，ArrayList需要每次都移动元素位置，耗费巨大
//		Consumed time： 146ms
//		Consumed time： 478ms   数目2倍，时间4倍， O(N^2)
//		Consumed time： 7ms     
//		Consumed time： 6ms     数目2倍，时间几乎不动, O(N)
		lst.clear();
		for (int i=0;i<N;i++) {
			lst.add(0,i);
		}
	}
	
	public int sum(List<Integer> lst, int N) {
//		Consumed time： 3ms
//		Consumed time： 1ms      数目2倍，时间几乎不动, O(N)
//		Consumed time： 706ms
//		Consumed time： 2807ms    数目2倍，时间4倍， O(N^2)  LinkedList get操作为O(N)
		int total = 0;
		for (int i=0;i<N;i++) {
			total += lst.get(i);
		}
		return total;
	}
	
	//时间计算
	public void timeCalc() {
		long startTime=System.currentTimeMillis();
		removeEvensVer3(lst);
		long endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("Consumed time： "+(endTime-startTime)+"ms");
	}
	
	//Consumed time： 940ms
	public static void removeEvensVer1 (List<Integer> lst) {
		int i=0;
		while (i< lst.size()) {
			if (lst.get(i) % 2 == 0) {  //对LinkedList get()太慢了
				lst.remove(i);
			} else {
				i++;
			}
		}
	}

	// java.util.ConcurrentModificationException
	// for增强型，无法直接删除remove
	public static void removeEvensVer2 (List<Integer> lst) {
		for (Integer i : lst) {
			if (i % 2 == 0) {
				lst.remove(i);
			}
		}
	}

	//LinkedList Consumed time： 10ms
	//ArrayList 由于需要移动，还是太慢了
//	Consumed time： 8660ms
//	Consumed time： 30505ms   大约4倍， O(N^2)
	public static void removeEvensVer3 (List<Integer> lst) {
		Iterator<Integer> ii = lst.iterator();
		
		while (ii.hasNext()) {
			if (ii.next() % 2 == 0) {
				ii.remove();
			}
		}
	}
	
	public static void main(String[] args) {
		int N= 400000;
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ListDemo ld = new ListDemo();		
		ld.setLst(al);
		ld.setTimes(N);	
		ld.makeList1(al, N);
		ld.timeCalc();
		ld.makeList1(al, 2*N);
		ld.setTimes(2*N);
		ld.timeCalc();
		
/*		ld.setLst(ll);
		ld.setTimes(N);
		ld.makeList1(ll, N);
		ld.timeCalc();*/
/*		ld.setTimes(2*N);
		ld.makeList1(ll, 2*N);
		ld.timeCalc();*/
	}


	public List<Integer> getLst() {
		return lst;
	}

	public void setLst(List<Integer> lst) {
		this.lst = lst;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
	
	
}