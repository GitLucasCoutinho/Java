# ocooldev-security-jwt

Projeto Spring Boot de autenticação e autorização com JWT (JSON Web Token).

## Pré-requisitos
- Java 17+
- Maven
- (Opcional) H2 Console para visualizar o banco em memória

## Como rodar o projeto

1. **Clone o repositório:**
   ```sh
   git clone <url-do-repo>
   cd ocooldev-security-jwt
   ```
2. **Execute o projeto:**
   ```sh
   mvn spring-boot:run
   ```
   O projeto sobe em `http://localhost:8081`.

3. **Acesse o H2 Console (opcional):**
   - URL: `http://localhost:8081/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `sa` | Senha: (em branco)

---

## Fluxo de uso (exemplo prático)

### 1. Cadastro de usuário

**Requisição:**
```
POST http://localhost:8081/users
Content-Type: application/json

{
  "name": "bad",
  "username": "badbunny",
  "password": "123123",
  "roles": ["USER", "MANAGER"]
}
```
**Resposta:**
- 200 OK (usuário criado)
- 409 Conflict (usuário já existe)

---

### 2. Login

**Requisição:**
```
POST http://localhost:8081/login
Content-Type: application/json

{
  "username": "badbunny",
  "password": "123123"
}
```
**Resposta:**
Para verificar informações do token acesse  https://www.jwt.io/
```json
{
  "login": "badbunny",
  "token": "Bearer <jwt-token>"
}
```

---

### 3. Acesso a rotas protegidas

#### A) Rota `/users` (USER ou MANAGER)
**Requisição:**
```
GET http://localhost:8081/users
Authorization: Bearer <jwt-token>
```
**Resposta:**
- 200 OK: `Authorized user`
- 403 Forbidden: sem permissão

#### B) Rota `/users/managers` (apenas MANAGER)
**Requisição:**
```
GET http://localhost:8081/users/managers
Authorization: Bearer <jwt-token>
```
**Resposta:**
- 200 OK: `Authorized manager`
- 403 Forbidden: sem permissão

---

## Observações
- O token JWT deve ser enviado no header `Authorization` com o prefixo `Bearer`.
- O banco é em memória (H2) e será resetado a cada reinício da aplicação.
- As roles devem ser cadastradas como `USER`, `MANAGER`, etc. O sistema adiciona o prefixo `ROLE_` automaticamente.

---

## Exemplos de usuário

```json
{
  "name": "bad",
  "username": "badbunny",
  "password": "123123",
  "roles": ["USER", "MANAGER"]
}
```

---

## Dúvidas
Abra uma issue ou entre em contato.

