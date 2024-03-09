package kr.or.ddit.basic;
// 동기화 처리
// 각 쓰레드들이 공통된 데이터 사용할 때 데이터를 하나의 쓰레드가 독점해서 끝까지 쓸때까지 데이터를 보호해주는 기능
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
	Vector는 동기화 처리 되어있고 ArrayList는 동기화처리가 안되어있음.
	 
	- Vector, Hashtable (map 옛날 버전) 등의 예전부터 지원하는 Collection객체들은 
	    내부에 동기화 처리가 되어있다.
	  
	 그런데, 새로 구성된 Collection들은 동기화 처리가 되어 있지 않다.
	 그래서, 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면
	 동기화 처리를 한 후에 사용해야 한다.
	
 */

public class ThreadTest17 {
	// Vector는 내부적으로 동기화 처리가 되어 있다. 
	// 평상시 쓰레드 쓰지 않을 때는 동기화 처리하는 vector는 잘 사용안함.
	private static Vector<Integer> vec = new Vector<Integer>();
	
	// 동기화 처리가 되지 않은 List
	// 만약 쓰레드 써야하는데 리스트 쓰고싶다? 동기화문제 생기니 동기화 처리 해주자!
	private static List<Integer> list1 = new ArrayList<Integer>();
	
	// 동기화 처리를 한 List
	private static List<Integer> list2 = 
			Collections.synchronizedList(new ArrayList<Integer>());
	
	public static void main(String[] args) {
		// 익명 구현체로 쓰레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=10000; i++){
//					vec.add(i);
//					list1.add(i);
					list2.add(i);
				}
			}
		};
		// -----------------------------------
		Thread[] thArr = new Thread[] {
				new Thread(r), new Thread(r), new Thread(r),
				new Thread(r), new Thread(r)
		};
		
		for(Thread th : thArr) {
			th.start();
		}
		
		for(Thread th : thArr) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
//		System.out.println("vec의 개수 : " + vec.size());
//		System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
		
	}
	
}
