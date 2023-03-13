import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Lottery {
    private List<Toy> listToys = new ArrayList<>();
    private int toy_count;

    public List<Toy> getToys() {
        System.out.print("Оставшиеся игрушки: ");
        for (int i = 0; i < listToys.size(); i++) {
            System.out.print(listToys.get(i).getToy_name() + ", ");
        }
        System.out.println();
        return listToys;
    }

    public int getToy_count() {
        return toy_count;
    }

    public int getToy_count_print() {
        System.out.println("Количество игрушек = " + toy_count + "\n*****");
        return toy_count;
    }

    // добавить игрушку в розыгрыш
    public List<Toy> add_toy_in_listToy(Toy name) {
        listToys.add(name);
        toy_count++;
        return listToys;
    }

    // удалить игрушку из списка по имени
    private List<Toy> delete_toy_in_listToy(Toy name) {
        listToys.remove(name);
        toy_count--;
        return listToys;
    }

    // запись выигранных игрушек в файл ".txt"
    private void saveInFile(int n) {
        try (FileWriter writer = new FileWriter("win_toys.txt", false)) {
            String text = listToys.get(n).getToy_name();
            writer.write(text + "\n");
            System.out.println(text);
            writer.close();
        } catch (Exception ex) {
            System.out.println("Ошибка записи файла.");
        }
    }

    // чтение файла с выигранными игрушками
    public void readFile() {
        try {
            FileReader fr = new FileReader("win_toys.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }

        } catch (Exception ex) {
            System.out.println("Ошибка чтения файла.");
        }
    }

    // Розыгрыш, с задаваемой вероятностью на победу
    // При выигрыше, записываем имя игрушки в файл и удаляем её из списка разыгрываемых игрушек
    public void lotToy() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Вероятность выигрыша " + Main.chance + "%.");
        System.out.println("Измененить вероятность выйгрыша, нажмите 1.");
        System.out.println("Старт розыгрыша, нажмите любую, кроме 1.");
        String changeСhance = scan.next();
        if (changeСhance.equals("1")) {
            System.out.println("Выберите вероятность выйгрыша, значение: 1...100: ");
            Main.chance = scan.nextInt();
        }
        if (Main.chance >= 0 && Main.chance <= 100) {
            Random rnd = new Random();
            int num = 100 - rnd.nextInt(100);
            if (num > Main.chance) {
                System.out.println("Ничего не выйграли!");
            } else if (num < Main.chance) {
                System.out.println("Ура, выйграли игрушку!!!");
                int n = rnd.nextInt(listToys.size());
                saveInFile(n);
                delete_toy_in_listToy(listToys.get(n));
            }
        } else {
            System.out.println("Неверное значение! Попробуйте еще.");
        }
    }
}
