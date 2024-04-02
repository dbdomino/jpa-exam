package com.minod.jpa.domain.jpql;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberNamedJpql is a Querydsl query type for MemberNamedJpql
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberNamedJpql extends EntityPathBase<MemberNamedJpql> {

    private static final long serialVersionUID = 314636150L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberNamedJpql memberNamedJpql = new QMemberNamedJpql("memberNamedJpql");

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

    public QMemberNamedJpql(String variable) {
        this(MemberNamedJpql.class, forVariable(variable), INITS);
    }

    public QMemberNamedJpql(Path<? extends MemberNamedJpql> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberNamedJpql(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberNamedJpql(PathMetadata metadata, PathInits inits) {
        this(MemberNamedJpql.class, metadata, inits);
    }

    public QMemberNamedJpql(Class<? extends MemberNamedJpql> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.teamJpql = inits.isInitialized("teamJpql") ? new QTeamJpql(forProperty("teamJpql")) : null;
    }

}

