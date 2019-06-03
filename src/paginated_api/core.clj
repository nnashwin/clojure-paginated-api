(ns paginated-api.core
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn])
  (:use ring.adapter.jetty)
  (:gen-class))

;; Read in config for db
;; Check out http://clojure-doc.org/articles/ecosystem/java_jdbc/home.html for the structure of the db-spec
(def db-spec
  (edn/read-string (slurp "data/db-config.edn")))

(defn print-db-connection
  [& args]
  (println "Hello, World!")
  (println "It's the next line")
  (println (jdbc/query db-spec ["SELECT 3*5 AS result"])))

(defn handler [req]
  (print-db-connection)
  {:status 200
   :headers {"Content-type" "text/html"}
   :body "Yo Dude!"})

(run-jetty handler {:port 8675})