import java.time.Duration;
import java.time.LocalTime;

public class Car {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String licencePlate;

    public Car(String licencePlate, LocalTime startTime, LocalTime endTime) {
        this.licencePlate = licencePlate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public int getAvgSpeed() {
        Duration totalTime = Duration.between(startTime, endTime);
        float hours = (float) totalTime.getSeconds() / (float) 3600;
        return (int) (10 / hours);
    }
    public boolean wasOnHighwayIn(LocalTime minuteStart) {
        LocalTime minuteEnd = minuteStart.plusMinutes(1).minusNanos(1);
        return this.startTime.isBefore(minuteEnd) && this.endTime.isAfter(minuteStart);
    }

    public boolean passedEntryPointAt(LocalTime minuteStart) {
        LocalTime minuteEnd = minuteStart.plusMinutes(1).minusNanos(1);
        return startTime.isAfter(minuteStart) && startTime.isBefore(minuteEnd);
    }

    public boolean exitedHighwayBefore(LocalTime currentTime) {
        return this.endTime.isBefore(currentTime);
    }


    public boolean wasOvertakenBy(Car fastestCar) {
        return startTime.isBefore(fastestCar.startTime) && endTime.isAfter(fastestCar.endTime);
    }

    public boolean isSpeeding(int speedLimit) {
        return getAvgSpeed() > speedLimit;
    }

    @Override
    public String toString() {
        return "Car{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", licencePlate='" + licencePlate + '\'' +
                ", avgSpeed='" + getAvgSpeed() + '\'' +
                '}';
    }

}
