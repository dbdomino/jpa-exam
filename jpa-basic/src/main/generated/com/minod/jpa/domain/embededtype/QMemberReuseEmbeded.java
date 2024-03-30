package com.minod.jpa.domain.embededtype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberReuseEmbeded is a Querydsl query type for MemberReuseEmbeded
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberReuseEmbeded extends EntityPathBase<MemberReuseEmbeded> {

    private static final long serialVersionUID = 540611997L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberReuseEmbeded memberReuseEmbeded = new QMemberReuseEmbeded("memberReuseEmbeded");

    public final QAddressC addressA;

    public final QAddressC addressB;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPeriodC period;

    public final StringPath username = createString("username");

    public QMemberReuseEmbeded(String variable) {
        this(MemberReuseEmbeded.class, forVariable(variable), INITS);
    }

    public QMemberReuseEmbeded(Path<? extends MemberReuseEmbeded> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberReuseEmbeded(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberReuseEmbeded(PathMetadata metadata, PathInits inits) {
        this(MemberReuseEmbeded.class, metadata, inits);
    }

    public QMemberReuseEmbeded(Class<? extends MemberReuseEmbeded> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressA = inits.isInitialized("addressA") ? new QAddressC(forProperty("addressA")) : null;
        this.addressB = inits.isInitialized("addressB") ? new QAddressC(forProperty("addressB")) : null;
        this.period = inits.isInitialized("period") ? new QPeriodC(forProperty("period")) : null;
    }

}

