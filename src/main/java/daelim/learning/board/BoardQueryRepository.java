package daelim.learning.board;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import daelim.learning.board.dto.BoardListResponse;
import daelim.learning.board.dto.BoardSearchCond;
import daelim.learning.user.QUser;
import daelim.learning.user.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static daelim.learning.board.QBoard.board;
import static daelim.learning.user.QUser.user;

@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory query;

    public BoardQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Board> findAll(BoardSearchCond cond) {

        OrderSpecifier[] orderSpecifier = createOrderSpecifiers(cond);

        System.out.println("studyType::::::::::::::::: "+cond.getStudyType());

        return query.select(board)
                .from(board)
                .where(
                        likeStudyType(cond.getStudyType())
                )
                .orderBy(orderSpecifier)
                .fetch();
    }

    private OrderSpecifier[] createOrderSpecifiers(BoardSearchCond cond) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        String orderCond = cond.getSortType();

        if(Objects.isNull(orderCond)){
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, board.createdAt));
        }else if(orderCond.equals("NEW")){
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, board.createdAt));
        }else if(orderCond.equals("POP")){
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, board.viewCount));
        }
        return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
    }

    private BooleanExpression likeStudyType(StudyType studyType) {
        if (studyType != null && !studyType.equals("")) {
            return board.studyType.eq(studyType);
        }
        return null;
    }
}
