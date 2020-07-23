#lang racket
(define (tipos l1)
  (map (lambda (x)
    (cond
      ((integer? x) 'E)
      ((real? x) 'R)
      ((complex? x) 'C)
    )
   )l1)
)
