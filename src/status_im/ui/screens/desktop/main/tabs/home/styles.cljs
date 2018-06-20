(ns status-im.ui.screens.desktop.main.tabs.home.styles
  (:require [status-im.ui.components.colors :as colors]))

(def chat-list-view
  {:flex 1 
   :background-color colors/white})

(defn chat-list-item [current?]
  {:padding 12 
   :flex-direction :row 
   :background-color (if current? colors/gray-lighter colors/white)
   :align-items :center})

(def chat-list-header
  {:flex-direction :row 
   :align-items :center 
   :padding 11})

(def chat-icon
  {:width 46 
   :height 46 
   :border-radius 46 
   :margin-right 16})

(def chat-list-separator
  {:height 1 
   :background-color colors/gray-light
   :margin-horizontal 16})

(def chat-name-box
  {:flex-direction :row 
   :align-items :center})

(def chat-name-last-msg-box
  {:margin-right 25 
   :width 183})

(defn chat-name [current?]
  {:font-size 14
   :font-weight (if current? "600" :normal) }) 

(def chat-last-message 
  {:color colors/gray
   :font-size 14})

(def add-new
  {:background-color colors/blue
   :width 34
   :height 34
   :border-radius 34
   :justify-content :center
   :align-items :center})

(def topic-image
  (merge chat-icon
         {:background-color colors/blue
          :align-items :center
          :justify-content :center}))

(def topic-text
  {:font-size 25.6 
   :color colors/white})
