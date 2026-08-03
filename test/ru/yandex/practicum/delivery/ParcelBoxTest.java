package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParcelBoxTest {
    @Test
    void shouldAddParcelWhenMaximumWeightIsNotExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        box.addParcel(new StandardParcel("Книги", 6, "Минск", 1));
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void shouldAddParcelWhenWeightEqualsMaximum() {
        ParcelBox<FragileParcel> box = new ParcelBox<>(10);
        box.addParcel(new FragileParcel("Ваза", 10, "Минск", 1));
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void shouldNotAddParcelWhenMaximumWeightIsExceeded() {
        ParcelBox<PerishableParcel> box = new ParcelBox<>(10);
        box.addParcel(new PerishableParcel("Торт", 11, "Минск", 1, 2));
        assertTrue(box.getAllParcels().isEmpty());
    }
}

