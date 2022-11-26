package com.example.gamesapi.gamesapi.games.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GameSpecification implements Specification<Game> {

    private static final Logger log = LoggerFactory.getLogger(GameSpecification.class);

    private GamesCriteria criteria;

    private static final String NAME = "name";
    private static final String RATING = "rating";
    private static final String YEAR = "year";

    /*
    * OPERADORES
    * */
    private static final String GREATHER_THAN = "GREATHER_THAN";
    private static final String LESS_THAN = "LESS_THAN";
    private static final String EQUALS = "EQUALS";
    private static final String DIFFERENT = "DIFFERENT";
    private static final String LIKE = "LIKE";

    @Autowired
    public GameSpecification(GamesCriteria criteria) {
        super();
        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate(Root<Game> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {

/*        if (criteria.getKey().equals(NAME)){
            return builder.like(builder.upper(root.get(NAME)), "%" + criteria.getValue().toUpperCase(Locale.ROOT) + "%");
        }
        else if (criteria.getKey().equals(RATING)){
            return builder.greaterThanOrEqualTo(root.get(RATING),criteria.getValue());
        }
        else if (criteria.getKey().equals(YEAR)){
            //TODO: Filtrar el año del string y compararlo con el criteriagetValue()
        }*/
        return builder.like(root.get(NAME),"%" + criteria.getValue() + "%");
    }
}
