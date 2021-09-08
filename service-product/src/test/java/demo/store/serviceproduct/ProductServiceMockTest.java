package demo.store.serviceproduct;

import demo.store.serviceproduct.entity.Category;
import demo.store.serviceproduct.entity.Product;
import demo.store.serviceproduct.repository.ProductRepository;
import demo.store.serviceproduct.service.ProductService;
import demo.store.serviceproduct.service.ProductServiceImplement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        productService = new ProductServiceImplement(productRepository);

        //create mock product
        Product newProd = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("20500.00"))
                .stock(Double.parseDouble("5"))
                .build();

        //find the mock product by id
        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(newProd));
        //when updated, returns updated product
        Mockito.when(productRepository.save(newProd)).thenReturn(newProd);
    }


    //validates search of our product
    @Test
    public void whenValidGetId_thenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertEquals(found.getName(), "computer");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productService.updateStock(1L, Double.parseDouble("10"));
        Assertions.assertEquals(newStock.getStock(), Double.parseDouble("15"));
    }
}
