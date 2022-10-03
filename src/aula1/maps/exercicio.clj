(ns aula1.maps.exercicio
  (:require [aula1.maps.data :refer [cliente compras]]))

;; dado o mapa no namespace data.clj, escrever funcoes que:
;; altera a idade do cliente
;; remove o endereco do cliente
;; atualiza o valor de um item de compra
;; adicionar o vetor de compras no mapa do cliente
;; adiciona um novo item de compra
;; retorna o nome do cliente

(defn altera-idade
  [cliente idade]
  (assoc cliente :idade idade))

(defn remove-endereco
  [cliente]
  (dissoc cliente :endereco))

(defn atualiza-valor-item
  [compras idx valor]
  (assoc-in compras [idx :valor] valor))

(defn adiciona-compra->cliente
  [cliente compras]
  (assoc cliente :compras compras))

(defn adicionar-um-item-na-compra
  [cliente compras item]
  (-> cliente
      (adiciona-compra->cliente compras)
      (update :compras conj item)))

(defn get-nome-cliente
  [cliente]
  (:nome cliente))

(assoc compras 3 {:item "" :valor 50})

(comment
  (-> cliente
    (adicionar-um-item-na-compra compras {:item "fralda" :valor 50})
    get-nome-cliente)
  )

