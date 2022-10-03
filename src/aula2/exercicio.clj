(ns aula2.exercicio
  (:require [aula2.data :as data]
            [aula2.listas-e-vetores :as lista]
            [clojure.set :as cset]
            [clojure.dls]))


;; 1. criar um vetor com nome de clientes que conter� todos os clientes de ambas as colec�es do namespace `aula2.data`.

(defn clients
  "Get clients names in a vector."
  [& clients]
  (->> (apply concat clients)
       ;; (map #(str (:first-name %) " " (:last-name %)))
       (into [])))

(comment
  (clients data/clientes-lista data/clientes-vetor)
  )

;; 2. adicionar um cliente no vetor `clientes`.

(defn add-new-client
  "Add a new client in the client vector."
  [clients new-client]
  (conj clients new-client))

(comment
  (-> (clients data/clientes-lista data/clientes-vetor)
      (add-new-client lista/novo-cliente))
  )

;; 3. retornar o cliente na posic�o `p` do vetor clientes.

(defn find-client
  "Find a client in a specific position of the clients vector."
  [clients index]
  (get clients index))

(comment
  (-> (clients data/clientes-lista data/clientes-vetor)
      (find-client 2))
  (-> (clients data/clientes-lista data/clientes-vetor)
      (find-client 100))
  )

;; 4. retornar o nome do cliente mais novo do vetor `clientes`.

(defn get-name-from-newest-client
  "Get the name from the newest client."
  [clients]
  (let [{:keys [first-name last-name]} (peek clients)]
    (str first-name " " last-name)))

(comment
  (-> (clients data/clientes-lista data/clientes-vetor)
      get-name-from-newest-client)
  )

;; 5. remover o cliente mais novo do vetor `clientes`.

(defn remove-newest-client
  "Remove the newest client from the clients vector."
  [clients]
  (pop clients))

(comment
  (-> (clients data/clientes-lista data/clientes-vetor)
      (remove-newest-client))
  )

;; 6. retornar o nome do cliente mais antigo do vetor `clientes`.

(defn get-oldest-client
  "Get the oldest client from the clients vector."
  [clients]
  (first clients))

(comment
  (-> (clients data/clientes-lista data/clientes-vetor)
      get-oldest-client)
  )

;; 7. remover o cliente mais antigo do vetor `clientes`.

(defn remove-oldest-client
  "Remove the oldest client from the clients vector."
  [clients]
  (rest clients))

(comment
  (-> (clients data/clientes-lista data/clientes-vetor)
      remove-oldest-client)
  )

;; 8. remover do vetor `clientes` os clientes com o `last-year-revenue` menor que 50000 (dica: usar `remove` ou `filter`, procurar na documentac�o);

(defn remove-clients-last-revenue-year<50000
  "Remove clients by last revenue year less than 50000."
  [clients]
  (filter #(> (:last-year-revenue %) 50000) clients))

(comment
  (-> (clients data/clientes-lista data/clientes-vetor)
      remove-clients-last-revenue-year<50000)
  )

