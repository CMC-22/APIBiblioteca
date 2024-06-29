let url = "http://localhost:8080/api/v1/usuario/";


document.addEventListener("DOMContentLoaded", function () {
  const nombre = document.getElementById("nombre");
  const direccion = document.getElementById("direccion");
  const correo = document.getElementById("correo");

  if (nombre) nombre.addEventListener("keypress", soloLetras);
  if (direccion) direccion.addEventListener("keypress", validarDireccion);
  if (correo) correo.addEventListener("keypress", validarCorreo);
});

function soloLetras(event) {
  const letrasPermitidas = [
    'A', 'Á', 'B', 'C', 'D', 'E', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M',
    'N', 'Ñ', 'O', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z',
    'a', 'á', 'b', 'c', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm',
    'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z', ' '
  ];
  const key = event.key;

  if (!letrasPermitidas.includes(key)) {
    event.preventDefault();
  }
}

function validarDireccion(event) {
  // Puedes permitir letras, números, espacios y otros caracteres comunes en direcciones
  const caracteresPermitidos = [
    'A', 'Á', 'B', 'C', 'D', 'E', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M',
    'N', 'Ñ', 'O', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z',
    'a', 'á', 'b', 'c', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm',
    'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z', ' ',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
    '#', '/', '.', ',', '-'
  ];
  const key = event.key;

  if (!caracteresPermitidos.includes(key)) {
    event.preventDefault();
  }
}

function validarCorreo(event) {
  // Permitir letras, números, arroba (@) y guion bajo (_) en correos
  const caracteresPermitidos = /^[a-zA-Z0-9._@-]+$/;
  const key = event.key;

  if (!caracteresPermitidos.includes(key)) {
    event.preventDefault();
  }
}


// Función para validar el campo Nombre
function validarNombre(elemento) {
  if (elemento.value.trim() === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Nombre es obligatorio',
    });
    elemento.focus();
    return false;
  }
  if (elemento.value.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Nombre no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

// funcion para verificar el titulo del libro sea unico
function verificarNombre(nombre, callback) {
  $.ajax({
    url: 'http://localhost:8080/api/v1/usuario/check/' + encodeURIComponent(nombre),
    type: 'GET',
    success: function (response) {
      callback(response);
    },
    error: function (xhr, status, error) {
      console.error('Error al verificar el nombre del usuario:', error);
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Hubo un problema al verificar el nombre del usuario.',
      });
      callback(false);
    }
  });
}

// Función para validar el campo Dirección
function validarDireccion(elemento) {
  // Eliminar espacios en blanco al inicio y al final
  let valor = elemento.value.trim();
  if (!elemento || !elemento.value) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Dirección es obligatorio',
    });
    elemento.focus();
    return false;
  }

  
  if (elemento.value.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Dirección no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

// Función para validar el campo Correo
function validarCorreo(elemento) {
  if (elemento.value.trim() === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Correo es obligatorio',
    });
    elemento.focus();
    return false;
  }
  if (elemento.value.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Correo no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

// Función para validar el campo Tipo de Usuario
function validarTipoUsuario(elemento) {
  if (elemento.value.trim() === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Tipo de Usuario es obligatorio',
    });
    elemento.focus();
    return false;
  }
  if (elemento.value.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Tipo de Usuario no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

// Función para registrar el usuario
function registrarUsuario(event) {
  event.preventDefault();

  var form = document.getElementById('form-usuarios');

  if (!validarNombre(form.nombre) || !validarDireccion(form.direccion) || !validarCorreo(form.email) ||
    !validarTipoUsuario(form['tipo-usuario'])) {
    return;
  }

  var nombre = form.nombre.value.trim();
  verificarNombre(nombre, function (exists) {
    if (exists) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'El nombre del usuario ya esta registrado',
      });
    } else {
      var usuario = {
        nombre: form.nombre.value,
        direccion: form.direccion.value,
        correo: form.email.value,
        tipo_usuario: form['tipo-usuario'].value
      };


      $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/v1/usuario/',
        contentType: "application/json",
        data: JSON.stringify(usuario),
        success: function (response) {
          Swal.fire({
            icon: 'success',
            title: 'Éxito',
            text: 'Usuario registrado correctamente',
          });
          form.reset();
        },
        error: function (xhr, status, error) {
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Error al registrar el usuario: ' + error,
          });
        }
      });
    }
  }
  )
}


