let url = "http://localhost:8081/api/v1/multas/";

function validarUsuarioMultado(elemento) {
    var valor = elemento.value.trim();
    if (valor === '') {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'El campo Usuario multado es obligatorio',
        });
        elemento.focus();
        return false;
    }
    if (valor.includes('*')) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'El campo Usuario multado no puede contener asteriscos (*)',
        });
        elemento.focus();
        return false;
    }
    return true;
}

function validarPrestamo(elemento) {
    var valor = elemento.value.trim();
    if (valor === '') {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'El campo Préstamo es obligatorio',
        });
        elemento.focus();
        return false;
    }
    if (valor.includes('*')) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'El campo Préstamo no puede contener asteriscos (*)',
        });
        elemento.focus();
        return false;
    }
    return true;
}

function validarValorMulta(elemento) {
    var valor = elemento.value.trim();
    if (valor === '') {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'El campo Valor de la Multa es obligatorio',
        });
        elemento.focus();
        return false;
    }
    if (valor.includes('*')) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'El campo Valor de la Multa no puede contener asteriscos (*)',
        });
        elemento.focus();
        return false;
    }
    return true;
}

function validarFechaMulta(elemento) {
    var valor = elemento.value.trim();
    if (valor === '') {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'El campo Fecha de la Multa es obligatorio',
        });
        elemento.focus();
        return false;
    }
    if (valor.includes('*')) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'El campo Fecha de la Multa no puede contener asteriscos (*)',
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


//Cargar usuarios
async function cargarUsuarios() {
  try {
      const response = await fetch('http://localhost:8081/api/v1/usuariosprestamo');
      const usuarios = await response.json();
      const usuarioSelect = document.getElementById('usuario-multado');
      usuarios.forEach(usuario => {
          const option = document.createElement('option');
          option.value = usuario.id_usuario;
          option.textContent = usuario.nombre;
          usuarioSelect.appendChild(option);
      });
  } catch (error) {
      console.error('Error cargando usuarios:', error);
  }
}

async function cargarPrestamos() {
  const usuarioId = document.getElementById('usuario-multado').value;
  try {
      const response = await fetch(`http://localhost:8081/api/v1/multas/usuario/${usuarioId}`);
      const prestamos = await response.json();
      const prestamoSelect = document.getElementById('prestamo-multa');
      prestamoSelect.innerHTML = ''; // Limpiar opciones anteriores
      prestamos.forEach(prestamo => {
          const option = document.createElement('option');
          option.value = prestamo.id_prestamo;
          option.textContent = `Prestamo ID: ${prestamo.id_prestamo}, Libro: ${prestamo.libro.titulo}`;
          prestamoSelect.appendChild(option);
      });
  } catch (error) {
      console.error('Error cargando préstamos:', error);
  }
}


async function registrarMultas(event) {
    event.preventDefault();

    var form = document.getElementById('form-multas');

    if (!validarUsuarioMultado(form['usuario-multado']) || !validarPrestamo(form['prestamo-multa']) || 
        !validarValorMulta(form['valor-multa']) || !validarFechaMulta(form['fecha-multa']) || 
        !validarEstado(form['estado-multa'])) {
        return;
    }

    const multas = {
        usuario: { id_usuario: form['usuario-multado'].value},
        prestamo: { id_prestamo: form['prestamo-multa'].value},
        valor_multa: form['valor-multa'].value,
        fecha_multa: form['fecha-multa'].value,
        estado: form['estado-multa'].value
    };

    try {
      const response = await fetch('http://localhost:8081/api/v1/multas/', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(multas)
      });

      if (response.ok) {
        Swal.fire({
          icon: 'success',
          title: 'Éxito',
          text: 'Multa registrada correctamente',
        });
        form.reset();
      }else{
        const error = await response.json();
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Error al registrar la multa: ' + error.message,
        });
      }
    }catch (error) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Error al registrar la multa: ' + error.message,
      });
    }
  }

    /*$.ajax({
        type: 'POST',
        url: 'http://localhost:8081/api/v1/multas/',
        contentType: "application/json",
        data: JSON.stringify(multas),
        success: function (response) {
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Multa registrada correctamente',
            });
            form.reset();
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error al registrar la multa: ' + error,
            });
        }
    });
}*/



