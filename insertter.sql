insert into public.t_categories (id, name)
values (1, 'Sport complex'),
       (2, 'Library'),
       (3, 'University'),
       (4, 'Mosques'),
       (5, 'Business center'),
       (6, 'Pharmacy'),
       (7, 'Shopping center'),
       (8, 'Restaurants'),
       (9, 'Hospital'),
       (10, 'Airport'),
       (11, 'Hostel'),
       (12, 'Barber');

insert into public.t_roles (id, name)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

insert into public.t_groups (id, name)
values (1, 'tourist'),
       (2, 'student'),
       (3, 'job-seeker'),
       (4, 'citizen');

insert into public.t_newstype (id, type)
values (1, 'sport'),
       (2, 'vacancy'),
       (3, 'film'),
       (4, 'positive-news'),
       (5, 'space'),
       (6, 'music');

insert into public.t_news (id, content, title, newstype_id)
values (1, 'Google started internship, send your portfolios', 'GOOGLE INTERNSHIP', 2),
       (2, 'SpaceX created new type of rockets', 'New rocket by SpaceX', 5),
       (3, 'Dimash Kudaibergen won on China', 'Winner of China`s got talent', 6),
       (4, 'The President supported the idea of creating a TikTok project aimed at studying the history of the country',
        'Tik-tok project accepted by President', 4);

insert into public.t_places (id, address, name, category_id)
VALUES (1, 'Abay 23', 'Library named after Abay', 2),
       (2, 'Shevchenko 99', 'Pheonix', 1),
       (3, 'Manasa 77', 'IITU', 3),
       (4, 'Bayzakova 3', 'Bayzak center', 5),
       (5, 'Seifullin 22', 'Bro-Barberr', 12),
       (6, 'Zharokov 46', 'Hospital #2', 9),
       (7, 'Nazarbayev 82', 'Shafran', 8),
       (8, 'Dostyk 1', 'Dostyk Plaza', 7),
       (9, 'Momyshuly 100', 'Almaty Airport', 10),
       (10, 'Abay 124', 'Ritz Carlton', 12),
       (11, 'Satbayev 11', 'Bayken', 4),
       (12, 'Al-Farabi 154', 'Europharma', 6);

insert into public.t_users (id, password, username, role_id)
values (1, 'admin', 'admin', 1),
       (2, '111', 'armanS', 2),
       (3, '111', 'armanT', 2),
       (4, '111', 'armanJ', 2),
       (5, '111', 'armanC', 2),
       (6, '111', 'armanST', 2);

insert into public.t_users_t_groups (users_id, groups_id)
VALUES (2, 1),
       (3, 2),
       (4, 3),
       (5, 4),
       (6, 1),
       (6, 2);