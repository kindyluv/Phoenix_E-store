package africa.semicolon.phoenix.web.Controllers;


import africa.semicolon.phoenix.data.dto.AppUserRequestDto;
import africa.semicolon.phoenix.data.dto.AppUserResponseDto;
import africa.semicolon.phoenix.service.user.AppUserService;
import africa.semicolon.phoenix.web.Exceptions.BusinessLogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AppUserController   {

    @Autowired
    AppUserService appUserService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody AppUserRequestDto request){
        try{
            AppUserResponseDto response = appUserService.createUser(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (BusinessLogicException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
