window.bootstrap = require('bootstrap/dist/js/bootstrap.bundle.js');

const url = 'http://localhost:8080/intento3/api/donantes'
const contenedor = document.querySelector('tbody')
let resultados = '';

const modalDonantes = new bootstrap.Modal(document.getElementById('modalDonante')) 

const formDonantes = document.querySelector('form') 

const nombresDonantes = document.getElementById('nombres')
const apellidosDonantes = document.getElementById('apellidos') 
const generoDonantes = document.getElementById('genero') 
const tDocumentoDonantes = document.getElementById('tipoDoc') 
const documentoDonantes = document.getElementById('documento') 
const rhDonantes = document.getElementById('RH') 
const telefonoDonantes = document.getElementById('telefono') 
const mailDonantes = document.getElementById('mail') 
const pwdDonantes = document.getElementById('pwd') 

let opcion = '' 

btnCrear.addEventListener('click', () => { 
    nombresDonantes.value=''
    apellidosDonantes.value='' 
    generoDonantes.value=''
    tDocumentoDonantes.value=''
    documentoDonantes.value=''
    rhDonantes.value=''
    telefonoDonantes.value='' 
    mailDonantes.value='' 
    pwdDonantes.value=''
    documentoDonantes.disabled = false
    modalDonantes.show()    
    opcion = 'crear'
})

const ajax = (options) => { 
    let { url, method, success, error, data } = options; 
    const xhr = new XMLHttpRequest(); 
    xhr.addEventListener("readystatechange", (e) => { 
        if (xhr.readyState !== 4) return; 
        if (xhr.status >= 200 && xhr.status < 300) { 
            let json = JSON.parse(xhr.responseText); 
            success(json); 
        } else { 
            let message = xhr.statusText || "Ocurrió un error"; 
            error(`Error ${xhr.status}: ${message}`); 
        } 
    }); 
    xhr.open(method || "GET", url); 
    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8"); 
    xhr.send(JSON.stringify(data));

};

const getAll = () => {
    ajax({ 
        url: url, 
        method: "GET", 
        success: (res) => { 
            console.log(res);

            res.forEach((donantes) => { 
                resultados += `<tr> 
                <td width="10%">${donantes.nombres}</td>
                <td width="10%">${donantes.apellidos}</td>
                <td width="10%">${donantes.tipo_documento}</td> 
                <td width="10%">${donantes.numero_documento}</td> 
                <td width="10%">${donantes.genero}</td> 
                <td width="10%">${donantes.RH}</td> 
                <td width="10%">${donantes.telefono}</td> 
                <td width="10%">${donantes.email}</td> 
                <td width="10%">${donantes.password}</td> 
                <td class="text-center" width="10%"><a class="btnEditar btn btn-primary">Editar</a><a class="btnBorrar btn btn-danger">Borrar</a></td> 
            </tr>` 
            }); 
            contenedor.innerHTML = resultados 
        }, 
        error: (err) => { 
            console.log(err); 
            $table.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`); 
        }, 
    }); 
}; 

document.addEventListener("DOMContentLoaded", getAll);

/* ************************************ */
document.addEventListener("click", (e) => {

    if (e.target.matches(".btnBorrar")) {
        const fila = e.target.parentNode.parentNode
        const id = fila.firstElementChild.innerHTML
        console.log(numero_documento)
        alertify.confirm(`¿Estás seguro de eliminar el id ${numero_documento}?`,
            function () {
                ajax({
                    url: url + "/" + numero_documento,
                    method: "DELETE",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    success: (res) => location.reload(),
                    error: (err) => alert(err),
                });
                alertify.success('Registro eliminado')
            },
            function () {
                alertify.error('Cancel')
            });


    }
    if (e.target.matches(".btnEditar")) {
        const fila = e.target.parentNode.parentNode

        nombresDonantes.value= fila.children[0].innerHTML
        apellidosDonantes.value= fila.children[1].innerHTML
        generoDonantes.value= fila.children[2].innerHTML
        tDocumentoDonantes.value= fila.children[3].innerHTML
        documentoDonantes.value= fila.children[4].innerHTML
        rhDonantes.value= fila.children[5].innerHTML
        telefonoDonantes.value= fila.children[6].innerHTML 
        mailDonantes.value= fila.children[7].innerHTML 
        pwdDonantes.value= fila.children[8].innerHTML
        documentoDonantes.disabled = true
        opcion = 'editar'
        modalDonantes.show()
    }
})

formDonantes.addEventListener('submit', (e) => {
    e.preventDefault()
    let metodo = "POST"
    if (opcion == 'editar') {
        metodo = "PUT"
    }
    ajax({
        url: url,
        method: metodo,
        headers: {
            'Content-Type': 'application/json'
        },
        success: (res) => location.reload(),
        error: (err) =>
            $form.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`),
        data: {
            "RH": rhDonantes.value,
            "apellidos": apellidosDonantes.value,
            "correo": mailDonantes.value,
            "genero": generoDonantes.value,
            "nombres": nombresDonantes.value,
            "numero_documento": documentoDonantes.value,
            "pwd": pwdDonantes.value,
            "telefono": telefonoDonantes.value,
            "tipo_documento": tDocumentoDonantes.value
        },
    });
    modalDonantes.hide()
})


