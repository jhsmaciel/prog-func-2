(ns aula5.exercicio
  (:require [aula4.data :as data]))

;; Utilizar loop/recur para resolver os exercicios

;; retornar o vetor de clientes a chave `:full-name` adicionada nos mapas de clientes. (full-name deve ser a concatenacao do `:first-name` com o `:last-name`).

(defn get-full-name
  [{:keys [first-name last-name]}]
  (str first-name " " last-name))

(defn add-full-names
  [clients]
  (loop [clients-vector clients
         acc []]
    (if (empty? clients-vector)
      acc
      (recur
        (rest clients-vector)
        (let [first-client (first clients-vector)]
          (conj acc
                (assoc first-client :full-name (get-full-name first-client))))))))

(comment
  (add-full-names data/clients)
  )

;; retornar um mapa de compras, onde a chave deve ser o id do cliente e o valor deve ser um vetor que contendo todas as compras do cliente.

(defn get-purchase-map
  [purchases]
  (loop [purchases-vector purchases
         acc {}]
    (if (empty? purchases-vector)
      acc
      (recur
        (rest purchases-vector)
        (let [first-purchase (first purchases-vector)
              first-purchase2 (dissoc first-purchase :client-id)]
          (update acc (:client-id first-purchase) #(conj (or % []) first-purchase2)))))))

(comment
  (get-purchase-map data/purchases)
  )

;; retornar o vetor de clientes com a chave `:purchases`. O valor dessa chave sera um vetor contendo mapas com `:item-id` e `:amount`.

(defn get-clients-with-purchases
  [clients purchases]
  (let [purchases (get-purchase-map purchases)]
    (loop [clients-vector clients
           acc []]
      (if (empty? clients-vector)
        acc
        (recur
          (rest clients-vector)
          (let [first-client (first clients-vector)]
            (conj acc
                  (assoc first-client :purchases (get purchases (:id first-client))))))))))

(comment
  (->> purchases
      get-purchase-map
      (get-clients-with-purchases data/clients))

  (get-clients-with-purchases data/clients data/purchases)
  )

;; escrever uma funcao que tras a frequencia de cada caracter em uma string.

(defn get-chars-frequency
  [word]
  (loop [word-vector word
         acc {}]
    (if (empty? word-vector)
      acc
      (recur
        (rest word-vector)
        (let [first-word-vector (first word-vector)]
          (update acc first-word-vector #(inc (or % 0))))))))

(comment
  (get-chars-frequency "abacaxi")
  (get-chars-frequency "arara")
  (get-chars-frequency "")
  (get-chars-frequency ["a" "c" "b"])
  (get-chars-frequency [1 2 3 3 3 4 5 1 2])
  )