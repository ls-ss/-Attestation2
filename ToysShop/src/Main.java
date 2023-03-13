import java.security.KeyStore;

public class Main {
    public static int chance = 50;
    public static void main(String[] args) {
        // Создание объектов "Игрушки"
        Toy doll = new Toy(1, "Кукла");
        Toy car = new Toy(2, "Машина");
        Toy paints = new Toy(3, "Краски");
        Toy lego = new Toy(4, "Lego");
        Toy balloon = new Toy(5, "Воздушный шарик");
        Toy kitchen = new Toy(6, "Кухня");
        Toy pail = new Toy(7, "Ведро");

        // Создание объекта "Розыгрыш" с добавлением в него игрушек
        Lottery lot = new Lottery();
        lot.add_toy_in_listToy(doll);
        lot.add_toy_in_listToy(car);
        lot.add_toy_in_listToy(paints);
        lot.add_toy_in_listToy(lego);
        lot.add_toy_in_listToy(balloon);
        lot.add_toy_in_listToy(kitchen);
        lot.add_toy_in_listToy(pail);

        while (true) {
            if (lot.getToy_count() == 0) {
                break;
            }

            // Запуск розыгрыша
            lot.lotToy();

            // Выводим оставшиеся игрушки
            lot.getToys();

            // Число игрушек в списке
            lot.getToy_count_print();
        }
    }
}
