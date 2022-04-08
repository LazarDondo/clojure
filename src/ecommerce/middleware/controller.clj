(ns ecommerce.middleware.controller
  (:require [clojure.java.jdbc :as sql]
            [korma.core :as k]
            [korma.db :refer [defdb mysql]])
  (:import java.sql.DriverManager))

(def db-config(clojure.edn/read-string (slurp "configuration/migratus-conf.edn")))

(defdb db (mysql db-config))

(k/defentity user
  (k/table :user))

(defn find-user [params]
(k/select user
 (k/where params)))