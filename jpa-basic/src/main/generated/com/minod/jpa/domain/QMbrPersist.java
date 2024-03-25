package com.minod.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMbrPersist is a Querydsl query type for MbrPersist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbrPersist extends EntityPathBase<MbrPersist> {

    private static final long serialVersionUID = 846165978L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMbrPersist mbrPersist = new QMbrPersist("mbrPersist");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTeam team;

    public final StringPath username = createString("username");

    public QMbrPersist(String variable) {
        this(MbrPersist.class, forVariable(variable), INITS);
    }

    public QMbrPersist(Path<? extends MbrPersist> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMbrPersist(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMbrPersist(PathMetadata metadata, PathInits inits) {
        this(MbrPersist.class, metadata, inits);
    }

    public QMbrPersist(Class<? extends MbrPersist> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

