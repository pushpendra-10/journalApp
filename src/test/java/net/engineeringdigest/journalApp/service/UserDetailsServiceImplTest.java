package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;


public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUserameTest(){
        when(userRepository.findByUserName(anyString())).thenReturn(User.builder()
                .userName("ram")
                .Password("fneageagersg")
                .roles(new ArrayList<>())
                .build()
        );
        UserDetails user = userDetailsService.loadUserByUsername("ram");
    }

}
