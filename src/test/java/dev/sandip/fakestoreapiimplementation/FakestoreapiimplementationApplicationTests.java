package dev.sandip.fakestoreapiimplementation;

import dev.sandip.fakestoreapiimplementation.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FakestoreapiimplementationApplicationTests {

    private ProductRepository productRepository;
    @Test
    void contextLoads() {
    }
    @Test
    void testQueries(){
//productRepository.findAllByCategory_Title(Title);
    }

}
