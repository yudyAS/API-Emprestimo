# language: pt

Funcionalidade: Simulação de Emprestimo

  Esquema do Cenario: Solicitações de emprestimo
    Dado um cliente com score <score> e renda mensal <renda>
    E deseja um empréstimo de <emprestimo>
    Quando ele solicita um empréstimo
    Então a situação deve ser "<situacao>"
    E o valor da parcela mensal deve ser exibido

    Exemplos:
      | score | renda | emprestimo | situacao  |
      | 500   | 3000  | 10000      | APROVADO  |
      | 200   | 3000  | 10000      | REPROVADO |
      | 300   | 3000  | 100000     | REPROVADO |
