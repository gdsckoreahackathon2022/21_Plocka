package com.jupging.jupgingServer.user.domain;

import com.jupging.jupgingServer.common.BaseTimeEntity;
import com.jupging.jupgingServer.like.domain.Like;
import com.jupging.jupgingServer.user.domain.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String picture;

    @Column(nullable = true)
    private String nickName;

    @Column(nullable = true)
    private Float height;

    @Column(nullable = true)
    private Float weight;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likeList = new ArrayList<>();

    @Column(nullable = true)
    private int likeCount = 0;

    @Column(nullable = true)
    private int level;

    public User( String email, String picture, Float height, Float weight, GenderType genderType) {

        this.email = email;
        this.picture = picture;
        this.height = height;
        this.weight = weight;
        this.genderType = genderType;
    }

    public User(String email) {
        this.email = email;
    }

    public User update(String nickName, String picture) {
        this.nickName = nickName;
        this.picture = picture;
        return this;
    }

    public void updateLevel(int level) {
        this.level = level;
    }

    public void update(String nickName, String profile,
                       Float height, Float weight, GenderType gender) {
        this.nickName = nickName;
        this.picture = profile;
        this.height = height;
        this.weight = weight;
        this.genderType = gender;
    }

    public void updateLikeCount(Boolean isLike) {
        if(isLike) this.likeCount += 1;
        else if (this.likeCount > 0) this.likeCount -= 1;
    }
}
