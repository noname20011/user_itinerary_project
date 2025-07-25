package domain.travel.travel_itinerary.controller.auth;

import domain.travel.travel_itinerary.config.Translator;
import domain.travel.travel_itinerary.domain.entity.RefreshToken;
import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.auth.AuthRequestDTO;
import domain.travel.travel_itinerary.dto.auth.AuthResponseDTO;
import domain.travel.travel_itinerary.dto.user.UserRequestDTO;
import domain.travel.travel_itinerary.dto.user.UserResponseDTO;
import domain.travel.travel_itinerary.exception.NotFoundException;
import domain.travel.travel_itinerary.helper.base.dto.ResponseData;
import domain.travel.travel_itinerary.repository.UserRepository;
import domain.travel.travel_itinerary.security.JwtProvider;
import domain.travel.travel_itinerary.security.UserDetailServiceImpl;
import domain.travel.travel_itinerary.service.RefreshTokenService;
import domain.travel.travel_itinerary.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailServiceImpl userDetailServiceImpl;

    @PostMapping("/login/")
    public AuthResponseDTO login(@Valid @RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse httpServletResponse) {
        try {
//            Save username, password to validate login and save cache
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDTO.getPhoneNumber(),
                            authRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserResponseDTO user = userService.findUserByPhoneNumber(authRequestDTO.getPhoneNumber());

//            Create accessToken, refreshToken
            String accessToken = jwtProvider.generateAccessToken(userDetails);
            String refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getPhoneNumber()).getToken();

//            Save refreshToken to HttpOnly
            ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                    .secure(true)
                    .httpOnly(true)
                    .path("/auth/")
                    .maxAge(Duration.ofDays(7))
                    .sameSite("Strict")
                    .build();
            httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

            return new AuthResponseDTO(HttpStatus.OK.value(), Translator.toLocale("user.login.success"), user, accessToken);
        } catch (BadCredentialsException ex) {
            throw new DisabledException("Username or password incorrect.");
        }
    }


    @Transactional
    @PostMapping("/register/")
    public AuthResponseDTO register(@Valid @RequestBody UserRequestDTO requestDTO, HttpServletResponse httpServletResponse) {
        try {
            User result = userService.registerUser(requestDTO);
            UserResponseDTO user = userService.findUserByPhoneNumber(result.getPhoneNumber());

//            Save username, password to validate login and save cache
            UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(result.getPhoneNumber());
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //            Create accessToken, refreshToken
            String accessToken = jwtProvider.generateAccessToken(userDetails);
            String refreshToken = refreshTokenService.createRefreshToken(result.getPhoneNumber()).getToken();

//            Save refreshToken to HttpOnly
            ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .secure(true)
                    .path("/auth/")
                    .maxAge(Duration.ofDays(7))
                    .sameSite("Strict")
                    .build();
            httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

            return new AuthResponseDTO(HttpStatus.OK.value(), "Register successfully!", user, accessToken);
        } catch (BadCredentialsException ex) {
            throw new DisabledException("Username or password incorrect.");
        }
    }

    @PostMapping("/refresh/")
    public ResponseData<?> refreshToken(HttpServletRequest request) {
//        Get refreshToken from Cookie;
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        return refreshTokenService.getRefreshToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsername)
                .map(phoneNumber -> {
                    UserDetails userDetails = userRepository.findByPhoneNumber(phoneNumber)
                            .map(user -> new org.springframework.security.core.userdetails.User(
                                    user.getPhoneNumber(),
                                    user.getPassword(),
                                    List.of(new SimpleGrantedAuthority(user.getRole().name()))
                            )).orElseThrow(() -> new UsernameNotFoundException("Phone number not found"));

                    String newAccessToken = jwtProvider.generateAccessToken(userDetails);
                    return new ResponseData<>(HttpStatus.OK.value(), "Refresh Token successfully!", newAccessToken);
                }).orElseThrow(() -> new NotFoundException("Refresh token not found"));

    }

    @PostMapping("/logout/")
    public ResponseData<?> logout(HttpServletRequest request, HttpServletResponse response) {

//        Get refreshToken from Cookie;
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }
        if (refreshToken != null) {
            refreshTokenService.deleteRefreshToken(refreshToken);
        }


//        refreshToken been deleted  by resend expired refreshToken to client
        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/auth/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("user.logout.success"));
    }

}
