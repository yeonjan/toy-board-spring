package toy.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import toy.board.entity.common.BaseEntity;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email")
    private String email;


}
