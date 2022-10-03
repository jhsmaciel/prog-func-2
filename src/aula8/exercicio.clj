(ns aula8.exercicio)

;; 1- Criar um temporizador que exibe a hora
;; durante uma quantidade determinada de tempo.
;; Possibilitar que uma vez iniciado ele seja interrompido a qualquer momento via repl
;; Segue algumas funcoes auxiliares:

(defn minutos->milisegundos
  [minutos]
  (* minutos 60 1000))

;(minutos->milisegundos 15)=> 900000

(defn hora-agora []
  (.getTime (java.util.Date.)))

;(hora-agora)=> #inst"2022-09-30T10:06:29.477-00:00"

(defn pausa [mls]
  (Thread/sleep mls))

;(pausa 1000)
;"Elapsed time: 1005.258208 msecs"
;=> nil

(defn temporizador
  [minutos]
  (future
    (let [horario-para-parar (+ (hora-agora) (minutos->milisegundos minutos))]
      (loop []
        (if (<= (hora-agora) horario-para-parar)
          (do
            (println (java.util.Date. (hora-agora)))
            (pausa 1000)
            (recur))
          "Parou!!")))))

(comment
  (def abc (temporizador 0.25))
  (future-cancel abc)
  (future-cancelled? abc)
  )

;; 2- Criar uma funcao que recebe uma mensagem-prometida,
;; entao imprime repetidamente um contador de segundos
;; at� que uma mensagem seja entregue via uma promise, deve imprimir
;; a mensagem e o valor acumulado do contador

(defn imprime-msg-prometida
  [msg-prometida]
  (future
    (loop [contador 0]
      (if-not (realized? msg-prometida)
        (do
          (pausa 1000)
          (println "Contador:" contador)
          (recur (+ contador 1000)))
        (println (str "Promessa recebida: " @msg-prometida "\n" "Contador " contador))))))

(def mensagem-prometida (promise))

(imprime-msg-prometida mensagem-prometida)

(deliver mensagem-prometida "Oi!")






