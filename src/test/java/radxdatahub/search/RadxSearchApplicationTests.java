package radxdatahub.search;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = { "SEARCH_USERNAME=username", "SEARCH_PASSWORD=password" })
class RadxSearchApplicationTests {

	@Test
	void contextLoads() {
	}

}
