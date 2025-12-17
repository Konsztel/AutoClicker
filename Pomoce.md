- Automatyzacja szukania templatek
- org.sikuli.script.

// 0,8f - 80% dopasowanie
Pattern okButton = new Pattern("Ścieżka do elementu którego mam szukać").similar(0.8f);

// Definicja zrzutu ekranu (x, y, szerokość, wysokość)
// Przykład: zaczynamy od (500, 300), obszar 400x200 pikseli
Region region = new Region(500, 300, 400, 200);

// Klika w centrum wzorca którego szukał
region.click(okButton); 

// Klika w punkt 10px w prawo i 5px w dół od środka
region.click(match.getTarget().offset(10, 5));

// Czekamy max 5 sekund
if (region.exists(Nazwa Szukanego Wzorca, 5) != null) {

// Fajnie podkreśla kod co poprawić
TODO 
