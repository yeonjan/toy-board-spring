package toy.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import toy.board.entity.common.BaseEntity;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class SeriesTag extends BaseEntity {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
