package pl.confitura.jelatyna.infrastructure.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.extern.slf4j.Slf4j;
import pl.confitura.jelatyna.presentation.PresentationRepository;

@Component
@RequestScope
@Slf4j
public class Security {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PresentationRepository presentationRepository;

    public boolean isOwner(String userId) {
        return userId.equals(getUserId());
    }

    public boolean presentationOwnedByUser(String presentationId) {
        String ownerId = presentationRepository.findOne(presentationId).getSpeaker().getId();
        return ownerId.equals(getUserId());
    }

    private JelatynaPrincipal getPrincipal() {
        return (JelatynaPrincipal) ((Authentication) request.getUserPrincipal()).getPrincipal();
    }

    private String getUserId() {
        return getPrincipal().getId();
    }
}
