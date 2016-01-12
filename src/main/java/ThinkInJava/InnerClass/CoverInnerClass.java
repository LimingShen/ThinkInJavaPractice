package ThinkInJava.InnerClass;
class BaseClass {
	public BaseClass(){
		System.out.println("In BaseClass constructor");
		Yolk y = new Yolk();
		f();
		g();
	}
	
	protected class Yolk {
		public Yolk(){
			System.out.println("In BaseClass.Yolk");
		}
	}
	
	private Destroy d = new Destroy();
	public class Destroy {
		public Destroy(){
			System.out.println("In BaseClass.Destroy");
		}
		
		public void foo(){
			System.out.println("In BaseClass.Destroy.foo()");
		}
	}
	
	public void f(){
		System.out.println("In BaseClass.f()");
	}
	
	public void insertDestroy(Destroy d2){
		System.out.println(this.getClass().toString());
		d = d2;
	}
	
	public void g(){
		d.foo();
	}
}

public class CoverInnerClass extends BaseClass{

	public CoverInnerClass() {
		System.out.println("In CoverInnerClass constructor");
		System.out.println("----------------------------------------");
		insertDestroy(new Destroy());
	}
	
	//Overwrite inner class
	public class Yolk{
		public Yolk(){
			System.out.println("In CoverInnerClass.Yolk");
		}
	}
	
	public class Destroy extends BaseClass.Destroy{
		public Destroy(){
			System.out.println("In CoverInnerClass.Destroy");
		}
		
		public void foo(){
			System.out.println("In CoverInnerClass.Destroy.foo()");
		}
		
		public class test{
			test(){}
		}
	}
	
	//Overwrite method
	public void f(){
		System.out.println("In CoverInnerClass.f()");
	}
	
	public static void main(String[] args){
		BaseClass b = new BaseClass();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		CoverInnerClass c = new CoverInnerClass();
		System.out.println("============================");
		Yolk y = c.new Yolk();
		c.f();
		System.out.println("+++++++++++++++++++++++++++++");
		Destroy d = c.new Destroy();
		c.g();
	}
}
/**
 * Output:
 * In BaseClass.Destroy
 * In BaseClass constructor
 * In BaseClass.Yolk
 * In CoverInnerClass.f()
 * In BaseClass.Destroy.foo()
 * In CoverInnerClass constructor
 * ----------------------------------------
 * In BaseClass.Destroy
 * In CoverInnerClass.Destroy
 * class CoverInnerClass
 * ============================
 * In CoverInnerClass.Yolk
 * In CoverInnerClass.f()
 * +++++++++++++++++++++++++++++
 * In BaseClass.Destroy
 * In CoverInnerClass.Destroy
 * In CoverInnerClass.Destroy.foo()
 */


