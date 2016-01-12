package ThinkInJava.InnerClass;
class OuterClass {
	class InnerClass {
		public InnerClass(int i){
			System.out.println(i);
		}
	}
}

public class InheritInner extends OuterClass.InnerClass{

	public InheritInner(OuterClass o) {
		// TODO Auto-generated constructor stub
		o.super(1);
	}

}
