package br.com.anibal.armory.springjwt.controller;

import br.com.anibal.armory.springjwt.model.AuthenticationRequest;
import br.com.anibal.armory.springjwt.model.AuthenticationResponse;
import br.com.anibal.armory.springjwt.services.MyUserDetailsService;
import br.com.anibal.armory.springjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    public static final String AUTHENTICATION_URL = "/authenticate";

    @PostMapping(AUTHENTICATION_URL)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().build();
        }

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }
}
