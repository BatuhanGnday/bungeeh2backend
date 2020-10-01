package com.bungee.bungeeh2backend.database.models.restaurant.review.types;

import lombok.Getter;

@Getter
public enum Rate {
    WORST(1),
    WORSE(2),
    AVERAGE(3),
    BETTER(4),
    BEST(5);

    private int rate;

    Rate(int rate) {
        this.rate = rate;
    }
}
