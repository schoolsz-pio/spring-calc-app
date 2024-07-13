---
title: Kalkulator Spring
author: Piotr Szotek
date: 2024-07-13
---

## Wprowadzenie

Aplikacja Kalkulator jest webową aplikacją umożliwiającą użytkownikom 
wykonywanie obliczeń w formacie zrozumiałym dla języka JavaScript.

Aplikacja umożliwia rejestrację, logowanie oraz zapisywanie historii obliczeń. 
Niezalogowani użytkownicy również mogą dokonywać obliczeń, jednak nie będą one 
zapisywane, a po odświeżeniu strony nie będzie można ich odzyskać.


## Założenia Projektu

### Hasła przechowywane w postaci haszowanej

Ze względu na standard przechowywania haseł w postaci innej niż "plain text" 
oraz możliwość powielania haseł przez użytkowników, zastosowano bibliotekę Bcrypt.

### Konto nie jest wymagane do wykonania obliczeń

Konto nie jest wymagane do wykonania obliczeń. Użytkownicy mogą swobodnie 
korzystać z funkcji kalkulatora bez konieczności rejestracji lub logowania. 

Poza małą możliwością, że strona będzie miała znaczny ruch lub padnie ofiarą DDoS, 
wykonywanie obliczeń nie ma powodu, aby wymagało autoryzacji. 
Jedynym ograniczeniem jest brak zapisu historii obliczeń, co oznacza, 
że po odświeżeniu strony użytkownik traci dostęp do wcześniejszych wyników.

### Dane przechowywane w bazie MySQL

Ze względu na potrzebę trwałego przechowywania danych oraz zapewnienie ich 
bezpieczeństwa i integralności, dane są przechowywane w bazie MySQL.

Tabele są tworzone na podstawie obiektów znajdujących się w katalogu `entities/`


## Struktura projektu 

- CalcApplication.java: Klasa główna uruchamiająca aplikację.
- controllers: Folder zawierający kontrolery obsługujące żądania HTTP.
  - api/CalculationController.java: Obsługuje żądania związane z obliczeniami.
  - api/UserController.java: Obsługuje żądania związane z użytkownikami.
  - AuthController.java: Obsługuje rejestrację i logowanie.
  - IndexController.java: Obsługuje żądania główne aplikacji.
- entities: Folder zawierający klasy encji (danych) używanych w aplikacji.
  - Calculation.java: Reprezentuje tabele obliczeń.
  - User.java: Reprezentuje tabele użytkowników.
- repositories: Pakiet zawierający interfejsy repozytoriów do operacji na bazie danych.
  - CalculationRepository.java: Repozytorium dla obliczeń.
  - UserRepository.java: Repozytorium dla użytkowników.
- services: folder zawierający logikę biznesową aplikacji.
  - CalculationService.java: Logika  dla obliczeń.
  - CustomUserDetailsService.java: Logika  logowania użytkowników.
- WebSecurityConfig.java: Konfiguracja zabezpieczeń aplikacji.

## Prezencajca działania

- Wynik można uzyskać bez logowania, jednak nie będzie możliwości usunięcia wyniku, ponieważ nie jest on zapamiętany w systemie.
![anon-calc](https://raw.githubusercontent.com/schoolsz-pio/spring-calc-app/main/assets/anon_calc.png)


- Aby móc przechowywać historię wyników, konieczne jest stworzenie konta lub zalogowanie się.
![register-form](https://raw.githubusercontent.com/schoolsz-pio/spring-calc-app/main/assets/register_page.png)

- Jeśli podany adres email jest już używany przez innego użytkownika, użytkownik zostaje przekierowany do strony logowania(`/login`) i wyświetla się komunikat, że ten adres już istnieje.
![email-exist](https://raw.githubusercontent.com/schoolsz-pio/spring-calc-app/main/assets/email_exist.png)


- Jeśli adres email jest unikalny, po przekierowaniu do strony logowania wyświetlany jest komunikat, że konto zostało zapisane.
![email-saved](https://raw.githubusercontent.com/schoolsz-pio/spring-calc-app/main/assets/email_saved.png)

- Po zalogowaniu użytkownik może korzystać z funkcji kalkulatora, gdzie wyniki mogą być zapisywane, jeśli użytkownik ma konto.

![login-form](https://raw.githubusercontent.com/schoolsz-pio/spring-calc-app/main/assets/login_page.png)

- Podobnie jak przy braku konta, można użyć JavaScript do wyświetlenia wyników. Jednak w przypadku posiadania konta wyniki zostaną zapisane.
![eampleexpressions](https://raw.githubusercontent.com/schoolsz-pio/spring-calc-app/main/assets/exampleexpressions.png)

- Po zalogowaniu użytkownik może również przejść do podstrony `/history`, gdzie wyświetlane są przechowywane wyniki wraz z wyrażeniami.
![histor-page](https://raw.githubusercontent.com/schoolsz-pio/spring-calc-app/main/assets/history_page.png)


- Aby wylogować użytkownika, można przejść na podstronę /logout, która automatycznie wyloguje użytkownika i przekieruje go na stronę logowania.

- Struktura użytkowników wygląda następująco:
![api-users](https://raw.githubusercontent.com/schoolsz-pio/spring-calc-app/main/assets/api_all_users.png)


## Link do repozytorium
[https://github.com/schoolsz-pio/spring-calc-app.git](https://github.com/schoolsz-pio/spring-calc-app.git)
