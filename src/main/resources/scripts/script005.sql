INSERT INTO product (name, description, active, productFatherId, createProductDate)
VALUES ('Cartao de Credito', 'produto que disponibiliza um credito ao cliente', true, NULL, NOW()),
('Cartao de Debito', 'usa o dinheiro do cliente nas transacoes', true, NULL, NOW())
,('Cheque Especial', 'faz uma divida como se fosse um credito com juros', true, NULL, NOW()),
('Outro Produto', 'Descrição de Outro Produto', true, NULL, NOW());