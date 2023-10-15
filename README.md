# Catering2-spring-boot (wersja robocza)

### Funkcji użytkownika

Catering2 jest aplikacją pozwalającą na obsługę rezerwacji i rozliczeń obiadów zamawianych w zewnętrznej firmie cateringowej w cylku tygodniowym.
Każdy użytkownik po zalogowaniu może zamówić dania na cały kolejny tydzień.
Do momentu opłacenia i odznaczeniu przez administratora użytkownik może usunąć a następnie złożyć zamówienie.
Po uregulowaniu należności niemożliwa jest zmiana zamówienie.
Prócz tego użytkownik może podglądnąć zamówienie z poprzedniego tygodnia w celu sprawdzenia posiłku na obecny dzień i tydzień.

### Funkcje administratora

Osoba będąca administratorem posiada dodatkowe możliwości:
1. Wyświetlać listę wszystich użytkowników, usuwać i modyfikować,
2. Dodawać nowych użytkowników,
3. Dodawać działy, do których należą użytkownicy.
4. Edytować menu na kolejny tydzień,
5. Przeglądać listę zamówień z możliwością odznaczania wpłat i usuwania zamówień,
6. Przeglądać listę zamówień z bierzącego tygodnia.


![Catering2-spring-boot](https://github.com/LNawrocki/Catering2-spring-boot/blob/master/2023-10-15_13-26-03.gif)

### Funkcje do wdrożenia
1. Panel konfiguracyjny, blokujący dostęp i możliwość zamawiania w trakcie tworzenia nowej listy dań,
2. Panel podsumowujący rozliczenia z firmą zewnętrzną dostarczającą posiłki,
3. Obsługa nieaktywnych użytkowników,
4. Spring Security.


# Catering2-spring-boot (draft)
### User functions
Catering2 is an application that allows you to handle reservations and settlements of dinners ordered from an external catering company on a weekly basis. After logging in, each user can order dishes for the entire next week. Until payment is made and deselected by the administrator, the user can delete and then place an order. Once the payment has been settled, it is impossible to change the order. In addition, the user can view the order from the previous week to check the meal for the current day and week.

### Administrator features
A person who is an administrator has additional possibilities:

1. View a list of all users, delete and modify,
2. Add new users,
3. Add departments to which users belong.
4. Edit the menu for the next week,
5. View the list of orders with the option of deselecting payments and deleting orders,
6. View the list of orders from the current week.


### Features to be implemented
1. Configuration panel that blocks access and ordering when creating a new dish list,
2. A panel summarizing settlements with an external company delivering meals,
3. Support for inactive users,
4. Spring Security,