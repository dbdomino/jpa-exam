package com.minod.jpa.domain.jpql;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberJpql is a Querydsl query type for MemberJpql
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberJpql extends EntityPathBase<MemberJpql> {

    private static final long serialVersionUID = -1843884635L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberJpql memberJpql = new QMemberJpql("memberJpql");

    public final com.minod.jpa.domain.embededtype.QBaseEmbeded _super = new com.minod.jpa.domain.embededtype.QBaseEmbeded(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QTeamJpql teamJpql;

    public final StringPath username = createString("username");

    public QMemberJpql(String variable) {
        this(MemberJpql.class, forVariable(variable), INITS);
    }

    public QMemberJpql(Path<? extends MemberJpql> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberJpql(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberJpql(PathMetadata metadata, PathInits inits) {
        this(MemberJpql.class, metadata, inits);
    }

    public QMemberJpql(Class<? extends MemberJpql> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.teamJpql = inits.isInitialized("teamJpql") ? new QTeamJpql(forProperty("teamJpql")) : null;
    }

}

