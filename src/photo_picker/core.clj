(ns photo-picker.core
 (:require [fs.core :as fs])
 (:import (java.io File)))

(defn foo [src-folder]
  (let [folders (.listFiles (File. src-folder))]
    (dotimes [i-th-folder 100]
      (let [folder (rand-nth folders)
            files (.listFiles folder)]
         (println (str "folder: " folder))
         (dotimes [j-th-file 10]
           (let [file (rand-nth files)
                 extension (fs/extension file)]
             (println file)
             (if (and (fs/file? file) (= ".jpg" (.toLowerCase (fs/extension file))))
               (fs/copy+ file (str "/home/tony/tmp/" i-th-folder "/" j-th-file ".jpg")))))))))


;(defn -main [args]
;   (foo (get 0
