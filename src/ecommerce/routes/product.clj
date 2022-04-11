(ns ecommerce.routes.product
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
    
    (def product-schema
      {:name [st/required st/string]
       :description [st/required st/string]
       :price [st/required st/number]
       :supplierId [st/required st/number]})   

    (defn product-valid? [params]
      (st/valid? {:name (:name params)
                  :description (:description params)
                  :price (:price params)
                  :supplierId (:supplierId params)
   } product-schema))  
    
    
    (defn get-all-products [session]
      (render-file "products.html" {:products (controller/get-all-products)
                                      :admin (layout/is-admin? session)}))
    
    (defn edit-product [{:keys [params session]}]
      (controller/update-product params))

    (defn delete-product [{:keys [params session]}]
        (controller/delete-product (:id (:identity session))))
    
    (defroutes product-routes
      (GET "/products" product (get-all-products (:session product)))
      (PUT "/product" product (edit-product product))
      (DELETE "/product" product (delete-product product)))