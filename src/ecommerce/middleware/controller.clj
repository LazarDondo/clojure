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

 (k/defentity product
  (k/table :product))

 (defn get-all-products []
  (k/select product))

  (defn update-product [params]
    (k/update product
     (k/set-fields params)
     (k/where {:id (:id params)})))

  (defn delete-product [id]
    (k/delete product
     (k/where {:id id})))

(k/defentity order
  (k/table :productorder))

(defn add-order [params]
(k/insert order
 (k/values params)))

 (defn get-all-orders [userId]
  (k/select order
    (k/fields :* [:product.name :product])
    (k/join product (= :productOrder.productId :product.id))
    (k/where {:userId userId})))

(defn update-order [params]
(k/update order
 (k/set-fields params)
 (k/where {:id (:id params)})))