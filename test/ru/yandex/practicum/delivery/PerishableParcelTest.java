package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PerishableParcelTest {
    private final PerishableParcel parcel =
            new PerishableParcel("Торт", 2, "Минск", 10, 3);

    @Test
    void shouldNotBeExpiredBeforeTimeToLiveEnds() {
        assertFalse(parcel.isExpired(12));
    }

    @Test
    void shouldNotBeExpiredOnLastValidDay() {
        assertFalse(parcel.isExpired(13));
    }

    @Test
    void shouldBeExpiredAfterTimeToLiveEnds() {
        assertTrue(parcel.isExpired(14));
    }
}

