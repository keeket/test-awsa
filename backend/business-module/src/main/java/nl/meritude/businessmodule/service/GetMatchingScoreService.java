package nl.meritude.businessmodule.service;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.matchmodule.exception.MatchException;
import nl.meritude.matchmodule.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetMatchingScoreService {

    @Autowired
    private MatchService matchingBean;

    public String getMatchingScore(Long vacancyId) throws BusinessException {
        // hier
        try {
            log.info("Get Matches request for id: {}", vacancyId);
            String score = matchingBean.getMatchScore(vacancyId);
            log.info("Score '{}' retrieved for id '{}'", score, vacancyId);
            return score;
        } catch (MatchException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
