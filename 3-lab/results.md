==============Insert=================

---Insert elements to begin( 50k )

LinkedList: 7 ms

ArrayList: 3202 ms

LinkedList is faster

Потому что в ArrayList необходимо сдвигать или копировать массив. А добавление певрого элемента в LinkedList происходит за O(1)

---Insert elements to middle( 50k )

LinkedList: 61487 ms

ArrayList: 2083 ms

ArrayList is faster

Потому что в LinkedList необходимо сначала найти место вставки за O(i). У ArrayList поиск составляет O(1), затем сдвиг или копирование массива

---Insert elements to end( 50k )

LinkedList: 3 ms

ArrayList: 6 ms

Time is equal

Потому что ArrayList не сдвигает массив. А добавление последнего элемента в LinkedList происходит за O(1)

==============Get====================

---Get elements from begin ( 50k )

LinkedList: 2 ms

ArrayList: 4 ms

Time is equal

Потому что получение первого элемента у LinkedList происходит за O(1), а у ArrayList для любого элемента за O(1)

---Get elements from middle ( 50k )

LinkedList: 74830 ms

ArrayList: 2 ms

ArrayList is faster

Потому что получение элемента в общем слуае у LinkedLIst происходит за O(i), а у ArrayList для любого элемента за O(1)

---Get elements from end ( 50k )

LinkedList: 4 ms

ArrayList: 1 ms

Time is equal

Потому что получение последнего элемента у LinkedList происходит за O(1), а у ArrayList для любого элемента за O(1)

==============Remove=================

---Remove elements from begin ( 50k )

LinkedList: 5 ms

ArrayList: 3657 ms

LinkedList is faster

Потому что в ArrayList необходимо сдвигать или копировать массив. А удаление певрого элемента в LinkedList происходит за O(1)

---Remove elements from middle ( 50k )

LinkedList: 73829 ms

ArrayList: 1776 ms

ArrayList is faster

Потому что в LinkedList необходимо сначала найти место улаления за O(i). У ArrayList поиск составляет O(1), затем сдвиг или копирование половины массива

---Remove elements from end ( 50k )

LinkedList: 2 ms

ArrayList: 3 ms

Time is equal

Потому что ArrayList не сдвигает массив. А добавление удаление элемента в LinkedList происходит за O(1)
