# language: pt
Funcionalidade: Gerenciamento de Clientes

  Cenario: Criar um novo cliente com sucesso
    Dado que eu tenha os dados válidos do cliente
    Quando eu envio uma requisição POST para "/clientes"
    Entao o cliente deve ser criado com sucesso
    E a resposta deve conter os dados do cliente criado
    E o status de resposta deve ser 201

  Cenario: Atualizar um cliente existente com sucesso
    Dado que eu tenha os dados a serem atualizados de um cliente existente
    Quando eu envio uma requisição PUT para "/clientes/{id}"
    Entao o cliente deve ser atualizado com sucesso
    E a resposta deve conter os dados atualizados do cliente
    E o status de resposta deve ser 200

  Cenario: Buscar um cliente existente com sucesso
    Dado que eu tenha um ID de um cliente existente
    Quando eu envio uma requisição GET para "/clientes/{id}"
    Entao o cliente deve ser atualizado com sucesso
    E a resposta deve contar os dados atualizados do cliente
    E o status de resposta deve ser 200

  Cenario: Listar todos os clientes ativos com sucesso
    Dado que existam clientes cadastrados e ativos no sistema
    Quando eu envio uma requisição GET para "/clientes"
    Entao uma lista de clientes deve ser retornada
    E o status de resposta deve ser 200

  Cenario: Deletar um cliente existente com sucesso
    Dado que eu tenha o ID de um cliente existente
    Quando eu envio uma requisição DELETE para "clientes/{id}"
    Entao o cliente deve ser desativado com sucesso
    E o status da resposta deve ser 204