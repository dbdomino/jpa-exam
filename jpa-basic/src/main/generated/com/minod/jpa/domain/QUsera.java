package com.minod.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsera is a Querydsl query type for Usera
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsera extends EntityPathBase<Usera> {

    private static final long serialVersionUID = -1450218029L;

    public static final QUsera usera = new QUsera("usera");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QUsera(String variable) {
        super(Usera.class, forVariable(variable));
    }

    public QUsera(Path<? extends Usera> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsera(PathMetadata metadata) {
        super(Usera.class, metadata);
    }

}

