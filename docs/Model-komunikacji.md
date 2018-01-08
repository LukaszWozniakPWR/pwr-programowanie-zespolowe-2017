Poniższy diagram przedstawia uproszczony model komunikacji:

                      +--------------+             +-----------+             +--------+
    +------------+    |   Android    |  Polecenie  |  Socket   |  Polecenie  |        |
    | Użytkownik |<-->|       /      |<----------->|-----------|<----------->| Serwer |
    +------------+    | Przeglądarka |  Odpowiedź  | WebSocket |  Odpowiedź  |        |
                      +--------------+             +-----------+             +--------+
                           Klient                Serwer komunikacji          Serwer gry

Gdy klient chce połączyć się z serwerem gry, ustanawia dwukierunkowy kanał transmisji do serwera gry. W zależności od tego, czy klient jest aplikacją na platformie Android [1] czy aplikacją w przeglądarce [2] jako sposób komunikacji wybiera Socket [1] lub WebSocket [2]. 

Zarówno polecenia (żądania) jak i odpowiedzi są w formacie JSON.
