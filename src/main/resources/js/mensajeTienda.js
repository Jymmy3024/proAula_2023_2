/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function() {
    const botonSubmit = document.querySelector("button[type='submit']");
    const nombreInput = document.getElementById("nombre");
    const descripcionInput = document.getElementById("InputDescripcion");
  
    botonSubmit.addEventListener("click", function(event) {
  
      let hayErrores = false;
  
      if (nombreInput.value.length > 45) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("En el campo de nombre no se deben ingresar nombres mayores a 45 caracteres, debe ser obligatorio y único.");
        hayErrores = true;
      }
  
      if (descripcionInput.value.length > 200) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("El campo descripción debe tener como máximo 200 caracteres y debe ser obligatorio.");
        hayErrores = true;
      }
  
      if (!hayErrores) {
        // Si no hay errores, el formulario se enviará
      }
    });
  });
  
