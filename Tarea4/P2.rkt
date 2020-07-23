#lang racket
(define (periS lados)
  (if (empty? lados) 0
      (+ (periS (cdr lados)) (car lados))
  )
)
(define (periC lados [suma 0])
  (if (empty? lados) suma
      (periC (cdr lados) (+ suma (car lados))))
)
