-- Create schema 'documents' if not exist
CREATE SCHEMA IF NOT EXISTS customers;

-- Create table 'products'

CREATE TABLE IF NOT EXISTS customers.products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_category VARCHAR(255) NOT NULL,
    is_available BOOLEAN NOT NULL,
    product_price DECIMAL(10,2) NOT NULL
);



-- Giving access to schema and tables
GRANT ALL PRIVILEGES ON SCHEMA customers TO admin;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA customers TO admin;


INSERT INTO customers.products (product_name, product_category, is_available, product_price) VALUES
('Laptop Gamer', 'Electrónica', TRUE, 1599.99),
('Silla Ergonómica', 'Muebles', TRUE, 299.50),
('Cámara DSLR', 'Fotografía', FALSE, 899.00),
('Smartwatch Deportivo', 'Accesorios', TRUE, 199.99),
('Cafetera Automática', 'Electrodomésticos', FALSE, 120.75),
('Teclado Mecánico', 'Computación', TRUE, 89.90),
('Audífonos Inalámbricos', 'Audio', TRUE, 149.99),
('Bicicleta de Montaña', 'Deportes', TRUE, 1200.00),
('Monitor 4K', 'Electrónica', FALSE, 450.00),
('Mochila Antirrobo', 'Accesorios', TRUE, 65.00);
