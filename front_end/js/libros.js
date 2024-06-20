const url = "http://localhost:8081/api/v1/libro/";

// Función para listar libros con o sin filtro
function listarLibro() {
    const capturarFiltro = document.getElementById("filter-name").value;
    let urlLocal = url;
    if (capturarFiltro !== "") {
        urlLocal += "busquedafiltro/" + capturarFiltro;
    }

    fetch(urlLocal)
        .then(response => response.json())
        .then(result => {
            const cuerpoTabla = document.getElementById("cuerpoTablaLibros");
            cuerpoTabla.innerHTML = "";

            result.forEach(libro => {
                const trRegistro = document.createElement("tr");

                trRegistro.innerHTML = `
                    <td>${libro.id}</td>
                    <td>${libro.titulo}</td>
                    <td>${libro.autor}</td>
                    <td>${libro.isbn}</td>
                    <td>${libro.genero}</td>
                    <td>${libro.numero_de_ejemplares_disponibles}</td>
                    <td>${libro.numero_de_ejemplares_ocupados}</td>
                    <td>
                        <button class="btn btn-warning editar-libro" onclick="consultarLibroID(${libro.id})">Editar</button>
                        <button class="btn btn-danger eliminar-libro" onclick="eliminarLibro(${libro.id})">Eliminar</button>
                    </td>
                `;
                cuerpoTabla.appendChild(trRegistro);
            });
        })
        .catch(error => alert("Error en la petición " + error));
}

// Función para eliminar un libro
function eliminarLibro(id) {
    Swal.fire({
        title: "¿Estás seguro?",
        text: "¿Deseas eliminar este libro?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Eliminar",
        cancelButtonText: "Cancelar"
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(url + id, { method: 'DELETE' })
                .then(response => {
                    if (response.ok) {
                        Swal.fire("¡Eliminado!", "El libro ha sido eliminado correctamente.", "success");
                        listarLibro();
                    } else {
                        Swal.fire("Error", "Error al eliminar el libro.", "error");
                    }
                })
                .catch(error => Swal.fire("Error", "Error al eliminar el libro. " + error, "error"));
        }
    });
}

// Función para consultar un libro por su ID
function consultarLibroID(id) {
    fetch(url + id)
        .then(response => response.json())
        .then(libro => {
            document.getElementById("titulo").value = libro.titulo;
            document.getElementById("autor").value = libro.autor;
            document.getElementById("isbn").value = libro.isbn;
            document.getElementById("genero").value = libro.genero;
            document.getElementById("ejemplares").value = libro.numero_de_ejemplares_disponibles;
            document.getElementById("ocupados").value = libro.numero_de_ejemplares_ocupados;
            document.getElementById("id").value = libro.id;  // Necesario para actualizar
        })
        .catch(error => alert("Error al consultar el libro: " + error));
}

// Función para registrar o actualizar un libro
function registrarLibro(event) {
    event.preventDefault();
    const titulo = document.getElementById("titulo").value;
    const autor = document.getElementById("autor").value;
    const isbn = document.getElementById("isbn").value;
    const genero = document.getElementById("genero").value;
    const ejemplares = document.getElementById("ejemplares").value;
    const ocupados = document.getElementById("ocupados").value;

    if (!validarCampos()) {
        Swal.fire("¡Error!", "Llene todos los campos correctamente", "error");
        return;
    }

    const formData = {
        titulo,
        autor,
        isbn,
        genero,
        numero_de_ejemplares_disponibles: parseInt(ejemplares),
        numero_de_ejemplares_ocupados: parseInt(ocupados)
    };

    const id = document.getElementById("id").value;
    const method = id ? 'PUT' : 'POST';
    const endpoint = id ? url + id : url;

    fetch(endpoint, {
        method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => response.json())
    .then(result => {
        Swal.fire("¡Excelente!", "Se guardó correctamente su registro", "success");
        limpiarFormulario();
        listarLibro();
    })
    .catch(error => Swal.fire("Error", "Error al guardar, " + error, "error"));
}

// Función para validar los campos
function validarCampos() {
    const titulo = document.getElementById("titulo").value;
    const autor = document.getElementById("autor").value;
    const isbn = document.getElementById("isbn").value;
    const genero = document.getElementById("genero").value;
    const ejemplares = parseInt(document.getElementById("ejemplares").value);
    const ocupados = parseInt(document.getElementById("ocupados").value);

    if (titulo === '' || autor === '' || isbn === '' || genero === '' || isNaN(ejemplares) || isNaN(ocupados)) {
        return false;
    }
    if (!/^\d{13}$/.test(isbn)) {
        alert("El ISBN debe tener 13 dígitos numéricos.");
        return false;
    }
    if (ejemplares < ocupados) {
        alert("El número de ejemplares disponibles no debe ser menor que los ocupados.");
        return false;
    }
    // Comprobación para evitar títulos duplicados
    const titles = Array.from(document.querySelectorAll("#cuerpoTablaLibros td:nth-child(2)")).map(td => td.textContent);
    if (titles.includes(titulo)) {
        alert("El título ya existe en la lista.");
        return false;
    }
    return true;
}

// Función para limpiar el formulario después de registrar o actualizar
function limpiarFormulario() {
    document.getElementById("titulo").value = '';
    document.getElementById("autor").value = '';
    document.getElementById("isbn").value = '';
    document.getElementById("genero").value = '';
    document.getElementById("ejemplares").value = '';
    document.getElementById("ocupados").value = '';
    document.getElementById("id").value = '';
}

// Filtrar libros
document.getElementById("aplicar-filtro").addEventListener('click', listarLibro);
document.getElementById("limpiar-filtro").addEventListener('click', function() {
    document.getElementById("filter-name").value = '';
    listarLibro();
});

// Inicializar la lista de libros al cargar la página
window.onload = listarLibro;
