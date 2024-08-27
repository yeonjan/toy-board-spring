package toy.board.domain.posting.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import toy.board.domain.posting.dto.request.PatchPostingRequest;
import toy.board.domain.posting.dto.request.SavePostingRequest;
import toy.board.domain.posting.dto.response.PostingResponse;
import toy.board.domain.posting.service.PostingService;
import toy.board.global.response.CommonDeleteResponse;
import toy.board.global.response.CommonPatchResponse;
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
    public ResponseEntity<CommonPatchResponse> patchPosting(@AuthenticationPrincipal Member member, @RequestBody @Valid PatchPostingRequest requestDto, @PathVariable("postingId") Integer postingId) {
        Integer id = postingService.patchPosting(member, requestDto, postingId);
        CommonPatchResponse response = new CommonPatchResponse(id, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{postingId}/read-status")
    public ResponseEntity<CommonPatchResponse> toggleReadStatus(@AuthenticationPrincipal Member member, @PathVariable("postingId") Integer postingId) {
        Integer id = postingService.toggleReadStatus(member, postingId);
        CommonPatchResponse response = new CommonPatchResponse(id, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{postingId}")
    public ResponseEntity<CommonDeleteResponse> deletePosting(@AuthenticationPrincipal Member member, @PathVariable("postingId") Integer postingId) {
        Integer id = postingService.deletePosting(member, postingId);
        CommonDeleteResponse response = new CommonDeleteResponse(id, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping()
    public ResponseEntity<Page<PostingResponse>> getPostings(@AuthenticationPrincipal Member member, @RequestParam(required = false, defaultValue = "0", name = "page") int page,
                                                             @RequestParam(name = "isRead") Boolean isRead, @RequestParam(name = "categoryId") Integer categoryId) {
        Page<PostingResponse> postingList = postingService.getPostingList(member, page, isRead, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(postingList);
    }

}
