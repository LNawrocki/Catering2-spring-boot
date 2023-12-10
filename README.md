# Catering2-spring-boot (wersja robocza)

#### Technologie
- Java
- Spring Boot
- MySQL
- JSP, CSS
- Maven

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
7. Zamawianie obiadów dla użytkowników
8. Edycja predefniniowanych list dań (szybsze tworzenie nowego menu)
9. Edycja predefiniowanej listy cen (szybsze tworzenie nowego menu)


![Catering2-spring-boot](https://github.com/LNawrocki/Catering2-spring-boot/blob/master/Catering2_20231210.gif)

### Funkcje do wdrożenia
1. Spring Security,
2. Generowanie pliku csv z podsumowanie finansowego


# Catering2-spring-boot (draft)

#### Technologies
- Java
- Spring Boot
- MySQL
- JSP, CSS
- Maven

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
7. Ordering lunches for users
8. Editing predefined dish lists (faster creation of a new menu)
9. Editing the predefined price list (faster creation of a new menu)


### Features to be implemented
1. Spring Security,
2. Generating a csv file from the financial summary
