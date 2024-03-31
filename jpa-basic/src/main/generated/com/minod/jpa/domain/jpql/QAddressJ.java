package com.minod.jpa.domain.jpql;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddressJ is a Querydsl query type for AddressJ
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAddressJ extends BeanPath<AddressJ> {

    private static final long serialVersionUID = -294099104L;

    public static final QAddressJ addressJ = new QAddressJ("addressJ");

    public final StringPath city = createString("city");

    public final StringPath street = createString("street");

    public final StringPath zipcode = createString("zipcode");

    public QAddressJ(String variable) {
        super(AddressJ.class, forVariable(variable));
    }

    public QAddressJ(Path<? extends AddressJ> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddressJ(PathMetadata metadata) {
        super(AddressJ.class, metadata);
    }

}

