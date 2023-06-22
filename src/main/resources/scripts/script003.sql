CREATE TABLE problem_product (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  problem_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  create_problem_product DATETIME,
  is_active TINYINT(1) NOT NULL,
  FOREIGN KEY (problem_id) REFERENCES problem (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE ON UPDATE CASCADE
);