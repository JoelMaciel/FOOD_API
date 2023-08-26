package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.request.PasswordRequest;
import br.com.joelmaciel.food.api.dtos.request.UserRequest;
import br.com.joelmaciel.food.api.dtos.request.UserWithPasswordRequest;
import br.com.joelmaciel.food.api.dtos.response.UserDTO;
import br.com.joelmaciel.food.domain.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRegistrationService userRegistrationService;

    @GetMapping
    public List<UserDTO> getAll() {
        return userRegistrationService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getOne(@PathVariable Long userId) {
        return userRegistrationService.findByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO add(@RequestBody @Valid UserWithPasswordRequest userRequest) {
        return userRegistrationService.saveUser(userRequest);
    }

    @PutMapping("/{userId}")
    public UserDTO update(@PathVariable Long userId, @RequestBody @Valid UserRequest userRequest) {
        return userRegistrationService.updateUser(userId, userRequest);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long userId, @RequestBody @Valid PasswordRequest passwordRequest) {
        userRegistrationService.changePassword(userId, passwordRequest.getCurrentPassword(), passwordRequest.getNewPassword());
    }
}
