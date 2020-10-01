package com.bungee.bungeeh2backend.database.models.restaurant;

import com.bungee.bungeeh2backend.database.models.restaurant.review.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Restaurant {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    private String profileUUID;

    @NonNull
    private String address;

    @NonNull
    private String phoneNumber;

    private String webSite;

    @NonNull
    private String workingHours;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviewList = new ArrayList<>();

}
