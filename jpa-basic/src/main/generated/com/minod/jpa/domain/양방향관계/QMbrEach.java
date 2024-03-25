package com.minod.jpa.domain.양방향관계;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMbrEach is a Querydsl query type for MbrEach
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbrEach extends EntityPathBase<MbrEach> {

    private static final long serialVersionUID = -435078274L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMbrEach mbrEach = new QMbrEach("mbrEach");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTeamEach teamEach;

    public final StringPath username = createString("username");

    public QMbrEach(String variable) {
        this(MbrEach.class, forVariable(variable), INITS);
    }

    public QMbrEach(Path<? extends MbrEach> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMbrEach(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMbrEach(PathMetadata metadata, PathInits inits) {
        this(MbrEach.class, metadata, inits);
    }

    public QMbrEach(Class<? extends MbrEach> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.teamEach = inits.isInitialized("teamEach") ? new QTeamEach(forProperty("teamEach")) : null;
    }

}

