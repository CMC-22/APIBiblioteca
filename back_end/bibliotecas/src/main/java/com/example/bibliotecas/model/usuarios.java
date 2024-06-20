package com.example.bibliotecas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name="usuarios")
public class usuarios {
		
		@Id
		@GeneratedValue(strategy = GenerationType.UUID)
		@Column(name = "id_usarios", nullable = false, length = 36)
		private String id_usuarios;
		
		@Column(name = "nombre", nullable = false, length = 50)
		private String nombre;
		
		@Column(name = "direccion", nullable = false, length = 50)
		private String direccion;
		
		@Column(name = "correo", nullable = false, length = 50)
		private String correo;
		
		@Column(name = "tipo_usario", nullable = true, length = 50)
		private String tipo_usuario;

		public usuarios() {
			super();
		}

		public usuarios(String id_usuarios, String nombre, String direccion, String correo, String tipo_usuario) {
			super();
			this.id_usuarios = id_usuarios;
			this.nombre = nombre;
			this.direccion = direccion;
			this.correo = correo;
			this.tipo_usuario = tipo_usuario;
		}

		public String getId_usuarios() {
			return id_usuarios;
		}

		public void setId_usuarios(String id_usuarios) {
			this.id_usuarios = id_usuarios;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public String getTipo_usuario() {
			return tipo_usuario;
		}

		public void setTipo_usuario(String tipo_usuario) {
			this.tipo_usuario = tipo_usuario;
		}
		
		
		/*sirve para no dejar que se coloquen las ""*/
		public boolean contieneCamposVacios() {
			return nombre.isEmpty() || direccion.isEmpty() || correo.isEmpty() ||
					tipo_usuario.isEmpty();
		}
	

}
