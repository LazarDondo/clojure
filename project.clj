(defproject e-commerce "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
      [org.clojure/clojure "1.10.3"]
      [migratus "1.3.6"]
      [mysql/mysql-connector-java "8.0.15"]
      [org.clojure/java.jdbc "0.7.9"]
      [ring-webjars "0.2.0"]
      [ring/ring-core "1.7.1"]
      [ring/ring-defaults "0.3.2"]
      [ring/ring-json "0.5.0-beta1"]
      [org.webjars/bootstrap "4.3.0"]
      [org.webjars/font-awesome "5.7.2"]
      [org.webjars/jquery "3.3.1-2"]
      [buddy/buddy-auth "3.0.323"]
      [compojure "1.6.2"]
      [liberator "0.15.3"]
      [selmer "1.12.50"]
      [markdown-clj "1.10.0"]
      [metosin/ring-http-response "0.9.1"]
      [ring/ring-anti-forgery "1.3.0"]
      [funcool/struct "1.4.0"]
      [korma/korma "0.4.3"]
      [org.clojure/java.jdbc "0.7.12"]
      [mysql/mysql-connector-java "8.0.15"]
  ]

  :plugins [
      [migratus-lein "0.7.3"]
      [lein-ring "0.12.5"]
  ]

  :main ^:skip-aot e-commerce.core
  :target-path "target/%s"

  :migratus {:store :database
             :migration-dir "resources/migrations"
             :db (clojure.edn/read-string (slurp "configuration/migratus-conf.edn"))}

  :ring {:handler ecommerce.handler/app}

  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})

