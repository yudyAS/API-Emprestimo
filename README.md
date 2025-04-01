# API de Simulação de Empréstimos

Esta é uma API para simulação de empréstimos, permitindo que um usuário envie dados financeiros e receba uma resposta indicando se o empréstimo foi aprovado ou reprovado. Todos os pedidos de simulação são armazenados no banco de dados.

## Tecnologias Utilizadas
- Java 23
- Spring Boot 3.4.3
- Spring Data JPA
- Hibernate
- MySQL
- Swagger (OpenAPI)
- Postman (para testes)

---

## Instalação e Execução

### **1. Clonar o repositório**
```sh
git clone https://github.com/seu-repositorio.git
cd sua-api-emprestimos
```

### **2. Configurar o banco de dados**
Certifique-se de ter um banco MySQL rodando. No arquivo `application.properties` ou `application.yml`, configure as credenciais:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/emprestimos_db
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### **3. Compilar e executar**
```sh
mvn clean install
mvn spring-boot:run
```
A API rodará em: `http://localhost:8081`

---

## Endpoints Disponíveis

### **1. Simular um empréstimo**
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

**Empréstimo Reprovado (Score Baixo):**
```json
{
  "situacao": "REPROVADO",
  "motivo": "Seu score está abaixo do mínimo necessário para solicitar um empréstimo."
}
```

**Empréstimo Reprovado (Valor Excedente):**
```json
{
  "situacao": "REPROVADO",
  "motivo": "Valor solicitado excede o limite permitido."
}
```

Todos os pedidos de empréstimos são armazenados no banco, mesmo os reprovados.

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

## Testando com Postman

1. Abra o Postman e crie uma nova requisição **POST** para `http://localhost:8081/api/v1/simulacoes/emprestimos`
2. No corpo da requisição (**Body - raw - JSON**), insira:
```json
{
  "nomeCompleto": "Maria Souza",
  "valorCreditoSolicitado": 6000.0,
  "score": 280,
  "rendaMensal": 2500.0
}
```
3. Clique em **Send** e veja a resposta JSON.

---

## Documentação com Swagger

A API possui documentação interativa gerada pelo Swagger. Para acessar:
```
http://localhost:8081/swagger-ui/index.html
```

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
