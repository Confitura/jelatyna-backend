package pl.confitura.jelatyna.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.web.context.WebApplicationContext
import pl.confitura.jelatyna.BaseIntegrationSpecification
import pl.confitura.jelatyna.infrastructure.security.JelatynaPrincipal
import spock.lang.Ignore

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given

@Ignore("example test for using spring security")
class UserControllerSpec extends BaseIntegrationSpecification {

    @Autowired
    WebApplicationContext context

    @WithMockUser(roles = ["ADMIN"])
    def "should test"() {
        given:
        def rest = given()
                .webAppContextSetup(context)
                .auth().principal(new JelatynaPrincipal().setAdmin(true).setName("ADMINS NAME"))
        when:
        rest = rest.when()
                .get("/users")
        then:
        rest.then()
                .statusCode(200)

    }
}
