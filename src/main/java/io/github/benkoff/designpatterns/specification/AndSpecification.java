package io.github.benkoff.designpatterns.specification;

public class AndSpecification<T> implements Specification<T> {
    private Specification<T> first;
    private Specification<T> second;

    public AndSpecification(final Specification<T> first, final Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(final T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}
