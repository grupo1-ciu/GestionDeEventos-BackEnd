package ciu.grupo1.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ciu.grupo1.dto.UsuarioLoginDto;
import ciu.grupo1.dto.UsuarioRegistroDto;
import ciu.grupo1.model.Rol;
import ciu.grupo1.model.TipoRol;
import ciu.grupo1.model.Usuario;
import ciu.grupo1.model.UsuarioRol;
import ciu.grupo1.repository.RolRepository;
import ciu.grupo1.repository.UsuarioRepository;
import ciu.grupo1.repository.UsuarioRolRepository;
import ciu.grupo1.security.UserInfoDetails;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserInfoDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Optional <Usuario> usuarioDetalles = usuarioRepository.findWithUsuariosRolesRolByEmail(username); // Asumiendo que el 'email' es usado como nombre de usuario.

        return usuarioDetalles.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    public List<Usuario> getUsuarios(){
    	return this.usuarioRepository.findAll();
    }

    public String addUser(UsuarioRegistroDto usuarioDto) {
    	Usuario usuario = usuarioDto.toModel(true);

        usuario.setPassword(encoder.encode(usuario.getPassword()));
        Rol rol = this.rolRepository.findByNombre(TipoRol.ROLE_USER);
        System.out.println(usuario.getId());
        usuarioRepository.save(usuario);
        this.crearYGuardarUsuarioRol(rol, usuario);
        
        return "El usuario fue creado!";
    }
    
    @Transactional(readOnly = true)
    public Usuario getUsuarioWithRoles(String email) {
    	return this.usuarioRepository.findWithUsuarioRolesByEmail(email);
    }
    
    @Transactional(readOnly = true)
    public Usuario getUsuarioWithInscripciones(String email) {
    	return this.usuarioRepository.findWithInscripcionesByEmail(email);
    }
    
    @Transactional(readOnly = true)
    public UsuarioLoginDto getUsuarioLoginDtoWithRolesRol(String email) {
    	UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto();
    	Optional<Usuario> usuario = this.usuarioRepository.findWithUsuariosRolesRolByEmail(email);
    
    	List<String> roles = (usuario.get().getUsuarioRoles().stream()
    							.map(ur -> ur.getRol().getNombre().toString())
    							.collect(Collectors.toList()));
    	
    	usuarioLoginDto.setNombre(usuario.get().getNombre());
    	usuarioLoginDto.setApellido(usuario.get().getApellido());
    	usuarioLoginDto.setRoles(roles);
    	
    	return usuarioLoginDto;
    }
    
    public List<String> getRolesByUsuarioEmail(String email) {
    	UsuarioLoginDto usuarioLoginDto = this.getUsuarioLoginDtoWithRolesRol(email);
    	return usuarioLoginDto.getRoles();
    }
    

    public Usuario obtenerUsuarioPorId(UUID idUsuario) {
        return usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + idUsuario));
    }
    
    private void crearYGuardarUsuarioRol(Rol rol, Usuario usuario) {
    	UsuarioRol usuarioRol = new UsuarioRol();
    	usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        usuario.getUsuarioRoles().add(usuarioRol);
        usuarioRolRepository.save(usuarioRol);
    }
}