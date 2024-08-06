package toy.board.domain.common.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import toy.board.global.exception.custom.ScrapingException;
import toy.board.model.vo.Content;

import java.io.IOException;

@Service
public class WebScrapingService {

    public Content getContent(String url) {

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ScrapingException();
        }

        String title = getTitle(document);
        String author = getAuthor(document);

        return Content.of(url, title, author);
    }

    private String getTitle(Document document) {

        return document.title();
    }

    private String getAuthor(Document document) {
        Element authorElement = document.selectFirst("meta[name=author]");

        if (authorElement == null) {
            authorElement = document.selectFirst("meta[property=og.article.author]");
        }

        return authorElement != null ? authorElement.attr("content") : "무명";
    }

}
