Feature: Registro de usuarios
  Como visitante de Advantage Online Shopping
  Quiero crear una cuenta
  Para acceder a las funcionalidades de compra

  @CP01 @registro @positivo
  Scenario: CP01 - Registrar un usuario con datos válidos
    Given que el usuario abre el formulario de registro
    When completa el formulario de registro con datos válidos
    Then la cuenta queda registrada correctamente

  @CP02 @registro @negativo
  Scenario: CP02 - Rechazar un correo inválido
    Given que el usuario abre el formulario de registro
    When intenta registrarse con un correo inválido
    Then el registro es bloqueado por la validación del formulario

  @CP03 @registro @negativo
  Scenario: CP03 - Impedir el registro sin campos obligatorios
    Given que el usuario abre el formulario de registro
    When intenta registrarse sin completar los campos obligatorios
    Then el usuario permanece en el formulario de registro
