package project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.main.banner-mode=off"
})
public class PreInitializationTest {

    @Test
    public void testPreInitialization() {
    }
}