(ns status-im.ui.screens.desktop.main.add-new.views
  (:require-macros [status-im.utils.views :as views])
  (:require [status-im.ui.components.icons.vector-icons :as icons]
            [re-frame.core :as re-frame]
            [status-im.ui.screens.add-new.new-public-chat.db :as public-chat-db]
            [taoensso.timbre :as log]
            [status-im.ui.components.react :as react]))

(views/defview new-contact []
  (views/letsubs [new-contact-identity [:get :contacts/new-identity]
                  contacts      [:all-added-people-contacts]
                  chat-error [:new-contact-error-message]
                  topic [:get :public-group-topic]
                  topic-error [:new-topic-error-message]
                  account [:get-current-account]]
    [react/scroll-view
     [react/view {:style {:flex 1 :background-color :white :margin-left 24 :margin-right 37}}
     ^{:key "newcontact"}
     [react/view {:style {:height 64 :align-items :flex-start :justify-content :center}}
      [react/text {:style {:font-size 20 :color :black :font-weight "600"}}
       "New Chat"]]
     [react/text {:style {:font-size 14}} "Contact code"]
     [react/view {:style {:height 1 :background-color "#e8ebec"}}]
     [react/view {:style {:height 90 :margin-bottom 16 :background-color :white :border-radius 12}}
                          ;:box-shadow "0 0.5px 4.5px 0 rgba(0, 0, 0, 0.04)"}}
      [react/view {:style {:flex-direction :row :margin-top 16}}
       [react/text-input {:placeholder "0x..."
                          :flex 1
                          :style {:font-size 14 :background-color "#eef2f5" :margin-right 12}
                          :on-change   (fn [e]
                                         (let [native-event (.-nativeEvent e)
                                               text (.-text native-event)]
                                           (re-frame/dispatch [:set :contacts/new-identity text])))}]
       [react/touchable-highlight {:on-press #(when-not chat-error (re-frame/dispatch [:add-contact-handler new-contact-identity]))}
        [react/view {:style {:width 140 :height 45 :border-radius 8 :background-color (if chat-error "#eef2f5" "#4360df") :align-items :center
                             :justify-content :center}}
         [react/text {:style {:font-size 16 :color (if chat-error "#939ba1" :white)}} "Start chat"]]]]]
     ^{:key "choosecontact"}
     [react/view
      (when (seq contacts) [react/text {:style {:font-size 14}} "Or choose a contact"])
      [react/view {:style {:margin-top 12}} 
       (doall
        (for [c contacts]
        ^{:key (:whisper-identity c)}
        [react/touchable-highlight {:on-press #(do
                                                 (re-frame/dispatch [:set :contacts/new-identity (:whisper-identity c)])
                                                 (re-frame/dispatch [:add-contact-handler (:whisper-identity c)]))}
         [react/view {:style {:flex-direction "row"
                             :align-items :center
                             :margin-bottom 16}}
         [react/image {:style {:width 46 :height 46 :border-radius 46 :margin-right 16}
                       :source {:uri (:photo-path c)}}]
         [react/text {:style {:font-size 14}} (:name c)]]]))]]
     ^{:key "publicchat"}
     [react/view {:style {:height 64 :align-items :flex-start :justify-content :center}}
      [react/text {:style {:font-size 20 :color :black :font-weight "600"}}
       "Join Public Chat"]]
     [react/text {:style {:font-size 14}} "Topic"]
     [react/view {:style {:height 1 :background-color "#e8ebec"}}]
     [react/view {:style {:height 90 :margin-bottom 16 :background-color :white :border-radius 12}}
                          ;:box-shadow "0 0.5px 4.5px 0 rgba(0, 0, 0, 0.04)"}}
      [react/view {:style {:flex-direction :row :margin-top 16}}
       [react/view {:style {:flex 1}}
        [react/text-input {:placeholder "#"
                           :flex 1
                           :style {:font-size 14 :background-color "#eef2f5" :margin-right 12}
                           :on-change   (fn [e]
                                          (let [native-event (.-nativeEvent e)
                                                text (.-text native-event)]
                                            (re-frame/dispatch [:set :public-group-topic text])))}]]
       [react/touchable-highlight {:on-press #(when-not topic-error (re-frame/dispatch [:create-new-public-chat topic]))}
        [react/view {:style {:width 150 :height 45 :border-radius 8 :background-color (if topic-error "#eef2f5" "#4360df") :align-items :center
                             :justify-content :center}}

         [react/text {:style {:font-size 16 :color (if topic-error "#939ba1" :white) }} "Join public chat"]]]]]]]))
