/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function() {
    const fechaInput = document.getElementById("InputFecha");
    const botonSubmit = document.querySelector("button[type='submit']");
    const telefonoInput = document.getElementById("InputTelefono");
    const cedulaInput = document.getElementById("InputCedula");
    const apellidoInput = document.getElementById("apellido");
    const nombreInput = document.getElementById("nombre");
    const generoInput = document.getElementById("InputGenero");
    const emailInput = document.getElementById("InputEmail");
    const contraseñaInput = document.getElementById("InputContraseña");
  
    botonSubmit.addEventListener("click", function(event) {
      const fechaNacimiento = new Date(fechaInput.value);
      const fechaLimite = new Date();
      fechaLimite.setFullYear(fechaLimite.getFullYear() - 15); // Restar 15 años a la fecha actual
  
      let hayErrores = false;
  
      if (fechaNacimiento > fechaLimite) {
        event.preventDefault(); // Evitar el envío del formulario si la fecha es menor de 15 años
        alert("La fecha de nacimiento debe ser mayor de 15 años.");
        hayErrores = true;
      }
  
      if (telefonoInput.value.length > 15) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("El teléfono no puede tener más de 15 caracteres.");
        hayErrores = true;
      }
  
      if (cedulaInput.value.length > 15) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("La cédula no puede tener más de 15 caracteres.");
        hayErrores = true;
      }
  
      if (apellidoInput.value.length > 20 || /[^a-zA-Z0-9\s]/.test(apellidoInput.value)) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("El apellido no puede tener más de 20 caracteres o contener caracteres especiales.");
        hayErrores = true;
      }
  
      if (nombreInput.value.length > 20 || /[^a-zA-Z0-9\s]/.test(nombreInput.value)) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("El nombre no puede tener más de 20 caracteres o contener caracteres especiales.");
        hayErrores = true;
      }

      if (generoInput.value.length > 1) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("El genero deber tener un solo carácter M para Masculino F para femenino y O de otro");
        hayErrores = true;
      }

      if (emailInput.value.length > 45) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("El correo no debe ser mayor a 45 caracteres y debe ser un correo valido.");
        hayErrores = true;
      }

      if (contraseñaInput.value.length > 20) {
        event.preventDefault(); // Evitar el envío del formulario si hay errores
        alert("La contraseña debe ser de máximo 20 caracteres.");
        hayErrores = true;
      }
  
      if (!hayErrores) {
        // Si no hay errores, el formulario se enviará
      }
    });
  });
  

