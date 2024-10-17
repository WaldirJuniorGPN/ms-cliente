# Microsserviço de Cliente - Tech Challenge

Este projeto faz parte do **Tech Challenge** e tem como objetivo desenvolver um **Microsserviço de Gerenciamento de Clientes** que será utilizado em um **Sistema de Gerenciamento de Pedidos**. Este serviço é responsável pelas operações de criação, leitura, atualização e exclusão (CRUD) de clientes.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot 3.3.4**: Framework para a construção de aplicações Java, facilitando o desenvolvimento de microsserviços.
- **Spring Data JPA**: Integração com banco de dados relacional para simplificar as operações CRUD.
- **MySQL**: Banco de dados utilizado para persistência dos dados de clientes.
- **Lombok**: Redução de código boilerplate com anotações para getters, setters e outras funcionalidades.
- **Hibernate Validator**: Validação de beans.
- **ModelMapper**: Mapeamento de objetos DTO para entidades e vice-versa.
- **OpenAPI (Springdoc)**: Documentação e interface gráfica para explorar as APIs REST.
- **H2 Database**: Banco de dados em memória utilizado para testes.
- **JUnit 5 e Cucumber**: Frameworks para testes unitários e BDD.

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

## Como Executar

### Pré-requisitos:

- JDK 17
- Maven
- Docker (para rodar o MySQL em container, opcional)

### Passos:

1. Clonar o repositório:
   ```bash
   git clone git@github.com:WaldirJuniorGPN/ms-cliente.git
   ```
2. Navegar até a pasta do projeto:
   ```bash
   cd ms-cliente
   ```
3. Rodar o projeto:
   ```bash
   mvn spring-boot:run
   ```

### Endpoints

- **Listar todos os clientes**: `GET /clientes`
- **Buscar cliente por ID**: `GET /clientes/{id}`
- **Criar novo cliente**: `POST /clientes`
- **Atualizar cliente**: `PUT /clientes/{id}`
- **Deletar cliente**: `DELETE /clientes/{id}`

## Estrutura de Banco de Dados

A tabela de clientes será criada com os seguintes campos:

- `id`: Identificador único do cliente.
- `nome`: Nome completo do cliente.
- `email`: Endereço de email.
- `telefone`: Número de telefone.
- `endereco`: Endereço do cliente.

## Como Executar os Testes

Este projeto possui diferentes perfis de testes configurados no Maven. Você pode executar testes unitários, de integração e de BDD (Behavior-Driven Development) com os seguintes comandos:

### Executando Testes Unitários e de Integração

```bash
mvn test -Punit-integration-tests
```

Esse comando executa os testes unitários e de integração.

### Executando Testes BDD

```bash
mvn test -Psystem-test
```

Esse comando executa os testes BDD usando o Cucumber.

### Executando Todos os Testes

```bash
mvn test
```

## Documentação da API

A documentação da API está disponível via **OpenAPI** (Springdoc). Após rodar o projeto, você pode acessá-la em:

```
http://localhost:8080/swagger-ui.html
```

## Contribuições

Sinta-se à vontade para abrir issues ou pull requests com sugestões de melhorias.

---