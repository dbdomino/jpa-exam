package com.minod.jpa.domain.embededtype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEmbeded is a Querydsl query type for BaseEmbeded
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEmbeded extends EntityPathBase<BaseEmbeded> {

    private static final long serialVersionUID = -1523734456L;

    public static final QBaseEmbeded baseEmbeded = new QBaseEmbeded("baseEmbeded");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public QBaseEmbeded(String variable) {
        super(BaseEmbeded.class, forVariable(variable));
    }

    public QBaseEmbeded(Path<? extends BaseEmbeded> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEmbeded(PathMetadata metadata) {
        super(BaseEmbeded.class, metadata);
    }

}

