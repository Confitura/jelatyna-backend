package pl.confitura.jelatyna.registration.voucher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class VoucherService {

    private final VoucherRepository voucherRepository;

    public Voucher generateVoucher(String originalBuyer, String createdBy) {
        return voucherRepository.save(new Voucher()
                .setCreationDate(now()))
                .setOriginalBuyer(originalBuyer)
                .setCreatedBy(createdBy);
    }

    public boolean isValid(Voucher voucher) {
        if (voucher == null || voucher.getId() == null) {
            return false;
        } else {
            return voucherRepository.existsById(voucher.getId());
        }
    }

    public List<Voucher> findUnusedVouchers() {
        return voucherRepository.findUnusedVouchers();
    }
}
