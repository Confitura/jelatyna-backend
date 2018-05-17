package pl.confitura.jelatyna.registration;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.experimental.Accessors;
import pl.confitura.jelatyna.registration.ticket.Ticket;

@Entity
@Data
@Accessors(chain = true)
public class Participant {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(100)")
    private String id;

    @OneToOne
    @NotNull
    private Ticket ticket;

    private String name;
    private String email;
    private String city;
    private String gender;
    private String experience;
    private String role;
    private String size;
    private String info;
    private String createdBy;
    private String originalBuyer;
    private LocalDateTime creationDate;
    private LocalDateTime registrationDate;
    private LocalDateTime arrivalDate;
    private String registeredBy;
    private LocalDateTime ticketSendDate;
    private LocalDateTime surveySendDate;
    private boolean emailSent;

    public boolean alreadyArrived() {
        return this.arrivalDate != null;
    }

    boolean ticketNotSentYet() {
        return this.ticketSendDate == null;
    }

    public boolean surveyNotSentYet() {
        return surveySendDate == null;
    }
}