//funciones de tabla
function listarUsuario() {
  $.ajax({
    url: url,
    type: "GET",
    success: function (result) {
      let cuerpoTablaUsuario = document.getElementById("cuerpoTablaUsuario");
      cuerpoTablaUsuario.innerHTML = "";
      result.forEach(usuario => {
        let trRegistro = document.createElement("tr");

        trRegistro.innerHTML = `
                      <td>${usuario.id_usuario}</td>
                      <td>${usuario.nombre}</td>
                      <td>${usuario.direccion}</td>
                      <td>${usuario.correo}</td>
                      <td>${usuario.tipo_usuario}</td>
                      <td>
                          <a href="javascript:eliminarUsuario('${usuario.id_usuario}');" class="btn btn-danger btn-sm btn-eliminar"><i class="fas fa-trash-alt"></i></a>
                          <a href="javascript:obtenerUnUsuario('${usuario.id_usuario}');" class="btn btn-primary btn-sm btn-editar"><i class="fas fa-edit"></i></a>
                      </td>
                  `;
        cuerpoTablaUsuario.appendChild(trRegistro);
      });
    },
    error: function (error) {
      console.error("Error al listar los usuarios", error);
    }
  });
}

//esta es la funcion de filtrar - funciona perfectamente
function filtrarUsuario() {
  let filtro = document.getElementById("filtrarUsuario").value.trim();
  if (filtro === "") {
    listarUsuario();
  } else {
    $.ajax({
      url: url + "busquedafiltro/" + filtro,
      type: "GET",
      success: function (result) {
        let cuerpoTablaUsuario = document.getElementById("cuerpoTablaUsuario");
        cuerpoTablaUsuario.innerHTML = "";
        result.forEach(usuario => {
          let trRegistro = document.createElement("tr");

          trRegistro.innerHTML = `
                      <td>${usuario.id_usuario}</td>
                      <td>${usuario.nombre}</td>
                      <td>${usuario.direccion}</td>
                      <td>${usuario.correo}</td>
                      <td>${usuario.tipo_usuario}</td>
                        <td>
                          <a href="javascript:eliminarUsuario('${usuario.id_usuario}');" class="btn btn-danger btn-sm"><i class="fas fa-trash-alt"></i></a>
                          <a href="javascript:obtenerUnUsuario('${usuario.id_usuario}');" class="btn btn-primary btn-sm"><i class="fas fa-edit"></i></a>
                        </td>
                    `;
          cuerpoTablaUsuario.appendChild(trRegistro);
        });
      },
      error: function (error) {
        console.error("Error al filtrar los usuarios", error);
      }
    });
  }
}

function limpiarFiltro() {
  document.getElementById("filtrarUsuario").value = "";
  listarUsuario();
}


//funcion de eliminar
function eliminarUsuario(id_usuario) {
  Swal.fire({
    title: '¿Estás seguro?',
    text: "No podrás revertir esto",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Sí, eliminarlo',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        url: 'http://localhost:8080/api/v1/usuario/eliminarPermanente/' + id_usuario,
        type: "DELETE",
        success: function (result) {
          //recarga la lista de  usuarios despues de eliminar
          listarUsuario();
          Swal.fire(
            'Eliminado!',
            'El usuario ha sido eliminado.',
            'success'
          );
        },
        error: function (error) {
          console.error("Error al eliminar el usuario", error);
          Swal.fire(
            'Error!',
            'Hubo un problema al intentar eliminar el usuario.',
            'error'
          );
        }
      });
    }
  });
}

//funcion para obtener y mostrar los detalles de un usuario para editar
function obtenerUnUsuario(id) {
  $.ajax({
    url: url + id,
    type: "GET",
    success: function (usuario) {
      document.getElementById("editBookId").value = usuario.id_usuario;
      document.getElementById("editNombre").value = usuario.nombre;
      document.getElementById("editDireccion").value = usuario.direccion;
      document.getElementById("editCorreo").value = usuario.correo;
      document.getElementById("editTipoUsuario").value = usuario.tipo_usuario;
      openModal();//abre el modal para editar
    },
    error: function (error) {
      console.error("Error al obtener el usuario", error);
      Swal.fire(
        'Error!',
        'Hubo un problema al intentar obtener los detalles del usuario.',
        'error'
      );
    }
  });
}

//funcion auxliar para abrir el modal de edicion
function openModal() {
  document.getElementById("editModal").style.display = "block";
}

//funcion auxiliar para cerrar el modal de edcion
function closeModal() {
  document.getElementById("editModal").style.display = "none";
}
//funcion para actualizar un libro
function updateUsuario(event) {
  event.preventDefault();

  let id = document.getElementById("editBookId").value;
  let usuario = {
    nombre: document.getElementById("editNombre").value,
    direccion: document.getElementById("editDireccion").value,
    correo: document.getElementById("editCorreo").value,
    tipo_usuario: document.getElementById("editTipoUsuario").value,
  };

  $.ajax({
    url: url + id,
    type: "PUT",
    contentType: "application/json",
    data: JSON.stringify(usuario),
    success: function (result) {
      closeModal();
      listarUsuario();
      Swal.fire(
        'Actualizado!',
        'El usuario ha sido actualizado.',
        'success'
      );
    },
    error: function (error) {
      console.error("Error al actualizar el usuario", error);
      Swal.fire(
        'Error!',
        'Hubo un problema al intentar actualizar el usuario.',
        'error'
      )
    }
  });
}



