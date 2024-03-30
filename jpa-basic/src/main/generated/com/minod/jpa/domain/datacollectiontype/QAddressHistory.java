package com.minod.jpa.domain.datacollectiontype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddressHistory is a Querydsl query type for AddressHistory
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAddressHistory extends BeanPath<AddressHistory> {

    private static final long serialVersionUID = -1927255287L;

    public static final QAddressHistory addressHistory = new QAddressHistory("addressHistory");

    public final StringPath city = createString("city");

    public final StringPath street = createString("street");

    public final StringPath zipcode = createString("zipcode");

    public QAddressHistory(String variable) {
        super(AddressHistory.class, forVariable(variable));
    }

    public QAddressHistory(Path<? extends AddressHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddressHistory(PathMetadata metadata) {
        super(AddressHistory.class, metadata);
    }

}

