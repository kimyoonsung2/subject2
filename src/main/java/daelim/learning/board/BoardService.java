package daelim.learning.board;

import daelim.learning.board.dto.*;
import daelim.learning.reply.Reply;
import daelim.learning.reply.ReplyRepository;
import daelim.learning.user.User;
import daelim.learning.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;

    private final UserRepository userRepository;

    public List<BoardListResponse> findTopBoard() {
        return boardRepository.findAllByOrderByViewCountDesc().stream()
                .limit(4)
                .map(BoardListResponse::new)
                .collect(Collectors.toList());
    }

    public List<BoardListResponse> findAll() {
        List<BoardListResponse> boardList = boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardListResponse::new).toList();
        return boardList;
    }

    public List<BoardListResponse> findAll(BoardSearchCond boardSearchCond) {
        List<BoardListResponse> boardList = boardQueryRepository.findAll(boardSearchCond).stream().map(BoardListResponse::new).toList();
        return boardList;
    }

    //글 작성
    public void write(BoardRequest request, HttpSession session) {
        Long userNo = (Long) session.getAttribute("userNo");
        User writer = userRepository.findById(userNo).orElseThrow(() -> new EntityNotFoundException("not found user"));
        request.setWriter(writer);
        boardRepository.save(request.toEntity());
    }

    public BoardDetailResponse detailBoard(Long boardNo, HttpServletRequest request) {
        Board board = boardRepository.findById(boardNo).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        if(!request.getRequestURI().contains("update")) {
            board.incrementViewCount(); // 조회수 1 증가
            boardRepository.save(board); // 변경된 조회수 저장
        }

        return BoardDetailResponse.builder()
                .boardNo(board.getBoardNo())
                .writer(board.getWriter())
                .title(board.getTitle())
                .contactLink(board.getContactLink())
                .studySubject(board.getStudySubject())
                .studyType(board.getStudyType().getDescription())
                .totalPeople(board.getTotalPeople())
                .content(board.getContent())
                .dueDate(board.getDueDate())
                .viewCount(board.getViewCount())
                .build();
    }

    public BoardDetailResponse updateBoard(Long boardNo, BoardUpdateRequest request) {
        Board board = boardRepository.findById(boardNo).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));
        board.update(request); // JPA 변경감지로 update 메서드만 호출하면 알아서 UPDATE 쿼리 날라감

        return BoardDetailResponse.builder()
                .boardNo(board.getBoardNo())
                .writer(board.getWriter())
                .title(board.getTitle())
                .contactLink(board.getContactLink())
                .studySubject(board.getStudySubject())
                .studyType(board.getStudyType().getDescription())
                .totalPeople(board.getTotalPeople())
                .content(board.getContent())
                .dueDate(board.getDueDate())
                .viewCount(board.getViewCount())
                .build();
    }

    public void deleteBoard(Long boardNo) {
        boardRepository.deleteById(boardNo);
    }
}
