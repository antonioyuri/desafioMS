package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.example.demo.model.Product;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BuscaProductSpecification {

    public Specification<Product> toSpec(String q,String minPrice,String maxPrice ){
        return (root,query,builder)->{

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasText(q)){

                Path<String>campoNome = root.get("name");
                Path<String>campoDesc = root.get("description");

                Predicate predicateNome = builder.or(builder.like(builder.lower(campoNome),"%"+q.toLowerCase()+"%"), builder.like(builder.lower(campoDesc),"%"+q.toLowerCase()+"%"));
                predicates.add(predicateNome);

            }

            if(StringUtils.hasText(minPrice)){

                Path<String>minPricePath = root.get("price");
                Predicate predicateMinPrice = builder.greaterThanOrEqualTo(minPricePath, minPrice);
                predicates.add(predicateMinPrice);
            }

            if(StringUtils.hasText(maxPrice)){

                Path<String>maxPricePath = root.get("price");
                Predicate predicateMaxPrice = builder.lessThanOrEqualTo(maxPricePath, maxPrice);
                predicates.add(predicateMaxPrice);
            }

            return builder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
