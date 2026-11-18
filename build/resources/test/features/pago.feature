Feature: Pago del pedido
  Como usuario con productos en el carrito
  Quiero completar el pago
  Para confirmar mi compra en el sitio de demostración

  @CP11 @checkout @e2e @positivo
  Scenario: CP11 - Completar la compra con MasterCredit de prueba
    Given que el usuario autenticado tiene un producto en el carrito
    When completa los datos ficticios de pago y confirma la compra
    Then se muestra la confirmación de la compra

  @CP12 @checkout @negativo
  Scenario: CP12 - Impedir el pago si faltan datos obligatorios
    Given que el usuario autenticado tiene un producto en el carrito
    When intenta pagar sin completar los datos obligatorios
    Then el sistema impide continuar con el pago
