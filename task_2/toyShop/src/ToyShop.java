import java.util.Scanner;

public class ToyShop {
    private ToyMachine toyMachine;

    public ToyShop(ToyMachine toyMachine) {
        this.toyMachine = toyMachine;
    }

    public ToyShop() {
        this.toyMachine = new ToyMachine();
    }

    // Метод для запуска магазина игрушек
    public void runToyShop() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить новую игрушку");
            System.out.println("2. Выдать призовую игрушку");
            System.out.println("3. Изменить вес частоты выпадения игрушки");
            System.out.println("4. Просмотреть все игрушки в автомате");
            System.out.println("5. Выйти из магазина");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewToyFromConsole(scanner);
                    break;
                case 2:
                    issuePrizeToy();
                    break;
                case 3:
                    updateFrequencyFromConsole(scanner);
                    break;
                case 4:
                    viewAllToys();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Неправильный выбор. Попробуйте еще раз.");
            }
        }
    }

    // Метод для добавления новой игрушки через консоль
    private void addNewToyFromConsole(Scanner scanner) {
        System.out.println("Введите id игрушки:");
        int id = scanner.nextInt();
        System.out.println("Введите название игрушки:");
        scanner.nextLine(); // Очистка буфера после nextInt()
        String name = scanner.nextLine();
        System.out.println("Введите количество игрушек:");
        int quantity = scanner.nextInt();
        System.out.println("Введите частоту выпадения игрушки (%):");
        double frequency = scanner.nextDouble();

        toyMachine.addNewToy(id, name, quantity, frequency);
        System.out.println("Игрушка успешно добавлена в автомат.");
    }

    // Метод для выдачи призовой игрушки
    private void issuePrizeToy() {
        toyMachine.drawAndWriteToFile();
    }

    // Метод для изменения веса частоты выпадения игрушки через консоль
    private void updateFrequencyFromConsole(Scanner scanner) {
        System.out.println("Введите id игрушки, для которой хотите изменить вес частоты:");
        int toyId = scanner.nextInt();
        System.out.println("Введите новый вес частоты выпадения игрушки (%):");
        double newFrequency = scanner.nextDouble();

        toyMachine.updateFrequency(toyId, newFrequency);
        System.out.println("Вес частоты успешно изменен.");
    }

    private void viewAllToys() {
        System.out.println("Список всех игрушек в автомате:");
        for (Toy toy : toyMachine.getToys()) {
            System.out.println("ID: " + toy.getId() + ", Название: " + toy.getName() + ", Количество: " + toy.getQuantity() + ", Частота выпадения: " + toy.getFrequency() + "%");
        }
    }
}