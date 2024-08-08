package toy.board.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import toy.board.model.entity.common.BaseEntity;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class PostingTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    private Posting post;
}
