package com.minod.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMbr3 is a Querydsl query type for Mbr3
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMbr3 extends EntityPathBase<Mbr3> {

    private static final long serialVersionUID = -185582887L;

    public static final QMbr3 mbr3 = new QMbr3("mbr3");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QMbr3(String variable) {
        super(Mbr3.class, forVariable(variable));
    }

    public QMbr3(Path<? extends Mbr3> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMbr3(PathMetadata metadata) {
        super(Mbr3.class, metadata);
    }

}

