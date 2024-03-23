package com.minod.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMbr1 is a Querydsl query type for Mbr1
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbr1 extends EntityPathBase<Mbr1> {

    private static final long serialVersionUID = -185582889L;

    public static final QMbr1 mbr1 = new QMbr1("mbr1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QMbr1(String variable) {
        super(Mbr1.class, forVariable(variable));
    }

    public QMbr1(Path<? extends Mbr1> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMbr1(PathMetadata metadata) {
        super(Mbr1.class, metadata);
    }

}

