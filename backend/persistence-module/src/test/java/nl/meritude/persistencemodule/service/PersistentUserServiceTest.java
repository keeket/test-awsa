package nl.meritude.persistencemodule.service;

import nl.meritude.persistencemodule.domain.Employer;
import nl.meritude.persistencemodule.domain.User;
import nl.meritude.persistencemodule.exception.PersistenceException;
import nl.meritude.persistencemodule.repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersistentUserServiceTest {

    @Mock
    private UserRepo userRepoMock;

    @InjectMocks
    private PersistentUserService databaseBean;

    @Test
    void testNewUserReturnsUserId() {
        // given
        User user = getUser();
        User persistedUser = getPersistedUser(1L);
        Long expectedUserId = persistedUser.getId();
        when(userRepoMock.save(user)).thenReturn(persistedUser);

        // when
        Long actualUserId = databaseBean.newUser(user);

        // then
        verify(userRepoMock).save(user);
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void testGetUserByIdReturnsUser() throws PersistenceException {
        // given
        User expectedUser = getPersistedUser(1L);
        Long UserId = expectedUser.getId();
        when(userRepoMock.findById(UserId)).thenReturn(Optional.of(expectedUser));

        // when
        User actualUser = databaseBean.getUser(UserId);

        // then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetUserByIdThrowsPersistenceException() {
        // given
        String expectedErrorMessage = "User does not exist with id: 1";
        when(userRepoMock.findById(1L)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(PersistenceException.class, () -> {
            databaseBean.getUser(1L);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testGetUserByEmailReturnsUser() throws PersistenceException {
        // given
        User expectedUser = getPersistedUser(1L);
        String testEmail = expectedUser.getEmail();
        when(userRepoMock.findByEmail(testEmail)).thenReturn(Optional.of(expectedUser));

        // when
        User actualUser = databaseBean.getUser("email");

        // then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetUserByEmailThrowsPersistenceException() {
        // given
        String testEmail = "email";
        String expectedErrorMessage = "No user found with email: " + testEmail;
        when(userRepoMock.findByEmail(testEmail)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(PersistenceException.class, () -> {
            databaseBean.getUser(testEmail);
        });

        // then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void testUpdateUser() {
        // given
        User user = getPersistedUser(1L);

        // when
        databaseBean.updateUser(user);

        // then
        verify(userRepoMock).save(user);
    }

    private User getUser() {
        User user = new Employer();

        user.setEmail("email");
        user.setPassword("password");
        user.setName("name");
        user.setPhoneNr("0612345678");

        return user;
    }

    private User getPersistedUser(Long userId) {
        User user = getUser();

        user.setId(userId);

        return user;
    }
}
