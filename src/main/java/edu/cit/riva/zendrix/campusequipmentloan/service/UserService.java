//package edu.cit.riva.zendrix.campusequipmentloan.service;
//
//import edu.cit.riva.zendrix.campusequipmentloan.model.User;
//import edu.cit.riva.zendrix.campusequipmentloan.repository.UserRepository;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User processOAuthPostLogin(String username, String email, String provider) {
//        Optional<User> existUser = userRepository.findByEmail(email);
//
//        if (existUser.isEmpty()) {
//            User newUser = new User();
//            newUser.setUsername(username);
//            newUser.setEmail(email);
//            newUser.setProvider(provider);
//            HashSet<String> roles = new HashSet<>();
//            roles.add("ROLE_USER");
//            newUser.setRoles(roles);
//
//            return userRepository.save(newUser);
//        }
//
//        return existUser.get();
//    }
//}
