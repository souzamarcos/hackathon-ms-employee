# language: pt
Funcionalidade: API - Clientes

  Cenario: Registrar um novo cliente
    Quando submeter um novo cliente
    Entao a mensagem é registrada com sucesso

  Cenario: Buscar um cliente existente por id
    Dado que um cliente já foi registrado
    Quando requisitar a busca de um cliente por id
    Entao cliente é exibido com sucesso

  Cenario: Buscar um cliente existente por cpf
    Dado que um cliente já foi registrado
    Quando requisitar a busca de um cliente por cpf
    Entao cliente é exibido com sucesso

  Cenario: Excluir um cliente existente por cpf
    Dado que um cliente já foi registrado
    Quando requisitar a exclusao de um cliente por cpf
    Entao a mensagem de exclusao é exibida com sucesso