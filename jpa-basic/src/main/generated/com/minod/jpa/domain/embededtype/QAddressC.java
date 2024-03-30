package com.minod.jpa.domain.embededtype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddressC is a Querydsl query type for AddressC
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAddressC extends BeanPath<AddressC> {

    private static final long serialVersionUID = 1557492558L;

    public static final QAddressC addressC = new QAddressC("addressC");

    public final StringPath city = createString("city");

    public final StringPath street = createString("street");

    public final StringPath zipcode = createString("zipcode");

    public QAddressC(String variable) {
        super(AddressC.class, forVariable(variable));
    }

    public QAddressC(Path<? extends AddressC> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddressC(PathMetadata metadata) {
        super(AddressC.class, metadata);
    }

}

