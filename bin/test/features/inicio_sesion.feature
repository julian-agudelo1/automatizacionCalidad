Feature: Inicio de sesión
  Como usuario registrado
  Quiero iniciar sesión
  Para acceder a mi cuenta

  @CP04 @autenticacion @positivo
  Scenario: CP04 - Iniciar sesión con credenciales válidas
    Given que existe una cuenta de prueba para iniciar sesión
    When inicia sesión con credenciales válidas
    Then el usuario queda autenticado con su cuenta

  @CP05 @autenticacion @negativo
  Scenario: CP05 - Rechazar una contraseña incorrecta
    Given que existe una cuenta de prueba para iniciar sesión
    When inicia sesión con una contraseña incorrecta
    Then el inicio de sesión es rechazado
