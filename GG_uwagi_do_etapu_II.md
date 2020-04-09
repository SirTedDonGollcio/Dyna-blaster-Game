Szanowni Studenci,

co mam zrobić z zawartością repozytorium?

W repozytorium powinna znajdować się wersja projektu, pozwalająca na jego uruchomienie.

Znalazłem plik .rar - powinny być bezpośrednio pliki źródłowe, a nie binarne archiwum... 

Rozpakowałem... w archiwum - całe mnóstwo katalogów, plików... gdzie co jest? jak to uruchomić? itp. itd.

Do projektu powinny być dołączone skrypty do jego uruchomienia!!! Lub na bieżącym etapie przynajmniej instrukcja jak program skompilować i uruchomić.

Ale... znalazłem jakąś klasę Main, udało mi się skompilować i uruchomić... jednak 
po uruchomieniu widzę tylko okno z kilkoma przyciskami i... co bym nie zrobił pojawia się co najwyżej puste okno...

Nigdzie nie widzę diagramu klas...

Nigdzie nie widzę plików konfiguracyjnych i klas do ich odczytu...

To co udało mi się uruchomić nie spełnia wymagań etapu II, więc nie mogę go zaliczyć.




Przy okazji zamieszczam ogólne informacje z etapu I (komentarz ogólny, do wszystkich):

1. wszelkie "PARAMETRY" gry, takie jak: liczba punktów za określone elementy (wroga, poziom, czas, itd.), początkowa/maksymalna liczba żyć, prędkość, zasięg bomb... 
oraz wszystkie inne tego typu parametry związane z rozgrywką (nie GUI!) powinny być odczytywane z konfiguracji a NIE zapisane na stałe w kodzie;<br/>
  wygląd plansz (w tym m.in. ich wielkość - liczba wierszy/kolumn, ale także liczba i początkowe rozmieszczenie wrogów) oczywiście też z konfiguracji - jeśli 
różne poziomy trudności: to różne opisy? albo chociaż 'modyfikatory' zapisane w konfiguracji (jak np. współczynniki zdobytych punktów za wyższe poziomy trudności); <br/>
  w szczególności liczba plansz składających się na grę (-> scenariusz gry) MUSI wynikać z konfiguracji (np. jawny parametr; 
albo określona konwencja nazywania plików z definicją planszy - brak kolejnego pliku oznacza zakończenie gry; 
albo taki zapis plansz, z którego wynika ich liczba; albo...);

2. dokumentację należy tworzyć na bieżąco;

3. zapoznać się z wszystkimi wymaganiami (uwagi ogólne - 11 punktów; co wymagane na zaliczeniu projektu; co na kolejne etapy...) - nie wszystkie wymagania "szczegółowe" mają swoje bezpośrednie odzwierciedlenie w etapach, są sprawdzane dopiero przy zaliczeniu projektu, a ich brak skutkuje obniżeniem oceny;

4. na kolejny etap przygotować "moduł odczytu i parsowania plików konfiguracyjnych": MODUŁ, czyli jakąś odrębną klasę (pakiet... interfejs... - myśleć od razu przyszłościowo o funkcjonalności sieciowej!) odczytującą konfigurację i udostępniającą wartości parametrów poprzez wygodny interfejs, a nie jakąś tam "metodę pomocniczą" działającą "tu i teraz";
  a także wszystkie inne elementy zgodnie z opisem na stronie projektu


Z poważaniem,<br/>
Grzegorz Galiński
