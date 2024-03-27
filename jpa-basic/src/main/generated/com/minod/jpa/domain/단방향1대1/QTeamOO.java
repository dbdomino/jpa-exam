package com.minod.jpa.domain.단방향1대1;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamOO is a Querydsl query type for TeamOO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamOO extends EntityPathBase<TeamOO> {

    private static final long serialVersionUID = 1838272746L;

    public static final QTeamOO teamOO = new QTeamOO("teamOO");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.minod.jpa.domain.단방향1대N.MbrOne, com.minod.jpa.domain.단방향1대N.QMbrOne> members = this.<com.minod.jpa.domain.단방향1대N.MbrOne, com.minod.jpa.domain.단방향1대N.QMbrOne>createList("members", com.minod.jpa.domain.단방향1대N.MbrOne.class, com.minod.jpa.domain.단방향1대N.QMbrOne.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QTeamOO(String variable) {
        super(TeamOO.class, forVariable(variable));
    }

    public QTeamOO(Path<? extends TeamOO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamOO(PathMetadata metadata) {
        super(TeamOO.class, metadata);
    }

}

