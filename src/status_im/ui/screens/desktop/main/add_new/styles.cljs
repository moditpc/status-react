(ns status-im.ui.screens.desktop.main.add-new.styles)

(def new-contact-view
  {:flex 1 
   :background-color :white 
   :margin-left 24 
   :margin-right 37})

(def new-contact-title 
  {:height 64 
   :align-items :flex-start 
   :justify-content :center})

(def new-contact-title-text
  {:font-size 20 
   :color :black 
   :font-weight "600"})

(def new-contact-subtitle
  {:font-size 14})

(def new-contact-separator
  {:height 1 :background-color "#e8ebec"})

(def add-contact-edit-view
  {:height 45 
   :margin-bottom 32 
   :background-color :white 
   :border-radius 12
   :flex-direction :row 
   :margin-top 16})

(def add-contact-input
  {:font-size 14 
   :background-color "#eef2f5" 
   :margin-right 12 
   :border-radius 8})

(def add-contact-button 
  {:width 140 
   :height 45 
   :border-radius 8 
   :align-items :center
   :justify-content :center})

(def add-contact-button-text 
  {:font-size 16 })

(def suggested-contact-view
  {:flex-direction "row"
   :align-items :center
   :margin-bottom 16})

(def suggested-contact-image
  {:width 46 
   :height 46 
   :border-radius 46 
   :margin-right 16})

(def suggested-topic-image
  (merge suggested-contact-image
         {:background-color "#4360df"
          :align-items :center
          :justify-content :center}))

(def suggested-topic-text
  {:font-size 25.6 
   :color :white})
