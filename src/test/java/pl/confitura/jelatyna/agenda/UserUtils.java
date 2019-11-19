package pl.confitura.jelatyna.agenda;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.confitura.jelatyna.infrastructure.security.SecurityHelper;
import pl.confitura.jelatyna.registration.ParticipationData;
import pl.confitura.jelatyna.registration.ParticipationRepository;
import pl.confitura.jelatyna.registration.voucher.Voucher;
import pl.confitura.jelatyna.registration.voucher.VoucherService;
import pl.confitura.jelatyna.user.dto.User;
import pl.confitura.jelatyna.user.UserFacade;

import static java.time.LocalDateTime.now;

@Component
@AllArgsConstructor
public class UserUtils {

    private final UserFacade userRepository;
    private final ParticipationRepository participationRepository;
    private final VoucherService voucherService;


    public User createUser(String title) {
        return userRepository.createUser(new User().setId(title).setName(title));
    }

    public User markArrived(User user) {
        Voucher voucher = voucherService.generateVoucher("");
        ParticipationData data = participationRepository.save(new ParticipationData().setArrivalDate(now()).setVoucher(voucher));
//        return userRepository.update(user.setParticipationData(data)); TODO
        return user;
    }
}
