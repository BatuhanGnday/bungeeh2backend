package com.bungee.bungeeh2backend.database.models.restaurant.review;

import com.bungee.bungeeh2backend.database.models.UserProfile;
import com.bungee.bungeeh2backend.database.models.restaurant.review.types.Rate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "review")
@NoArgsConstructor
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(
            orphanRemoval = true,
            targetEntity = UserProfile.class,cascade = CascadeType.ALL
    )
    private UserProfile userProfile;
    private Rate rate;
    private String review;


}
