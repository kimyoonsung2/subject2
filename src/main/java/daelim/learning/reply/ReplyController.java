package daelim.learning.reply;

import daelim.learning.board.Board;
import daelim.learning.board.BoardRepository;
import daelim.learning.reply.dto.ReplyRequest;
import daelim.learning.user.User;
import daelim.learning.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성
    @PostMapping("/board/detail/reply/{boardNo}")
    public String write(@PathVariable(name="boardNo") Long boardNo, ReplyRequest request, HttpSession session) throws NullPointerException {
        replyService.save(request, boardNo, session);
        return "redirect:/board/detail/"+boardNo;
    }

    // 수정
    @PostMapping("/board/detail/{boardNo}/reply/update/{replyNo}")
    public String update(@PathVariable(name="boardNo") Long boardNo, @PathVariable(name = "replyNo") Long replyNo,
                         ReplyRequest request, RedirectAttributes redirectAttributes) {

        replyService.update(request, replyNo);

        redirectAttributes.addAttribute("boardNo", boardNo);
        return "redirect:/board/detail/{boardNo}";
    }


    // 삭제
    @GetMapping("/board/detail/{boardNo}/reply/remove/{replyNo}")
    public String delete(@PathVariable(name="boardNo") Long boardNo, @PathVariable(name = "replyNo") Long replyNo, RedirectAttributes redirectAttributes) {
        replyService.delete(replyNo);
        redirectAttributes.addAttribute("boardNo", boardNo);
        return "redirect:/board/detail/{boardNo}";
    }
}
