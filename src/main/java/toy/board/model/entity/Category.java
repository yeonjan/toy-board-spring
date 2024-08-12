package toy.board.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import toy.board.model.entity.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private Integer sequence;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    private Integer getLastChildSequence() {
        return child.size() - 1;
    }

    public static Category of(Member member, Category parent, String name) {
        Category category = new Category();
        category.member = member;
        category.parent = parent;
        category.name = name;
        category.sequence = parent != null ? parent.getLastChildSequence() + 1 : 0;
        return category;
    }
}
