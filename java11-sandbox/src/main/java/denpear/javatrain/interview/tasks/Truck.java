package denpear.javatrain.interview.tasks;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

class Truck {
    /**
     * Задание 3. Есть класс с данными грузовика
     * public class Truck{
     * long id;
     * double x; //координата по оси x
     * double y; //координата по оси y
     * double speed; //скорость грузовика
     * }
     * Дан список грузовиков, и координаты груза.
     * Считаем что грузовики находятся в чистом поле, могут ехать в любом направлении, их скорости постоянны и они не сталкиваются друг с другом.
     * Надо найти какой грузовик может первым забрать груз
     * Задача написать функцию
     * public long findFirstTruck(List<Truck> trucks, int xCargo, int yCargo); //где trucks это список грузовиков, xCargo и yCargo это координаты груза
     * функция должна возвращать id грузовика, который сможет забрать груз раньше всех
     */

    private static Instant measurementTimeAtom = Instant.now(Clock.fixed(Instant.parse("2022-07-09T10:15:30Z"), ZoneId.of("UTC")));
    long id;
    double x; //координата по оси x
    double y; //координата по оси y
    double speed; //скорость грузовика

    public Truck(long id, double x, double y, double speed) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    /**
     * Метод работает с полями группы объектов одного типа через геттеры
     *
     * @param trucks - грузовики со своими координатами в данный момент и потенциальной скоростью
     * @param xCargo - x координата груза
     * @param yCargo - y координата груза
     * @return
     */
    public static long findFirstTruck(List<Truck> trucks, int xCargo, int yCargo) {

        Map<Truck, Double> arrivalRateTable = new HashMap<>();

        for (Truck lorry : trucks) {
            double xAxisSteps = (lorry.getX() > xCargo) ? lorry.getX() - xCargo : xCargo - lorry.getX();
            double yAxisSteps = (lorry.getY() > yCargo) ? lorry.getY() - yCargo : yCargo - lorry.getY();
            double pLength = xAxisSteps + yAxisSteps;
            double tripTime = pLength / lorry.getSpeed();
            arrivalRateTable.put(lorry, tripTime);
        }
        Optional<Map.Entry<Truck, Double>> speederTruck = arrivalRateTable.entrySet().stream().min(Comparator.comparingDouble(Map.Entry::getValue));

        return speederTruck.get().getKey().getId();
    }

    public static void main(String[] args) {
        Truck truck1 = new Truck(1L, 10.34, 15.56, 16.45);
        Truck truck2 = new Truck(2L, 20.34, 25.56, 26.45);
        Truck truck3 = new Truck(3L, 30.34, 35.56, 36.45);

        List<Truck> trucks = new ArrayList<>();
        trucks.add(0, truck1);
        trucks.add(1, truck3);
        trucks.add(2, truck2);

        String instantExpected = "2022-07-09T10:15:31Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));
        Instant measurementTime = Instant.now(clock);

        System.out.println("Быстрее всего доедет грузовик с номером " + findFirstTruck(trucks, 34, 56));
        System.out.println("Быстрее всего доедет грузовик с номером " + findFirstTruck(trucks, 24, 26));

        receiveCoordinates(3, 4, 5, measurementTime);
        receiveCoordinates(3, 4, 5, measurementTime);

    }

    /**
     * Задание 5. Есть сервис в который приходят пакеты данных с координатами расположения грузовиков
     * При поступлении новых координат по какому либо грузовику вызывается метод
     * reveiveCoordinates(int truckId, int x, int y, Instant measurementTime) где truckId это id грузовика; x,y - координаты, measurementTime - время измерения
     * Валидным пакетом для грузовика считается пакет с временем измерения(measurementTime) позже чем предыдущий полученный валидный пакет по этому же грузовику
     * Задача сервиса отфильровывать невалидные пакеты и отдавать дальше на обработку только валидные.
     * Дальнейшая обработка осуществляется в методе processCoordinates(int truckId, int x, int y, Instant measurementtime)
     * public void receiveCoordinates(int truckId, int x, int y, Instant measurementTime){
     * ....
     * processCoordinates(truckId, x, y, measurementTime);
     * }
     * public void processCoordinates(int truckId, int x, int y, Instant measurementTime){
     * System.out.println("Пришел пакет "+truckId+" "+x+" "+y+" "+ measurementTime);
     * }
     * Задача: реализовать класс с методом reveiveCoordinates, который осуществляет фильтацию
     *
     * @param truckId
     * @param x
     * @param y
     * @param measurementTime
     */

    public static void receiveCoordinates(int truckId, int x, int y, Instant measurementTime) {
        if (measurementTime.isAfter(measurementTimeAtom)) {
            measurementTimeAtom = measurementTime;
            processCoordinates(truckId, x, y, measurementTime);
        }
    }

    public static void processCoordinates(int truckId, int x, int y, Instant measurementTime) {
        System.out.println("Пришел пакет " + truckId + " " + x + " " + y + " " + measurementTime);
    }

    public long getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

}