package com.minod.jpa.domain.jpql;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDayLightJpql is a Querydsl query type for DayLightJpql
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDayLightJpql extends EntityPathBase<DayLightJpql> {

    private static final long serialVersionUID = 400698213L;

    public static final QDayLightJpql dayLightJpql = new QDayLightJpql("dayLightJpql");

    public final QDayJpql _super = new QDayJpql(this);

    //inherited
    public final NumberPath<Integer> age = _super.age;

    //inherited
    public final BooleanPath booltype = _super.booltype;

    //inherited
    public final EnumPath<Day> day = _super.day;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath light = createString("light");

    //inherited
    public final StringPath username = _super.username;

    public QDayLightJpql(String variable) {
        super(DayLightJpql.class, forVariable(variable));
    }

    public QDayLightJpql(Path<? extends DayLightJpql> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDayLightJpql(PathMetadata metadata) {
        super(DayLightJpql.class, metadata);
    }

}

