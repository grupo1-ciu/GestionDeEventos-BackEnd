package ciu.grupo1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ciu.grupo1.model.Usuario;
import ciu.grupo1.repository.UsuarioRepository;
import ciu.grupo1.security.UserInfoDetails;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userDetail = usuarioRepository.findByEmail(username); // Asumiendo que el 'email' es usado como nombre de usuario.

        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public String addUser(Usuario usuario) {
        // Encode password before saving the user
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return "User Added Successfully";
    }
}