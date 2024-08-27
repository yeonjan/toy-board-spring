package toy.board.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class Content {
    @Column(nullable = false)
    private String title;

    @Setter
    private String memo;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String writer;

    public static Content of(String url, String title, String writer) {
        Content content = new Content();
        content.url = url;
        content.title = title;
        content.writer = writer;
        return content;
    }


}
