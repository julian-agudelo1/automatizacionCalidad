Feature: Carrito de compras
  Como cliente de Advantage Online Shopping
  Quiero administrar productos en mi carrito
  Para revisar mi compra antes del pago

  @CP06 @carrito @positivo
  Scenario: CP06 - Agregar un producto al carrito
    Given que el usuario abre el catálogo de productos
    When agrega un producto al carrito
    Then el producto aparece en el carrito

  @CP07 @carrito @positivo
  Scenario: CP07 - Agregar productos de categorías diferentes
    Given que el usuario abre el catálogo de productos
    When agrega productos de dos categorías diferentes
    Then ambos productos aparecen en el carrito

  @CP08 @carrito @positivo
  Scenario: CP08 - Modificar la cantidad de un producto
    Given que el usuario abre el catálogo de productos
    When modifica la cantidad del producto a 3
    Then el carrito refleja la cantidad 3 y su importe

  @CP09 @carrito @validacion
  Scenario: CP09 - Validar el subtotal y el total
    Given que el usuario abre el catálogo de productos
    When agrega dos unidades del parlante
    Then el subtotal de los productos y el total son "$539.98"

  @CP10 @carrito @alternativo
  Scenario: CP10 - Eliminar un producto del carrito
    Given que el usuario abre el catálogo de productos
    When elimina el producto del carrito
    Then el carrito queda vacío
