import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String filePath = "src/measurements.txt";
    public static List<Car> loadCars(){
        List<String> textLines = loadText();
        List<Car> cars = new ArrayList<>(List.of());

        for (String line : textLines) {
            String[] lineParts = line.split(" ");
            String licencePlate = lineParts[0];
            Car newCar = getCar(lineParts, licencePlate);
            cars.add(newCar);
        }
        return cars;
    }

    private static Car getCar(String[] lineParts, String licencePlate) {
        int startHours = Integer.parseInt(lineParts[1]);
        int startMinutes = Integer.parseInt(lineParts[2]);
        int startSeconds = Integer.parseInt(lineParts[3]);
        int startNano = Integer.parseInt(lineParts[4]) * 1000000;
        int endHours = Integer.parseInt(lineParts[5]);
        int endMinutes = Integer.parseInt(lineParts[6]);
        int endSeconds = Integer.parseInt(lineParts[7]);
        int endNano = Integer.parseInt(lineParts[8]) * 1000000;
        LocalTime startTime = LocalTime.of(startHours, startMinutes, startSeconds, startNano);
        LocalTime endTime = LocalTime.of(endHours, endMinutes, endSeconds, endNano);
        return new Car(licencePlate, startTime, endTime);
    }

    private static List<String> loadText(){
        try{
            return Files.readAllLines(Paths.get(filePath));
        }catch (IOException e){
            System.out.println("Exception occurred during reading file " + e);
            return List.of();
        }
    }

}
