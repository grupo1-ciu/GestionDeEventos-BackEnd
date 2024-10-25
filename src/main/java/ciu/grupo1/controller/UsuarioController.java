package ciu.grupo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ciu.grupo1.dto.UsuarioDto;
import ciu.grupo1.dto.UsuarioLoginDto;
import ciu.grupo1.request.AuthRequest;
import ciu.grupo1.security.UserInfoDetails;
import ciu.grupo1.service.JwtService;
import ciu.grupo1.service.UsuarioService;


@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/usuarios")
    public String addNewUser(@RequestBody UsuarioDto usuarioDto) {
    	return usuarioService.addUser(usuarioDto);
    }

    @GetMapping("/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
    
//    @PostMapping("/login")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public List<String> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
//    	return null;
//    }

    @PostMapping("/generateToken")
    public UsuarioLoginDto authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws UsernameNotFoundException {
    	UsuarioLoginDto usuarioLoginDto = this.usuarioService.getUsuarioLoginDtoWithRolesRol(authRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        
        if (authentication.isAuthenticated()) {
        	String token = jwtService.generateToken(authRequest.getUsername());
        	usuarioLoginDto.setToken(token);
        	return usuarioLoginDto;
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
