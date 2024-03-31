package com.minod.jpa.domain.jpql;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderJpql is a Querydsl query type for OrderJpql
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderJpql extends EntityPathBase<OrderJpql> {

    private static final long serialVersionUID = -1793551611L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderJpql orderJpql = new QOrderJpql("orderJpql");

    public final QAddressJ address;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMemberJpql memberJpql;

    public final NumberPath<Integer> orderAmount = createNumber("orderAmount", Integer.class);

    public final QProductJpql productJpql;

    public QOrderJpql(String variable) {
        this(OrderJpql.class, forVariable(variable), INITS);
    }

    public QOrderJpql(Path<? extends OrderJpql> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderJpql(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderJpql(PathMetadata metadata, PathInits inits) {
        this(OrderJpql.class, metadata, inits);
    }

    public QOrderJpql(Class<? extends OrderJpql> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddressJ(forProperty("address")) : null;
        this.memberJpql = inits.isInitialized("memberJpql") ? new QMemberJpql(forProperty("memberJpql"), inits.get("memberJpql")) : null;
        this.productJpql = inits.isInitialized("productJpql") ? new QProductJpql(forProperty("productJpql")) : null;
    }

}

