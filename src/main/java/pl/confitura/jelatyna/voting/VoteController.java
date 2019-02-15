package pl.confitura.jelatyna.voting;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import pl.confitura.jelatyna.infrastructure.WebUtils;
import pl.confitura.jelatyna.presentation.PresentationRepository;

@RepositoryRestController
public class VoteController {

    private PresentationRepository presentationRepository;
    private VoteRepository voteRepository;
    private WebUtils webUtils;
    private LocalValidatorFactoryBean beanValidator;

    @Autowired
    public VoteController(PresentationRepository presentationRepository, VoteRepository voteRepository,
                          WebUtils webUtils, LocalValidatorFactoryBean beanValidator) {
        this.presentationRepository = presentationRepository;
        this.voteRepository = voteRepository;
        this.webUtils = webUtils;
        this.beanValidator = beanValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(beanValidator);
    }

    @RequestMapping(value = "/votes/start/{token}", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<Resources<?>> start(@PathVariable String token) {
        var votes = voteRepository.findAllForToken(token);
        if (votes.isEmpty()) {
            votes = generateVotes(token);
        }
        return ResponseEntity.ok(new Resources<>(votes));
    }

    private Set<Vote> generateVotes(@PathVariable String token) {
        var presentations = Lists.newArrayList(this.presentationRepository.findAllForV4p());
        Collections.shuffle(presentations);
        var votes = IntStream.range(0, presentations.size())
                .mapToObj(idx -> new Vote()
                        .setClient(webUtils.getClientIp())
                        .setToken(token)
                        .setPresentation(presentations.get(idx))
                        .setOrder(idx))
                .collect(toList());
        return this.voteRepository.saveAll(votes);
    }

    @PostMapping("/votes")
    @Transactional
    public ResponseEntity<Resource<Vote>> save(@RequestBody @Valid Vote vote) {
        var loaded = voteRepository.findById(vote.getId());
        loaded.setRate(vote.getRate());
        loaded.setVoteDate(LocalDateTime.now());
        return ResponseEntity.ok(new Resource<>(loaded));
    }


    @PreAuthorize("@security.isAdmin()")
    @GetMapping("/votes/statistics")
    @ResponseBody
    @Transactional
    public List<PresentationStats> statistics() {
        var allVotes = voteRepository.findAll();
        var votesStream = StreamSupport.stream(allVotes.spliterator(), true);

        var statsByPresentation = votesStream
                .map(PresentationStats::new)
                .collect(Collectors.groupingBy(PresentationStats::getPresentationId,
                        Collectors.reducing(PresentationStats::add)));

        return statsByPresentation.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(Comparator.comparing(PresentationStats::getPositiveVotes))
                .collect(toList());

    }
}
