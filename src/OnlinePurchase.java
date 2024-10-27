import java.util.Scanner;

public class OnlinePurchase {

    // Создаём пользовательское исключение при недействительном ИНН
    public static class InvalidTaxNumberException extends Exception {
        // Конструктор, в который передаём сообщение при выбросе исключения
        public InvalidTaxNumberException(String message) {
            super(message);
        }
    }

    // Создаём метод, который проверяет, является ли ИНН действительным
    public static void validateTaxNumber(String taxNumber) throws InvalidTaxNumberException {
        // Если null или длина не равна 10 (юр.лицо) или 12 (физ.лицо)
        if (taxNumber == null || (!taxNumber.matches("\\d{10}") && !taxNumber.matches("\\d{12}"))) {
            throw new InvalidTaxNumberException("Недействительный ИНН - " + taxNumber); // выбрасываем исключение
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Считываем с потока ввода ФИО и ИНН
        System.out.println("Введите ФИО: ");
        String fullName = sc.nextLine();

        System.out.println("Введите ИНН: ");
        String taxNumber = sc.nextLine();

        // Через блок try-catch выполняем код и обрабатываем исключение
        try {
            validateTaxNumber(taxNumber);
            System.out.println("Заказ оформлен успешно для клиента: " + fullName);
        } catch (InvalidTaxNumberException e) {
            System.out.println("Ошибка при оформлении заказа: " + e.getMessage());
        } finally {
            sc.close(); // закрываем поток ввода для освобождения ресурсов
        }
    }
}