# Microsserviço de Cliente - Tech Challenge

Este projeto faz parte do **Tech Challenge** e tem como objetivo desenvolver um **Microsserviço de Gerenciamento de Clientes** que será utilizado em um **Sistema de Gerenciamento de Pedidos**. Este serviço é responsável pelas operações de criação, leitura, atualização e exclusão (CRUD) de clientes.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot 3.3.4**: Framework para a construção de aplicações Java, facilitando o desenvolvimento de microsserviços.
- **Spring Security**: Implementação de autenticação e autorização, utilizando JWT (JSON Web Token) para segurança.
- **Spring Data JPA**: Integração com banco de dados relacional para simplificar as operações CRUD.
- **MySQL**: Banco de dados utilizado para persistência dos dados de clientes.
- **Lombok**: Redução de código boilerplate com anotações para getters, setters e outras funcionalidades.
- **Hibernate Validator**: Validação de beans.
- **ModelMapper**: Mapeamento de objetos DTO para entidades e vice-versa.
- **OpenAPI (Springdoc)**: Documentação e interface gráfica para explorar as APIs REST.
- **H2 Database**: Banco de dados em memória utilizado para testes.
- **JUnit 5 e Cucumber**: Frameworks para testes unitários e BDD.
- **Docker**: Containerização da aplicação para facilitar o deploy em diferentes ambientes.

## Funcionalidades

Este microsserviço será responsável por:
- Cadastro de clientes.
- Edição dos dados de clientes.
- Exclusão de clientes.
- Consulta de informações de clientes.
- Autenticação e autorização utilizando JWT.

## Estrutura do Projeto

- **Group**: `com.techchallenge4`
- **Artifact**: `ms-cliente`
- **Nome**: Microsserviço de Cliente
- **Descrição**: Microsserviço responsável pela gestão de clientes no sistema.
- **Banco de Dados**: MySQL, acessado via Spring Data JPA.

## Como Executar a Aplicação Localmente

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

### Utilizando Docker

Para rodar a aplicação dentro de um container Docker, siga os passos abaixo:

1. **Criar a imagem Docker**:

   No diretório raiz do projeto, execute o comando:
   ```bash
   docker build -t ms-cliente .
   ```

2. **Rodar o container**:

   Após a construção da imagem, rode o container com o comando:
   ```bash
   docker run -p 8080:8080 ms-cliente
   ```

Isso irá iniciar o microsserviço no endereço `http://localhost:8080`.

### Dockerfile

O Dockerfile utilizado para construir a imagem do microsserviço está configurado para:

1. Utilizar uma imagem base do Maven para compilar e construir o projeto.
2. Copiar os arquivos do projeto para o container e gerar o pacote JAR.
3. Utilizar uma imagem base do OpenJDK 17 para rodar o microsserviço a partir do arquivo JAR gerado.

Aqui está o conteúdo do Dockerfile:

```Dockerfile
FROM maven:3.8.3-openjdk-17-slim AS build
COPY /src /ms-cliente/src
COPY /pom.xml /ms-cliente
WORKDIR /ms-cliente
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY --from=build /ms-cliente/${JAR_FILE} /ms-cliente/ms-cliente.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/ms-cliente/ms-cliente.jar"]
```

## Segurança

O projeto implementa autenticação JWT utilizando o **Spring Security**. As seguintes rotas são protegidas e exigem um token JWT válido para acessar:

- **Rotas protegidas**: Todas as rotas, exceto `/clientes`, `/login`, e as rotas relacionadas ao Swagger (`/swagger-ui/**`, `/v3/api-docs/**`).

Os tokens JWT são gerados e validados pela aplicação, garantindo que apenas usuários autenticados possam acessar as rotas protegidas.

### Autenticação e Autorização

O fluxo de autenticação funciona da seguinte forma:

1. O usuário se autentica enviando suas credenciais para o endpoint `/login`.
2. Se as credenciais estiverem corretas, a aplicação retorna um JWT.
3. O usuário utiliza esse JWT para acessar as rotas protegidas, incluindo as operações de CRUD de clientes.

## Endpoints

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
