set foreign_key_checks = 0;
truncate table product;
truncate table item;
truncate table cart;
truncate table app_user;
truncate table cart_item_list;

insert into product (id, name, price, quantity)
values (12, 'Luxury Map', 2340, 3),
       (13, 'MacBook Air', 18340, 4),
       (14, 'Rocking chair', 5340, 5),
       (15, 'Purple T-shirt', 7340, 7);

insert into item (id, product_id, quantity_added_to_cart)
values (102, 14, 2),
        (122, 15, 3),
        (133, 12, 1);

insert into cart(id, total_Price)
values (345, 0.0),
        (355, 0.0),
        (366, 0.0);

insert into app_user(id, first_name, last_name, email, my_cart_id)
values (5005, 'John', 'Badmus', 'john@email.com', 345),
       (5010, 'Chris', 'Tuck', 'chris@email.com', 355),
       (5011, 'GoodNews', 'Confidence', 'goodconfidence@email.com', 366);

insert into cart_item_list(cart_id, item_list_id)
values (345, 102),
        (345, 122),
        (345, 133);


set foreign_key_checks = 1