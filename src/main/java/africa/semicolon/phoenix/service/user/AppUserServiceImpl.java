package africa.semicolon.phoenix.service.user;

import africa.semicolon.phoenix.data.dto.AppUserRequestDto;
import africa.semicolon.phoenix.data.dto.AppUserResponseDto;
import africa.semicolon.phoenix.data.models.AppUser;
import africa.semicolon.phoenix.data.repository.AppUserRepository;
import africa.semicolon.phoenix.web.Exceptions.BusinessLogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public AppUserResponseDto createUser(AppUserRequestDto request) throws BusinessLogicException {
        //check that user doest exist
        Optional<AppUser> savedUser = appUserRepository.findByEmail(request.getEmail());
        //create an app user object
        if (savedUser.isPresent()) throw new BusinessLogicException("User with email already exists");
        AppUser newUser = new AppUser();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setAddress(request.getAddress());
        //save object
        appUserRepository.save(newUser);
        //return response
        return null;
    }

    private AppUserResponseDto buildResponse(AppUser appUser){
        return AppUserResponseDto.builder()
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .address(appUser.getAddress())
                .build();

    }
}
