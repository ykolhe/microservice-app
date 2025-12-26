INSERT INTO category (id, name, description)
VALUES
    (nextval('category_seq'), 'Computer Keyboards', 'Wired and wireless keyboards'),
    (nextval('category_seq'), 'Monitors', 'Computer monitors'),
    (nextval('category_seq'), 'Display Screen', 'Display and screens'),
    (nextval('category_seq'), 'Mic', 'Microphones and audio input devices'),
    (nextval('category_seq'), 'Accessories', 'Computer accessories');


INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Mechanical Keyboard', 'RGB mechanical keyboard', 20, 4500.00, 1),
    (nextval('product_seq'), 'Wireless Keyboard', 'Bluetooth keyboard', 15, 3200.00, 1),

    (nextval('product_seq'), '24 Inch Monitor', 'Full HD monitor', 10, 12000.00, 51),
    (nextval('product_seq'), '27 Inch Monitor', 'QHD monitor', 8, 22000.00, 51),

    (nextval('product_seq'), 'LED Display Screen', 'LED display panel', 12, 9000.00, 101),

    (nextval('product_seq'), 'USB Microphone', 'Condenser mic', 18, 3500.00, 151),

    (nextval('product_seq'), 'Mouse Pad', 'Large gaming mouse pad', 50, 499.00, 201),
    (nextval('product_seq'), 'USB Hub', '4-port USB hub', 30, 1299.00, 201);
