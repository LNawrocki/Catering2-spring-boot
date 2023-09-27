# CREATE DATABASE catering CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use catering;

INSERT INTO catering.department (id, name, payment_perc) VALUES (1, 'Biuro', 50);
INSERT INTO catering.department (id, name, payment_perc) VALUES (2, 'Produkcja', 90);
INSERT INTO catering.department (id, name, payment_perc) VALUES (3, 'Lakiernia', 10);

INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (1, true, 'Nawrocki', 'admin', 'Łukasz', '$2a$10$SrqpdU.KRWLbhU2kdwSs2uoG5Ks1lr6yesD8fv6oj.F23qcHQ7fkq', true, 1);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (2, true, 'nazwisko produkcja', 'produkcja', 'imie produkcja', '$2a$10$yaQOjsXb5O235OZ/hHV3y.BrdUWXRNWVfOZNYNZb/f2nUJwxQrm0u', false, 2);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (3, true, 'nazwisko lakiernia', 'lakiernia', 'imie lakiernia', '$2a$10$caF6nVeZYKoLdxlt4inHGu0FV/w4hf6m.oRoKPvgr3syniOdh6wAi', false, 3);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (4, true, 'biuro', 'biuro', 'biuro', '$2a$10$CY9H.S.L/2Ye3LjFvbk1ieg5puht41NIm10/RJk3P1JD79Lt3VDSq', false, 1);


INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (1, 1, 'Brak', 0.00);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (2, 1, 'Bułka z szynką, serem i warzywami', 7.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (3, 1, 'Bułka z szynką, serem i warzywami x2', 14.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (4, 1, 'Kartacze z mięsem ze skwarkami i cebulką', 14.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (5, 1, '"Danie voka" kurczak +  ryż + warzywa z  patelni', 14.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (6, 1, 'Sznycel w sosie pieczarkowym + ziemniaki + surówka z białej kapusty i warzyw', 18.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (7, 1, 'Shoarma drobiowa + frytki + kapusta pekińska z sosiem', 18.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (8, 1, 'Pierś drobiowa faszerowana szpinakiem pieczona w cieście francuskim + surówka grecka kolorowa – sos vinegret', 18.10);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (9, 2, 'Brak', 0.00);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (10, 2, 'Tarta z warzywami (brokuły, pomidror, ser) /sos', 14.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (11, 2, 'Naleśniki z serem zapiekane z budyniem  w foremce ', 14.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (12, 2, 'Lazania z mięsem zapiekana w foremce', 14.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (13, 2, 'Szaszłyk drobiowy z warzywami , mix sałat , sos + ryż brązowy + zestaw surówek', 18.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (14, 2, 'Zapiekana pierś z kurczaka z pieczarkami i serem + ziemniaki + mizeria ogórkowa ze śmietaną i koperkiem', 18.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (15, 2, 'Wegetariański kociołek warzywny + kluski gnioczki  + zestaw surówek', 18.20);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (16, 3, 'Brak', 0.00);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (17, 3, 'Sandwicze z pieczarkami, serem i szynką zapiekane', 14.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (18, 3, 'Zestaw śniadaniowy z sałatką jajeczną , wędliną, serem, warzywami /pieczywo ', 14.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (19, 3, 'PILAV - ryż z warzywami i mięsem wieprzowym zapiekany w foremce', 14.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (20, 3, 'Roladka drobiowa w sosie śmietanowo-koperkowym + ryż dziki + sałatka z czerwonych buraczków', 18.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (21, 3, 'Nagetsy z kurczaka + ziemnaki opiekane + mix sałat', 18.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (22, 3, 'Burger vege z warzywami i dodatkami', 18.30);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (23, 4, 'Brak', 0.00);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (24, 4, 'Bułka z pastą z kurczaka  i dodatkami', 7.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (25, 4, 'Bułka z pastą z kurczaka  i dodatkami x2', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (26, 4, 'Czeburaki ukraińskie z mięsem mielonym smażone', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (27, 4, 'Łazanki z mięsem i warzywami', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (28, 4, 'Rumsztyk wieprzowy z cebulką i pieczarkami + ziemniaki + sałatka z czerwonej kapusty', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (29, 4, 'Jesienna "szakszuka" fasolka szparagowa, pierś z kurczaka, cukinia, papryka, makaron pełnoziarnisty, sos meksykański', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (30, 4, 'Szpinakowy kotlecik z serem + ryż brązowy + zestaw sałatek', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (31, 5, 'Brak', 0.00);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (32, 5, 'Bułka z pasta jajeczną i warzywami', 7.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (33, 5, 'Bułka z pasta jajeczną i warzywami x2', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (34, 5, 'Sałatka grecka z warzywami i jajkiem / pieczywo', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (35, 5, 'Żurek z jakiem i kiełbasą / pieczywo', 14.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (36, 5, 'Ryba dorsz zapiekany w sosie "alla puttanesca" + ziemniaki + surówka z kiszonej kapusty', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (37, 5, 'Morszczuk w cieście naleśnikowym + ziemniaki + zestaw surówek', 18.40);
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (38, 5, 'Uwijaczki drobiowe z serem i kolorową papryką + ziemniaki + zestaw surówek', 18.40);


