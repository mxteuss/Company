# 🏢 Company — Sistema de Gerenciamento de Funcionários

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)


Aplicação back-end desenvolvida em **Java com Spring Boot** para gerenciamento de funcionários.  
O sistema implementa **autenticação JWT**, controle de acesso por papéis (Admin e Usuário), e persistência em **PostgreSQL**, garantindo segurança e escalabilidade.


## ✨ Funcionalidades

### Usuário
- Autenticação via JWT.
- Acesso aos próprios dados.

### Administrador
- CRUD completo de funcionários (criar, listar, atualizar e deletar).
- Visualização de relatórios da empresa.
- Controle de permissões e papéis de usuários.

### ⚙️ Gerais
- Persistência de dados com PostgreSQL.
- Arquitetura **Stateless**.
- Integração com Spring Security e Spring Data JPA.

## 🛠️ Tecnologias utilizadas
- **Java 17**
- **Spring Boot 3+**
- **Spring Security** (autenticação e autorização)
- **Spring Data JPA** (persistência)
- **JWT (JSON Web Token)** (controle de sessão Stateless)
- **PostgreSQL** (banco de dados relacional)
- **Maven** (gerenciamento de dependências)

## ⚙️ Como executar o projeto

1. **Clone o repositório**
   ```bash
   git clone https://github.com/mxteuss/company.git
   cd company


2. **Configurar banco no PostgreSQL**
   ```shell
   CREATE DATABASE company;
   ```
   A configuração do banco está para Postgre, então o application properties deve estar assim:
    ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/company
   spring.datasource.username=root
   spring.datasource.password=root
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   ```
 
3. **Execute a aplicação**
   ```shell
   ./mvnw spring-boot:run
   ```

4. **Acesse a API**
   ```shell
   http://localhost:8080   
   ```

## 🏛️ Endpoints principais

| Método | Endpoint | Descrição | Permissão |
|--------|-----------|------------|------------|
| POST | `/auth/login` | Autentica o usuário e retorna o token JWT | Público |
| POST | `/auth/register` | Registra um usuário e persiste os dados no banco | Público |
| GET | `/employees/report` | Gera relatório da empresa | Admin |
| POST | `/employees/save` | Cria um novo funcionário | Admin |





> 🏛️ Projeto em desenvolvimento — novas features de relatórios e controle de departamentos em breve!

