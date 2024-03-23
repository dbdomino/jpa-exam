package com.minod.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMbr2 is a Querydsl query type for Mbr2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbr2 extends EntityPathBase<Mbr2> {

    private static final long serialVersionUID = -185582888L;

    public static final QMbr2 mbr2 = new QMbr2("mbr2");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QMbr2(String variable) {
        super(Mbr2.class, forVariable(variable));
    }

    public QMbr2(Path<? extends Mbr2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMbr2(PathMetadata metadata) {
        super(Mbr2.class, metadata);
    }

}

