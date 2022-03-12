DELETE FROM RECEITA;
ALTER TABLE RECEITA AUTO_INCREMENT=1;

INSERT INTO RECEITA(id,descricao, valor, data_receita) VALUES(null, 'Salário', '4000', '2022-02-05');
INSERT INTO RECEITA(id,descricao, valor, data_receita) VALUES(null,'Projeto paralelo', '1500', '2022-02-04');
INSERT INTO RECEITA(id,descricao, valor, data_receita) VALUES(null, 'Venda celular', '600', '2022-02-03');

DELETE FROM DESPESA;
ALTER TABLE DESPESA AUTO_INCREMENT=1;

INSERT INTO DESPESA(id, descricao, valor, data_despesa) VALUES(null, 'Conta de energia', '230', '2022-03-25');
INSERT INTO DESPESA(id, descricao, valor, data_despesa) VALUES(null, 'Condomínio', '420', '2022-03-16');
INSERT INTO DESPESA(id, descricao, valor, data_despesa) VALUES(null, 'Supermercado', '1000', '2022-03-06');
