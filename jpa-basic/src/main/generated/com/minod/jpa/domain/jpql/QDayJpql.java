package com.minod.jpa.domain.jpql;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDayJpql is a Querydsl query type for DayJpql
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDayJpql extends EntityPathBase<DayJpql> {

    private static final long serialVersionUID = -1432523309L;

    public static final QDayJpql dayJpql = new QDayJpql("dayJpql");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final BooleanPath booltype = createBoolean("booltype");

    public final EnumPath<Day> day = createEnum("day", Day.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QDayJpql(String variable) {
        super(DayJpql.class, forVariable(variable));
    }

    public QDayJpql(Path<? extends DayJpql> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDayJpql(PathMetadata metadata) {
        super(DayJpql.class, metadata);
    }

}

