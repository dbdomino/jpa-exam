package com.minod.jpa.domain.datacollectiontype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberCollection is a Querydsl query type for MemberCollection
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberCollection extends EntityPathBase<MemberCollection> {

    private static final long serialVersionUID = -1697045599L;

    public static final QMemberCollection memberCollection = new QMemberCollection("memberCollection");

    public final ListPath<AddressHistory, SimplePath<AddressHistory>> addressHistories = this.<AddressHistory, SimplePath<AddressHistory>>createList("addressHistories", AddressHistory.class, SimplePath.class, PathInits.DIRECT2);

    public final SetPath<String, StringPath> favoriteFoods = this.<String, StringPath>createSet("favoriteFoods", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QMemberCollection(String variable) {
        super(MemberCollection.class, forVariable(variable));
    }

    public QMemberCollection(Path<? extends MemberCollection> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberCollection(PathMetadata metadata) {
        super(MemberCollection.class, metadata);
    }

}

