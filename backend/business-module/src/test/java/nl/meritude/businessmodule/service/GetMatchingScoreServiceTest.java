package nl.meritude.businessmodule.service;

import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.matchmodule.exception.MatchException;
import nl.meritude.matchmodule.service.MatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetMatchingScoreServiceTest {

	@Mock
	private MatchService matchingBeanMock;

	@InjectMocks
	private GetMatchingScoreService getMatchingScoreService;

	@Test
	void testMatchingBeanReturnsExpectedResult() throws BusinessException, MatchException {
		// given
		String expectedScore = "match score";
		when(matchingBeanMock.getMatchScore(1L)).thenReturn(expectedScore);

		// when
		String actual = getMatchingScoreService.getMatchingScore(1L);

		// then
		Assertions.assertEquals(expectedScore, actual);
	}

	@Test
	@DisplayName("BusinessException when MatchException is thrown")
	void testGetMatchingScoreThrowsException() throws MatchException {
		// given
		String expectedErrorMessage = "foutje";
		when(matchingBeanMock.getMatchScore(1L)).thenThrow(new MatchException(expectedErrorMessage));

		// when
		Exception exception = assertThrows(BusinessException.class, () -> {
					getMatchingScoreService.getMatchingScore(1L);
				});

		// then
		String actualMessage = exception.getMessage();
		assertEquals(expectedErrorMessage, actualMessage);
	}
}
