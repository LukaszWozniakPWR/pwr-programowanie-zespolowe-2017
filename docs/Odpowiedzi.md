# Format odpowiedzi
Odpowiedzi są w formacie JSON. Polecenie składa się z typu odpowiedzi i właściwej odpowiedzi. 

Format odpowiedzi:

    {
        "type":         TYP_ODPOWIEDZI,
        TYP_ODPOWIEDZI: ODPOWIEDŹ
    }

Przykładowa odpowiedź:

    {
        "type": "SetNicknameResponse",
        "SetNicknameResponse": {
            "success": true
        }
    }


# Typy odpowiedzi

## `Ping`
Serwer wysyła `ping` aby sprawdzić czy klient jest podłączony. Klient musi odpowiedzieć na to żądanie (zalecane [`Pong`](Polecenia#pong)). 


## `BooleanResponse`

**Pola**

|nazwa          |typ                            |opis                                                 |
|---------------|-------------------------------|-----------------------------------------------------|
|`success`      |`boolean`                      |`true` jeśli się powiodło, `false` w przeciwnym razie|

## `SetNicknameResponse`
**Alias** [`BooleanResponse`](#booleanresponse) 

Odpowiedź na ustawienie nazwy użytkownika. 

## `RequestGameResponse`
Odpowiedź na prośbę o zagarnie partii. 

**Pola**

|nazwa          |typ                            |opis                                                 |
|---------------|-------------------------------|-----------------------------------------------------|
|`success`      |`boolean`                      |`true` jeśli się powiodło, `false` w przeciwnym razie|
|`nickname`     |`string`                       |nazwa gracza do którego wysłano prośbę               |

## `RequestGame`
Prośba o zagarnie partii. 

**Pola**

|nazwa          |typ                            |opis                                                 |
|---------------|-------------------------------|-----------------------------------------------------|
|`nickname`     |`string`                       |nazwa gracza, który wysłał prośbę                    |

## `PlayerList`
Lista graczy

**Pola**

|nazwa          |typ                            |opis                                                 |
|---------------|-------------------------------|-----------------------------------------------------|
|`players`      |[`Player[]`](#player)          |Lista graczy                                         |


## `Player`
**(typ)**

Gracz

**Pola**

|nazwa          |typ                            |opis                                       |
|---------------|-------------------------------|-------------------------------------------|
|`name`         |`string`                       |unikalna nazwa gracza                      |
|`state`        |[`PlayerState`](#playerstate)  |status gracza                              |

## `PlayerState`
**(typ wyliczeniowy)**

Stan gracza.

**Wartości**

|nazwa      |opis               |
|-----------|-------------------|
|`FREE`     |wolny              |
|`PLAYING`  |w trakcie gry      |


## `GameStartedResponse`
Rozpoczęcie gry. Alias dla [`GameState`](#gamestate)


## `OpponentActionResponse`
Przeciwnik wykonał ruch. Alias dla [`GameState`](#gamestate)


## `GameState`
**(abstract / typ)**

Stan gry.

**Pola**

|nazwa          |typ                              |opis                                       |
|---------------|---------------------------------|-------------------------------------------|
|`opponent`     |[`Player`](#player)              |przeciwnik                                 |
|`self`         |[`Player`](#player)              |gracz                                      |
|`opponentState`|[`OpponentState`](#opponentstate)|stan gry przeciwnika                       |
|`selfState`    |[`SelfState`](#selfstate)        |stan gry gracza                            |
|`turn`         |[`Turn`](#turn)                  |wskazuje czyja tura                        |


## `OpponentState`
**typ**

Stan gry przeciwnika.

**Pola**

|nazwa          |typ                              |opis                                       |
|---------------|---------------------------------|-------------------------------------------|
|`passed`       |`boolean`                        |czy gracz spasował                         |
|`score`        |`int`                            |ilość wygranych rund                       |
|`points`       |`int`                            |ilość punktów gracza                       |
|`frontRow`     |[`Row`](#row)                    |pierwszy rząd                              |
|`middleRow`    |[`Row`](#row)                    |środkowy rząd                              |
|`rearRow`      |[`Row`](#row)                    |tylny rząd                                 |
|`handLength`   |`int`                            |ilość kart w ręce                          |


## `SelfState`
**typ**

Stan gry gracza. Dziedziczy po [`OpponentState`](#opponentstate).

**Pola**
Dodatkowo:

|nazwa          |typ                              |opis                                       |
|---------------|---------------------------------|-------------------------------------------|
|`hand`         |[`Card[]`](#card)                |karty w ręce                               |
|`graveyard`    |[`Card[]`](#card)                |karty na stosie kart odrzuconych           |


## `Row`
**typ**

Rząd kart.

**Pola**

|nazwa          |typ                              |opis                                       |
|---------------|---------------------------------|-------------------------------------------|
|`elements`     |[`Card[]`](#card)                |karty w ręce                               |
|`effects`      |[`Effect[]`](#effect)            |efekty działające na rząd                  |
|`points`       |`int`                            |suma punktów w rzędzie                     |


## `Card`
**typ**

Karta.

**Pola**

|nazwa          |typ                              |opis                                       |
|---------------|---------------------------------|-------------------------------------------|
|`name`         |`enum`                           |nazwa karty (patrz `model/Cards.java`)     |
|`strength`     |`int`                            |aktualna siła / punkty karty               |


## `Turn`
**(typ wyliczeniowy)**

Tura.

**Wartości**

|nazwa      |opis               |
|-----------|-------------------|
|`YOUR`     |tura gracza        |
|`OPPONENT` |tura przeciwnika   |


## `Effect`
**(typ wyliczeniowy)**

Efekt.

**Wartości**

|nazwa            |opis                                            |
|-----------------|------------------------------------------------|
|`COMMANDERS_HORN`|róg dowódcy - podwaja punkty w rzędzie          |
|`BAD_WEATHER`    |zła pogoda - zmniejsza bazową ilość punktów do 1|


## `ActionResponse`
Odpowiedź na ruch/akcję gracza. Dziedziczy po [`BooleanResponse`](#booleanresponse).

**Pola**
Dodatkowo:

|nazwa          |typ                              |opis                                       |
|---------------|---------------------------------|-------------------------------------------|
|`game`         |[`GameState`](#gamestate)        |stan gry po akcji gracza                   |


## `PutCardResponse`
Położenie karty. Alias dla [`ActionResponse`](#actionresponse)


## `PassResponse`
Spasowanie. Alias dla [`ActionResponse`](#actionresponse)


## `GameEndedResponse`
Informacja o zakończeniu rozgrywki. 

**Pola**

|nazwa          |typ                              |opis                                                 |
|---------------|---------------------------------|-----------------------------------------------------|
|`reason`       |[`GameEndReason`](#gameendreason)|powód zakończenia rozgrywki                          |



## `GameEndReason`
**(typ wyliczeniowy)**

Powód zakończenia rozgrywki. 

**Wartości**

|nazwa                  |opis                                            |
|-----------------------|------------------------------------------------|
|`OPPONENT_DISCONNECTED`|przeciwnik się rozłączył                        |
|`WON`                  |gra zakończona, gracz wygrał                    |
|`LOST`                 |gra zakończona, gracz przegrał                  |
