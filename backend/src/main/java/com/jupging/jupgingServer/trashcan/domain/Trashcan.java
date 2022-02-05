package com.jupging.jupgingServer.trashcan.domain;

import com.jupging.jupgingServer.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trashcan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Trashcan(User user, Double latitude, Double longitude){
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
