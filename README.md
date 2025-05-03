# API de Simulação de Empréstimos

Esta é uma API para simulação de empréstimos, permitindo que um usuário envie dados financeiros e receba uma resposta indicando se o empréstimo foi aprovado ou reprovado. Todos os pedidos de simulação são armazenados no banco de dados.

## Tecnologias Utilizadas
- Java 23
- Spring Boot 3.4.3
- Spring Data JPA
- Hibernate
- Postman (para testes)
---

## Instalação e Execução

### **1. Clonar o repositório**
```sh
git clone https://github.com/seu-repositorio.git
cd sua-api-emprestimos
```

### **2. Configuração do Banco de Dados H2**
O banco de dados H2 é configurado automaticamente com o Spring Boot. Para acessar a interface do banco de dados:
#### - Execute o projeto.


#### - Acesse a URL: http://localhost:8080/h2-console.


#### - Use as seguintes credenciais de conexão:
```properties
JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (deixe em branco)
```

### **3. Compilar e executar**
```sh
mvn clean install
mvn spring-boot:run
```
A API rodará em: `http://localhost:8080`

---
### Desenho da Aplicação 
![Image](https://github.com/user-attachments/assets/328468ff-74e0-47c4-bfdf-6d0e92a0ab39)
---

## Endpoints Disponíveis
### **1. Adicionar novo cliente**
#### **POST `/api/clientes`**
![Image](https://github.com/user-attachments/assets/da9fc278-dd1a-4577-9eb7-a845ee8e098c)
---
### **Listar todos os clientes**
#### **GET `/api/clientes`**
![Image](https://github.com/user-attachments/assets/7ec57236-3dbb-45a1-86ec-042048bddab9)
---
### **2. Simular um empréstimo**
#### **POST `/api/v1/simulacoes/emprestimos`**

#### **Request Body (JSON)**
```json
{
  "nomeCompleto": "João da Silva",
  "valorCreditoSolicitado": 3000.0,
  "score": 650,
  "rendaMensal": 2500.0
}
```

#### **Respostas Possíveis**

**Empréstimo Aprovado:**
```json
{
  "situacao": "APROVADO",
  "valorAprovado": 3000.0,
  "jurosAnoPercentual": 7.0,
  "quantidadeMaximaParcelas": 12,
  "valorParcelaMensal": 259.58
}
```
![Image](https://github.com/user-attachments/assets/552f5a2e-d206-42dc-a14b-6e3a068e687b)
---

**Empréstimo Reprovado (Score Baixo):**
```json
{
  "situacao": "REPROVADO",
  "motivo": "Seu score está abaixo do mínimo necessário para solicitar um empréstimo."
}
```
![Image](https://github.com/user-attachments/assets/755a9167-2f19-44af-a243-59b3cc3463e1)
---

**Empréstimo Reprovado (Valor Excedente):**
```json
{
  "situacao": "REPROVADO",
  "motivo": "Valor solicitado excede o limite permitido."
}
```
![Image](https://github.com/user-attachments/assets/c6cbc2ef-f32d-46b9-9c02-ccedfbcd2fe1)
---
### **Regras**
- O nome tem que ter entre 15 e 50 caracteres
- Score não pode ser menor que 1 e maior que 1000
#### Ilustração do erro que sera mostrado
![Image](https://github.com/user-attachments/assets/629e68a1-0d0e-4d98-9c11-bb5ca480a8de)
---
### **3. Listar todos os empréstimos**
#### **GET `/api/v1/simulacoes/emprestimos`**

![Image](https://github.com/user-attachments/assets/64dde23d-9d18-4993-a0ca-b1e8212e7c07)



### Todos os pedidos de empréstimos são armazenados no banco, mesmo os reprovados.

---
![Image](https://github.com/user-attachments/assets/2a2cea46-20d1-4d0c-8d13-a550e425b48d)
![Image](https://github.com/user-attachments/assets/3dc698e1-df5d-413f-94aa-d4c981cac0e8)
---

## Modelo do Banco de Dados

A API utiliza um modelo relacional para armazenar os dados:

### **Tabela `cliente`**
| Campo       | Tipo      | Descrição                   |
|------------|----------|-----------------------------|
| id         | Long     | ID do cliente              |
| nomeCompleto | String  | Nome do cliente           |
| rendaMensal | Double  | Renda mensal do cliente   |
| score      | Integer  | Score de crédito          |

### **Tabela `emprestimo`**
| Campo                 | Tipo    | Descrição                              |
|-----------------------|--------|--------------------------------------|
| id                   | Long   | ID do empréstimo                   |
| status               | String | "APROVADO" ou "REPROVADO"          |
| valorCreditoSolicitado | Double | Valor do empréstimo solicitado    |
| jurosAnoPercentual   | Double | Percentual de juros anual          |
| quantidadeMaximaParcelas | Integer | Número máximo de parcelas      |
| valorParcelaMensal   | Double | Valor estimado da parcela mensal   |
| cliente_id           | Long   | Referência ao cliente associado   |



---

## Melhorias Futuras
- Implementar autenticação (JWT)
- Adicionar testes unitários com JUnit e Mockito
- Criar endpoint para listar todos os empréstimos de um cliente
- Adicionar novas regras para taxas de juros variáveis

---

## Autor
- **Yudy Antunes dos Santos**
- Desenvolvedor Backend Jr.
- [GitHub](https://github.com/yudyAS)  |  [LinkedIn](https://linkedin.com/in/yudy-antunes-224650336/)
