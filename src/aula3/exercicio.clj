(ns aula3.exercicio
  (:require [aula3.data :refer [mapa]]
            [clojure.core])
  (:require [aula3.data :as data]))


;; escrever uma funcao que retorna a primeira compra do cliente dado o ID.
(defn get-primeira-compra-cliente
  "funcao que retorna a primeira compra do cliente dado o ID."
  [{compras :compras} id]
  (let [{[primeiro] id} compras]
    primeiro))

(comment
  (get-primeira-compra-cliente mapa "3")
  )
(defn first-purchase
  [id mapa]
  (let [{{[primeira-compra-cliente] id}
         :compras} mapa]
  primeira-compra-cliente))

(defn get-compras-cliente
  "funcao que retorna as compras de um cliente dado o ID."
  [{:keys [compras] } id]
  (get compras id))

(comment
  (get-compras-cliente mapa "3")
  )

;; escrever uma funcao que adiciona a compra {:item "mesa" :valor 250} no vetor de compras de um cliente, dado o ID. Deve retornar o mapa inteiro.

;; com assoc-in
(defn adiciona-compra
  [{compras :compras :as mapa} id nova-compra]
  (let [{compras-cliente id} compras]
    (assoc-in mapa [:compras id]
              (conj compras-cliente nova-compra))))

;; com update-in
(defn adiciona-compra2
  [mapa id nova-compra]
  (update-in mapa [:compras id] conj nova-compra))

(defn adiciona-compra
  "funcao que adiciona a compra {:item \"mesa\" :valor 250} no vetor de compras de um cliente, dado o ID. Deve retornar o mapa inteiro."
  [mapa id novo-item]
  (update-in mapa [:compras id] conj novo-item))

(comment
  (adiciona-compra mapa "5" {:item "mesa" :valor 250})
  )

;; escrever uma funcao que da desconto de 10% para o primeiro item de compra do cliente dado o ID. Deve retornar o mapa inteiro

(defn desconto-valor
  "funcao que da desconto de 10% para o primeiro item de compra do cliente dado o ID. Deve retornar o mapa inteiro"
  [mapa id]
  (update-in mapa [:compras id 0 :valor] #(* % 0.9)))

(comment
  (desconto-valor mapa "5")
  )


(defn abc
  [desconto ])

(* 1.1M 200)

;; com assoc-in
(defn adiciona-desconto
  [{compras :compras :as mapa} id]
  (let [{[{valor-primeira-compra :valor}] id} compras]
    (assoc-in mapa [:compras id 0 :valor] (* 0.9 valor-primeira-compra))))


;; com update-in
(defn adiciona-desconto2
  [mapa id]
  (update-in mapa [:compras id 0 :valor] #(* 0.9 %)))

