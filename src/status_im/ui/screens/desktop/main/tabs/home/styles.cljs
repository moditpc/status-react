(ns status-im.ui.screens.desktop.main.tabs.home.styles
  (:require-macros [status-im.utils.views :as views])
  (:require [re-frame.core :as re-frame]
            [status-im.utils.gfycat.core :as gfycat]
            [status-im.ui.screens.home.styles :as styles]
            [status-im.ui.screens.home.views.inner-item :as chat-item]
            [taoensso.timbre :as log]
            [status-im.ui.components.icons.vector-icons :as icons]
            [status-im.ui.components.react :as react]))

(def chat-list-view
  {:flex 1 
   :background-color :white})

(def chat-list-item
  {:padding 12 
   :flex-direction :row 
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
   :background-color "#e8ebec" 
   :margin-horizontal 16})

(def chat-name-last-msg-box
  {:margin-right 25 
   :width 183})

(def chat-name
  {:font-size 14}) 

(def chat-last-message 
  {:color "#939ba1"
   :font-size 14})

(def add-new
  {:background-color "#0000ff"
   :width 34
   :height 34
   :border-radius 34
   :justify-content :center
   :align-items :center})
