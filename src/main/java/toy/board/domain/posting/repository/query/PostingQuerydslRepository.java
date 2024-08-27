package toy.board.domain.posting.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import toy.board.domain.posting.dto.request.PostingSearchCriteria;
import toy.board.model.entity.Member;
import toy.board.model.entity.Posting;

import java.util.List;

import static toy.board.model.entity.QCategory.category;
import static toy.board.model.entity.QPosting.posting;


@Repository
@RequiredArgsConstructor
public class PostingQuerydslRepository implements PostingQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<Posting> findAllByMemberAndCriteria(Member member, Pageable pageable, PostingSearchCriteria criteria) {
        List<Posting> content = queryFactory.selectFrom(posting)
                .leftJoin(posting.category, category)
                .where(memberEq(member),
                        categoryEq(criteria.categoryId()),
                        readEq(criteria.isRead()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> countQuery = queryFactory.select(posting.count())
                .from(posting)
                .where(memberEq(member),
                        categoryEq(criteria.categoryId()),
                        readEq(criteria.isRead()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression memberEq(Member member) {
        return member != null ? posting.member.eq(member) : null;
    }

    private BooleanExpression categoryEq(Integer categoryIdCond) {
        return categoryIdCond != null ? posting.category.id.eq(categoryIdCond) : null;
    }

    private BooleanExpression readEq(Boolean readCond) {
        return readCond != null ? posting.isRead.eq(readCond) : null;
    }


}
