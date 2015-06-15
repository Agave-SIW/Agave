# Agave `Progetto SIW 2015`

--------------
* Gaetano Bonofiglio
* Veronica Iovinella
* Andrea Salvoni

--------------

### Estensioni
* Quando il **cliente** effettua il login, l'applicazione lo ricorda fino al logout.
* Un cliente può registrarsi da solo al sito.
* Ad ogni prodotto può essere associata una serie di **recensioni** di altri clienti.
 * Una recensione è composta da un **commento**, una **valutazione** da 1 a 5 e un *autore* (un cliente). 
 * Nella pagina del singolo prodotto è mostrato l'elenco delle recensioni. 
 * Nella pagina con l'elenco dei prodotti è mostrata la media delle valutazioni per ogni prodotto.
* Quando l'**amministratore** effettua il login, l'applicazione lo ricorda fino al logout.
 * Se l'amministratore non è loggato non può accedere al **pannello admin**.
* Quando un amministratore invia un nuovo prodotto deve **caricare un'immagine** che poi verrà mostrata nel riepilogo del prodotto.
* Quando un cliente effettua un **ordine**, ogni prodotto (*riga ordine*) selezionato  viene salvato temporaneamente nel **carrello** fino alla conferma o alla cancellazione (di fatto, il carrello rappresenta l'ordine *aperto* delle specifiche originali). 
  * Carrello, ordini e ordini evasi sono comunque di tipo *Orders*, ma sono differenziati in base alla loro associazione a Customer, con diversa cardinalità (il carrello rappresenta l'ordine in creazione, quindi è 1 a 1).
 * Il carrello può essere svuotato o confermato. Alla conferma l'ordine diventa effettivo e il carrello viene comunque svuotato. Questo provoca un cambiamento dell'associazione.

 
### Specifiche Originali

* Si vuole realizzare un sistema informativo su Web per la gestione degli ordini di una piccola azienda che effettua una attività di e-commerce dei propri prodotti
* Oltre agli utenti occasionali, due tipologie di attori interagiscono con il sistema: i clienti e l'amministrazione
* Il cliente può svolgere le seguenti operazioni (è possibile introdurne altre):
  * Consultazione catalogo prodotti
  * Creazione di un ordine
  * Consultazione dei propri ordini
* L'amministrazione può svolgere le seguenti operazioni:
  * Inserimento di un prodotto nel catalogo
  * Inserimento di un cliente nella anagrafica clienti
  * Evasione di un ordine
  
* Per ogni prodotto sono di interesse un nome, un codice, una descrizione, un costo, la quantità in magazzino
* Ogni prodotto può essere fornito da uno o più fornitori, ogni fornitore può fornire uno o più prodotti
* Ogni fornitore ha una partita iva e un indirizzo, telefono, indirizzo di posta elettronica
* Ogni cliente ha nome, cognome, data di nascita, data di registrazione, indirizzo, indirizzo di posta elettronica

* Un ordine si compone di più righe: ogni riga riporta un prodotto, il prezzo unitario del prodotto e la quantità ordinata (ad es. penne, 1€, 400) 
* Per ogni ordine è necessario memorizzare:
  * la data in cui l'ordine è stato aperto dal cliente 
  * la data in cui l'ordine è stato chiuso dal cliente (da quel momento in poi l'ordine non verrà modificato e l'amministrazione può procedere ad evadere l'ordine)
  * la data in cui l'ordine è stato evaso (cioè la data in cui i prodotti ordinati sono stati prelevati dal magazzino e spediti al cliente)
* I clienti che vogliono effettuare gli ordini devono essere registrati nella anagrafica dei clienti della azienda: solo i clienti identificati e autenticati possono effettuare ordini
* Il catalogo dei prodotti può essere consultato da clienti non autenticati

* Segue una bozza dei principali casi d'uso
* I casi d'uso dovranno essere estesi e completati a piacere (giustificando ogni scelta)
* NB: ipotizziamo che i pagamenti vengano gestiti off-line

* Caso d'uso UC1: consulta listino
  * Attore primario: utente non registrato
  * Scenario principale di successo: 
    * L'utente consulta il catalogo dei prodotti
    * L'utente sceglie un prodotto e ne richiede i dettagli 
    * Il sistema mostra i dettagli del prodotto scelto
    * L'utente ripete i passi precedenti un numero indefinito di volte
	
* Caso d'uso UC2: effettua ordine
  * Attore primario: cliente (utente registrato)
  * Scenario principale di successo: 
    * Il cliente crea un ordine
    * Il cliente consulta il catalogo dei prodotti
    * Il cliente sceglie un prodotto e aggiunge codice e quantità del prodotto scelto all'ordine
    * Il sistema imposta il prezzo unitario del prodotto scelto al prezzo corrente di listino
    * Il sistema registra la riga ordine
    * Il cliente ripete i due passi precedenti finché necessario
    * Il cliente "chiude" l'ordine
    * Il sistema registra l'ordine
  * Precondizioni: il cliente è identificato e autenticato
  
* Caso d'uso UC3: consulta i propri ordini
  * Attore primario: cliente
  * Scenario principale:
    * Il cliente consulta l'elenco dei propri ordini
    * Il sistema mostra al cliente l'elenco dei suoi ordini
    * Il cliente chiede il dettaglio di un ordine
    * Il sistema mostra il dettaglio dell'ordine
    * Il cliente ripete i due passi precedenti finché necessario
  * Precondizioni: il cliente è identificato e autenticato

* Caso d'uso UC4: inserimento prodotti nel catalogo
  * Attore primario: amministrazione
  * Scenario principale:
    * L'amministratore inserisce un nuovo prodotto nel catalogo specificandone i dettagli
    * Il sistema registra il prodotto
    * I punti precedenti vengono ripetuti fino a che necessario
  * Precondizioni: l'amministratore è identificato e autenticato
  
* Caso d'uso UC5: recupera indirizzo cliente
  * Attore primario: amministrazione
  * Scenario principale:
    * L’amministratore fornisce l'id di un ordine 
    * Il sistema mostra all’amministratore i dati del cliente che ha effettuato l’ordine
  * Precondizioni: l’amministratore è identificato e autenticato

* Caso d'uso UC6: evasione ordine
  * Attore primario: amministrazione
  * Scenario principale:
    * Il sistema presenta all'amministratore gli ordini chiusi, ma non evasi
    * L'amministratore sceglie un ordine
    * Il sistema evade l'ordine: aggiorna l'ordine inserendo la data di spedizione e aggiorna la quantità dei prodotti in magazzino (sottraendo la quantità di prodotti usati per l'ordine)
  * Precondizioni:
    * l'amministratore è identificato e autenticato
  * Eccezioni:
    * alcuni prodotti potrebbero non essere presenti in magazzino nella quantità specificata dall'ordine. In questo caso l'ordine rimane in sospeso



