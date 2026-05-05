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

@Data
@Component
public class BrandSpec implements Specification<Brand> {
        private Long id;
        private String name;
    @Override
    public @Nullable Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.equal(root.get("name"), name);
    }
}
