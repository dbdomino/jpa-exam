package com.minod.jpa.domain.embededtype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddressNick is a Querydsl query type for AddressNick
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddressNick extends EntityPathBase<AddressNick> {

    private static final long serialVersionUID = 729528472L;

    public static final QAddressNick addressNick = new QAddressNick("addressNick");

    public final StringPath AddressNick = createString("AddressNick");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAddressNick(String variable) {
        super(AddressNick.class, forVariable(variable));
    }

    public QAddressNick(Path<? extends AddressNick> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddressNick(PathMetadata metadata) {
        super(AddressNick.class, metadata);
    }

}

