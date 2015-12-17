(defproject triple "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [com.taoensso/timbre "4.1.4"]
                 [environ "1.0.1"]
                 [compojure "1.4.0"]
                 [ring "1.4.0"]
                 [reagent "0.5.1" :exclusions [cljsjs/react]] ;使用CDN加载React依赖。
                 [secretary "1.2.3"]]
  :plugins [[lein-figwheel "0.5.0-1"]
            [lein-ring "0.9.7"]
            [lein-garden "0.2.6"]
            [lein-cljsbuild "1.1.1"]
            [lein-bower "0.5.1"]
            [lein-environ "1.0.1"]
            [lein-externs "0.1.5"]]
  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"
                                    "test/js"]
  :bower-dependencies []
  :bower {:directory "resources/public/lib"}
  :source-paths ["src/clj" "src/cljs"]
  :figwheel {:css-dirs ["resources/public/css"]}
  :garden {:builds [{:id "screen"
                     :source-paths ["src/clj"]
                     :stylesheet triple.style/screen
                     :compiler {:vendors ["webkit"]
                                :auto-prefix #{:transition}
                                :output-to "resources/public/css/screen.css"
                                :prettyprint? false}}]}
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs" "env/dev/cljs"]
                        :figwheel {:on-jsload "triple.core/on-js-reload"}
                        :compiler {:main triple.dev
                                   :asset-path "js/compiled/out"
                                   :output-to "resources/public/js/compiled/triple.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :source-map-timestamp true}}
                       {:id "prod"
                        :source-paths ["src/cljs" "env/prod/cljs"]
                        :compiler {:main triple.prod
                                   :output-to "resources/public/js/compiled/triple.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]}
  :profiles {:dev {:source-paths ["env/dev/clj"]
                   :dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [binaryage/devtools "0.4.1"]
                                  [figwheel-sidecar "0.5.0"]]
                   :repl-options {:init-ns triple.dev
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
             :prod {:source-paths [ ]}})
