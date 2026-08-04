package ru.yandex.practicum.delivery;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableItems = new ArrayList<>();

    private static final int MAX_BOX_WEIGHT = 100;
    private static final ParcelBox<StandardParcel> standardBox = new ParcelBox<>(MAX_BOX_WEIGHT);
    private static final ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(MAX_BOX_WEIGHT);
    private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(MAX_BOX_WEIGHT);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatuses();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить местоположение отслеживаемых отправлений");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип: 1 — стандартная, 2 — хрупкая, 3 — скоропортящаяся");
        int type = readInt();

        if (type < 1 || type > 3) {
            System.out.println("Неверный тип посылки.");
            return;
        }

        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        System.out.println("Введите вес:");
        int weight = readInt();
        System.out.println("Введите адрес доставки:");
        String address = scanner.nextLine();
        System.out.println("Введите день отправки:");
        int sendDay = readInt();

        switch (type) {
            case 1:
                StandardParcel standardParcel =
                        new StandardParcel(description, weight, address, sendDay);
                allParcels.add(standardParcel);
                standardBox.addParcel(standardParcel);
                break;
            case 2:
                FragileParcel fragileParcel =
                        new FragileParcel(description, weight, address, sendDay);
                allParcels.add(fragileParcel);
                trackableItems.add(fragileParcel);
                fragileBox.addParcel(fragileParcel);
                break;
            case 3:
                System.out.println("Введите срок хранения в днях:");
                int timeToLive = readInt();
                PerishableParcel perishableParcel = new PerishableParcel(
                        description, weight, address, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableBox.addParcel(perishableParcel);
                break;
            default:
                System.out.println("Неверный тип посылки.");
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставки: " + totalCost);
    }

    private static void reportStatuses() {
        System.out.println("Введите новое местоположение:");
        String newLocation = scanner.nextLine();

        for (Trackable item : trackableItems) {
            item.reportStatus(newLocation);
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберите коробку: 1 — стандартная, 2 — хрупкая, 3 — скоропортящаяся");
        int type = readInt();

        switch (type) {
            case 1:
                printParcelDescriptions(standardBox.getAllParcels());
                break;
            case 2:
                printParcelDescriptions(fragileBox.getAllParcels());
                break;
            case 3:
                printParcelDescriptions(perishableBox.getAllParcels());
                break;
        }
    }

    private static void printParcelDescriptions(List<? extends  Parcel> parcels) {
        if (parcels.isEmpty()) {
            System.out.println("Коробка пуста.");
            return;
        }

        for (Parcel parcel : parcels) {
            System.out.println(parcel.getDescription());
        }
    }

    private static int readInt() {
        while (true) {
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println(
                        "Некорректный ввод. Введите целое число:"
                );
            }
        }
    }
}


