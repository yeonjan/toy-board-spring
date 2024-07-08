package toy.board.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import toy.board.model.entity.common.BaseEntity;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nickname;

    private String email;


    public static Member of(String nickname, String email) {
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .build();
    }

    public void updateUserInfo(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }


}
