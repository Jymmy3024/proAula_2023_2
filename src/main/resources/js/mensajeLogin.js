/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function() {
  const correoInput = document.getElementById("correo");
  const mensajeCaracteresCorreo = document.getElementById("mensaje-caracteres-correo");
  
  const contrasenaInput = document.getElementById("contrasena");
  const mensajeCaracteresContrasena = document.getElementById("mensaje-caracteres-contrasena");
  const mensajeContrasena = document.getElementById("mensaje-contrasena");
  
  const botonSubmit = document.querySelector("button[type='submit']");

  botonSubmit.addEventListener("click", function(event) {
    // Validación del campo de correo
    if (correoInput.value.length >= 40) {
      event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("El correo no puede tener más de 40 caracteres.");
        hayErrores = true;
    } 

    // Validación del campo de contraseña
    const contrasenaValue = contrasenaInput.value;
    const longitudValida = contrasenaValue.length >= 8 && contrasenaValue.length <= 40;
    const letras = /[a-zA-Z]/.test(contrasenaValue);
    const numeros = /\d/.test(contrasenaValue);
    const caracteresEspeciales = /[^a-zA-Z\d]/.test(contrasenaValue);

    if (!longitudValida || !(letras && numeros && caracteresEspeciales)) {
      event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("El campo contraseña debe tener números, letras y caracteres especiales.");
        hayErrores = true;
    } 
  });
});



  

