package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.example.demo.model.Product;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BuscaProductForm {

    private String q;
    private String min_price;
    private String max_price;

    public BuscaProductForm(String q, String min_price, String max_price) {
        this.q = q;
        this.min_price = min_price;
        this.max_price = max_price;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public Specification<Product> toSpec(){
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
