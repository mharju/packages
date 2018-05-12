(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.9.0-SNAPSHOT" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "0.3.1")
(def +version+ (str +lib-version+ "-0"))
(def +lib-folder+ (str "react-pose" +lib-version+))

(task-options!
 pom  {:project     'cljsjs/leaflet-omnivore
       :version     +version+
       :description "Leaflet Omnivore Plugin"
       :url         "https://github.com/mapbox/leaflet-omnivore"
       :scm         {:url "https://github.com/cljsjs/packages"}
       :license     {"MIT" "http://opensource.org/licenses/MIT"}})

(deftask package []
  (comp
    (download :url      (str "https://api.tiles.mapbox.com/mapbox.js/plugins/leaflet-omnivore/v" +lib-version+ "/leaflet-omnivore.min.js")
              :target "cljsjs/leaflet-omnivore/leaflet-omnivore.inc.js"
              :checksum "EFEA2EDA2077AA58AC8FF3B63FBCE54B")
    (deps-cljs :provides ["leaflet-omnivore" "cljsjs.leaflet-omnivore"]
               :requires ["cljsjs.leaflet"])
    (sift :include #{#"^cljsjs" #"^deps.cljs"})
    (pom)
    (jar)))
