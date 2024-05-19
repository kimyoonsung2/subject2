package daelim.learning.reply;

import daelim.learning.board.Board;
import daelim.learning.board.BoardRepository;
import daelim.learning.reply.dto.ReplyListResponse;
import daelim.learning.reply.dto.ReplyRequest;
import daelim.learning.user.User;
import daelim.learning.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void save(ReplyRequest replyRequest, Long boardNo, HttpSession session) {

        Long userNo = (Long) session.getAttribute("userNo");

        User writer = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("not found userNo = "+userNo));
        Board board = boardRepository.findByBoardNo(boardNo);

        Reply reply = replyRequest.toEntity(board, writer);

        replyRepository.save(reply);
    }

    // 조회
    public List<ReplyListResponse> findAll(Long boardNo) {
        return replyRepository.findByBoardNoBoardNo(boardNo).stream().map(
                reply -> ReplyListResponse.builder()
                        .comment(reply.getComment())
                        .user(reply.getUserNo())
                        .replyNo(reply.getReplyNo())
                        .build()
        ).toList();
    }

    // 수정
    public void update(ReplyRequest replyRequest, Long replyNo) {
        Reply reply = replyRepository.findById(replyNo).orElseThrow();
        reply.setComment(replyRequest.getComment());
        replyRepository.save(reply);
    }

    public void delete(Long replyNo) {
        replyRepository.deleteById(replyNo);
    }
}
