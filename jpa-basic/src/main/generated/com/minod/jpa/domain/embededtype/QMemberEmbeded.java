package com.minod.jpa.domain.embededtype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberEmbeded is a Querydsl query type for MemberEmbeded
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEmbeded extends EntityPathBase<MemberEmbeded> {

    private static final long serialVersionUID = -1275216865L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberEmbeded memberEmbeded = new QMemberEmbeded("memberEmbeded");

    public final QAddressC address;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPeriodC period;

    public final StringPath username = createString("username");

    public QMemberEmbeded(String variable) {
        this(MemberEmbeded.class, forVariable(variable), INITS);
    }

    public QMemberEmbeded(Path<? extends MemberEmbeded> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberEmbeded(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberEmbeded(PathMetadata metadata, PathInits inits) {
        this(MemberEmbeded.class, metadata, inits);
    }

    public QMemberEmbeded(Class<? extends MemberEmbeded> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddressC(forProperty("address")) : null;
        this.period = inits.isInitialized("period") ? new QPeriodC(forProperty("period")) : null;
    }

}

