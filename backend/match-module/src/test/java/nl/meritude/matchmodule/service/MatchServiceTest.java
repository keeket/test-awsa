package nl.meritude.matchmodule.service;

import nl.meritude.matchmodule.exception.MatchException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Test
    void testGetMatchScoreReturnsScore() throws MatchException {
        // when
        String actualScore = matchService.getMatchScore(1L);

        // then
        assertEquals("Ja leuk", actualScore);
    }
}