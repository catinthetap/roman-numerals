(ns roman-numerals
  (:require [clojure.string :as str])
  (:gen-class))

(def upper-name (comp str/upper-case name))

(defn roman-keys->string [x]
  (apply str (map upper-name x)))

(def roman->number
  {:M  1000
   :CM 900
   :D  500
   :CD 400
   :C  100
   :XC 90
   :L  50
   :XL 40
   :X  10
   :IX 9
   :V  5
   :IV 4
   :I  1})

(defn- get-lowest-roman-for-number [x]
  (let [numbers-to-romans [[1000 :M]
                           [900 :CM]
                           [500 :D]
                           [400 :CD]
                           [100 :C]
                           [90 :XC]
                           [50 :L]
                           [40 :XL]
                           [10 :X]
                           [9 :IX]
                           [5 :V]
                           [4 :IV]
                           [1 :I]]]
    (reduce (fn [acc item]
              (if (<= (first item) x)
                (reduced (second item))
                acc))
            :0
            numbers-to-romans)))

(defn generate [x]
  (roman-keys->string
    (loop [remainder x numerals []]
      (let [lowest-roman (get-lowest-roman-for-number remainder)]
        (if (zero? remainder)
          numerals
          (recur (- remainder (lowest-roman roman->number)) (conj numerals lowest-roman)))))))

(defn string->int [x]
  (try (Integer/parseInt x)
       (catch Exception e nil)))

(defn generate-response [arabic-number]
  (cond
    (zero? arabic-number)
    "There is no Roman numeral for zero"
    (< arabic-number 0)
    "There are no negative Roman numerals"
    (> arabic-number 3999)
    "This application does not support numbers greater than 3,999"
    :else (str "Roman numeral for " arabic-number " is: " (generate arabic-number))))

(defn -main "main function - takes a string as per cmd"
  [string-integer]
  (let [arabic-number (string->int string-integer)]
    (prn (generate-response arabic-number))))