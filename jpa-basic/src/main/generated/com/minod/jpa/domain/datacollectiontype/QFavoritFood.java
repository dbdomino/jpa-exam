package com.minod.jpa.domain.datacollectiontype;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFavoritFood is a Querydsl query type for FavoritFood
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFavoritFood extends BeanPath<FavoritFood> {

    private static final long serialVersionUID = 1761556350L;

    public static final QFavoritFood favoritFood = new QFavoritFood("favoritFood");

    public final StringPath foodName = createString("foodName");

    public QFavoritFood(String variable) {
        super(FavoritFood.class, forVariable(variable));
    }

    public QFavoritFood(Path<? extends FavoritFood> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoritFood(PathMetadata metadata) {
        super(FavoritFood.class, metadata);
    }

}

