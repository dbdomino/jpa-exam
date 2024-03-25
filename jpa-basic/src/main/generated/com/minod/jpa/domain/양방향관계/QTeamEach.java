package com.minod.jpa.domain.양방향관계;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamEach is a Querydsl query type for TeamEach
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamEach extends EntityPathBase<TeamEach> {

    private static final long serialVersionUID = 924166046L;

    public static final QTeamEach teamEach = new QTeamEach("teamEach");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MbrEach, QMbrEach> MbrEach = this.<MbrEach, QMbrEach>createList("MbrEach", MbrEach.class, QMbrEach.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QTeamEach(String variable) {
        super(TeamEach.class, forVariable(variable));
    }

    public QTeamEach(Path<? extends TeamEach> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamEach(PathMetadata metadata) {
        super(TeamEach.class, metadata);
    }

}

