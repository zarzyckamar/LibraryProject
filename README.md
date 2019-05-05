# LibraryProject
#### Recruitment Library Project

> Aplikacja korzysta z integrenowanego tomcata odpalanego ze spring boota.

> Aplikacja kliencka udostępnia trzy endpointy na porcie 8080:
>- wyświetlanie książki po zadanym polu ISBN ( path= "**/api/byISBN**", metoda GET),
>- wyświetlanie książek według kategorii ( path= "**/api/byCategory**", metoda GET),
>- wyświetlenie autorów i ich średniej oceny w porządku malejącym (path ="**/api/byAverageRating**", metoda GET),  
>- _oraz dodatkowy pozwalający wyświetlić wszystkie pozycje w księgozbiorze ( path= "/api/all", metoda GET)._


> Aplikacja wykorzystuje 5 głównych metod:
> - getJsonToObject() - zapis pliku json do obiektu
> - creatOutputModel() - zwrócenie listy z ksiązkami według utworzonego modelu,
> - getBookByISBN() - zwrócenie książki z listy książek po zadanym stringu isbn,
> - getBookByCategory() - zwrócenie listy książek po kategorii (zadanym stringu category),
> - createListWithAverageRating() - zwrócenie listy autorów wraz z ich średnimi ocenami.
