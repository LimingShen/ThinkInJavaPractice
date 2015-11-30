interface Dog {
	public void spark();
};

abstract class Cat {
	public abstract void smile();
};

public class ImplemtationHide {
	
	private class HaShiQi implements Dog { 
		public void spark() {
			System.out.println("Wang! Wang! Wang!");
		}
	}
	
	private class JiaFeiMao extends Cat {
		public void smile() {
			System.out.println("Miao ~ ~ ~");
		}
	}
	
	public Dog getHaShiQi(){
		return new HaShiQi();
	}
	
	public Cat getJiaFeiMao(){
		return new JiaFeiMao();
	}

	public static void main(String[] args) {
		ImplemtationHide i = new ImplemtationHide();
		Dog dog = i.getHaShiQi();
		Cat cat = i.getJiaFeiMao();
		
		dog.spark();
		cat.smile();
	}

}
