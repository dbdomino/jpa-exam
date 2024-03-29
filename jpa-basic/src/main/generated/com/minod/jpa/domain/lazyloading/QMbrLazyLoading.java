package com.minod.jpa.domain.lazyloading;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMbrLazyLoading is a Querydsl query type for MbrLazyLoading
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbrLazyLoading extends EntityPathBase<MbrLazyLoading> {

    private static final long serialVersionUID = -905566956L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMbrLazyLoading mbrLazyLoading = new QMbrLazyLoading("mbrLazyLoading");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTeamLazyLoading team;

    public final StringPath username = createString("username");

    public QMbrLazyLoading(String variable) {
        this(MbrLazyLoading.class, forVariable(variable), INITS);
    }

    public QMbrLazyLoading(Path<? extends MbrLazyLoading> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMbrLazyLoading(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMbrLazyLoading(PathMetadata metadata, PathInits inits) {
        this(MbrLazyLoading.class, metadata, inits);
    }

    public QMbrLazyLoading(Class<? extends MbrLazyLoading> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.team = inits.isInitialized("team") ? new QTeamLazyLoading(forProperty("team")) : null;
    }

}

