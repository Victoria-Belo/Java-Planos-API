➔ PROJETO SYRINX - API PLANO DE SAÚDE

➔ OBJETIVO: Aplicação APIREST com SpringBoot.

➔ DESAFIO: Desenvolver uma APIREST que torne cadastre, busque, edite e exclua algumas entidades que se correlacionam no âmbito do plano de saúde.

➔ DESCRIÇÃO: Aplicação APIREST para planos de saúde

➔ STATUS: Funcionando, sujeito a alteração;

[Observações]

A tabela plans possui os planos
A tabela prices possui os valores para cada plano listado
A tabela beneficiaries possui clientes responsáveis pelo plano  
A tabela dependents possui dependentes do beneficiário principal
Cada plano tem três faixas de preços, sendo estas categorizadas por idade:
  Faixa 1: Clientes até 17 anos
  Faixa 2: Clientes de 18 a 40 anos
  Faixa 3: Clientes com mais de 40 anos
[ATENÇÃO] Cada plano pode possuir preços variados dependendo do mínimo de pessoas participantes, sendo representada na tabela de preços pela coluna 'minimovidas'.


