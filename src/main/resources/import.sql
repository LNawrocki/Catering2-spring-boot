# CREATE DATABASE catering CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use catering;

INSERT INTO catering.department (id, name, payment_perc) VALUES (1, 'Biuro', 30);
INSERT INTO catering.department (id, name, payment_perc) VALUES (2, 'Produkcja', 50);
INSERT INTO catering.department (id, name, payment_perc) VALUES (3, 'Lakiernia', 80);

INSERT INTO catering.users (department_id, last_name, login, name, password, super_admin, user_id) VALUES (1, 'Nawrocki', 'admin', 'Łukasz', '$2a$10$C51xjC3nufMwRMoKC9DOaOj2vyShpFvxXnKqDbAMNTv8P1j4qO5NK', true, 1); #hasło admin
INSERT INTO catering.users (department_id, last_name, login, name, password, super_admin, user_id) VALUES (1, 'Nawro2', 'user', 'Luk2', '$2a$10$dVzs8LDCb4KrdmLpGOZu2.isIzqS93aSi161B72XRNXC1Gb4BY3DO', false, 2); #hasło user
INSERT INTO catering.users (department_id, last_name, login, name, password, super_admin, user_id) VALUES (2, 'Nawro3', 'user1', 'Luk2', '$2a$10$dVzs8LDCb4KrdmLpGOZu2.isIzqS93aSi161B72XRNXC1Gb4BY3DO', false, 3); #hasło user
INSERT INTO catering.users (department_id, last_name, login, name, password, super_admin, user_id) VALUES (3, 'Nawro3', 'user2', 'Luk2', '$2a$10$dVzs8LDCb4KrdmLpGOZu2.isIzqS93aSi161B72XRNXC1Gb4BY3DO', false, 4); #hasło user

# INSERT INTO catering.user_orders (id, kw, user_id, user_meal_thu, user_meal_fri, user_meal_mon, user_meal_tue, user_meal_wed, user_price_fri, user_price_mon, user_price_thu, user_price_tue, user_price_wed, user_qty_fri, user_qty_mon, user_qty_thu, user_qty_tue, user_qty_wed, user_shift_fri, user_shift_mon, user_shift_thu, user_shift_tue, user_shift_wed) VALUES (1, 38, 1, 'umthu', 'umfri', 'ummon', 'umtue', 'umwed', 3.5, 4.5, 5.5, 6.5, 7.5, 1, 1, 1, 1, 1, 5, 1, 4, 2, 3);

# INSERT INTO catering.orders (id, kw, paid, to_pay, order_meal_thu, order_meal_fri, order_meal_mon, order_meal_tue, order_meal_wed, order_price_fri, order_price_mon, order_price_thur, order_price_tue, order_price_wed, order_qty_fri, order_qty_mon, order_qty_thu, order_qty_tue, order_qty_wed, order_shift_fri, order_shift_mon, order_shift_thu, order_shift_tue, order_shift_wed, user_id) VALUES (1, 38, true, 15.2, 'om_thursday1', 'om_friday1', 'om_monday1', 'om_tue', 'om_wed', 10, 20, 30, 40, 50, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1);

INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (1, 1, 'Bułka z szynką, serem i warzywami', 7.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (2, 1, 'Kartacze z mięsem ze skwarkami i cebulką', 14.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (3, 1, '"Danie voka" kurczak +  ryż + warzywa z  patelni', 14.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (4, 1, 'Sznycel w sosie pieczarkowym + ziemniaki + surówka z białej kapusty i warzyw', 18.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (5, 1, 'Shoarma drobiowa + frytki + kapusta pekińska z sosiem', 18.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (6, 1, 'Pierś drobiowa faszerowana szpinakiem pieczona w cieście francuskim + surówka grecka kolorowa – sos vinegret', 18.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (7, 1, 'Brak', 0.00);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (8, 2, 'Tarta z warzywami (brokuły, pomidror, ser) /sos', 14.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (9, 2, 'Naleśniki z serem zapiekane z budyniem  w foremce ', 14.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (10, 2, 'Lazania z mięsem zapiekana w foremce', 14.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (11, 2, 'Szaszłyk drobiowy z warzywami , mix sałat , sos + ryż brązowy + zestaw surówek', 18.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (12, 2, 'Zapiekana pierś z kurczaka z pieczarkami i serem + ziemniaki + mizeria ogórkowa ze śmietaną i koperkiem', 18.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (13, 2, 'Wegetariański kociołek warzywny + kluski gnioczki  + zestaw surówek', 18.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (14, 2, 'Brak', 0.00);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (15, 3, 'Sandwicze z pieczarkami, serem i szynką zapiekane', 14.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (16, 3, 'Zestaw śniadaniowy z sałatką jajeczną , wędliną, serem, warzywami /pieczywo ', 14.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (17, 3, 'PILAV - ryż z warzywami i mięsem wieprzowym zapiekany w foremce', 14.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (18, 3, 'Roladka drobiowa w sosie śmietanowo-koperkowym + ryż dziki + sałatka z czerwonych buraczków', 18.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (19, 3, 'Nagetsy z kurczaka + ziemnaki opiekane + mix sałat', 18.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (20, 3, 'Burger vege z warzywami i dodatkami', 18.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (21, 3, 'Brak', 0.00);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (22, 4, 'Bułka z pastą z kurczaka  i dodatkami', 7.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (23, 4, 'Czeburaki ukraińskie z mięsem mielonym smażone', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (24, 4, 'Łazanki z mięsem i warzywami', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (25, 4, 'Rumsztyk wieprzowy z cebulką i pieczarkami + ziemniaki + sałatka z czerwonej kapusty', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (26, 4, 'Jesienna "szakszuka" fasolka szparagowa, pierś z kurczaka, cukinia, papryka, makaron pełnoziarnisty, sos meksykański', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (27, 4, 'Szpinakowy kotlecik z serem + ryż brązowy + zestaw sałatek', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (28, 4, 'Brak', 0.00);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (29, 5, 'Bułka z pasta jajeczną i warzywami', 7.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (30, 5, 'Sałatka grecka z warzywami i jajkiem / pieczywo', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (31, 5, 'Żurek z jakiem i kiełbasą / pieczywo', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (32, 5, 'Ryba dorsz zapiekany w sosie ''''alla puttanesca'''' + ziemniaki + surówka z kiszonej kapusty', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (33, 5, 'Morszczuk w cieście naleśnikowym + ziemniaki + zestaw surówek', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (34, 5, 'Uwijaczki drobiowe z serem i kolorową papryką + ziemniaki + zestaw surówek', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (35, 5, 'Brak', 0.00);

