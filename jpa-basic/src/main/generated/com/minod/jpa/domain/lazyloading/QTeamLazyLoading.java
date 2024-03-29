package com.minod.jpa.domain.lazyloading;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamLazyLoading is a Querydsl query type for TeamLazyLoading
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamLazyLoading extends EntityPathBase<TeamLazyLoading> {

    private static final long serialVersionUID = 1504862722L;

    public static final QTeamLazyLoading teamLazyLoading = new QTeamLazyLoading("teamLazyLoading");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MbrLazyLoading, QMbrLazyLoading> MbrEach = this.<MbrLazyLoading, QMbrLazyLoading>createList("MbrEach", MbrLazyLoading.class, QMbrLazyLoading.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QTeamLazyLoading(String variable) {
        super(TeamLazyLoading.class, forVariable(variable));
    }

    public QTeamLazyLoading(Path<? extends TeamLazyLoading> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamLazyLoading(PathMetadata metadata) {
        super(TeamLazyLoading.class, metadata);
    }

}

