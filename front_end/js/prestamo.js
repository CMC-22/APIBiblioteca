let url = "http://localhost:8081/api/v1/prestamo/";

function validarFechaPrestamo(elemento) {
  var valor = elemento.value.trim();
  if (valor === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Fecha préstamo es obligatorio',
    });
    elemento.focus();
    return false;
  }
  if (valor.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Fecha préstamo no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

function validarFechaDevolucion(elemento) {
  var valor = elemento.value.trim();
  if (valor === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Fecha devolución es obligatorio',
    });
    elemento.focus();
    return false;
  }
  if (valor.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Fecha devolución no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

function validarUsuario(elemento) {
  var valor = elemento.value.trim();
  if (valor === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Usuario es obligatorio',
    });
    elemento.focus();
    return false;
  }
  return true;
}

function validarLibro(elemento) {
  var valor = elemento.value.trim();
  if (valor === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Libro es obligatorio',
    });
    elemento.focus();
    return false;
  }
  return true;
}

function validarEstado(elemento) {
  var valor = elemento.value.trim();
  if (valor === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Estado es obligatorio',
    });
    elemento.focus();
    return false;
  }
  if (valor.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Estado no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

//cargar la lista de usuario
function cargarUsuarios() {
  $.ajax({
    url: url + "usuariosregistrados",
    type: "GET",
    success: function (usuarios) {
      let selectUsuarios = document.getElementById("usuario-prestamo");
      selectUsuarios.innerHTML = "<option value=''>Seleccionar...</option>";
      usuarios.forEach(usuario => {
        selectUsuarios.innerHTML += `<option value="${usuario.id_usuario}">${usuario.nombre}</option>`;
      });
    },
    error: function (error) {
      console.error("Error al cargar la lista de usuarios", error);
    }
  });
}

// Cargar lista de libros
function cargarLibros() {
  $.ajax({
    url: url + "librosregistrados",
    type: "GET",
    success: function (libros) {
      let selectLibros = document.getElementById("libro-prestado");
      selectLibros.innerHTML = "<option value=''>Seleccionar...</option>";
      libros.forEach(libro => {
        selectLibros.innerHTML += `<option value="${libro.id_libro}">${libro.titulo}</option>`;
      });
    },
    error: function (error) {
      console.error("Error al cargar la lista de libros", error);
    }
  });
}
// Inicializar carga de datos
cargarUsuarios();
cargarLibros();

function registrarPrestamo(event) {
  event.preventDefault();

  var form = document.getElementById('form-prestamos');

  if (!validarFechaPrestamo(form['fecha-prestamo']) ||
    !validarFechaDevolucion(form['fecha-devolucion']) ||
    !validarUsuario(form['usuario-prestamo']) ||
    !validarLibro(form['libro-prestado']) ||
    !validarEstado(form['estado'])) {
    return;
  }

  var prestamo = {
    usuario: { id_usuario: form['usuario-prestamo'].value },
    libro: { id_libro: form['libro-prestado'].value },
    fecha_prestamo: form['fecha-prestamo'].value,
    fecha_devolucion: form['fecha-devolucion'].value,
    estado: form['estado'].value
  };

  $.ajax({
    type: 'POST',
    url: 'http://localhost:8081/api/v1/prestamo/',
    contentType: "application/json",
    data: JSON.stringify(prestamo),
    success: function (response) {
      Swal.fire({
        icon: 'success',
        title: 'Éxito',
        text: 'Prestamo registrada correctamente',
      });
      form.reset();
      listarPrestamo(); // Actualizar la tabla después de registrar
    },
    error: function (xhr, status, error) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Error al registrar la prestamo: ' + error,
      });
    }
  });
}



