package toy.board.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Content {
    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String writer;
}
