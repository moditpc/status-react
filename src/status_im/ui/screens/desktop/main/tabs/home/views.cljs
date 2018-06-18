(ns status-im.ui.screens.desktop.main.tabs.home.views
  (:require-macros [status-im.utils.views :as views])
  (:require [re-frame.core :as re-frame]
            [status-im.utils.gfycat.core :as gfycat]
            [status-im.ui.screens.home.styles :as styles]
            [status-im.ui.screens.home.views.inner-item :as chat-item]
            [taoensso.timbre :as log]
            [status-im.ui.components.icons.vector-icons :as icons]
            [status-im.ui.components.react :as react]))

(views/defview unviewed-indicator [chat-id]
  (let [unviewed-messages-count (re-frame/subscribe [:unviewed-messages-count chat-id])]
    (when (pos? @unviewed-messages-count)
      [react/view
       [react/text {:font  :medium}
        @unviewed-messages-count]])))

(views/defview chat-list-item-inner-view [{:keys [chat-id name group-chat public? public-key] :as chat-item}]
  (letsubs [photo-path [:get-chat-photo chat-id]
            current-chat-id [:get-current-chat-id]
            last-message [:get-last-message chat-id]]
    (let [name (str
                 (if public? "#" "")
                 (or name
                     (gfycat/generate-gfy public-key)))]
      ;(log/debug "chat-list-item:" chat-item)
      ;(log/debug "last-message" last-message)
      [react/view {:style {:font-size 14 :padding 12 :background-color (if (= current-chat-id chat-id) "#eef2f5" :white) :flex-direction :row :align-items :center}}
       (when public?
         [icons/icon :icons/public-chat])
       (when (and group-chat (not public?))
         [icons/icon :icons/group-chat])
       [react/image {:style {:width 46 :height 46 :border-radius 46 :margin-right 16}
                     :source {:uri photo-path}}]
       [react/view {:style {:margin-right 25 :width 183}}
        [react/text {:style {:font-size 14
                             :font-weight (if (= current-chat-id chat-id) 600 :normal)
                             :ellipsize-mode :tail
                             :number-of-lines 1
                             }} name]
        [react/text {:style {:color "#939ba1"
                             :font-size 14}
                     :ellipsize-mode :tail
                     :number-of-lines 1}
         (:content last-message)]]
       [react/view {:style {:justify-content :flex-start}}
        [chat-item/message-timestamp last-message]
        [react/text]]
       [react/view {:style {:flex 1}}]
       [unviewed-indicator chat-id]])))

(defn chat-list-item [[chat-id chat]]
  [react/touchable-highlight {:on-press #(re-frame/dispatch [:navigate-to-chat chat-id])}
   [chat-list-item-inner-view (assoc chat :chat-id chat-id)]])

(views/defview chat-list-view []
  (views/letsubs [home-items [:home-items]]
    [react/view {:style {:flex 1 :background-color :white}}
     [react/view {:style {:align-items :center :flex-direction :row :padding 11}}
      [react/view {:style {:flex 1}}]
      [react/touchable-highlight {:on-press #(re-frame/dispatch [:navigate-to :new-contact])}
       [react/view {:style {:background-color "#0000ff"
                            :width 34
                            :height 34
                            :border-radius 34
                            :justify-content :center
                            :align-items :center}}
        [icons/icon :icons/add {:style {:tint-color :white}}]]]]
     [react/view {:style {:height 1 :background-color "#e8ebec" :margin-horizontal 16}}]
     [react/scroll-view
      [react/view
       (for [[index chat] (map-indexed vector home-items)]
         ^{:key (str chat index)}
         [chat-list-item chat])]]]))
