import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Person> people = readPeople(scanner);
        Map<String, Product> products = readProducts(scanner);
        processPurchases(scanner, people, products);
        printResults(people);
    }

    private static Map<String, Person> readPeople(Scanner scanner) {
        Map<String, Person> people = new LinkedHashMap<>();
        System.out.println("Введите покупателей (пример: Павел = 10000; Анна = 2000). Пустая строка — переход к продуктам:");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            String[] entries = input.split(";");
            for (String entry : entries) {
                try {
                    Person person = Person.parse(entry);
                    people.put(person.getName(), person);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return people;
    }

    private static Map<String, Product> readProducts(Scanner scanner) {
        Map<String, Product> products = new LinkedHashMap<>();
        System.out.println("\nВведите продукты (например: Хлеб = 40; Торт = 1000). Пустая строка — переход к покупкам:");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            String[] entries = input.split(";");
            for (String entry : entries) {
                try {
                    Product product = Product.parse(entry);
                    products.put(product.getName(), product);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return products;
    }

    private static void processPurchases(Scanner scanner, Map<String, Person> people, Map<String, Product> products) {
        System.out.println("\nВыбирайте покупки (например: Павел - Хлеб). Завершите ввод словом END:");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("END")) break;
            if (!input.contains("-")) continue;

            String[] parts = input.split("-");
            if (parts.length != 2) continue;

            String personName = parts[0].trim();
            String productName = parts[1].trim();

            Person person = people.get(personName);
            Product product = products.get(productName);

            if (person == null) {
                System.out.println("Покупатель \"" + personName + "\" не найден");
                continue;
            }
            if (product == null) {
                System.out.println("Продукт \"" + productName + "\" не найден");
                continue;
            }

            person.buy(product);
        }
    }

    private static void printResults(Map<String, Person> people) {
        for (Person person : people.values()) {
            System.out.println(person.showPurchases());
        }
    }
}
