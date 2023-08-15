insert into ORDERS (ID, USER_ID)
values (1, 1),
       (2, 1),
       (3, 1);

insert into ORDERED_PRODUCTS (ID, NAME, PRICE, ORDER_ID)
values (1, 'product 1', 1000, 1),
       (2, 'product 2', 900, 1),
       (3, 'product 3', 1500, 2),
       (4, 'product 4', 2000, 3),
       (5, 'product 1', 1000, 3);

insert into SHIPPINGS (ID, ADDRESS, ORDER_ID)
values (1, 'address 1', 1),
       (2, 'address 2', 2),
       (3, 'address 3', 3)