package ThinkInJava.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubListTest {

	public SubListTest() {
		// TODO Auto-generated constructor stub
	}
	
	public void foo(){
		System.out.println("In SubListTest");
	}

	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
		List<Integer> l2 = l1.subList(0, 3);
		System.out.println(l1);
		System.out.println(l2);
		
		l1.set(0, 7);
		System.out.println(l1);
		System.out.println(l2);
		
		l2.set(0, 1);
		System.out.println(l1);
		System.out.println(l2);
	}

}