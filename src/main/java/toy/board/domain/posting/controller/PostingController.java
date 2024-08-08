package toy.board.domain.posting.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import toy.board.domain.posting.dto.request.PatchPostingRequest;
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
    public ResponseEntity<CommonPostResponse> savePosting(@AuthenticationPrincipal Member member, @RequestBody @Valid SavePostingRequest requestDto) {
        Integer id = postingService.savePosting(member, requestDto);
        CommonPostResponse response = new CommonPostResponse(id, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PatchMapping("/{postingId}")
    public ResponseEntity<CommonPostResponse> patchPosting(@AuthenticationPrincipal Member member, @RequestBody @Valid PatchPostingRequest requestDto, @PathVariable("postingId") Integer postingId) {
        Integer id = postingService.patchPosting(member, requestDto,postingId);
        CommonPostResponse response = new CommonPostResponse(id, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
