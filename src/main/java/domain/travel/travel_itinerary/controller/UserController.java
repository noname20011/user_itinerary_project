package domain.travel.travel_itinerary.controller;

import domain.travel.travel_itinerary.dto.user.UserRequestDTO;
import domain.travel.travel_itinerary.dto.user.UserResponseDTO;
import domain.travel.travel_itinerary.helper.base.dto.ResponseData;
import domain.travel.travel_itinerary.helper.validators.uuid_validation.ValidUUID;
import domain.travel.travel_itinerary.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/")
    public ResponseData<UserResponseDTO> postUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO data = userService.addUser(userRequestDTO);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Create successfully!", data);
    }

    @PutMapping("/{userId}")
    public ResponseData<UserResponseDTO> putUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID userId) {
        UserResponseDTO data = userService.putUser(userId, userRequestDTO);
        return new ResponseData<>(HttpStatus.OK.value(), "Update successfully!", data);
    }

    @PatchMapping("/{userId}")
    public ResponseData<UserResponseDTO> patchUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @ValidUUID(message = "Id invalid uuid type!") @PathVariable UUID userId) {
        UserResponseDTO data = userService.putUser(userId, userRequestDTO);
        return new ResponseData<>(HttpStatus.OK.value(), "Update successfully!", data);
    }

    @GetMapping("/phone")
    public ResponseData<UserResponseDTO> getUserByPhoneNumber(
            @Pattern( regexp = "^(0|\\+84)[3|5|7|9][0-9]{8}$", message = "Phone number not valid!")
            @Valid @RequestParam("phoneNumber") String phoneNumber) {
        UserResponseDTO data = userService.findUserByPhoneNumber(phoneNumber);
        return new ResponseData<>(HttpStatus.OK.value(), "Get user successfully!", data);
    }

    @GetMapping("/id")
    public ResponseData<UserResponseDTO> getUserById (@Valid @RequestParam("id") UUID id) {
        UserResponseDTO data = userService.getUserById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get user successfully!", data);
    }

    @DeleteMapping("/{userId}")
    public ResponseData<UserResponseDTO> deleteUser (@ValidUUID @PathVariable UUID userId) {
        userService.deleteUser(userId);
        return new ResponseData<>(HttpStatus.OK.value(), "Delete user successfully!");
    }
}
