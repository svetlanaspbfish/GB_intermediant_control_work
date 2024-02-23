import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToyMachine {
    private List<Toy> toys = new ArrayList<>();

    // Метод для добавления новых игрушек
    public void addNewToy(int id, String name, int quantity, double frequency) {
        Toy newToy = new Toy(id, name, quantity, frequency);
        toys.add(newToy);
    }

    // Метод для изменения веса (частоты выпадения игрушки)
    public void updateFrequency(int toyId, double newFrequency) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setFrequency(newFrequency);
                break;
            }
        }
    }

    // Метод для розыгрыша игрушек и записи результатов в файл
    public void drawAndWriteToFile() {
        // Логика выбора призовой игрушки и запись результатов в файл
        Toy prizeToy = drawToy();
        writeToFile(prizeToy);
    }

    // Логика выбора призовой игрушки
    private Toy drawToy() {
        if (toys.isEmpty()) {
            System.out.println("Нет доступных игрушек в автомате");
            return null;
        }

        // Вычисляем общую сумму частот выпадения для всех доступных игрушек
        double totalFrequency = 0;
        for (Toy toy : toys) {
            totalFrequency += toy.getFrequency();
        }

        // Генерируем случайное число в диапазоне от 0 до общей суммы частот
        double randomValue = Math.random() * totalFrequency;

        // Выбираем игрушку на основе сгенерированного случайного числа и частот выпадения
        double cumulativeFrequency = 0;
        for (Toy toy : toys) {
            cumulativeFrequency += toy.getFrequency();
            if (randomValue <= cumulativeFrequency) {
                // Уменьшаем количество выбранной игрушки на 1
                toy.setQuantity(toy.getQuantity() - 1);
                return toy;
            }
        }

        // Если не удалось выбрать игрушку (например, из-за округлений), возвращаем последнюю игрушку
        return toys.get(toys.size() - 1);
    }

    // Логика записи призовой игрушки в текстовый файл
    private void writeToFile(Toy toy) {
        try (FileWriter writer = new FileWriter("prize_toys.txt", true)) {
            String text = "Выигранная игрушка: " + toy.getName() + "\n";
            writer.write(text);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    // Метод для получения списка игрушек
    public List<Toy> getToys() {
        return toys;
    }
}