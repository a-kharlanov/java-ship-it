package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private final List<T> parcels = new ArrayList<>();
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

    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }
}

