/**
 * 
 */

/**
 * @author limshen
 *
 */
public class Reference {
	private int itemOuter=3;
	
	public Reference(){
		System.err.println("Initial Outer Class");
		itemOuter = 5;
	}
	
	public class ReferenceInnerPublic {
		private int itemInner;
		public ReferenceInnerPublic(){
			System.err.println("Initial Inner Class ReferenceInnerPublic");
			this.itemInner = Reference.this.itemOuter;
		}
		
		public void foo(){
			System.out.println("ItemOnner = " + itemInner);
			System.out.println("ItemOuter = " + itemOuter);
		}
	}
	
	private class ReferenceInnerPrivate {
		public ReferenceInnerPrivate(){
			System.err.println("Initial Inner Class ReferenceInnerPrivate");
		}
		
		public void foo(){
			System.out.println("In ReferenceInnerPrivate");
		}
	}
	
	public static void main(String[] args) {
		Reference outer = new Reference();
		Reference.ReferenceInnerPublic innerPublic = outer.new ReferenceInnerPublic();
		Reference.ReferenceInnerPrivate innerPrivate = outer.new ReferenceInnerPrivate();
		
		innerPublic.foo();
		innerPrivate.foo();
	}

}

class Test {
	public static void test(){
		Reference r = new Reference();
		
		Reference.ReferenceInnerPublic innerPublic = r.new ReferenceInnerPublic();
//		Illegal : Reference.ReferenceInnerPrivate innerPrivate = r.new ReferenceInnerPrivate();
	}
}
/* Output:
 * Initial Outer Class
 * Initial Inner Class ReferenceInnerPublic
 * Initial Inner Class ReferenceInnerPrivate
 * ItemOnner = 5
 * ItemOuter = 5
 * In ReferenceInnerPrivate
 */
