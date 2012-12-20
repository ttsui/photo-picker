(ns photo-picker.core
 (:require [fs.core :as fs])
 (:import [java.io File]
          [com.drew.metadata.exif ExifIFD0Directory ExifIFD0Descriptor]
          [com.drew.imaging ImageMetadataReader]))

(defn orientation [photo]
 (let [metadata (ImageMetadataReader/readMetadata photo)]
   (.getOrientationDescription (ExifIFD0Descriptor. (.getDirectory metadata ExifIFD0Directory)))))

(defn is-jpg? [file]
  (= ".jpg" (.toLowerCase (fs/extension file))))

(defn foo [src-folder dest-folder]
  (let [folders (.listFiles (File. src-folder))]
    (dotimes [i-th-folder 1]
      (let [folder (rand-nth folders)
            files (.listFiles folder)]
         (println (str "folder: " folder))
         (dotimes [j-th-file 1]
           (let [file (rand-nth files)
                 extension (fs/extension file)]
             (println file)
             (if (and (fs/file? file) (is-jpg? file))
               (do 
                 (println (orientation file))
                 (fs/copy+ file (str dest-folder "/" (.getName folder) "/" (.getName file)))))))))))


;(defn -main [args]
;   (foo (get 0
