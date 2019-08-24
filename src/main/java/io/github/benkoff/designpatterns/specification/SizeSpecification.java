package io.github.benkoff.designpatterns.specification;

import io.github.benkoff.designpatterns.specification.model.Product;
import io.github.benkoff.designpatterns.specification.model.Size;

public class SizeSpecification implements Specification<Product> {
    private Size size;

    public SizeSpecification(final Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(final Product item) {
        return this.size.equals(item.getSize());
    }
}
