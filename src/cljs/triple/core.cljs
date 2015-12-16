(ns triple.core
  (:require [reagent.core :as r :refer [cursor atom]]))

(defn app []
  [:h1 "Hello world!"])

(defn init! []
  (r/render-component app (.getElementById js/document "app")))



