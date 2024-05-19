package daelim.learning.like;

import daelim.learning.board.BoardRepository;
import daelim.learning.like.dto.LikeRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final LikeRepository likeRepository;
    private BoardRepository boardRepository; // BoardRepository 주입

    @PostMapping("/like/add/{boardNo}")
    @ResponseBody
    public String addLike(@PathVariable(name="boardNo") Long boardNo, HttpSession session) {
        Long userNo = (Long) session.getAttribute("userNo");

        LikeRequest request = LikeRequest.builder()
                .boardNo(boardNo)
                .userNo(userNo)
                .build();

        return likeService.addLike(request); // result 반환해서 추가했는지, 삭제했는지 확인
    }
}
