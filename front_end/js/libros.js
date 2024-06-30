let url = "http://localhost:8081/api/v1/libro/";

document.addEventListener("DOMContentLoaded", function () {
  const titulo = document.getElementById("titulo");
  const autor = document.getElementById("autor");
  const isbn = document.getElementById("isbn");
  const genero = document.getElementById("genero");
  const ejemplares = document.getElementById("ejemplares");
  const ocupados = document.getElementById("ocupados");

  if (titulo) titulo.addEventListener("keypress", soloLetras);
  if (autor) autor.addEventListener("keypress", soloLetras);
  if (isbn) isbn.addEventListener("keypress", soloNumeros);
  if (genero) genero.addEventListener("keypress", soloLetras);
  if (ejemplares) ejemplares.addEventListener("keypress", soloNumeros);
  if (ocupados) ocupados.addEventListener("keypress", soloNumeros);


  // Validar longitud del ISBN en el blur event
  if (isbn) isbn.addEventListener("blur", function (event) {
    validarIsbn(event.target);
  });
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

function soloNumeros(event) {
  const numerosPermitidos = [
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
  ];
  const key = event.key;

  if (!numerosPermitidos.includes(key)) {
    event.preventDefault();
  }
}

// funcion para validar campo de titulo
function validarTitulo(elemento) {
  if (elemento.value.trim() === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Título es obligatorio',
    });
    elemento.focus();
    return false;
  }
  if (elemento.value.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Título no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}
// funcion para verificar el titulo del libro sea unico
function verificarTitulo(titulo, callback) {
  $.ajax({
    url: 'http://localhost:8081/api/v1/libro/check/' + encodeURIComponent(titulo),
    type: 'GET',
    success: function (response) {
      callback(response);
    },
    error: function (xhr, status, error) {
      console.error('Error al verificar el título del libro:', error);
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Hubo un problema al verificar el título del libro.',
      });
      callback(false);
    }
  });
}

// funcion para validar campo de autor
function validarAutor(elemento) {
  if (elemento.value.trim() === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Autor es obligatorio',
    });
    elemento.focus();
    return false;
  }
  if (elemento.value.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Autor no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

// funcion para validar campo de ISBN
function validarIsbn(elemento) {
   // Eliminar espacios en blanco al inicio y al final
   let valor = elemento.value.trim();
  // Verificar si el elemento está definido y tiene un valor
  if (!elemento || !elemento.value) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo ISBN es obligatorio',
    });
    if (elemento) {
      elemento.focus();
    }
    return false;
  }

  if (valor.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo ISBN no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }

  // Verificar la longitud del ISBN (debe ser exactamente 13 caracteres numéricos)
  if (valor.length !== 13 || !(/^\d+$/.test(valor))) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo ISBN debe contener exactamente 13 números.',
    });
    elemento.focus();
    return false;
  }
  return true;
}

//verificar que isbn sea unico y no se repita
function verificarIsbn(isbn, callback) {
  $.ajax({
    url: 'http://localhost:8081/api/v1/libro/checkIsbn/' + isbn,
    type: 'GET',
    success: function (response) {
      callback(response);
    },
    error: function (xhr, status, error) {
      console.error('Error al verificar el ISBN:', error);
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Hubo un problema al verificar el ISBN.',
      });
      callback(false);
    }
  });
}

// funcion para validar campo de genero
function validarGenero(elemento) {
  if (elemento.value.trim() === '') {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Género es obligatorio',
    });
    elemento.focus();
    return false;
  }
  if (elemento.value.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo Genero no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

// funcion para validar campo de numero de ejemplares disponibles
function validarDisponibles(elemento) {
  var ejemplaresDisponibles = parseInt(elemento.value);
  var ejemplaresOcupados = parseInt(document.getElementById('ocupados').value);

  if (isNaN(ejemplaresDisponibles) || isNaN(ejemplaresOcupados)) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'Los campos de números deben ser valores numéricos',
    });
    elemento.focus();
    return false;
  }

  if (ejemplaresDisponibles < ejemplaresOcupados) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El número de ejemplares disponibles debe ser mayor que los ocupados',
    });
    elemento.focus();
    return false;
  }
  if (elemento.value.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo numero ejemplares disponibles no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}