//estas son las funciones de la tabla
function listarMultas() {
    $.ajax({
      url: url,
      type: "GET",
      success: function (result) {
        let cuerpoTablaMultas = document.getElementById("cuerpoTablaMultas");
        cuerpoTablaMultas.innerHTML = "";
        result.forEach(multas => {
          let trRegistro = document.createElement("tr");
  
          trRegistro.innerHTML = `
                      <td>${multas.id_multas}</td>
                      <td>${multas.usuario_multado}</td>
                      <td>${multas.prestamo}</td>
                      <td>${multas.valor_multa}</td>
                      <td>${multas.fecha_multa}</td>
                      <td>${multas.estado}</td>
                      <td>
                          <a href="javascript:eliminarMulta('${multas.id_multas}');" class="btn btn-danger btn-sm btn-eliminar"><i class="fas fa-trash-alt"></i></a>
                          <a href="javascript:obtenerUnaMulta('${multas.id_multas}');" class="btn btn-primary btn-sm btn-editar"><i class="fas fa-edit"></i></a>
                      </td>
                  `;
         cuerpoTablaMultas.appendChild(trRegistro);
        });
      },
      error: function (error) {
        console.error("Error al listar las multas", error);
      }
    });
  }



//funcion de eliminar -
function eliminarMulta(id_multas) {
  Swal.fire({
    title: '¿Estás seguro?',
    text: "No podrás revertir esto",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Sí, eliminarla',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        url: 'http://localhost:8081/api/v1/multas/eliminarPermanente/' + id_multas,
        type: "DELETE",
        success: function (result) {
          //recarga la lista de  multas despues de eliminar
          listarMultas();
          Swal.fire(
            'Eliminada!',
            'La multa ha sido eliminada.',
            'success'
          );
        },
        error: function (error) {
          console.error("Error al eliminar la multa", error);
          Swal.fire(
            'Error!',
            'Hubo un problema al intentar eliminar la multa.',
            'error'
          );
        }
      });
    }
  });
}

function obtenerUnaMulta(id) {
    $.ajax({
      url: url + id,
      type: "GET",
      success: function (multas) {
        // Verifica si los elementos existen antes de establecer el valor
      if (document.getElementById("editBookId")) {
        document.getElementById("editBookId").value = multas.id_multas;
      }
      if (document.getElementById("editUsuarioMultado")) {
        document.getElementById("editUsuarioMultado").value = multas.usuario_multado;
      }
      if (document.getElementById("editPrestamo")) {
        document.getElementById("editPrestamo").value = multas.prestamo;
      }
      if (document.getElementById("editValorMulta")) {
        document.getElementById("editValorMulta").value = multas.valor_multa;
      }
      if (document.getElementById("editFechaMulta")) {
        document.getElementById("editFechaMulta").value = multas.fecha_multa;
      }
      if (document.getElementById("editEstado")) {
        document.getElementById("editEstado").value = multas.estado;
      }
        openModal();//abre el modal para editar
      },
      error: function (error) {
        console.error("Error al obtener el libro", error);
        Swal.fire(
          'Error!',
          'Hubo un problema al intentar obtener los detalles del libro.',
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
  function updateMultas(event) {
    event.preventDefault();
  
    let id = document.getElementById("editBookId").value;
    let multas = {
      usuario_multado: document.getElementById("editUsuarioMultado").value,
      prestamo: document.getElementById("editPrestamo").value,
      valor_multa: document.getElementById("editValorMulta").value,
      fecha_multa: document.getElementById("editFechaMulta").value,
      estado: document.getElementById("editEstado").value,
    };
  
    $.ajax({
      url: url + id,
      type: "PUT",
      contentType: "application/json",
      data: JSON.stringify(multas),
      success: function (result) {
        closeModal();
        listarMultas();
        Swal.fire(
          'Actualizado!',
          'El libro ha sido actualizado.',
          'success'
        );
      },
      error: function (error) {
        console.error("Error al actualizar el libro", error);
        Swal.fire(
          'Error!',
          'Hubo un problema al intentar actualizar el libro.',
          'error'
        )
      }
    });
  }
  

