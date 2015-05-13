# Agave `Progetto SIW 2015`

--------------
* Gaetano Bonofiglio
* Veronica Iovinella
* Andrea Salvoni

--------------

##### Specifiche originali: https://dl.dropboxusercontent.com/u/20172572/didattica/siw/siw-progetto-1.pptx

### Estensioni
* Quando il **cliente** effettua il login, l'applicazione lo ricorda fino allo scadere della sessione.
* Quando l'**amministratore** effettua il login, l'applicazione lo ricorda fino allo scadere della sessione.
 * Se l'amministratore non è loggato non può accedere al **pannello admin**.
* Quando un amministratore invia un nuovo prodotto deve **caricare un'immagine** che poi verrà mostrata nel riepilogo del prodotto.
* Quando un cliente effettua un **ordine**, ogni prodotto selezionato (**riga ordine**) viene salvato temporaneamente nel **carrello** fino alla conferma o alla cancellazione (di fatto, il carrello rappresenta l'ordine *aperto*). Questo consente di avere gli ordini non confermati in una tabella a parte. Analogamente per gli **ordini evasi**.
 * Il carrello può essere svuotato o confermato. Alla conferma l'ordine diventa effettivo e il carrello viene comunque svuotato.
* Ad ogni prodotto può essere associata una serie di **recensioni** di altri clienti.
 * Una recensione è composta da un **commento**, una **valutazione** da 1 a 5 e un *autore* (un cliente). 
 * Nella pagina del singolo prodotto è mostrato l'elenco delle recensioni. 
 * Nella pagina con l'elenco dei prodotti è mostrata la media delle valutazioni per ogni prodotto.
