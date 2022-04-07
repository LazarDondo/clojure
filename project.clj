(defproject e-commerce "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
      [org.clojure/clojure "1.10.3"]
      [migratus "1.3.6"]
      [org.slf4j/slf4j-log4j12 "1.7.26"]
      [mysql/mysql-connector-java "8.0.15"]
      [org.clojure/java.jdbc "0.7.9"]
      [ch.qos.logback/logback-classic "1.2.6"]
  ]

  :plugins [
      [migratus-lein "0.7.3"]
  ]

  :main ^:skip-aot e-commerce.core
  :target-path "target/%s"

  :migratus {:store :database
             :migration-dir "resources/migrations"
             :db (clojure.edn/read-string (slurp "configuration/migratus-conf.edn"))}

  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})

