package com.minod.jpa.domain.단방향1대N;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMbrOne is a Querydsl query type for MbrOne
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbrOne extends EntityPathBase<MbrOne> {

    private static final long serialVersionUID = 637468889L;

    public static final QMbrOne mbrOne = new QMbrOne("mbrOne");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QMbrOne(String variable) {
        super(MbrOne.class, forVariable(variable));
    }

    public QMbrOne(Path<? extends MbrOne> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMbrOne(PathMetadata metadata) {
        super(MbrOne.class, metadata);
    }

}

