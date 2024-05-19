package daelim.learning.board.dto;

import daelim.learning.board.StudyType;
import daelim.learning.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDetailResponse {
    private Long boardNo;
    private String title;
    private User writer;
    private String studySubject;
    private String studyType;
    private String totalPeople;
    private String dueDate;
    private String contactLink;
    private String content;
    private int viewCount;
}
