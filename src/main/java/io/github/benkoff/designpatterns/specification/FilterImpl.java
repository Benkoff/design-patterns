package io.github.benkoff.designpatterns.specification;

import io.github.benkoff.designpatterns.specification.model.Product;

import java.util.List;
import java.util.stream.Stream;

public class FilterImpl implements Filter<Product> {
    @Override
    public Stream<Product> filter(final List<Product> items, final Specification<Product> specification) {
        return items.stream().filter(specification::isSatisfied);
    }
}
