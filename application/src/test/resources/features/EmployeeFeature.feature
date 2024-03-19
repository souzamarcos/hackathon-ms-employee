# language: pt
Funcionalidade: API - Funcionários

  Cenario: Registrar um novo funcionário
    Quando submeter um novo funcionário
    Entao o funcionário é registrado com sucesso

  Cenario: Buscar um funcionário existente por id
    Dado que um funcionário já foi registrado
    Quando requisitar a busca de um funcionário por id
    Entao funcionário é exibido com sucesso

  Cenario: Logar um funcionário existente por id
    Dado que um funcionário já foi registrado
    Quando requisitar login
    Entao token é exibido com sucesso