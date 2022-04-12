(ns ecommerce.routes.order
    (:require
      [compojure.core :refer :all]
      [ecommerce.layout :as layout]
      [clojure.java.io :as io]
      [ring.util.http-response :as response]
      [compojure.core :refer :all]
      [struct.core :as st]
      [ring.util.response :refer [redirect]]
      [selmer.parser :refer [render-file]]
      [ecommerce.middleware.controller :as controller]
      [compojure.response :refer [render]]
      [buddy.auth :refer [authenticated?]]
      [liberator.core :refer [defresource]]
      [clojure.data.json :as json]
      [clojure.java.io :as io]
      [liberator.representation :refer [ring-response as-response]]
      [clojure.set :refer [rename-keys]]
      [clojure.string :as str]))
    
    (def order-schema
      {:quantity [st/required st/number]
       :orderDate [st/required st/string]
       :shippingAddress [st/required st/string]
       :productId [st/required st/number]
       :userId [st/required st/number]})
    
    (defn order-valid? [params]
      (st/valid? {:quantity (:quantity params)
                  :orderDate (:orderDate params)
                  :shippingAddress (:shippingAddress params)
                  :productId (:productId params)
                  :userId (:userId params)
   } order-schema))
    
    (defn get-add-order-page [session &[message]]
      (if-not (authenticated? session)
        (redirect "/")
        (render-file "new-order.html" {:title "New order"
                                        :products (controller/get-all-products)
                                        :admin (layout/is-admin? session)
                                        :logged (:identity session)})))
    
    (defn add-order [{:keys [params session]}]
      (order-valid? params)
      (controller/add-order params)
      (redirect "/orders"))
    
    (defn get-all-orders [session &[message]]
      (render-file "orders.html" {:orders (controller/get-all-orders (:id (:identity session)))
                                      :admin (layout/is-admin? session)}))
    
    (defn edit-order [{:keys [params session]}]
      (controller/update-order params))
    
    (defroutes order-routes
      (GET "/order" order (get-add-order-page (:session order)))
      (POST "/order" order (add-order order))
      (GET "/orders" order (get-all-orders (:session order)))
      (PUT "/order" order (edit-order order)))