interface AnonymousInnerClass{
	public void foo();
}

abstract class AnonymousInnerClassWithConstructor{
	public AnonymousInnerClassWithConstructor(String s){
		System.out.println(s);
	}
};

public class AnonymousInnerClassTest {

	public AnonymousInnerClassTest() {}
	
	public AnonymousInnerClass test1(final String s){
		return new AnonymousInnerClass(){
			public void foo(){
				System.out.println(s);
			}
		};
	}
	
	public AnonymousInnerClassWithConstructor test2(String s){
		return new AnonymousInnerClassWithConstructor(s){
			{System.out.println("In AnonymousInnerClassWithConstructor");}
		};
	}

	public static void main(String[] args) {
		AnonymousInnerClassTest t = new AnonymousInnerClassTest();
		AnonymousInnerClass a1 = t.test1("test1...");
		a1.foo();
		
		AnonymousInnerClassWithConstructor a2 = t.test2("test2...");
	}
}
/**
 * Output:
 * test1...
 * test2...
 * In AnonymousInnerClassWithConstructor
 */
