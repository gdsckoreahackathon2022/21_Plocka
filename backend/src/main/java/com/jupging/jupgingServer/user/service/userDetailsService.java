//package com.jupging.jupgingServer.user.service;
//
//import com.jupging.jupgingServer.user.domain.User;
////import com.jupging.jupgingServer.user.domain.UserDetailsImpl;
//import com.jupging.jupgingServer.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class userDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        User user = userRepository.findByName(username)
////            .orElseThrow();
////
////        return new UserDetailsImpl(user);
////
////    }
//}
