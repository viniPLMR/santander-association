CREATE TABLE product (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  active TINYINT(1) NOT NULL,
  productFatherId BIGINT,
  createProductDate DATETIME
);