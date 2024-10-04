# Microsserviço de Cliente - Tech Challenge

Este projeto faz parte do **Tech Challenge** e tem como objetivo desenvolver um **Microsserviço de Gerenciamento de Clientes** que será utilizado em um **Sistema de Gerenciamento de Pedidos**. Este serviço é responsável pelas operações de criação, leitura, atualização e exclusão (CRUD) de clientes.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot 3.3.4**: Framework para a construção de aplicações Java, facilitando o desenvolvimento de microsserviços.
- **Spring Data JPA**: Integração com banco de dados relacional para simplificar as operações CRUD.
- **MySQL**: Banco de dados utilizado para persistência dos dados de clientes.
- **Lombok**: Redução de código boilerplate com anotações para getters, setters e outras funcionalidades.
- **Validation**: Biblioteca de validação de beans usando Hibernate Validator.

## Funcionalidades

Este microsserviço será responsável por:
- Cadastro de clientes.
- Edição dos dados de clientes.
- Exclusão de clientes.
- Consulta de informações de clientes.

## Estrutura do Projeto

- **Group**: `com.techchallenge4`
- **Artifact**: `ms-cliente`
- **Nome**: Microsserviço de Cliente
- **Descrição**: Microsserviço responsável pela gestão de clientes no sistema.
- **Banco de Dados**: MySQL, acessado via Spring Data JPA.

## Dependências Principais

- **Spring Web**: Criação de endpoints RESTful.
- **Spring Data JPA**: Manipulação e persistência de dados em banco de dados.
- **MySQL Driver**: Driver JDBC para MySQL.
- **Lombok**: Facilita a criação de código boilerplate.
- **Validation**: Implementação de validações de dados.

## Como Executar

1. **Pré-requisitos**:
   - JDK 17
   - Maven
   - Docker (se desejar rodar o MySQL em container)

2. **Passos**:
   - Clonar o repositório:
     ```bash
     git clone git@github.com:WaldirJuniorGPN/ms-cliente.git
     ```
   - Navegar até a pasta do projeto:
     ```bash
     cd ms-cliente
     ```
  
   - Rodar o projeto:
     ```bash
     mvn spring-boot:run
     ```

3. **Endpoints**:
   - Listar todos os clientes: `GET /clientes`
   - Buscar cliente por ID: `GET /clientes/{id}`
   - Criar novo cliente: `POST /clientes`
   - Atualizar cliente: `PUT /clientes/{id}`
   - Deletar cliente: `DELETE /clientes/{id}`

## Estrutura de Banco de Dados

A tabela de clientes será criada com os seguintes campos:
- `id`: Identificador único do cliente.
- `nome`: Nome completo do cliente.
- `email`: Endereço de email.
- `telefone`: Número de telefone.
- `endereco`: Endereço do cliente.

## Testes

Este microsserviço inclui testes unitários e de integração. Para executar os testes, utilize o seguinte comando:
```bash
mvn test
```

## Contribuições

Sinta-se à vontade para abrir issues ou pull requests com sugestões de melhorias.
