package toy.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import toy.board.entity.common.BaseEntity;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Getter
public class Tag extends BaseEntity {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member owner;


}
