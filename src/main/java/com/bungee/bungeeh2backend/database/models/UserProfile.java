package com.bungee.bungeeh2backend.database.models;

import com.bungee.bungeeh2backend.database.models.restaurant.review.Review;
import com.bungee.bungeeh2backend.database.models.users.credential.BungeeUserDetails;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "user_profiles")
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @NonNull
    private BungeeUserDetails userDetails;

    private String firstName;

    private String lastName;

    private DateTime joinedAt;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();
}
