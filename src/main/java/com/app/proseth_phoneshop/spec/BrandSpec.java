package com.app.proseth_phoneshop.spec;

import com.app.proseth_phoneshop.entity.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrandSpec implements Specification<Brand> {
        private final BrandFilter brandFilter;
    List<Predicate> predicates = new ArrayList<>();
    @Override
    public @Nullable Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(brandFilter.getName() !=null) {
        /*
            FOR OLD VESION
            Predicate name = cb.like(brand.get("name")), "%" + brandFilter.getName() + "%");
            predicates.add(name);
         */

            Predicate name = cb.like(
                    cb.upper(brand.get("name")),
                    "%" + brandFilter.getName().toUpperCase() + "%"  // toLowerCase → toUpperCase
            );
            predicates.add(name);
        }

        if(brandFilter.getId() !=null) {
            Predicate id = brand.get("id").in(brandFilter.getId());
            predicates.add(id);
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
