package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParcelCostTest {
    @Test
    void shouldCalculateStandardParcelCost() {
        Parcel parcel = new StandardParcel("Книги", 5, "Минск", 10);
        assertEquals(10, parcel.calculateDeliveryCost());
    }

    @Test
    void shouldCalculateFragileParcelCost() {
        Parcel parcel = new FragileParcel("Ваза", 5, "Гомель", 10);
        assertEquals(20, parcel.calculateDeliveryCost());
    }

    @Test
    void shouldCalculatePerishableParcelCost() {
        Parcel parcel = new PerishableParcel("Пирог", 5, "Брест", 10, 3);
        assertEquals(15, parcel.calculateDeliveryCost());
    }
}

