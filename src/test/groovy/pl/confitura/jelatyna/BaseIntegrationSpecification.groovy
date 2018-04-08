package pl.confitura.jelatyna

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("fake-db")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
abstract class BaseIntegrationSpecification extends Specification {

}
