package factoryCar;

import utilities.ExcelConfig;

public class CarFactory {
	public static void getViewCar(String carType) {
		ICar car;
		if(carType.equalsIgnoreCase("honda")) {
			car = new Honda();
			car.viewCar();
		}else if(carType.equalsIgnoreCase("toyota")) {
			car = new Toyota();
			car.viewCar();
		}else {
			System.out.println("Please enter correct type car");
		}
	}
}
