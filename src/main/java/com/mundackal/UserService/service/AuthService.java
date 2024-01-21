package com.mundackal.UserService.service;

import com.mundackal.UserService.dto.UserResponseDTO;
import com.mundackal.UserService.exception.InvalidTokenException;
import com.mundackal.UserService.exception.PassswordNotMatchingException;
import com.mundackal.UserService.exception.SessionNotFoundException;
import com.mundackal.UserService.exception.UserNotFoundException;
import com.mundackal.UserService.model.Role;
import com.mundackal.UserService.model.Session;
import com.mundackal.UserService.model.SessionStatus;
import com.mundackal.UserService.model.User;
import com.mundackal.UserService.repo.SessionRepository;
import com.mundackal.UserService.repo.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.nio.channels.MulticastChannel;
import java.time.LocalDate;
import java.util.*;

import static com.mundackal.UserService.mapper.UserMapper.UserToUserResponseDTOMapper;

@Service
public class AuthService {
    private SessionRepository sessionRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
    private static SecretKey secretKey = macAlgorithm.key().build();

    public AuthService(SessionRepository sessionRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<List<Session>> getAllSessions(){
        return ResponseEntity.ok(sessionRepository.findAll());
    }

    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<UserResponseDTO> signup(String email, String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user = userRepository.save(user);
        return ResponseEntity.ok(UserToUserResponseDTOMapper(user));
    }
    public void logout(String token, UUID userId) throws SessionNotFoundException {
        Session session = sessionRepository.findByTokenAndUser_Id(token, userId).orElseThrow(()->new SessionNotFoundException("No Session with token("+token+") and user id("+userId+")"));
        session.setToken("");
        session.setStatus(SessionStatus.INACTIVE);
        sessionRepository.save(session);
    }
    public ResponseEntity<UserResponseDTO> login(String email,String password) throws UserNotFoundException, PassswordNotMatchingException {
        User user = userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("No User found with email "+email));
        if(!bCryptPasswordEncoder.matches( password,user.getPassword())){
            throw new PassswordNotMatchingException("Password Doesn't Match");
        }
        Session session = new Session();
        session.setLoginTime(new Date());
        //String token = RandomStringUtils.randomAlphanumeric(20);

        //MacAlgorithm macAlgorithm = Jwts.SIG.HS256;//Algo
        //SecretKey secretKey = macAlgorithm.key().build();//Secret Key

        Map<String,Object> claims = new HashMap<>();
        claims.put("email",user.getEmail());
        claims.put("roles",user.getRoles());
        claims.put("createdAt",new Date());
        claims.put("expiryAt",new Date(LocalDate.now().plusDays(3).toEpochDay()));
        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        session.setToken(token);

        session.setUser(user);
        session.setStatus(SessionStatus.ACTIVE);
        session = sessionRepository.save(session);
        MultiValueMapAdapter headers = new MultiValueMapAdapter<>(new HashMap());
        headers.set(HttpHeaders.SET_COOKIE,session.getToken());
        return new ResponseEntity<>(UserToUserResponseDTOMapper(user),headers, HttpStatus.OK);
    }
    public SessionStatus validate(String token, UUID userId) throws InvalidTokenException {

        Optional<Session> session = sessionRepository.findByTokenAndUser_Id(token,userId);
        if(session.isEmpty() || session.get().equals(SessionStatus.INACTIVE) ){
            throw new InvalidTokenException("Token("+token+") is invalid");
        }

//        Jws<Claims> claims = Jwts.parser().build().parseSignedClaims(token);
//        String email = claims.getPayload().get("email").toString();
//        Set<Role> roles = (Set<Role>) claims.getPayload().get("roles");
//        Date createdAt = (Date)claims.getPayload().get("createdAt");
//        Date expiryAt = (Date) claims.getPayload().get("expiryAt");

        return SessionStatus.ACTIVE;
    }

}
