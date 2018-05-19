package pl.confitura.jelatyna.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.confitura.jelatyna.BaseIntegrationTest;
import pl.confitura.jelatyna.registration.voucher.VoucherService;

import static org.junit.jupiter.api.Assertions.fail;

class RegistrationControllerTest extends BaseIntegrationTest {

    @Autowired
    RegistrationController registrationController;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    VoucherService voucherService;

    @Test
    void user_is_able_to_create_participant(){
        //when user tries to register as participant

        //then participant is registered

        //and participant is assigned to user
        fail("NOT IMPLEMENTED YET");

    }

    @Test
    void shouldNotAcceptInvalidToken(){
        fail("NOT IMPLEMENTED YET");
    }

    // User is able to create Participant
    // Participant should be assigned to User
    // User can create only one participant
    // User can create Participant with Token
    // User can create Participant without Token
    // User can assign id to existing Participant
    // User can not assign id to Participant assigned to other User
}