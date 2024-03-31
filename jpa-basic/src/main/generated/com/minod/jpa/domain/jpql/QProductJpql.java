package com.minod.jpa.domain.jpql;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductJpql is a Querydsl query type for ProductJpql
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductJpql extends EntityPathBase<ProductJpql> {

    private static final long serialVersionUID = 112642726L;

    public static final QProductJpql productJpql = new QProductJpql("productJpql");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> stockAmount = createNumber("stockAmount", Integer.class);

    public QProductJpql(String variable) {
        super(ProductJpql.class, forVariable(variable));
    }

    public QProductJpql(Path<? extends ProductJpql> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductJpql(PathMetadata metadata) {
        super(ProductJpql.class, metadata);
    }

}

