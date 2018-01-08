# Format poleceń
Polecenia są w formacie JSON. Polecenie składa się z nazwy polecenia i argumentów polecenia. 

Format polecenia:

    {
        "command":  NAZWA_POLECENIA,
        "args":     ARGUMENTY_POLECENIA
    }

Przykładowe polecenie:

    {
        "command": "put_card",
        "args": {
                "card_id": 123456,
                "row": 1
         }
    }

# Lista poleceń

## `Pong`
Odpowiedź klienta na żądanie [`Ping`](Odpowiedzi#pong). 

## `set_nickname`
Ustawia nazwę użytkownika

**Argumenty**

|nazwa          |typ     |opis                                       |
|---------------|--------|-------------------------------------------|
|`nickname`     |`string`|nazwa użytkownika                          |

**Odpowiedź:** [`SetNicknameResponse`](Odpowiedzi#setnicknameresponse)

## `get_players`
Pobiera listę graczy

**Argumenty**
Brak

**Odpowiedź:** [`PlayerList`](Odpowiedzi#playerlist)

## `request_game`
Prośba o zagranie partii.

**Argumenty**

|nazwa          |typ     |opis                                              |
|---------------|--------|--------------------------------------------------|
|`nickname`     |`string`|nazwa użytkownika, do którego wysyłana jest prośba|

**Odpowiedź:** [`RequestGameResponse`](Odpowiedzi#requestgameresponse)

## `reject_request_game`
Odrzucenie prośby o zagranie partii.

**Argumenty**

|nazwa          |typ     |opis                                              |
|---------------|--------|--------------------------------------------------|
|`nickname`     |`string`|nazwa użytkownika, który wysłał prośbę            |

**Odpowiedź:** brak

## `put_card`
Wykłada kartę na stół

**Argumenty**

|nazwa          |typ    |opis                                         |
|---------------|-------|---------------------------------------------|
|`cardNumber`   |`int`  |numer karty z ręki (indeks w tablicy, baza 0)|
|`row`          |`int`  |numer rzędu do którego kładzie się kartę     |

**Odpowiedź:** [`PutCardResponse`](Odpowiedzi#putcardresponse)


## `pass`
Pass

**Argumenty**
Brak

**Odpowiedź:** [`PassResponse`](Odpowiedzi#passcardresponse)