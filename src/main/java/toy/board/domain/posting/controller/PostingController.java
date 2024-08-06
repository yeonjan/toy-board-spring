package toy.board.domain.posting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.board.domain.posting.dto.request.SavePostingRequest;
import toy.board.domain.posting.service.PostingService;
import toy.board.global.response.CommonPostResponse;
import toy.board.model.entity.Member;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/postings")
public class PostingController {

    private final PostingService postingService;

    @PostMapping
    public ResponseEntity<CommonPostResponse> savePosting(@AuthenticationPrincipal Member member, @RequestBody SavePostingRequest requestDto) {
        Integer id = postingService.savePosting(member, requestDto);
        CommonPostResponse response = new CommonPostResponse(id, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
