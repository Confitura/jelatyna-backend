package pl.confitura.jelatyna.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.confitura.jelatyna.BaseIntegrationTest;
import pl.confitura.jelatyna.registration.ticket.TicketService;

import static org.junit.jupiter.api.Assertions.fail;

class RegistrationControllerTest extends BaseIntegrationTest {

    @Autowired
    RegistrationController registrationController;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    TicketService ticketService;

    @Test
    void user_is_able_to_create_participant(){
        //when user tries to register as participant

        //then participant is registered

        //and participant is assigned to user

    }

    @Test
    void shouldNotAcceptInvalidToken(){
        fail("shouldNotAcceptInvalidToken");
    }

    // User is able to create Participant
    // Participant should be assigned to User
    // User can create only one participant
    // User can create Participant with Token
    // User can create Participant without Token
    // User can assign id to existing Participant
    // User can not assign id to Participant assigned to other User
}