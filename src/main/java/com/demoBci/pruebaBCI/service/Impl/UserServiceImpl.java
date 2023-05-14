package com.demoBci.pruebaBCI.service.Impl;

import com.demoBci.pruebaBCI.config.JwtService;
import com.demoBci.pruebaBCI.dao.PhonesDao;
import com.demoBci.pruebaBCI.dao.UserDao;
import com.demoBci.pruebaBCI.dto.*;
import com.demoBci.pruebaBCI.entity.Role;
import com.demoBci.pruebaBCI.entity.Phones;
import com.demoBci.pruebaBCI.entity.User;
import com.demoBci.pruebaBCI.service.IuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IuserService {
    @Autowired
    UserDao userDao;
    @Autowired
    PhonesDao phonesDao;

    private final UserDao repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${password.regex.regexp}")
    private String passwordRegex;

    @Value("${email.regex.regexp}")
    private String emailRegex;

    public ResponseEntity<MessageApiDto> create(UserDto userDto){

        Phones phones = new Phones();
        MessageApiDto messageApiDto = new MessageApiDto();
        UserApiDto userApiDto = new UserApiDto();

        try {

            boolean validateEmail = validateEmail(userDto.getEmail());
            boolean validatePassword = validatePassword(userDto.getPassword());

            if(!validateEmail){
                messageApiDto.setMessage("The email entered is invalid.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageApiDto);
            }

            if(!validatePassword){
                messageApiDto.setMessage("the password is not in the correct format.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageApiDto);
            }

            Date createDate = new Date();

            Optional<User>  currentUser = userDao.findByEmail(userDto.getEmail());

            if(currentUser.isPresent()){
                messageApiDto.setMessage("Email already exists");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageApiDto);
            }

            var user = User.builder()
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .created(createDate)
                    .last_login(createDate)
                    .isactive(true)
                    .role(Role.ADMIN)
                    .build();

            repository.save(user);

            for (PhoneDto telefono : userDto.getPhones()) {
                phones.setNumber(telefono.getNumber());
                phones.setCitycode(telefono.getCitycode());
                phones.setContrycode(telefono.getContrycode());
                phones.setUserId(user.getId().toString());

                phonesDao.save(phones);
            }

            var jwtToken = jwtService.generateToken(user);

            Optional<User> userDataBase = userDao.findById(user.getId());

            User userToken = userDataBase.get();

            userToken.setToken(jwtToken);

            repository.save(userToken);

            userApiDto.setName(user.getName());
            userApiDto.setEmail(user.getEmail());
            userApiDto.setPassword(user.getPassword());
            userApiDto.setPhones(userDto.getPhones());
            userApiDto.setId(user.getId().toString());
            userApiDto.setCreated(user.getCreated());
            userApiDto.setModified(user.getModified());
            userApiDto.setLast_login(user.getLast_login());
            userApiDto.setToken(userToken.getToken());
            userApiDto.setIsactive(user.getIsactive());
            userApiDto.setMessage("User Created.");

            return ResponseEntity.ok(userApiDto);
        }catch (Exception e){
            return null;
        }


    }



    public ResponseEntity<MessageApiDto> update(UserDto userDto, String userID, String token){

        try {

            MessageApiDto messageApiDto = new MessageApiDto();
            UserApiDto userApiDto = new UserApiDto();

            String tokenBearer = token.substring(7);

            Date modificationDate = new Date();

            UUID uid = UUID.fromString(userID);

            Optional<User> userDataBase = userDao.findById(uid);


            if(userDataBase.isPresent()){
                User currentUser = userDataBase.get();

                if(!tokenBearer.equals(currentUser.getToken())) {
                    messageApiDto.setMessage("The entered token is not authorized.");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(messageApiDto);
                }

                Optional<User> emailDataBase = userDao.findByEmail(userDto.getEmail());

                if(emailDataBase.isPresent() ){

                    User userEmailDataBase = emailDataBase.get();

                    if(!Objects.equals(userEmailDataBase.getId().toString(), currentUser.getId().toString())){
                        messageApiDto.setMessage("The email entered is already registered.");
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(messageApiDto);
                    }

                }

                boolean validateEmail = validateEmail(userDto.getEmail());

                if(!validateEmail){
                    messageApiDto.setMessage("The email is not in the correct format.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageApiDto);
                }

                boolean validatePassword = validatePassword(userDto.getPassword());

                if(!validatePassword){
                    messageApiDto.setMessage("the password is not in the correct format.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageApiDto);
                }

                currentUser.setName(userDto.getName());
                currentUser.setEmail(userDto.getEmail());
                currentUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
                currentUser.setModified(modificationDate);
                currentUser.setLast_login(modificationDate);

                repository.save(currentUser);

                List<Phones> phonesList = phonesDao.findByUserId(userID);

                for (Phones phone : phonesList) {

                    phonesDao.deleteById(phone.getId());
                }

                Phones phones = new Phones();

                for (PhoneDto phone : userDto.getPhones()) {
                    phones.setNumber(phone.getNumber());
                    phones.setCitycode(phone.getCitycode());
                    phones.setContrycode(phone.getContrycode());
                    phones.setUserId(userID);


                    phonesDao.save(phones);
                }

                var jwtToken = jwtService.generateToken(currentUser);

                userApiDto.setName(currentUser.getName());
                userApiDto.setEmail(currentUser.getEmail());
                userApiDto.setPassword(currentUser.getPassword());
                userApiDto.setPhones(userDto.getPhones());
                userApiDto.setId(currentUser.getId().toString());
                userApiDto.setCreated(currentUser.getCreated());
                userApiDto.setModified(currentUser.getModified());
                userApiDto.setLast_login(currentUser.getLast_login());
                userApiDto.setToken(jwtToken);
                userApiDto.setIsactive(currentUser.getIsactive());
                userApiDto.setMessage("Updated User");

            }else{
                messageApiDto.setMessage("User not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageApiDto);
            }
            return ResponseEntity.ok(userApiDto);

        }catch (Exception e) {

            return null;

        }

    }

    public boolean validateEmail(String email) {

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public boolean validatePassword(String password) {

        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
