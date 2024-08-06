package toy.board.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import toy.board.model.entity.common.BaseEntity;
import toy.board.model.vo.Content;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Posting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private Content content;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    public static Posting of(Content content, Member member, Category category) {
        Posting posting = new Posting();

        posting.isRead = false;
        posting.content = content;
        posting.member = member;
        posting.category = category;

        return posting;
    }


}
