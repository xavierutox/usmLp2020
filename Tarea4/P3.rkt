#lang racket
;;(join l1 l2)
;; Recibe 2 listas y las une en una sola
;; retorna una lista
(define (join l1 l2)
  (append l1 l2)
)
;;(menor l1)
;; Retorna el menor elemento de una lista
;; retorna un numero
(define (menor l1)
   (car(sort l1 <))
)
;;(ordenar l1)
;; Recibe una lista y retorna esa lista ordenada
;; Retorna una lista
(define (ordenar l1)
   (do ( (l2 '() (append l2 (list (menor l3)))) 
        (l3 l1 (remove (menor l3) l3)))
        ((= (length l2) (length l1)) l2))
)

(define (merge l1 l2)
  (ordenar (join l1 l2))
)