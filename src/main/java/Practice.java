package main;

public class Practice {

//    public Practice(){
//        Test1 t = new Test1();
//        t.foo();
//    }
    static int i = 0;
    Contents con = new Contents("Outer class variable");
    Practice(){
        //Cannot reference inner class in static context
        //static Contents cc = new Contents();
    }

    class Contents{
        Contents(String location){
            //Inner class has the privilege of visiting all the member in outer class
            con = null;
            System.out.println("Construct Inner Contents from " + location + " " + i++);
        }
        public void foo(){
            System.out.println("In Inner Contents...");
        }
    }

    public Contents getContents(){
        //It will get Inner class
       return new Contents("Outer class method");
    }

//    public static void main(String [] args) {
//        Practice m = new Practice();
//        //Can call the getContents() method even if it's private
//        Contents c = m.getContents();
//        c.foo();
//
//        //Can not create inner class directly in static method
//        //Contents c = new Practice.Contents();
//
//        //You must give the full name here(even if you use outerClassName.innerClassName when using inner class).
//        //Or it will get the inner class and raise an exception.
//        main.Contents c2 = new main.Contents();
//        c2.foo();
//        c2.test();
//    }
}

class Contents{
    public void foo(){
        System.out.println("In another Contents class...");
    }
    public void test(){
        //Can get inner class through outer class
        Practice m = new Practice();
        Practice.Contents c = m.getContents();
        c.foo();

        //Cannot create inner class directly from another class
        //Practice.Contents c = new Practice.Contents();
    }
}

/*Output:
Construct Inner Contents from Outer class variable 0
Construct Inner Contents from Outer class method 1
In Inner Contents...
In another Contents class...
Construct Inner Contents from Outer class variable 2
Construct Inner Contents from Outer class method 3
In Inner Contents...
 */
