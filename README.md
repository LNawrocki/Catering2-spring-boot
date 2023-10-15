# Catering2-spring-boot

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
3. Obsługa nieaktywnych użytkowników
4. Spring Security
5. 