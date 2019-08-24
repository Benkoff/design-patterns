package io.github.benkoff.designpatterns.specification;

import io.github.benkoff.designpatterns.specification.model.Color;
import io.github.benkoff.designpatterns.specification.model.Product;
import io.github.benkoff.designpatterns.specification.model.Size;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterSpecificationTest {
    private static final Product SMALL_RED = new Product("Small Green", Size.SMALL, Color.RED);
    private static final Product MEDIUM_BLUE = new Product("Medium Blue", Size.MEDIUM, Color.BLUE);
    private static final Product LARGE_BLUE = new Product("Medium Blue", Size.LARGE, Color.BLUE);
    private static final Product LARGE_RED = new Product("Large Red", Size.LARGE, Color.RED);
    private static final List PRODUCTS = List.of(SMALL_RED, MEDIUM_BLUE, LARGE_BLUE, LARGE_RED);
    
    @Before
    public void setUp() {
        //empty
    }

    @Test
    public void shouldReturnList_whenFilterByColor() {
        
        Stream actual = new FilterImpl().filter(PRODUCTS, new ColorSpecification(Color.RED));

        assertThat(actual)
            .contains(LARGE_RED)
            .contains(SMALL_RED)
            .hasSize(2);
    }

    @Test
    public void shouldReturnList_whenFilterBySize() {

        Stream actual = new FilterImpl().filter(PRODUCTS, new SizeSpecification(Size.LARGE));

        assertThat(actual)
            .contains(LARGE_RED)
            .contains(LARGE_BLUE)
            .hasSize(2);
    }

    @Test
    public void shouldReturnList_whenFilterBySizeAndColor() {
        
        Stream actual = new FilterImpl().filter(PRODUCTS, new AndSpecification<>(new SizeSpecification(Size.LARGE), new ColorSpecification(Color.RED)));
            
        assertThat(actual)
            .contains(LARGE_RED)
            .hasSize(1);
    }

    @Test
    public void shouldReturnList_whenFilterBySizeOrColor() {

        Stream actual = new FilterImpl().filter(PRODUCTS, new OrSpecification<>(new SizeSpecification(Size.LARGE), new ColorSpecification(Color.RED)));

        assertThat(actual)
            .contains(LARGE_RED)
            .contains(LARGE_BLUE)
            .contains(SMALL_RED)
            .hasSize(3);
    }
}