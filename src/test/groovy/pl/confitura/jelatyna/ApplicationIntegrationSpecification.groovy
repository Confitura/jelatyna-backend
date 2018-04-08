package pl.confitura.jelatyna

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.context.ApplicationContext

class ApplicationIntegrationSpecification extends BaseIntegrationSpecification{

    @Autowired
    ApplicationContext context

    def "context loads"() {
        expect:
        context != null
    }
}
