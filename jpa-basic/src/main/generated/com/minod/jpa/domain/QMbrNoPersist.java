package com.minod.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMbrNoPersist is a Querydsl query type for MbrNoPersist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbrNoPersist extends EntityPathBase<MbrNoPersist> {

    private static final long serialVersionUID = 482004313L;

    public static final QMbrNoPersist mbrNoPersist = new QMbrNoPersist("mbrNoPersist");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> teamId = createNumber("teamId", Long.class);

    public final StringPath username = createString("username");

    public QMbrNoPersist(String variable) {
        super(MbrNoPersist.class, forVariable(variable));
    }

    public QMbrNoPersist(Path<? extends MbrNoPersist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMbrNoPersist(PathMetadata metadata) {
        super(MbrNoPersist.class, metadata);
    }

}

