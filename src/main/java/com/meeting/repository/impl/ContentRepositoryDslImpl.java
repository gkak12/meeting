package com.meeting.repository.impl;

import com.meeting.domain.dto.response.ResponseContentVo;
import com.meeting.domain.entity.Content;
import com.meeting.repository.ContentRepositoryDsl;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.meeting.domain.entity.QContent.content;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ContentRepositoryDslImpl implements ContentRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Content> findContentByContentName(String contentName) {
        return jpaQueryFactory
                .selectFrom(content)
                .where(content.contentName.like("%"+contentName+"%"))
                .fetch();
    }

    @Override
    public ResponseContentVo findMostSelectedContent() {
        return jpaQueryFactory
                .select(Projections.fields(ResponseContentVo.class,
                        content.contentName,
                        content.count().as("selectionNumber")
                ))
                .from(content)
                .groupBy(content.contentSeq)
                .orderBy(content.count().desc())
                .fetchFirst();
    }
}
