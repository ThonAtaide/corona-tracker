package com.ataide.corona.coronatracker.domain.service;

import com.ataide.corona.coronatracker.application.dtos.*;
import com.ataide.corona.coronatracker.application.dtos.response.ResponseData;
import com.ataide.corona.coronatracker.application.dtos.response.ResponseError;
import com.ataide.corona.coronatracker.application.dtos.response.Response;
import com.ataide.corona.coronatracker.application.exceptions.*;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.ataide.corona.coronatracker.domain.entities.User;
import com.ataide.corona.coronatracker.domain.entities.UserType;
import com.ataide.corona.coronatracker.domain.repository.UserRepository;
import com.ataide.corona.coronatracker.domain.service.interfaces.SecurityService;
import com.ataide.corona.coronatracker.domain.service.interfaces.StoreService;
import com.ataide.corona.coronatracker.domain.service.interfaces.UserService;
import com.ataide.corona.coronatracker.domain.util.ServerUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;
    private final StoreService storeService;
    private final SecurityService securityService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Response registerUser(UserRegisterDto userRegisterDto) throws UsernameAllocatedException, MissingRequiredParameterException, ConstraintViolationsException {
        logger.info("registerUser called - {}", userRegisterDto);

        ServerUtil.validConstraintViolations(userRegisterDto);
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String encryptedPassword = securityService.encrypt(password);
        ProfileType userData = userRegisterDto.getUserType();
        Object entityCreated = null;

        if (repository.findByUsernameLike(username).isPresent()) throw new UsernameAllocatedException("Este username já está em uso.");

        User.UserBuilder userBuilder = User.builder()
                .username(username)
                .password(encryptedPassword);

        if (userData instanceof StoreDto) {
            User user = saveUser(userBuilder, UserType.STORE.toString());

            entityCreated = storeService.createStore((StoreDto) userData, user);
        }

        if (entityCreated == null) {
            return ResponseError.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .errors(List.of("Houve um erro e o usuário não pode ser criado."))
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        return ResponseData.builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    private User saveUser(User.UserBuilder userBuilder, String userType) {
        userBuilder.role(userType);
        return repository.save(userBuilder.build());
    }

    @Override
    public Response login(UserLoginDto userCredentials) throws ConstraintViolationsException, EntityNotFoundException {
        logger.info("login method called");

        ServerUtil.validConstraintViolations(userCredentials);
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();

        Optional<User> optionalUser = repository.findByUsernameLike(username);
        if (optionalUser.isEmpty()) return unauthorizedResponse();

        String cipherPassword = securityService.encrypt(password);
        Boolean authenticate = securityService.authenticate(cipherPassword, optionalUser.get().getPassword());

        if (!authenticate) return unauthorizedResponse();

        UserSessionDto userSession = getUserInfo(optionalUser.get());
        String token = securityService.getToken(userSession.toString());
        userSession.setJwtToken(token);

        return ResponseData.builder()
                .statusCode(HttpStatus.OK.value())
                .data(userSession)
                .timestamp(LocalDateTime.now())
                .build();
    }

    private UserSessionDto getUserInfo(User user) throws EntityNotFoundException {
        UserSessionDto.UserSessionDtoBuilder builder = UserSessionDto.builder();

        if (user.getRole().equals(UserType.STORE.toString())) {
            Store store = storeService.getStoreByUser(user.getId());
            validateRelatedEntity(store);
            builder
                    .id(store.getId())
                    .name(store.getName());
        }
        return builder.build();
    }

    private void validateRelatedEntity(Object object) throws EntityNotFoundException {
        if (object == null) throw new EntityNotFoundException("Falha no login.");
    }

    private Response unauthorizedResponse() {
        return ResponseError.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .errors(List.of("Usuário ou senha inválidos."))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
