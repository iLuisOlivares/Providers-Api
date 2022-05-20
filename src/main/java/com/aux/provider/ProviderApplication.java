package com.aux.provider;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.ProveedorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ProveedorService proveedorService){
		return args -> {
			ProveedorModel proveedor = proveedorService.saveProveedor(
					new ProveedorModel(23175949,"cedula"));
			UsuarioModel usuario = proveedorService.saveUsuario(
					new UsuarioModel(23175949,"luissolivareespdd14@gmail.com", "Miclave", null));
			PerfilModel perfil = proveedorService.savePerfil(
					new PerfilModel(23175949, "Luis", "Olivares", "direccion", 318544760, "Mifoto.com", "descripcion", "mipagina.com",null));


			proveedorService.setPerfilToProveedor(perfil.getId(), proveedor.getId());
			proveedorService.setUsuarioToProveedor(usuario.getEmail(), proveedor.getId());
		};
	}

}
