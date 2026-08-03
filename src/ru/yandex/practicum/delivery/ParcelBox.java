package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private final ArrayList<T> parcels = new ArrayList<>();
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() > maxWeight) {
            System.out.println("Посылка не добавлена: превышен максимальный вес коробки.");
            return;
        }
        parcels.add(parcel);
        currentWeight += parcel.getWeight();
    }

    public ArrayList<T> getAllParcels() {
        return parcels;
    }
}

