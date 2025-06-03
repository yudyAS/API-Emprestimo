# language: pt

	Funcionalidade: Simulação de Emprestimo
		Cenario: Cliente com score suficiente e renda adequada
			Dado um cliente com score 500 e renda mensal 3000
			E deseja um emprestimo de 10000
			Quando ele solicita um empréstimo
			Então a situação deve ser "APROVADO"
			E o valor da parcela mensal deve ser exibido