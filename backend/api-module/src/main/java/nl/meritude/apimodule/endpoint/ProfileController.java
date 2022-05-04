package nl.meritude.apimodule.endpoint;

import lombok.extern.slf4j.Slf4j;
import nl.meritude.apimodule.model.GenericResponse;
import nl.meritude.apimodule.model.PasswordRequest;
import nl.meritude.apimodule.model.ProfileRequest;
import nl.meritude.apimodule.model.UserProfileResponse;
import nl.meritude.apimodule.util.JwtService;
import nl.meritude.businessmodule.exception.BusinessException;
import nl.meritude.businessmodule.service.UserService;
import nl.meritude.persistencemodule.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class ProfileController {
    @Autowired
    private UserService userBean;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/profile")
    @CrossOrigin
    public GenericResponse updateProfile(HttpServletRequest req, @RequestBody ProfileRequest body) {
        log.info("Starting profile updating");
        Long userId = jwtService.getUserId(req);
        log.info("JWT read successfully");
        try {
            User user = userBean.getUser(userId);
            user.setName(body.getName());
            user.setPhoneNr(body.getPhoneNr());
            userBean.updateUser(user);
            return new GenericResponse(105, "Edited successfully");
        } catch(BusinessException be) {
            return new GenericResponse(405, "Invalid action");
        }
    }

    @GetMapping("/profile")
    @CrossOrigin
    public GenericResponse getProfile(HttpServletRequest req) {
        Long userId = jwtService.getUserId(req);
        try {
            User user = userBean.getUser(userId);
            return new GenericResponse(205, new UserProfileResponse(userId, user.getEmail(), user.getName(), user.getPhoneNr()));
        } catch(BusinessException be) {
            return new GenericResponse(405, "Invalid action");
        }
    }

    @PostMapping("/password")
    @CrossOrigin
    public GenericResponse updatePassword(HttpServletRequest req, @RequestBody PasswordRequest body) {
        Long userId = jwtService.getUserId(req);
        try {
            User user = userBean.getUser(userId);
            userBean.login(user.getEmail(), body.getOldPass());
            user.setPassword(body.getNewPass());
            userBean.updateUser(user);
            return new GenericResponse(105, "Edited successfully");
        } catch(BusinessException be) {
            return new GenericResponse(405, "Invalid action");
        }
    }
}
