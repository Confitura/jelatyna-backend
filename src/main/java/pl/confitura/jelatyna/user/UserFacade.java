package pl.confitura.jelatyna.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFacade {
    private final UserRepository userRepository;

    public boolean existsUserWithSocialId(String socialId) {
        return userRepository.existsBySocialId(socialId);
    }

    public pl.confitura.jelatyna.user.dto.User createUser(pl.confitura.jelatyna.user.dto.User dtoUser) {
        if (dtoUser != null) {
            User user = User.fromDto(dtoUser);
            return userRepository.save(user).toDto();
        } else {
            return null;
        }
    }

    public pl.confitura.jelatyna.user.dto.User findBySocialId(String socialId) {
        User user = userRepository.findBySocialId(socialId);
        return user == null ? null : user.toDto();
    }

    public pl.confitura.jelatyna.user.dto.User findById(String id) {
        User user = userRepository.findById(id);
        return user == null ? null : user.toDto();
    }

    public void update(pl.confitura.jelatyna.user.dto.User user) {
        userRepository.save(User.fromDto(user));
    }
}
