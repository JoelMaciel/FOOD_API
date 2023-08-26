package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.request.UserRequest;
import br.com.joelmaciel.food.api.dtos.request.UserWithPasswordRequest;
import br.com.joelmaciel.food.api.dtos.response.UserDTO;
import br.com.joelmaciel.food.domain.exception.BusinessException;
import br.com.joelmaciel.food.domain.exception.UserNotFoundException;
import br.com.joelmaciel.food.domain.model.User;
import br.com.joelmaciel.food.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserRegistrationService {

    public static final String MSG_ERROR_PASSWORD = "Current password entered does not match the user's password.";
    private final UserRepository userRepository;

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDTO::toDTO)
                .toList();
    }

    public UserDTO findByUserId(Long userId) {
        User user = searchByUserId(userId);
        return UserDTO.toDTO(user);
    }

    @Transactional
    public UserDTO saveUser(UserWithPasswordRequest userRequest) {
        User user = UserWithPasswordRequest.toEntity(userRequest);
        validateEmail(user);
        return UserDTO.toDTO(userRepository.save(user));
    }

    @Transactional
    public UserDTO updateUser(Long userId, UserRequest userRequest) {
        User user = searchByUserId(userId).toBuilder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();

        validateEmail(user);
        return UserDTO.toDTO(userRepository.save(user));
    }

    @Transactional
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = searchByUserId(userId);

        if (user.passwordDoesNotMatch(currentPassword)) {
            throw new BusinessException(MSG_ERROR_PASSWORD);
        }
        user.setPassword(newPassword);
    }

    public void validateEmail(User userRequest) {
        Optional<User> existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent() && !existingUser.get().equals(userRequest)) {
            throw new BusinessException(
                    String.format("A user with email %s already exists", userRequest.getEmail()));
        }
    }


    public User searchByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
