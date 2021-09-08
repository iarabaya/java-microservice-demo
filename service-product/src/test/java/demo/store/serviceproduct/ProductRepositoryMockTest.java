package demo.store.serviceproduct;

import demo.store.serviceproduct.entity.Category;
import demo.store.serviceproduct.entity.Product;
import demo.store.serviceproduct.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    //dependency injection
    @Autowired
    public ProductRepository productRepository;

    //unit testing Product Categories
    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Product product01 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("includes microprocessor, motherboard, RAM, SSD")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("164999.99"))
                .status("Created")
                .createAt(new Date()).build();

        productRepository.save(product01);

        List<Product> founds = productRepository.findByCategory(product01.getCategory());

        Assertions.assertEquals(founds.size(), 1);
    }


}
