package africa.semicolon.phoenix.security;

import africa.semicolon.phoenix.data.models.AppUser;
import africa.semicolon.phoenix.data.models.Authority;
import africa.semicolon.phoenix.data.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RDBUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        query for user details from db
        AppUser user = appUserRepository.findByEmail(username).orElse(null);
//        check that user exists
        if (user == null) throw new UsernameNotFoundException("Username does not exist");
//        return UserDetails
        return new User(user.getEmail(), user.getPassword(), getAuthorities(user.getAuthorities()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> {
                    return new SimpleGrantedAuthority(authority.name());
                }).collect(Collectors.toList());
    }
}
