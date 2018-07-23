package pl.confitura.jelatyna.presentation.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.experimental.Accessors;
import pl.confitura.jelatyna.presentation.Presentation;
import pl.confitura.jelatyna.user.User;


@Entity
@Data
@Accessors(chain = true)
public class UsersPerformedRate {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(100)")
    private String id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Presentation presentation;

    public UsersPerformedRate(User user, Presentation presentation) {
        this.user = user;
        this.presentation = presentation;
    }
}
