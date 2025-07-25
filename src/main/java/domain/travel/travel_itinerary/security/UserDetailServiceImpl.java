package domain.travel.travel_itinerary.security;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.exception.DisabledException;
import domain.travel.travel_itinerary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number: " + username));
        if (user.getActive()) {

            return new org.springframework.security.core.userdetails.User(
                    user.getPhoneNumber(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole().name()))

            );
        } else  throw new DisabledException("Account is locked. Please follow the instructions to unlock!");
    }
}
