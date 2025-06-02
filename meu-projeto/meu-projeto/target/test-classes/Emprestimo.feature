Feature: Simulação de Emprestimo
	Scenario: Cliente com score suficiente e renda adequada
		Given um cliente com score 500 e renda mensal 3000
		And deseja um empréstimo de 10000
		When ele solicita um empréstimo
		Then a situação deve ser "Aprovado"
		And o valor da parcela mensal deve ser exibido 