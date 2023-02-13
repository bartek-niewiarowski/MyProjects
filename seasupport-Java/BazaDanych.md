# SeaSupPort

## Skład zespołu

- [Ewa Miazga](https://gitlab-stud.elka.pw.edu.pl/emiazga)
- [Mikołaj Taudul](https://gitlab-stud.elka.pw.edu.pl/mtaudul)
- [Bartłomiej Niewiarowski](https://gitlab-stud.elka.pw.edu.pl/bniewiar)
- [Michał Jakomulski](https://gitlab-stud.elka.pw.edu.pl/mjakomul)

## Opis projektu bazy danych

Baza danych została zaprojektowana w celu obsługi danych w aplikacji seasupport, jej zadaniem jest przechowywanie danych użytkowników oraz innych danych kluczowych do funkcjonowania aplikacji: informacje o portach, wizytach, zarejestrowanych statkach, właścicielach jednostek oraz kaitanach. W projekcie bazy danych uwzględniliśmy podział niektórych typów obiektów, min. użytkownika na zwykłego oraz admin, a także właścicieli statków którzy mogą być prywatni oraz komercyjni.

## Technologia użyta do łączenia się z bazą z poziomu aplikacji

W celu łączenia się z bazą danych z poziomu aplikacji korzystaliśmy z popularnej implementacji Java Persistence API (JPA) jaką jest framework Hibernate.

## W celu usprawnienia pracy z bazą danych zaimplementowaliśmy

### Funkcje:

1. get_active_visit - na podstawie podanego loginu zwraca aktywną wizytę wybranego użytkownika.
2. popular_port - w opraciu o podane daty krańcowe, funkcja sprawdza jaki port był najpopularniejszy w danym okresie i zwraca jego id.
3. time_from_last_visit - na podstawie podanego loginu zwraca czas który minął od ostatniej wizyty użytkownika.
4. user_visits_stats - zwraca łączny czas spędzony w porcie przez wybranego użytkownika

### Procedury:
1. update_prices - w opraciu o podane wzrosty cen energii oraz kosztów administracji procedura uaktualnia ceny usług oferowanych w porcie.
2. remove_inactive_users - procedura usuwająca użytkowników nieaktywnych od więcej niż roku.

### Sekwencje:

### Triggery:
1. current_visit_date - sprawdza czy data wizyty, którą dodajemy, nie jest starsza od aktualnej daty
2. correct_ship_owner - sprawdza czy podano właściwe dane właściciela staku (stosujemy przejście z dwóch podencji na jedną tabelę)

## Napotkane problemy oraz kierunki rozwoju bazy danych

Implementacja podencji w modelu logicznym - wybór ostatecznie padł na wspólną tabelę.

Właściwe modelowanie relacji w modelu ORM za pomocą narzędzia Hibernate.

Baza danych zapewne będzie potrzebowała rozbudowy związanej z ewentualnym rozwojem aplikacji Seasupport, bardzo prawdopodobne jest dodanie nowych tabel, które będą przechowywać dane konieczne do realizacji nowych funkcjonalności aplikacji takich np obsługa rezerwacji funkcjonalności portu zapisanych w encji Price List.