// funcion para validar campo de numero de ejemplares ocupados
function validarOcupados(elemento) {
  var ejemplaresOcupados = parseInt(elemento.value);
  var ejemplaresDisponibles = parseInt(document.getElementById('ejemplares').value);

  if (isNaN(ejemplaresOcupados) || isNaN(ejemplaresDisponibles)) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'Los campos de números deben ser valores numéricos',
    });
    elemento.focus();
    return false;
  }

  if (ejemplaresDisponibles <= ejemplaresOcupados) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El número de ejemplares ocupados debe ser menor que los disponibles',
    });
    elemento.focus();
    return false;
  }
  if (elemento.value.includes('*')) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'El campo numero de ejemplares ocupados no puede contener asteriscos (*)',
    });
    elemento.focus();
    return false;
  }
  return true;
}

//funcion para registar el libro
function registrarLibro(event) {
  event.preventDefault();

  var form = document.getElementById('form-libros');

  if (!validarTitulo(form.titulo) || !validarAutor(form.autor) || !validarIsbn(form.isbn) ||
    !validarGenero(form.genero) || !validarDisponibles(form.ejemplares) || !validarOcupados(form.ocupados)) {
    return;
  }

  //para  verificar que el titulo del libro sea unico
  var titulo = form.titulo.value.trim();
  verificarTitulo(titulo, function (exists) {
    if (exists) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'El título del libro ya está registrado',
      });
    } else {
      var libro = {
        titulo: form.titulo.value,
        autor: form.autor.value,
        isbn: form.isbn.value,
        genero: form.genero.value,
        numero_ejemplares_disponibles: form.ejemplares.value,
        numero_ejemplares_ocupados: form.ocupados.value
      };

      //verificar que el isbn sea unico
      var isbn = form.isbn.value;
      verificarIsbn(isbn, function (exists) {
        if (exists) {
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'El ISBN ya está registrado',
          });
        } else {
          var libro = {
            titulo: form.titulo.value,
            autor: form.autor.value,
            isbn: form.isbn.value,
            genero: form.genero.value,
            numero_ejemplares_disponibles: form.ejemplares.value,
            numero_ejemplares_ocupados: form.ocupados.value
          };


          $.ajax({
            type: 'POST',
            url: 'http://localhost:8081/api/v1/libro/',
            contentType: "application/json",
            data: JSON.stringify(libro),
            success: function (response) {
              Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Libro registrado correctamente',
              });
              form.reset();
            },
            error: function (xhr, status, error) {
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error al registrar el libro: ' + error,
              });
            }
          });
        }
      });
    }
  }
  )
}


//estas son las funciones de la tabla
function listarLibro() {
  $.ajax({
    url: url,
    type: "GET",
    success: function (result) {
      let cuerpoTablaLibro = document.getElementById("cuerpoTablaLibro");
      cuerpoTablaLibro.innerHTML = "";
      result.forEach(libro => {
        let trRegistro = document.createElement("tr");

        trRegistro.innerHTML = `
                    <td>${libro.id_libro}</td>
                    <td>${libro.titulo}</td>
                    <td>${libro.autor}</td>
                    <td>${libro.isbn}</td>
                    <td>${libro.genero}</td>
                    <td>${libro.numero_ejemplares_disponibles}</td>
                    <td>${libro.numero_ejemplares_ocupados}</td>
                    <td>
                        <a href="javascript:eliminarLibro('${libro.id_libro}');" class="btn btn-danger btn-sm btn-eliminar"><i class="fas fa-trash-alt"></i></a>
                        <a href="javascript:obtenerUnLibro('${libro.id_libro}');" class="btn btn-primary btn-sm btn-editar"><i class="fas fa-edit"></i></a>
                    </td>
                `;
        cuerpoTablaLibro.appendChild(trRegistro);
      });
    },
    error: function (error) {
      console.error("Error al listar los libros", error);
    }
  });
}

