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
       (3, 'address 3', 3);

insert into PRODUCT (ID, NAME, BARCODE)
values (1, 'TAEMIN - The 4th Mini Album [Guilty] (Photo Book Ver.) (GUILTY Ver.)', '8804775367427'),
       (2, 'LUXURY 2023.10 (Cover : SHINee : TAEMIN)', '9000000100557'),
       (3, '6v6 베개커버_SPMAB49U22 3', '9000000062610');