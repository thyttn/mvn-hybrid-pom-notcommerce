package factoryCar;

public class Customer {

	public static void main(String[] args) {
		CarFactory factory = new CarFactory();
		factory.getViewCar("Honda");
		factory.getViewCar("Toyota");
		factory.getViewCar("hyundai");
	}
}
