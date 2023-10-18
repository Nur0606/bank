import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String[] users = new String[1000];
        String[] passwords = new String[1000];
        double[] balances = new double[1000];
        int userCount = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите операцию:");
            System.out.println("1. Регистрация");
            System.out.println("2. Вход в аккаунт");
            System.out.println("3. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                if (userCount < 1000) {
                    System.out.print("Введите ФИО: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Введите ИНН: ");
                    String inn = scanner.nextLine();
                    System.out.print("Введите возраст: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    if (age < 0 || age > 127) {
                        System.out.println("Возраст отрицательный или превышает норму.");
                        continue;
                    }
                    System.out.print("Введите номер телефона: ");
                    String phoneNumber = scanner.nextLine();
                    if (!phoneNumber.startsWith("+996") || !phoneNumber.matches("^\\+996\\d{9}$")) {
                        System.out.println("Номер введен некорректно.");
                        continue;
                    }
                    System.out.print("Введите почту: ");
                    String email = scanner.nextLine();
                    if (!email.matches("^(gmail|outlook)@.*\\.(com|ru|ca|us)$")) {
                        System.out.println("Почта введена некорректно.");
                        continue;
                    }
                    System.out.print("Введите пароль: ");
                    String password = scanner.nextLine();
                    if (password.length() < 8 || !password.matches(".*[a-z].*[A-Z].*\\d.*\\W.*")) {
                        System.out.println("Пароль недостаточно надежный.");
                        continue;
                    }
                    users[userCount] = inn;
                    passwords[userCount] = password;
                    balances[userCount] = 0.0;
                    userCount++;
                    System.out.println("Регистрация успешно завершена. Ваш номер карты: " + inn);
                } else {
                    System.out.println("Достигнуто максимальное количество пользователей.");
                }
            } else if (choice == 2) {
                System.out.print("Введите ИНН: ");
                String inn = scanner.nextLine();
                System.out.print("Введите пароль: ");
                String password = scanner.nextLine();
                int userIndex = -1;
                for (int i = 0; i < userCount; i++) {
                    if (users[i].equals(inn) && passwords[i].equals(password)) {
                        userIndex = i;
                        break;
                    }
                }
                if (userIndex != -1) {
                    System.out.println("Вход выполнен успешно!");
                    while (true) {
                        System.out.println("Выберите операцию:");
                        System.out.println("1. Проверить баланс");
                        System.out.println("2. Пополнить баланс");
                        System.out.println("3. Снять деньги");
                        System.out.println("4. Выйти");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice == 1) {
                            double balance = balances[userIndex];
                            System.out.println("Ваш баланс: " + balance);
                        } else if (choice == 2) {
                            System.out.print("Введите сумму для пополнения: ");
                            double deposit = scanner.nextDouble();
                            balances[userIndex] += deposit;
                            System.out.println("Баланс успешно пополнен.");
                        } else if (choice == 3) {
                            System.out.print("Введите сумму для снятия: ");
                            double withdrawal = scanner.nextDouble();
                            if (withdrawal > balances[userIndex]) {
                                System.out.println("Ошибка. Недостаточно средств на счету.");
                            } else {
                                balances[userIndex] -= withdrawal;
                                System.out.println("Средства успешно сняты.");
                            }
                        } else if (choice == 4) {
                            System.out.println("Выход из аккаунта. До свидания!");
                            break;
                        } else {
                            System.out.println("Неверный выбор операции. Попробуйте еще раз.");
                        }
                    }
                } else {
                    System.out.println("Ошибка входа. Проверьте ИНН и пароль.");
                }
            } else if (choice == 3) {
                System.out.println("Выход из системы. До свидания!");
                System.exit(0);
            } else {
                System.out.println("Неверный выбор операции. Попробуйте еще раз.");
            }
        }

    }
}