# language: pt
  Funcionalidade: Gerenciamento de Clientes

    Cenario: Criar um novo cliente com sucesso
      Dado que eu tenha um novo cliente com o nome "João da Silva Sauro"
      Quando eu envio a solicitação de criação de cliente
      Entao o cliente deve ser criado com sucesso
      E eu devo receber uma resposta com o ID do cliente