# language: pt
Funcionalidade: API - Clientes

  Cenario: Registrar um novo cliente
    Quando submeter um novo cliente
    Entao a mensagem é registrada com sucesso

  Cenario: Buscar um cliente existente por id
    Dado que um cliente já foi registrado
    Quando requisitar a busca de um pedido por id
    Entao cliente é exibido com sucesso

  Cenario: Buscar um cliente existente por cpf
    Dado que um cliente já foi registrado
    Quando requisitar a busca de um pedido por cpf
    Entao cliente é exibido com sucesso