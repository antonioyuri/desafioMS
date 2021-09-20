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

    public Specification<Product> toSpec(String q,String min_price,String max_price ){
        return (root,query,builder)->{

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasText(q)){

                Path<String>campoNome = root.get("name");
                Path<String>campoDesc = root.get("description");

                Predicate predicateNome = builder.or(builder.like(campoNome,q), builder.like(campoDesc,q));
                predicates.add(predicateNome);

            }

            if(StringUtils.hasText(min_price)){

                Path<String>minPrice = root.get("price");
                Predicate predicateMinPrice = builder.greaterThanOrEqualTo(minPrice, min_price);
                predicates.add(predicateMinPrice);
            }

            if(StringUtils.hasText(max_price)){

                Path<String>maxPrice = root.get("price");
                Predicate predicateMaxPrice = builder.lessThanOrEqualTo(maxPrice, max_price);
                predicates.add(predicateMaxPrice);
            }

            return builder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
