(ns status-im.data-store.realm.schemas.base.v6.core
  (:require [status-im.data-store.realm.schemas.base.v1.network :as network]
            [status-im.data-store.realm.schemas.base.v4.bootnode :as bootnode]
            [status-im.data-store.realm.schemas.base.v6.account :as account]
            [taoensso.timbre :as log]))

(def schema [network/schema
             bootnode/schema
             account/schema])

(defn migration [old-realm new-realm]
  (log/debug "migrating base database v6: " old-realm new-realm))
