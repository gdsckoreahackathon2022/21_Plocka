package com.jupging.jupgingServer.plogging.domain;

import com.jupging.jupgingServer.common.BaseTimeEntity;
import com.jupging.jupgingServer.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Plogging extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeImage;

    private String trashImage;

    @Column(nullable = false)
    private int avgPaceMin;

    @Column(nullable = false)
    private int avgPaceSec;

    @Column(nullable = false)
    private int calorie;

    @Column(nullable = false)
    private Double distance;

    @Column(nullable = false)
    private int runTimeHour;

    @Column(nullable = false)
    private int runTimeMin;

    @Column(nullable = false)
    private int runTimeSec;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Plogging(String routeImage, String trashImage, Double distance,
                    int calorie, int avgPaceMin, int avgPaceSec,
                    int runTimeHour, int runTimeMin, int runTimeSec, User user){
        this.routeImage = routeImage;
        this.trashImage = trashImage;
        this.distance = distance;
        this.calorie = calorie;
        this.avgPaceMin = avgPaceMin;
        this.avgPaceSec = avgPaceSec;
        this.runTimeHour = runTimeHour;
        this.runTimeMin = runTimeMin;
        this.runTimeSec = runTimeSec;
        this.user = user;
    }
}
