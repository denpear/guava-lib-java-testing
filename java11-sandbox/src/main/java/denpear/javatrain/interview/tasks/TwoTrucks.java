package denpear.javatrain.interview.tasks;

public class TwoTrucks {
    /**
     * На прямой линии расположены 2 грузовика и груз,
     * грузовики двигаются с постоянной одинаковой скоростью по направлению к грузу,
     * груз не двигается. Груз заберет первый приехавший грузовик,
     * если грузовики приедут к грузу одновременно то они столкнутся и груз не заберет никто
     * Задача: реализовать функцию
     * public String trucksAndCargo(int x1, int x2, int xCargo)
     * где x1 - координата первого грузовика, x2 - координата второго грузовика, xCargo - координата груза
     * Функция должна возвращать строку с номером грузовика заберущего груз, либо null если груз не заберет никто
     * пример x1 = 10, x2 = 20, xCargo= 22. Ответ "2"
     * Задача
     *
     * @param x1
     * @param x2
     * @param xCargo
     * @return
     */


    public static String trucksAndCargo(int x1, int x2, int xCargo) {

        if (x1 < x2 && (xCargo - x2) < (xCargo - x1)) {
            return "2";
        } else {
            return "1";
        }

    }

    public static void main(String[] args) {
        System.out.println(trucksAndCargo(10, 20, 22));
        System.out.println(trucksAndCargo(20, 25, 22));
        System.out.println(trucksAndCargo(25, 20, 22));
    }
}
