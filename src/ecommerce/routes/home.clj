(ns ecommerce.routes.home

  (:require
   [compojure.core :refer :all]
   [ecommerce.layout :as layout]
   [compojure.core :refer :all]
   [struct.core :as st]
   [ring.util.response :refer [redirect]]
   [selmer.parser :refer [render-file]]
   [ecommerce.middleware.controller :as controller]
   [compojure.response :refer [render]]
   [buddy.auth :refer [authenticated?]]
   [liberator.core :refer [defresource]]
   [liberator.representation :refer [ring-response as-response]]
   [clojure.set :refer [rename-keys]]
   [ecommerce.layout :as layout]))

   (defn home-page [&[error]]
    (render-file "login.html" {:title "Login"
                               :error error}))
  
  (def login-schema
    {:username [st/required st/string]
     :password [st/required st/string]})
  
  
  (defn get-login-page [&[error]]
    (render-file "/" {:title "Login"
                      :error error}))
  
  (defn validate-user? [params]
    (st/valid? {:username (:username params)
                :password (:password params)} login-schema))
  
  (defn get-user [params]
    (first (controller/find-user params)))
  
  
  (defn login-page-submit [{:keys [params session]}]
    (let [user (get-user params)]
      (cond
        (not (validate-user? params))
        (home-page "Enter username and password")
        (empty? user)
        (home-page "Enter credentials")
        :else
        (assoc (redirect "/home"):session (assoc session :identity user))))
  )
  
  (defn logout [request]
    (-> (redirect "/")
        (assoc :session {})))
  
  (defn home [session]
    (render-file "home.html" {:title "Home"
                               :logged (:identity session)
                                :admin (layout/is-admin? session)}))
  
  
  
  (defroutes home-routes
             (GET "/" [] (home-page))
             (POST "/" request (login-page-submit request ))
             (GET "/home" request (home (:session request))))