package com.jupging.jupgingServer.like.domain;

import com.jupging.jupgingServer.common.BaseTimeEntity;
import com.jupging.jupgingServer.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean likeState = true;

    // 좋아요 당하는 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    // 좋아요 누르는 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User fromUser;

    @Builder
    public Like(User fromUser) {
        this.fromUser = fromUser;
    }

    public static Like of(User fromUser) {
        return Like.builder()
            .fromUser(fromUser)
            .build();
    }

    public Like update() {
        this.likeState = !this.likeState;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
        if(!this.user.getLikeList().contains(this)) {
            user.getLikeList().add(this);
        }
    }
}
