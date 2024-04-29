import java.time.LocalTime;
import java.util.List;

public class CarsCounter {

    private final List<Car> cars;

    public CarsCounter(List<Car> cars) {
        this.cars = cars;
    }

    public int getTotalCars() {
        return cars.size();
    }

    public int totalCarsExitedBefore(LocalTime time) {
        int totalCarsBeforeNine = 0;
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).exitedHighwayBefore(time)) {
                totalCarsBeforeNine++;
            }
        }
        return totalCarsBeforeNine;
    }

    public float getTrafficIntensity(LocalTime time){
        int totalCars = 0;
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).wasOnHighwayIn(time)) {
                totalCars++;
            }
        }
        return (float) totalCars / (float) 10;
    }

    public int totalCarsPassedEntrypoint(LocalTime inputTime) {
        int totalCars = 0;
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).passedEntryPointAt(inputTime)) {
                totalCars++;
            }
        }
        return totalCars;
    }

    public Car getFastestCar() {
        Car fastestCar = cars.get(0);
        for (Car car: cars) {
            if (car.getAvgSpeed() > fastestCar.getAvgSpeed()) {
                fastestCar = car;
            }
        }
        return fastestCar;
    }

    public int totalOfOvertakenCarsByCar(Car fastestCar) {
        int totalCars = 0;
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).wasOvertakenBy(fastestCar)) {
                totalCars++;
            }
        }
        return totalCars;
    }

    public int numOfSpeedingCars(int speedLimit) {
        int totalCars = 0;
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).isSpeeding(speedLimit)) {
                totalCars++;
            }
        }
        return totalCars;
    }
}