function listarPrestamo() {
  $.ajax({
    url: url,
    type: "GET",
    success: function (result) {
      let cuerpoTablaPrestamo = document.getElementById("cuerpoTablaPrestamo");
      cuerpoTablaPrestamo.innerHTML = "";
      result.forEach(prestamo => {
        let trRegistro = document.createElement("tr");

        trRegistro.innerHTML = `
                      <td>${prestamo.id_prestamo}</td>
                      <td>${prestamo.fecha_prestamo}</td>
                      <td>${prestamo.fecha_devolucion}</td>
                      <td>${prestamo.usuario.nombre}</td>
                      <td>${prestamo.libro.titulo}</td>
                      <td>${prestamo.estado}</td>
                      <td>
                          <a href="javascript:eliminarPrestamo('${prestamo.id_prestamo}');" class="btn btn-danger btn-sm btn-eliminar"><i class="fas fa-trash-alt"></i></a>
                          <a href="javascript:obtenerUnPrestamo('${prestamo.id_prestamo}');" class="btn btn-primary btn-sm btn-editar"><i class="fas fa-edit"></i></a>
                      </td>
                  `;
        cuerpoTablaPrestamo.appendChild(trRegistro);
      });
    },
    error: function (error) {
      console.error("Error al listar los prestamos", error);
    }
  });
}

//funcion de eliminar -
function eliminarPrestamo(id_prestamo) {
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
        url: 'http://localhost:8081/api/v1/prestamo/eliminarPermanente/' + id_prestamo,
        type: "DELETE",
        success: function (result) {
          //recarga la lista de  prestamo despues de eliminar
          listarPrestamo();
          Swal.fire(
            'Eliminado!',
            'El prestamo ha sido eliminado.',
            'success'
          );
        },
        error: function (error) {
          console.error("Error al eliminar el prestamo", error);
          Swal.fire(
            'Error!',
            'Hubo un problema al intentar eliminar el prestamo.',
            'error'
          );
        }
      });
    }
  });
}

//funcion para obtener y mostrar los detalles de un libro para editar
function obtenerUnPrestamo(id) {
  $.ajax({
    url: url + id,
    type: "GET",
    success: function (prestamo) {
      // Verifica si los elementos existen antes de establecer el valor
      if (document.getElementById("editBookId")) {
        document.getElementById("editBookId").value = prestamo.id_prestamo;
      }
      if (document.getElementById("editFechaPrestamo")) {
        document.getElementById("editFechaPrestamo").value = prestamo.fecha_prestamo;
      }
      if (document.getElementById("editFechaDevolucion")) {
        document.getElementById("editFechaDevolucion").value = prestamo.fecha_devolucion;
      }
      if (document.getElementById("editEstado")) {
        document.getElementById("editEstado").value = prestamo.estado;
      }

      // Cargar listas de usuarios y libros para el formulario de actualización
      cargarUsuarios("editUsuario");
      cargarLibros("editLibro");

      // Preseleccionar el usuario y libro en los selectores
      setTimeout(() => {
        document.getElementById("editUsuario").value = prestamo.usuario.id_usuario;
        document.getElementById("editLibro").value = prestamo.libro.id_libro;
      }, 500); // Timeout to ensure options are loaded before selecting
    },
    error: function (error) {
      console.error("Error al cargar detalles del préstamo", error);
    }
  });

  // Abrir el modal
  openModal();//abre el modal para editar

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
function updatePrestamo(event) {
  event.preventDefault();

  let id = document.getElementById("editBookId").value;
  let prestamo = {
    fecha_prestamo: document.getElementById("editFechaPrestamo").value,
    fecha_devolucion: document.getElementById("editFechaDevolucion").value,
    usuario: {id_usuario: document.getElementById("editUsuario").value},
    libro: {id_libro: document.getElementById("editLibro").value},
    estado: document.getElementById("editEstado").value,
  };

  $.ajax({
    url: url + id,
    type: "PUT",
    contentType: "application/json",
    data: JSON.stringify(prestamo),
    success: function (result) {
      closeModal();
      listarPrestamo();
      Swal.fire(
        'Actualizado!',
        'El prestamo ha sido actualizado.',
        'success'
      );
    },
    error: function (error) {
      console.error("Error al actualizar el prestamo", error);
      Swal.fire(
        'Error!',
        'Hubo un problema al intentar actualizar el prestamo.',
        'error'
      )
    }
  });
}


