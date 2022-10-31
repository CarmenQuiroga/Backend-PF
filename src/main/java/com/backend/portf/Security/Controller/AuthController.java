package com.backend.portf.Security.Controller;



import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.backend.portf.Security.Entity.Rol;
import com.backend.portf.Security.Entity.Usuario;
import com.backend.portf.Security.Enums.RolNombre;
import com.backend.portf.Security.Service.RolService;
import com.backend.portf.Security.Service.UsuarioService;
import com.backend.portf.Security.dto.JwtDto;
import com.backend.portf.Security.dto.LoginUsuario;
import com.backend.portf.Security.dto.NuevoUsuario;
import com.backend.portf.Security.jwt.JwtProvider;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http:localhost:4200")
public class AuthController {

    
   private final PasswordEncoder passwordEncoder;
   private final AuthenticationManagerBuilder authenticationManagerBuilder;
   private final UsuarioService usuarioService;
   private final RolService rolService;
   private final JwtProvider jwtProvider;


   public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder,
          UsuarioService usuarioService, RolService rolService, JwtProvider jwtProvider){
      this.authenticationManagerBuilder = authenticationManagerBuilder;
      this.passwordEncoder = passwordEncoder;
      this.usuarioService = usuarioService;
      this.rolService = rolService;
      this.jwtProvider = jwtProvider;      
          }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
        return new ResponseEntity<>(new Mensaje("campos mal puesto o email invalido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
        return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
        return new ResponseEntity<>(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
      Usuario usuario =
         new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));
      Set<Rol> roles = new HashSet<>();
       roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
       if(nuevoUsuario.getRoles().contains("admin"))
           roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
       usuario.setRoles(roles); 
       usuarioService.save(usuario);  
        return new ResponseEntity<>(new Mensaje("usuario guardado"), HttpStatus.CREATED);        

    }
     
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
           return new ResponseEntity<>(new Mensaje("Revise sus credenciales"),HttpStatus.BAD_REQUEST); 
        
     try{  UsernamePasswordAuthenticationToken autheticationToken = new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword());   
           Authentication authentication= 
          authenticationManagerBuilder.getObject().authenticate(autheticationToken);
          SecurityContextHolder.getContext().setAuthentication(authentication);
          String jwt = jwtProvider.generateToken(authentication);
          JwtDto jwtDto = new JwtDto(jwt);
          return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    } catch (Exception e){
        return new ResponseEntity<>(new Mensaje("Revise sus credenciales"),HttpStatus.BAD_REQUEST);  
    }
}
} 
