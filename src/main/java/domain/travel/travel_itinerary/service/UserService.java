package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.user.UserRequestDTO;
import domain.travel.travel_itinerary.dto.user.UserResponseDTO;

import java.util.UUID;

public interface UserService {
    UserResponseDTO findUserByPhoneNumber(String phoneNumber);
    UserResponseDTO getUserById(UUID userId);
    UserResponseDTO addUser(UserRequestDTO userRequestDTO);
    User registerUser(UserRequestDTO userRequestDTO);
    UserResponseDTO putUser(UUID userId, UserRequestDTO userRequestDTO);
    void deleteUser(UUID userId);
}
