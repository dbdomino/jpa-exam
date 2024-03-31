package com.minod.jpa.domain.jpql;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamJpql is a Querydsl query type for TeamJpql
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamJpql extends EntityPathBase<TeamJpql> {

    private static final long serialVersionUID = -744249752L;

    public static final QTeamJpql teamJpql = new QTeamJpql("teamJpql");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MemberJpql, QMemberJpql> memberJpqlList = this.<MemberJpql, QMemberJpql>createList("memberJpqlList", MemberJpql.class, QMemberJpql.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QTeamJpql(String variable) {
        super(TeamJpql.class, forVariable(variable));
    }

    public QTeamJpql(Path<? extends TeamJpql> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamJpql(PathMetadata metadata) {
        super(TeamJpql.class, metadata);
    }

}

