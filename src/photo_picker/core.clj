(ns photo-picker.core
 (:require [fs.core :as fs])
 (:import (java.io File)))

(defn foo [src-folder]
  (let [folders (.listFiles (File. src-folder))]
    (dotimes [i-th-folder 5]
      (let [folder (rand-nth folders)
            files (.listFiles folder)]
         (println (str "folder: " folder))
         (dotimes [j-th-file 2]
           (let [file (rand-nth files)]
             (println file)
             (if (fs/file? file)
               (fs/copy+ file (str "/home/tony-common/tmp/" i-th-folder "/" j-th-file ".jpg")))))))))


;(defn -main [args]
;   (foo (get 0
