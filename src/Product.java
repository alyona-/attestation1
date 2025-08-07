import java.util.Objects;

public class Product {
    private String name;
    private Double price;

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Стоимость продукта не может быть отрицательной");
        }
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public static Product parse(String input) {
        if (!input.contains("=")) {
            throw new IllegalArgumentException("Неверный формат продукта. Пример: Хлеб = 40");
        }
        String[] parts = input.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Неверный формат продукта. Пример: Хлеб = 40");
        }
        String name = parts[0].trim();
        double price;
        try {
            price = Double.parseDouble(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат стоимости для " + name);
        }
        return new Product(name, price);
    }


    @Override
    public String toString() {
        return name + " (" + price + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
