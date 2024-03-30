package com.minod.jpa.domain.embededtype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPeriodC is a Querydsl query type for PeriodC
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPeriodC extends BeanPath<PeriodC> {

    private static final long serialVersionUID = 242099779L;

    public static final QPeriodC periodC = new QPeriodC("periodC");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public QPeriodC(String variable) {
        super(PeriodC.class, forVariable(variable));
    }

    public QPeriodC(Path<? extends PeriodC> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPeriodC(PathMetadata metadata) {
        super(PeriodC.class, metadata);
    }

}

