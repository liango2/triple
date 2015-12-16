(ns ^:figwheel-no-load triple.dev
  (:require [triple.core :as core]
            [figwheel.client :as figwheel :include-macros true]
            [devtools.core :as devtools]))

(enable-console-print!)

(figwheel/watch-and-reload
 :websocket-url "ws://localhost:3449/figwheel-ws"
 :jsload-callback core/init!)

(devtools/set-pref! :install-sanity-hints true)
(devtools/install!)

(core/init!)

