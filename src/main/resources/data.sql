INSERT INTO item 
(id, name, price) 
VALUES 
(1, 'Original Veggie Burger', 10),
(2, 'Small Veggie Burger', 9),
(3, 'Medium Veggie Burger', 11),
(4, 'Big Veggie Burger', 13),
(5, 'Family Veggie Burger', 18);

INSERT INTO customer
(id, first_name, last_name, address)
VALUES
(1, 'Bruno', 'Gasparotto', '208 Paulista Avenue, Sao Paulo - SP. Brazil'),
(2, 'John', 'Wick', '1400 Faria Lima, Sao Paulo - SP. Brazil'),
(3, 'Rose', 'Tyler', '300 Casa Verde Avenue, Sao Paulo - SP. Brazil');

INSERT INTO purchase
(id, date, total_value, customer_id)
VALUES
(1, '2017-12-01 07:01:01', 30, 1),
(2, '2017-12-01 07:02:01', 19, 1),
(3, '2017-12-01 07:03:01', 38, 1),
(4, '2017-12-01 07:04:01', 10, 2),
(5, '2017-12-01 07:05:01', 43, 2);

INSERT INTO purchase_items
(purchase_id, items_id)
VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 2),
(3, 1), (3, 1), (3, 2), (3, 2),
(4, 1),
(5, 1), (5, 2), (5, 3), (5, 4);