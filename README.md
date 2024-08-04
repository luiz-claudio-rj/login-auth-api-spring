# Login Authentication API

## Descrição
Este projeto é uma API de autenticação de login construída com Spring Boot. Ele utiliza JWT (JSON Web Tokens) para autenticação e inclui filtros de segurança personalizados para proteger as rotas da aplicação.

## Tecnologias Utilizadas
- Java
- Spring Boot
- Spring Security
- Maven

## Estrutura do Projeto
- `src/main/java/com/example/login_auth_api/infra/security`: Contém as classes de configuração de segurança e filtros.
- `src/main/java/com/example/login_auth_api/controllers`: Contém os controladores da API.
- `src/main/java/com/example/login_auth_api/domain/user`: Contém as classes de domínio relacionadas ao usuário.
- `src/main/java/com/example/login_auth_api/repository`: Contém os repositórios para acesso ao banco de dados.

## Configuração de Segurança
### SecurityConfig
A classe `SecurityConfig` configura a cadeia de filtros de segurança e define as regras de autorização para diferentes endpoints.

### SecurityFilter
A classe `SecurityFilter` é um filtro de segurança personalizado que valida o token JWT e autentica o usuário.

### CustomUserDetailsService
A classe `CustomUserDetailsService` carrega os detalhes do usuário a partir do banco de dados para autenticação.

## Endpoints
### Autenticação
- `POST /auth/login`: Endpoint para login de usuários.
- `POST /auth/register`: Endpoint para registro de novos usuários.

### Usuário
- `GET /user`: Endpoint protegido que retorna informações do usuário autenticado.

## Como Executar
1. Clone o repositório:
   ```sh
   git clone https://github.com/luiz-claudio-rj/login-auth-api-spring
