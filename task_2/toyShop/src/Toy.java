public class Toy {
    private int id; // id игрушки
    private String name; // название игрушки
    private int quantity; // количество игрушек
    private double frequency; // частота выпадения игрушки (вес в % от 100)

    // Конструктор класса
    public Toy(int id, String name, int quantity, double frequency) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.frequency = frequency;
    }

    // Геттеры для получения значений свойств
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getFrequency() {
        return frequency;
    }

    // Сеттеры для установки значений свойств
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
}