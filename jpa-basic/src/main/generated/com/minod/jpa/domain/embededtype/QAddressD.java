package com.minod.jpa.domain.embededtype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddressD is a Querydsl query type for AddressD
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAddressD extends BeanPath<AddressD> {

    private static final long serialVersionUID = 1557492559L;

    public static final QAddressD addressD = new QAddressD("addressD");

    public final StringPath language = createString("language");

    public final StringPath national = createString("national");

    public final StringPath region = createString("region");

    public QAddressD(String variable) {
        super(AddressD.class, forVariable(variable));
    }

    public QAddressD(Path<? extends AddressD> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddressD(PathMetadata metadata) {
        super(AddressD.class, metadata);
    }

}

