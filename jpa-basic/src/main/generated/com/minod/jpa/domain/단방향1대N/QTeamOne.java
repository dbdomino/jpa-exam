package com.minod.jpa.domain.단방향1대N;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamOne is a Querydsl query type for TeamOne
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamOne extends EntityPathBase<TeamOne> {

    private static final long serialVersionUID = 275309465L;

    public static final QTeamOne teamOne = new QTeamOne("teamOne");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MbrOne, QMbrOne> members = this.<MbrOne, QMbrOne>createList("members", MbrOne.class, QMbrOne.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QTeamOne(String variable) {
        super(TeamOne.class, forVariable(variable));
    }

    public QTeamOne(Path<? extends TeamOne> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamOne(PathMetadata metadata) {
        super(TeamOne.class, metadata);
    }

}

