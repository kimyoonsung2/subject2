package daelim.learning.board.dto;

import daelim.learning.board.StudyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchCond {
    private StudyType studyType;
    private String sortType;
}
