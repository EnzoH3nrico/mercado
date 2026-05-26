# REST API MERCADO

Projeto desenvolvido com Java + Spring Boot, focado na construção de uma API RESTful completa, aplicando conceitos de CRUD, segurança com JWT, boas práticas de arquitetura, e regras de negócio.

A aplicação permite o gerenciamento de produtos e fornecedores, além de contar com um módulo de usuários com autenticação e autorização.

🎯 Objetivo do Projeto

Demonstrar na prática como:

* consumir e construir uma API REST
* Implementar operações de CRUD
* Integrar MySQL com Spring Boot
* Aplicar regras de negócio
* Implementar autenticação e autorização com JWT
* Utilizar Flyway para versionamento do banco de dados
* Documentar endpoints com Swagger
 
# ⚙️ Funcionalidades

## 📦 Produtos
#### Cadastro de itens com:
* Nome
* Marca
* Preço

## 🏢 Fornecedores
#### Cadastro de dados complexos:

* Nome
* CNPJ
* Telefone
## 👤 Usuários

* CRUD completo de usuários
* Autenticação com JWT (JSON Web Token)
* Controle de acesso (segurança)
## 🔐 Segurança

* Implementação de autenticação via JWT
* Proteção de rotas
* Boas práticas com Spring Security

## 🧠 Regras de Negócio

* Validação de dados antes de persistência
* Estrutura organizada em camadas (Controller, Service, Repository)
* Tratamento de exceções
## 🗄️ Banco de Dados

* MySQL como banco relacional
* Versionamento com Flyway
* Scripts de migração controlados
## 📄 Documentação da API

A API possui documentação interativa via Swagger, permitindo:

* Visualizar endpoints
* Testar requisições diretamente no navegador

### Acesse após subir a aplicação:
http://localhost:8080/swagger-ui.html
## 🚀 Tecnologias Utilizadas
* Java 17+
* Spring Boot
* Spring Framework
* Spring Security
* JWT
* MySQL
* Flyway
* Maven
* Swagger (OpenAPI)
