package denpear.javatrain.interview.tasks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TrucksByTabelNumber {
    /**
     * есть 2 статичные мапы
     * public static Map<Long, Long> trucksToDriver // сопоставляет id грузовика с id водителя, назначенного на грузовик
     * public static Map<Long, String> driversToTabelNumber// сопоставляет id водителя с его табельным номером
     * Эти мапы не меняются со временем, и данные в них актуальны на все время работы сервиса.
     * Задача: создать класс, который предоставляет возможность определять id грузовика по табельному номеру водителя.
     * Другие сервисы обращаются к нашему сервису очень часто, поэтому требуется обеспечить максимально быструю работу класса
     */

    public static Map<Long, Long> trucksToDriver = new HashMap<>();
    public static Map<Long, String> driversToTabelNumber = new HashMap<>();


    public static Long trucksByTabelNumber(String tabelNumber) {


        final Collection<Map.Entry<Long, String>> driversOnTabelNumberEntries = driversToTabelNumber.entrySet();
        final Collection<Map.Entry<Long, Long>> trucksToDriverEntries = trucksToDriver.entrySet();

        Long dNumber = getDriverByTabelNumber(tabelNumber, driversOnTabelNumberEntries);
        return getTruckIdByDriver(dNumber, trucksToDriverEntries);
    }


    private static Long getDriverByTabelNumber(String tabelNumber, Collection<Map.Entry<Long, String>> entries) {
        for (Map.Entry<Long, String> entry : entries) {
            if (tabelNumber.equals(entry.getValue())) return entry.getKey();
        }
        return null;
    }

    private static Long getTruckIdByDriver(Long driverNumber, Collection<Map.Entry<Long, Long>> entries) {
        for (Map.Entry<Long, Long> entry : entries) {
            if (driverNumber.equals(entry.getValue())) return entry.getKey();
        }
        return null;
    }

    public static void main(String[] args) {
        trucksToDriver.put(34L, 56L);
        trucksToDriver.put(64L, 76L);
        trucksToDriver.put(24L, 46L);

        driversToTabelNumber.put(56L, "T3453278");
        driversToTabelNumber.put(76L, "T7453278");
        driversToTabelNumber.put(46L, "T4453278");

        System.out.println("Номер машины " + trucksByTabelNumber("T7453278"));
        System.out.println("Номер машины " + trucksByTabelNumber("T4453278"));

    }

}
