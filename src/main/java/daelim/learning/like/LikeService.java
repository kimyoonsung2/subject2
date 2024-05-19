package daelim.learning.like;

import daelim.learning.board.Board;
import daelim.learning.board.BoardRepository;
import daelim.learning.like.dto.LikeRequest;
import daelim.learning.user.User;
import daelim.learning.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public String addLike(LikeRequest request) {
        User user = userRepository.findById(request.getUserNo())
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
        Board board = boardRepository.findById(request.getBoardNo())
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));

        Likes existingLike = likeRepository.findByUserAndBoard(user, board);

        if (existingLike != null) {
            // 이미 찜한 상태이면 찜 해제
            likeRepository.delete(existingLike);
            return "removed"; // 해제하면 removed 반환
        } else {
            // 찜하지 않은 상태이면 찜 추가
            likeRepository.save(request.toEntity(user, board));
            return "added"; // 추가하면 added 반환
        }
    }

    public List<Long> findLikedBoardList(Long userNo) {
        // userNo를 기반으로 찜한 게시글의 ID 목록을 조회
        List<Long> likedBoardList = likeRepository.findByUserNo(userNo);
        return likedBoardList;
    }
}