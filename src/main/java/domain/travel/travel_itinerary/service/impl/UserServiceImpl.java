package domain.travel.travel_itinerary.service.impl;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.auth.AuthRequestDTO;
import domain.travel.travel_itinerary.dto.user.UserRequestDTO;
import domain.travel.travel_itinerary.dto.user.UserResponseDTO;
import domain.travel.travel_itinerary.exception.DuplicateResourceException;
import domain.travel.travel_itinerary.exception.NotFoundException;
import domain.travel.travel_itinerary.mapper.UserMapper;
import domain.travel.travel_itinerary.repository.UserRepository;
import domain.travel.travel_itinerary.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    //    Begin Methods
    @Override
    public UserResponseDTO findUserByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException("User not found!"));

        return userMapper.mapToResponseDto(user);
    }

    @Override
    public UserResponseDTO getUserById(UUID userId) {
        User user = userRepository.findByIdOrThrow(userId);
        log.info("getUserById {} ", user.getId());
        return userMapper.mapToResponseDto(user);
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByPhoneNumber(userRequestDTO.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User user = userMapper.mapToEntity(userRequestDTO);
        User saved = userRepository.save(user);
        UserResponseDTO userResponseDTO = userMapper.mapToResponseDto(saved);

        log.info("Add user successfully!  {} ", userResponseDTO);
        return userResponseDTO;
    }

    @Override
    public User registerUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByPhoneNumber(userRequestDTO.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User user = userMapper.mapToEntity(userRequestDTO);

        log.info("Register successfully! ");

        return userRepository.save(user);
    }

    @Override
    public UserResponseDTO putUser(UUID userId, UserRequestDTO userRequestDTO) {
        User saved = userRepository.update(userId, userMapper.mapToEntity(userRequestDTO));
        UserResponseDTO userResponseDTO = userMapper.mapToResponseDto(saved);

        log.info("Register user successfully!  {} ", userResponseDTO);
        return userResponseDTO;
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.delete(userId);
        log.info("Delete user successfully!  {} ", userId);
    }
}
