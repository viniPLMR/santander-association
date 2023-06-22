INSERT INTO problem_product (problem_id, product_id, create_problem_product, is_active)
SELECT p.id, pr.id, NOW(), true
FROM problem p, product pr
WHERE p.name = 'Problema Cartao debito' AND pr.name = 'Cartao de Debito';
