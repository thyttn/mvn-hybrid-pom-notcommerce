package factoryCar;

public class Toyota implements ICar {

	@Override
	public void viewCar() {
		System.out.println("View Toyota car");
	}

	@Override
	public void driveCar() {
		System.out.println("Driver Toyota car");
	}

}