//esta es la funcion de filtrar - funciona perfectamente
function filtrarLibro() {
  let filtro = document.getElementById("filtrarLibro").value.trim();
  if (filtro === "") {
    listarLibro();
  } else {
    $.ajax({
      url: url + "busquedafiltro/" + filtro,
      type: "GET",
      success: function (result) {
        let cuerpoTablaLibro = document.getElementById("cuerpoTablaLibro");
        cuerpoTablaLibro.innerHTML = "";
        result.forEach(libro => {
          let trRegistro = document.createElement("tr");

          trRegistro.innerHTML = `
                      <td>${libro.id_libro}</td>
                      <td>${libro.titulo}</td>
                      <td>${libro.autor}</td>
                      <td>${libro.isbn}</td>
                      <td>${libro.genero}</td>
                      <td>${libro.numero_ejemplares_disponibles}</td>
                      <td>${libro.numero_ejemplares_ocupados}</td>
                      <td>
                        <a href="javascript:eliminarLibro('${libro.id_libro}');" class="btn btn-danger btn-sm"><i class="fas fa-trash-alt"></i></a>
                        <a href="javascript:obtenerUnLibro('${libro.id_libro}');" class="btn btn-primary btn-sm"><i class="fas fa-edit"></i></a>
                      </td>
                  `;
          cuerpoTablaLibro.appendChild(trRegistro);
        });
      },
      error: function (error) {
        console.error("Error al filtrar los libros", error);
      }
    });
  }
}

function limpiarFiltro() {
  document.getElementById("filtrarLibro").value = "";
  listarLibro();
}


//funcion de eliminar -
function eliminarLibro(id_libro) {
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
        url: 'http://localhost:8081/api/v1/libro/eliminarPermanente/' + id_libro,
        type: "DELETE",
        success: function (result) {
          //recarga la lista de  libros despues de eliminar
          listarLibro();
          Swal.fire(
            'Eliminado!',
            'El libro ha sido eliminado.',
            'success'
          );
        },
        error: function (error) {
          console.error("Error al eliminar el libro", error);
          Swal.fire(
            'Error!',
            'Hubo un problema al intentar eliminar el libro.',
            'error'
          );
        }
      });
    }
  });
}

//funcion para obtener y mostrar los detalles de un libro para editar
function obtenerUnLibro(id) {
  $.ajax({
    url: url + id,
    type: "GET",
    success: function (libro) {
      document.getElementById("editBookId").value = libro.id_libro;
      document.getElementById("editTitulo").value = libro.titulo;
      document.getElementById("editAutor").value = libro.autor;
      document.getElementById("editGenero").value = libro.genero;
      document.getElementById("editIsbn").value = libro.isbn;
      document.getElementById("editEjemplaresDisponibles").value = libro.numero_ejemplares_disponibles;
      document.getElementById("editEjemplaresOcupados").value = libro.numero_ejemplares_ocupados;
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
function updateLibro(event) {
  event.preventDefault();

  let id = document.getElementById("editBookId").value;
  let libro = {
    titulo: document.getElementById("editTitulo").value,
    autor: document.getElementById("editAutor").value,
    genero: document.getElementById("editGenero").value,
    isbn: document.getElementById("editIsbn").value,
    numero_ejemplares_disponibles: document.getElementById("editEjemplaresDisponibles").value,
    numero_ejemplares_ocupados: document.getElementById("editEjemplaresOcupados").value,
  };

  $.ajax({
    url: url + id,
    type: "PUT",
    contentType: "application/json",
    data: JSON.stringify(libro),
    success: function (result) {
      closeModal();
      listarLibro();
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



