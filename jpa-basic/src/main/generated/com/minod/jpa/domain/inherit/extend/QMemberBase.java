package com.minod.jpa.domain.inherit.extend;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberBase is a Querydsl query type for MemberBase
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberBase extends EntityPathBase<MemberBase> {

    private static final long serialVersionUID = 1679411529L;

    public static final QMemberBase memberBase = new QMemberBase("memberBase");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath city = createString("city");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final StringPath street = createString("street");

    public final StringPath zipcode = createString("zipcode");

    public QMemberBase(String variable) {
        super(MemberBase.class, forVariable(variable));
    }

    public QMemberBase(Path<? extends MemberBase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberBase(PathMetadata metadata) {
        super(MemberBase.class, metadata);
    }

}

