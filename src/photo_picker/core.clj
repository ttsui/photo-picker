(ns photo-picker.core
 (:require [me.raynes.fs :as fs])
 (:import [java.io File]
          [com.drew.metadata.exif ExifIFD0Directory ExifIFD0Descriptor]
          [com.drew.imaging ImageMetadataReader]))

(defn orientation [photo]
 (let [metadata (ImageMetadataReader/readMetadata photo)]
   (.getOrientationDescription (ExifIFD0Descriptor. (.getDirectory metadata ExifIFD0Directory)))))

(defn is-jpg? [file]
  (= ".jpg" (.toLowerCase (fs/extension file))))

(defn foo [src-folder dest-folder]
  (let [contents (.listFiles (File. src-folder))
        folders (filter (fn [f] (.isDirectory f)) contents)]
    (dotimes [i-th-folder 30]
      (let [folder (rand-nth folders)
            files (.listFiles folder)]
         (println (str "folder: " folder))
         (dotimes [j-th-file 10]
           (let [file (rand-nth files)
                 extension (fs/extension file)]
             (println file)
             (if (and (fs/file? file)
                      (is-jpg? file)
                      (.startsWith (orientation file) "Top, left side"))
               (do 
                 (println (orientation file))
                 (fs/copy+ file (str dest-folder "/" (.getName folder) "/" (.getName file)))))))))))

;(defn -main [args]
;   (foo (get 0
