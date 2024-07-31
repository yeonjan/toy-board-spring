package toy.board.model.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    public static Member of(String nickname, String email, String picture) {
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .picture(picture)
                .role(Role.USER)
                .build();
    }

    public void updateUserInfo(Member newMember) {
        this.nickname = newMember.getNickname();
        this.email = newMember.getEmail();
        this.picture = newMember.getPicture();
        this.role = newMember.getRole();
    }

    public String getRoleKey() {
        return this.role.getKey();
    }


}
