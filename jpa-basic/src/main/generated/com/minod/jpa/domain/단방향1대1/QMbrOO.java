package com.minod.jpa.domain.단방향1대1;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMbrOO is a Querydsl query type for MbrOO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbrOO extends EntityPathBase<MbrOO> {

    private static final long serialVersionUID = -1332712816L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMbrOO mbrOO = new QMbrOO("mbrOO");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLockerOO locker;

    public final StringPath username = createString("username");

    public QMbrOO(String variable) {
        this(MbrOO.class, forVariable(variable), INITS);
    }

    public QMbrOO(Path<? extends MbrOO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMbrOO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMbrOO(PathMetadata metadata, PathInits inits) {
        this(MbrOO.class, metadata, inits);
    }

    public QMbrOO(Class<? extends MbrOO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.locker = inits.isInitialized("locker") ? new QLockerOO(forProperty("locker")) : null;
    }

}

