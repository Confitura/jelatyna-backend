package pl.confitura.jelatyna.registration;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.experimental.Accessors;
import pl.confitura.jelatyna.infrastructure.db.AuditedEntity;
import pl.confitura.jelatyna.registration.voucher.Voucher;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_voucher", columnNames = {"voucher_id"})
})
public class ParticipationData extends AuditedEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(100)")
    private String id;

    @OneToOne(optional = false)
    private Voucher voucher;

    private String city;
    private String gender;
    private String experience;
    private String role;
    private String size;
    private String info;
    private String mealOption;

    private LocalDateTime arrivalDate;
    private String registeredBy;
    private LocalDateTime ticketSendDate;
    private LocalDateTime surveySendDate;

    boolean alreadyArrived() {
        return this.arrivalDate != null;
    }

    boolean surveyNotSentYet() {
        return surveySendDate == null;
    }

    public boolean hasVoucher() {
        return voucher != null;
    }
}
