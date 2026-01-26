# 📌 ms-simulador-pix

![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Tests](https://img.shields.io/badge/tests-100%25-success)
![License](https://img.shields.io/badge/license-MIT-blue)

Microserviço em **Spring Boot** para simulação de autorização de transações **Pix**.  
Este projeto fornece uma API simples e open source para testar integrações e fluxos de pagamento **sem depender dos sistemas oficiais**.

---

## 📖 Sobre o projeto

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.2.2
- **Banco de dados:** H2 (em memória)

### Funcionalidades principais
- Autorização de transações Pix
- Consulta de transações
- Estorno (refund)
- Validação de chave Pix

---

## ▶️ Como executar

### 1️⃣ Clone o repositório
```bash
git clone https://github.com/seu-usuario/ms-simulador-pix.git
cd ms-simulador-pix
```
2️⃣ Compile e execute com Maven
```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em:
👉 http://localhost:8080

3️⃣ Rodar os testes
```bash
mvn test
```
👉 http://localhost:8080/h2-console

Credenciais:

JDBC URL: jdbc:h2:mem:pixdb

User Name: sa

Password: (em branco)

Após conectar, você poderá visualizar a tabela pix_transaction.

📌 Exemplos de uso (JSON)
🔹 Autorizar transação

Request
```bash
{
  "key": "teste@pix.com",
  "amount": 100.0,
  "description": "Pagamento de teste"
}
```
Response

```bash
{
  "id": 1,
  "key": "teste@pix.com",
  "amount": 100.0,
  "description": "Pagamento de teste",
  "status": "AUTHORIZED"
}
```

🔹 Listar transações

```bash
[
  {
    "id": 1,
    "key": "teste@pix.com",
    "amount": 100.0,
    "description": "Pagamento de teste",
    "status": "AUTHORIZED"
  },
  {
    "id": 2,
    "key": "cliente@pix.com",
    "amount": 50.0,
    "description": "Compra de produto",
    "status": "REFUNDED"
  }
]
```
🔹 Consultar transação

```bash
{
  "id": 1,
  "key": "teste@pix.com",
  "amount": 100.0,
  "description": "Pagamento de teste",
  "status": "AUTHORIZED"
}
```
🔹 Estornar transação

```bash
{
  "transactionId": "1"
}
```
Response

```bash
{
  "id": 1,
  "key": "teste@pix.com",
  "amount": 100.0,
  "description": "Pagamento de teste",
  "status": "REFUNDED"
}
```
🔹 Validar chave Pix

```bash
{
  "key": "teste@pix.com",
  "valid": true
}
```
📚 Documentação com Swagger / OpenAPI

    O projeto inclui documentação automática da API usando Swagger UI.
    Após iniciar a aplicação, acesse:
    
    👉 http://localhost:8080/swagger-ui.html


📜 Licença

    Este projeto é open source sob a licença MIT.
Sinta-se livre para usar, modificar e contribuir! 🎉
