package pl.confitura.jelatyna.agenda;

import static java.time.LocalDateTime.now;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import pl.confitura.jelatyna.registration.ParticipationData;
import pl.confitura.jelatyna.registration.ParticipationRepository;
import pl.confitura.jelatyna.registration.voucher.Voucher;
import pl.confitura.jelatyna.registration.voucher.VoucherService;
import pl.confitura.jelatyna.user.User;
import pl.confitura.jelatyna.user.UserRepository;

@Component
@AllArgsConstructor
public class UserUtils {

    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;
    private final VoucherService voucherService;


    public User createUser(String title) {
        return userRepository.save(new User().setId(title).setName(title));
    }

    public User markArrived(User user) {
        Voucher voucher = voucherService.generateVoucher("");
        ParticipationData data = participationRepository.save(new ParticipationData().setArrivalDate(now()).setVoucher(voucher));
        return userRepository.save(user.setParticipationData(data));
    }
}
