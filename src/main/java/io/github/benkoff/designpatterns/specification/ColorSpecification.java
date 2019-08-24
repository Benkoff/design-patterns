package io.github.benkoff.designpatterns.specification;

import io.github.benkoff.designpatterns.specification.model.Color;
import io.github.benkoff.designpatterns.specification.model.Product;

public class ColorSpecification implements Specification<Product> {
    private Color color;

    public ColorSpecification(final Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(final Product item) {
        return this.color.equals(item.getColor());
    }
}
