package com.minod.jpa.domain.단방향1대1;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLockerOO is a Querydsl query type for LockerOO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLockerOO extends EntityPathBase<LockerOO> {

    private static final long serialVersionUID = 632174501L;

    public static final QLockerOO lockerOO = new QLockerOO("lockerOO");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QLockerOO(String variable) {
        super(LockerOO.class, forVariable(variable));
    }

    public QLockerOO(Path<? extends LockerOO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLockerOO(PathMetadata metadata) {
        super(LockerOO.class, metadata);
    }

}

