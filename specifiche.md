# Agave `Progetto SIW 2015`

--------------
* Gaetano Bonofiglio
* Veronica Iovinella
* Andrea Salvoni

--------------

### Estensioni
* Quando il **cliente** effettua il login, l'applicazione lo ricorda fino al logout.
* Un cliente pu� registrarsi da solo al sito.
* Ad ogni prodotto pu� essere associata una serie di **recensioni** di altri clienti.
 * Una recensione � composta da un **commento**, una **valutazione** da 1 a 5 e un *autore* (un cliente). 
 * Nella pagina del singolo prodotto � mostrato l'elenco delle recensioni. 
 * Nella pagina con l'elenco dei prodotti � mostrata la media delle valutazioni per ogni prodotto.
* Quando l'**amministratore** effettua il login, l'applicazione lo ricorda fino al logout.
 * Se l'amministratore non � loggato non pu� accedere al **pannello admin**.
* Quando un amministratore invia un nuovo prodotto deve **caricare un'immagine** che poi verr� mostrata nel riepilogo del prodotto.
* Quando un cliente effettua un **ordine**, ogni prodotto (*riga ordine*) selezionato  viene salvato temporaneamente nel **carrello** fino alla conferma o alla cancellazione (di fatto, il carrello rappresenta l'ordine *aperto* delle specifiche originali). 
  * Carrello, ordini e ordini evasi sono comunque di tipo *Orders*, ma sono differenziati in base alla loro associazione a Customer, con diversa cardinalit� (il carrello rappresenta l'ordine in creazione, quindi � 1 a 1).
 * Il carrello pu� essere svuotato o confermato. Alla conferma l'ordine diventa effettivo e il carrello viene comunque svuotato. Questo provoca un cambiamento dell'associazione.

 
### Specifiche Originali

* Si vuole realizzare un sistema informativo su Web per la gestione degli ordini di una piccola azienda che effettua una attivit� di e-commerce dei propri prodotti
* Oltre agli utenti occasionali, due tipologie di attori interagiscono con il sistema: i clienti e l'amministrazione
* Il cliente pu� svolgere le seguenti operazioni (� possibile introdurne altre):
  * Consultazione catalogo prodotti
  * Creazione di un ordine
  * Consultazione dei propri ordini
* L'amministrazione pu� svolgere le seguenti operazioni:
  * Inserimento di un prodotto nel catalogo
  * Inserimento di un cliente nella anagrafica clienti
  * Evasione di un ordine
  
* Per ogni prodotto sono di interesse un nome, un codice, una descrizione, un costo, la quantit� in magazzino
* Ogni prodotto pu� essere fornito da uno o pi� fornitori, ogni fornitore pu� fornire uno o pi� prodotti
* Ogni fornitore ha una partita iva e un indirizzo, telefono, indirizzo di posta elettronica
* Ogni cliente ha nome, cognome, data di nascita, data di registrazione, indirizzo, indirizzo di posta elettronica

* Un ordine si compone di pi� righe: ogni riga riporta un prodotto, il prezzo unitario del prodotto e la quantit� ordinata (ad es. penne, 1�, 400) 
* Per ogni ordine � necessario memorizzare:
  * la data in cui l'ordine � stato aperto dal cliente 
  * la data in cui l'ordine � stato chiuso dal cliente (da quel momento in poi l'ordine non verr� modificato e l'amministrazione pu� procedere ad evadere l'ordine)
  * la data in cui l'ordine � stato evaso (cio� la data in cui i prodotti ordinati sono stati prelevati dal magazzino e spediti al cliente)
* I clienti che vogliono effettuare gli ordini devono essere registrati nella anagrafica dei clienti della azienda: solo i clienti identificati e autenticati possono effettuare ordini
* Il catalogo dei prodotti pu� essere consultato da clienti non autenticati

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
    * Il cliente sceglie un prodotto e aggiunge codice e quantit� del prodotto scelto all'ordine
    * Il sistema imposta il prezzo unitario del prodotto scelto al prezzo corrente di listino
    * Il sistema registra la riga ordine
    * Il cliente ripete i due passi precedenti finch� necessario
    * Il cliente "chiude" l'ordine
    * Il sistema registra l'ordine
  * Precondizioni: il cliente � identificato e autenticato
  
* Caso d'uso UC3: consulta i propri ordini
  * Attore primario: cliente
  * Scenario principale:
    * Il cliente consulta l'elenco dei propri ordini
    * Il sistema mostra al cliente l'elenco dei suoi ordini
    * Il cliente chiede il dettaglio di un ordine
    * Il sistema mostra il dettaglio dell'ordine
    * Il cliente ripete i due passi precedenti finch� necessario
  * Precondizioni: il cliente � identificato e autenticato

* Caso d'uso UC4: inserimento prodotti nel catalogo
  * Attore primario: amministrazione
  * Scenario principale:
    * L'amministratore inserisce un nuovo prodotto nel catalogo specificandone i dettagli
    * Il sistema registra il prodotto
    * I punti precedenti vengono ripetuti fino a che necessario
  * Precondizioni: l'amministratore � identificato e autenticato
  
* Caso d'uso UC5: recupera indirizzo cliente
  * Attore primario: amministrazione
  * Scenario principale:
    * L�amministratore fornisce l'id di un ordine 
    * Il sistema mostra all�amministratore i dati del cliente che ha effettuato l�ordine
  * Precondizioni: l�amministratore � identificato e autenticato

* Caso d'uso UC6: evasione ordine
  * Attore primario: amministrazione
  * Scenario principale:
    * Il sistema presenta all'amministratore gli ordini chiusi, ma non evasi
    * L'amministratore sceglie un ordine
    * Il sistema evade l'ordine: aggiorna l'ordine inserendo la data di spedizione e aggiorna la quantit� dei prodotti in magazzino (sottraendo la quantit� di prodotti usati per l'ordine)
  * Precondizioni:
    * l'amministratore � identificato e autenticato
  * Eccezioni:
    * alcuni prodotti potrebbero non essere presenti in magazzino nella quantit� specificata dall'ordine. In questo caso l'ordine rimane in sospeso



