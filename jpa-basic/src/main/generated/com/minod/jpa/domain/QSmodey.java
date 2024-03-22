package com.minod.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSmodey is a Querydsl query type for Smodey
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSmodey extends EntityPathBase<Smodey> {

    private static final long serialVersionUID = -2069600666L;

    public static final QSmodey smodey = new QSmodey("smodey");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSmodey(String variable) {
        super(Smodey.class, forVariable(variable));
    }

    public QSmodey(Path<? extends Smodey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSmodey(PathMetadata metadata) {
        super(Smodey.class, metadata);
    }

}

