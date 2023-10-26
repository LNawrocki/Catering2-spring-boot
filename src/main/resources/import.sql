# CREATE DATABASE catering CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use catering;

INSERT INTO catering.config (id, edit_mode, new_menu_avaliable) VALUES (1, false, false);

INSERT INTO catering.department (id, name, payment_perc) VALUES (1, 'Biuro', 50);
INSERT INTO catering.department (id, name, payment_perc) VALUES (2, 'Produkcja', 90);
INSERT INTO catering.department (id, name, payment_perc) VALUES (3, 'Lakiernia', 10);
INSERT INTO catering.department (id, name, payment_perc) VALUES (4, 'testowy', 40);

INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (1, true, 'admin', 'admin', 'admin', '$2a$10$t9EzBNHqNYOqRLVqt6YRAOkJ2FAfuK56/CNMbfZZreA4O82056X06', true, 1);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (2, true, 'biuro1', 'biuro1', 'biuro1', '$2a$10$Q546FSdLOsu2GL9zdtI02OwH18zmpMVIjN8oykkEIaTmrDO0A4.hG', false, 1);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (3, true, 'biuro2', 'biuro2', 'biuro2', '$2a$10$8lwNZHSknBj4xnhcw9wV6OBgtu9nz3LT1OyOZgN2q8yC3IBegKOu6', false, 1);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (4, true, 'biuro3', 'biuro3', 'biuro3', '$2a$10$.pEajgPu2fRRLSGIBSbFa.YFij4ivjjTEQAk1ZVP1ImflP4fXdsGS', false, 1);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (5, true, 'produkcja1', 'produkcja1', 'produkcja1', '$2a$10$Mh5b.MYIZ0XfQHkqomgCsuPkj7gQQdPiyOSa9I1L4.1RM7G.6DdFO', false, 2);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (6, true, 'produkcja2', 'produkcja2', 'produkcja2', '$2a$10$LB6/1TOQnNBkXwfLFkputeP.rR6dOZVWs.KjAQ9sE/L9ageqvjNBu', false, 2);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (7, true, 'produkcja3', 'produkcja3', 'produkcja3', '$2a$10$hdZF7PVnM04rD2opFxMQw.Ctei8UM123KUlKtF2cFhyfcP7TvDQhO', false, 2);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (8, true, 'lakiernia1', 'lakiernia1', 'lakiernia1', '$2a$10$R5bZWu/JRlMabhZmH4iNU.gu6FdBCKkp31y1JSrXihyUZo8OM6cfK', false, 3);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (9, true, 'lakiernia2', 'lakiernia2', 'lakiernia2', '$2a$10$0xAfsYbqws.r/e13Ubw1F.MkvF8mPOdgL2ABiWOFfFvDBx6J5TTCy', false, 3);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (10, true, 'lakiernia3', 'lakiernia3', 'lakiernia3', '$2a$10$ppuSKOTM/YtBbYkORoeKru2yC6pgF5YJ/oF8ahsAvGkgU.0BHWO.C', false, 3);
INSERT INTO catering.users (user_id, active, last_name, login, name, password, super_admin, department_id) VALUES (11, true, 'test', 'test', 'test', '$2a$10$0tt/1WqsJTqWTTgbcBe2ZOgO6XsqoXolOwCOuDQEigul.hpBlt5K.', false, 4);

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
INSERT INTO catering.new_menu (meal_no, day_id, meal_name, meal_price) VALUES (18, 3, 'Zestaw śniadaniowy z sałatką jajeczną , wędliną, serem, warzywami /pieczywo', 14.30);
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


INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (1, false, 41, 34, 'Sałatka grecka z warzywami i jajkiem / pieczywo', 5, '"Danie voka" kurczak +  ryż + warzywa z  patelni', 25, 'Bułka z pastą z kurczaka  i dodatkami x2', 13, 'Szaszłyk drobiowy z warzywami , mix sałat , sos + ryż brązowy + zestaw surówek', 19, 'PILAV - ryż z warzywami i mięsem wieprzowym zapiekany w foremce', 7.20, 7.05, 7.20, 9.10, 7.15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 37.70, 1);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (5, false, 44, 35, 'Żurek z jakiem i kiełbasą / pieczywo', 4, 'Kartacze z mięsem ze skwarkami i cebulką', 26, 'Czeburaki ukraińskie z mięsem mielonym smażone', 13, 'Szaszłyk drobiowy z warzywami , mix sałat , sos + ryż brązowy + zestaw surówek', 18, 'Zestaw śniadaniowy z sałatką jajeczną , wędliną, serem, warzywami /pieczywo', 7.20, 7.05, 7.20, 9.10, 7.15, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 37.70, 2);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (6, false, 44, 35, 'Żurek z jakiem i kiełbasą / pieczywo', 5, '"Danie voka" kurczak +  ryż + warzywa z  patelni', 25, 'Bułka z pastą z kurczaka  i dodatkami x2', 12, 'Lazania z mięsem zapiekana w foremce', 19, 'PILAV - ryż z warzywami i mięsem wieprzowym zapiekany w foremce', 7.20, 7.05, 7.20, 7.10, 7.15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 35.70, 3);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (7, false, 44, 32, 'Bułka z pasta jajeczną i warzywami', 2, 'Bułka z szynką, serem i warzywami', 26, 'Czeburaki ukraińskie z mięsem mielonym smażone', 13, 'Szaszłyk drobiowy z warzywami , mix sałat , sos + ryż brązowy + zestaw surówek', 21, 'Nagetsy z kurczaka + ziemnaki opiekane + mix sałat', 3.70, 3.55, 7.20, 9.10, 9.15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 32.70, 4);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (8, false, 44, 35, 'Żurek z jakiem i kiełbasą / pieczywo', 6, 'Sznycel w sosie pieczarkowym + ziemniaki + surówka z białej kapusty i warzyw', 27, 'Łazanki z mięsem i warzywami', 13, 'Szaszłyk drobiowy z warzywami , mix sałat , sos + ryż brązowy + zestaw surówek', 20, 'Roladka drobiowa w sosie śmietanowo-koperkowym + ryż dziki + sałatka z czerwonych buraczków', 12.96, 16.29, 12.96, 16.38, 16.47, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 75.06, 5);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (9, false, 44, 34, 'Sałatka grecka z warzywami i jajkiem / pieczywo', 6, 'Sznycel w sosie pieczarkowym + ziemniaki + surówka z białej kapusty i warzyw', 27, 'Łazanki z mięsem i warzywami', 12, 'Lazania z mięsem zapiekana w foremce', 19, 'PILAV - ryż z warzywami i mięsem wieprzowym zapiekany w foremce', 12.96, 16.29, 12.96, 12.78, 12.87, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 67.86, 6);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (10, false, 44, 33, 'Bułka z pasta jajeczną i warzywami x2', 8, 'Pierś drobiowa faszerowana szpinakiem pieczona w cieście francuskim + surówka grecka kolorowa – sos vinegret', 25, 'Bułka z pastą z kurczaka  i dodatkami x2', 12, 'Lazania z mięsem zapiekana w foremce', 18, 'Zestaw śniadaniowy z sałatką jajeczną , wędliną, serem, warzywami /pieczywo', 12.96, 16.29, 12.96, 12.78, 12.87, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 67.86, 7);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (11, false, 44, 35, 'Żurek z jakiem i kiełbasą / pieczywo', 4, 'Kartacze z mięsem ze skwarkami i cebulką', 27, 'Łazanki z mięsem i warzywami', 12, 'Lazania z mięsem zapiekana w foremce', 16, 'Brak', 1.44, 1.41, 1.44, 1.42, 0.00, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 5.71, 8);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (12, false, 44, 34, 'Sałatka grecka z warzywami i jajkiem / pieczywo', 5, '"Danie voka" kurczak +  ryż + warzywa z  patelni', 26, 'Czeburaki ukraińskie z mięsem mielonym smażone', 9, 'Brak', 16, 'Brak', 1.44, 1.41, 1.44, 0.00, 0.00, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4.29, 9);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (13, false, 44, 31, 'Brak', 3, 'Bułka z szynką, serem i warzywami x2', 24, 'Bułka z pastą z kurczaka  i dodatkami', 15, 'Wegetariański kociołek warzywny + kluski gnioczki  + zestaw surówek', 18, 'Zestaw śniadaniowy z sałatką jajeczną , wędliną, serem, warzywami /pieczywo', 0.00, 1.41, 0.74, 1.82, 1.43, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 5.40, 10);
INSERT INTO catering.new_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (14, false, 44, 31, 'Brak', 7, 'Shoarma drobiowa + frytki + kapusta pekińska z sosiem', 23, 'Brak', 9, 'Brak', 16, 'Brak', 0.00, 7.24, 0.00, 0.00, 0.00, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7.24, 11);

INSERT INTO catering.actual_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (4, false, 40, 34, 'Sałatka grecka z warzywami i jajkiem / pieczywo', 5, '"Danie voka" kurczak +  ryż + warzywa z  patelni', 25, 'Bułka z pastą z kurczaka  i dodatkami x2', 13, 'Szaszłyk drobiowy z warzywami , mix sałat , sos + ryż brązowy + zestaw surówek', 19, 'PILAV - ryż z warzywami i mięsem wieprzowym zapiekany w foremce', 7.20, 7.05, 7.20, 9.10, 7.15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 37.70, 1);
INSERT INTO catering.actual_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (6, true, 40, 33, 'Bułka z pasta jajeczną i warzywami x2', 6, 'Sznycel w sosie pieczarkowym + ziemniaki + surówka z białej kapusty i warzyw', 26, 'Czeburaki ukraińskie z mięsem mielonym smażone', 13, 'Szaszłyk drobiowy z warzywami , mix sałat , sos + ryż brązowy + zestaw surówek', 19, 'PILAV - ryż z warzywami i mięsem wieprzowym zapiekany w foremce', 7.20, 9.05, 7.20, 9.10, 7.15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 39.70, 5);
INSERT INTO catering.actual_orders (id, is_paid, kw, meal_fri, meal_fri_name, meal_mon, meal_mon_name, meal_thu, meal_thu_name, meal_tue, meal_tue_name, meal_wed, meal_wed_name, price_fri, price_mon, price_thu, price_tue, price_wed, qty_fri, qty_mon, qty_thu, qty_tue, qty_wed, shift_fri, shift_mon, shift_thu, shift_tue, shift_wed, to_pay, user_id) VALUES (7, true, 40, 33, 'Bułka z pasta jajeczną i warzywami x2', 7, 'Shoarma drobiowa + frytki + kapusta pekińska z sosiem', 29, 'Jesienna "szakszuka" fasolka szparagowa, pierś z kurczaka, cukinia, papryka, makaron pełnoziarnisty, sos meksykański', 12, 'Lazania z mięsem zapiekana w foremce', 19, 'PILAV - ryż z warzywami i mięsem wieprzowym zapiekany w foremce', 12.96, 16.29, 16.56, 12.78, 12.87, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 71.46, 2);
