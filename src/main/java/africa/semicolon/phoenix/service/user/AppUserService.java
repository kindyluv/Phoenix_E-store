package africa.semicolon.phoenix.service.user;

import africa.semicolon.phoenix.data.dto.AppUserRequestDto;
import africa.semicolon.phoenix.data.dto.AppUserResponseDto;
import africa.semicolon.phoenix.web.Exceptions.BusinessLogicException;

public interface AppUserService {
    AppUserResponseDto createUser(AppUserRequestDto request) throws BusinessLogicException;
}
