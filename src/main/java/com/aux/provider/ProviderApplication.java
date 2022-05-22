package com.aux.provider;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.PerfilService;
import com.aux.provider.services.ProveedorService;
import com.aux.provider.services.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	CommandLineRunner run(ProveedorService proveedorService, UsuarioService usuarioService, PerfilService perfilService){
		return args -> {
			ProveedorModel proveedor = proveedorService.saveProveedor(
					new ProveedorModel(1005569145,"cedula"));
			UsuarioModel usuario = usuarioService.saveUsuario(
					new UsuarioModel(1005569145,"luiss@gmail.com", "1234", null));
			PerfilModel perfil = perfilService.savePerfil(
					new PerfilModel(1005569145, "Sebastian", "Puello", "direccion", 318544760, "Mifoto.com", "descripcion", "mipagina.com",null));


			proveedorService.setPerfilToProveedor(perfil.getId(), proveedor.getId());
			proveedorService.setUsuarioToProveedor(usuario.getEmail(), proveedor.getId());
		};
	}

}
