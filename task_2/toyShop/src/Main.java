public class Main {
    public static void main(String[] args) {
        // Некоторые тестовые данные
        ToyMachine toyMachine = new ToyMachine();
        toyMachine.addNewToy(1, "Плюшевый мишка", 10, 20.0);
        toyMachine.addNewToy(2, "Кукла", 15, 15.0);
        toyMachine.addNewToy(3, "Машинка", 20, 10.0);

        // Действо
        ToyShop myToyShop = new ToyShop(toyMachine);
        myToyShop.runToyShop();
    }
}