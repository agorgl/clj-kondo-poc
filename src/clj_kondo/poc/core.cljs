(ns clj-kondo.poc.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [clj-kondo.poc.events :as events]
   [clj-kondo.poc.routes :as routes]
   [clj-kondo.poc.views :as views]
   [clj-kondo.poc.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (routes/start!)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
