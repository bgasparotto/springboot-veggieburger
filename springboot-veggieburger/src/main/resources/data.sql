INSERT INTO item 
(id, name, price) 
VALUES 
(1, 'Original Veggie Dog', 22),
(2, 'Small Veggie Dog', 21),
(3, 'Medium Dog', 23),
(4, 'Big Veggie Dog', 24),
(5, 'Family Veggie Dog', 32);

INSERT INTO customer
(id, name, address)
VALUES
(1, 'Bruno', '208 Paulista Avenue, Sao Paulo - SP. Brazil'),
(2, 'John', '1400 Faria Lima, Sao Paulo - SP. Brazil'),
(3, 'Rose', '300 Casa Verde Avenue, Sao Paulo - SP. Brazil');

INSERT INTO purchase
(id, date, total_value, customer_id)
VALUES
(1, '2017-12-01 07:01:01', 80, 1),
(2, '2017-12-01 07:02:01', 80, 1),
(3, '2017-12-01 07:03:01', 80, 1),
(4, '2017-12-01 07:04:01', 80, 2),
(5, '2017-12-01 07:05:01', 80, 2);

INSERT INTO purchase_items
(purchase_id, items_id)
VALUES
(1, 1), (1, 2), (1,3),
(2, 1), (2, 2),
(3,1),(3, 1), (3, 2), (3,2),
(4, 1),
(5, 1), (5,2), (5, 3), (5, 4), (5,5);