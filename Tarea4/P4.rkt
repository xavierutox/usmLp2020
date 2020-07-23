#lang racket
;;(arbol)
;;definicion de la variable global arbol
(define arbol '(2 () ()))

;;(crear-arbol)
;;inicializa o reinicia un arbol
(define (crear-arbol)
  (set! arbol '())
)

;;(search list numero)
;; busca un numero dentro de un arbol binario de busqueda y retorna si se encuentra o no
;; #t o #f dependiendo de si se encuentra o no
(define (search lista numero)
  (cond
      ((empty? lista)'#f)
      ((= (car lista) numero)'#t)
      ((< (car lista) numero)
       (search (car(cdr (cdr lista))) numero))
      ((> (car lista) numero)
       (search (car (cdr lista)) numero))
   )
)
;; (agregar list numero)
;; Agrega un numero a un arbol binario de busqueda
;; Retorna una lista con el estado que deberia tener el arbol luego de insertar
(define (agregar lista numero)
 (cond
      ((null? lista) (cons numero '(() ())))
      ((= (car lista) numero) lista)
      ((> (car lista) numero)
       (list (car lista) (agregar  (cadr lista) numero) (caddr lista)))
      ((< (car lista) numero)
       (list (car lista)(cadr lista) (agregar (caddr lista) numero )))
   )
)

(define (abb string [numero 0])
    (cond
      ((equal? 'buscar string)
       (search arbol numero))
      ((equal? 'agregar string)
       (set! arbol (agregar arbol numero)))
      ((equal? 'mostrar string)
       arbol)
    )
)