(ns codewars.core
  (:require [clojure.string :as str]))

;; Your task is to convert strings to how they would be written by Jaden Smith.
;; The strings are actual quotes from Jaden Smith, but they are not capitalized in the same way he originally typed them.
;; Not Jaden-Cased: "How can mirrors be real if our eyes aren't real"
;; Jaden-Cased:     "How Can Mirrors Be Real If Our Eyes Aren't Real"
(defn jaden-case [s]
  (str/join " " (map str/capitalize (str/split s #" "))))

;; Even or Odd
;; Retuns even if even, odd if odd
;; ... Kinda lame...
(defn even-or-odd [n]
  (if (even? n)
    "Even"
    "Odd"))

;; Take an integer n (n >= 0) and a digit d (0 <= d <= 9) as an integer. Square all numbers k (0 <= k <= n) between 0 and n.
;; Count the numbers of digits d used in the writing of all the k**2.
;; Call nb_dig (or nbDig or ...) the function taking n and d as parameters and returning this count.
;; need n+1 because we're not counting 0 as position 1
(defn nb-dig [n d]
  (->> (take (+ n 1) (range))
       (map #(* % %))
       (map str)
       (str/join "")
       (sort)
       (str/join "")
       (re-matcher (re-pattern (str (str d) "+")))
       (re-find)
       (count)))

;; Better way! Had the right idea, but I didn't know about the frequencies function
(defn nb-dig-better [n d]
  ((->> (range (inc n))
        (mapcat #(str (* % %)))
        frequencies)
   (first (str d))))

;; You are given an array of string inputs strarr and an integer k.
;; Your task is to return the longest possible string that can be derived by combining k consecutive elements of the input strarr.

(def tst ["it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"])

(defn get-sums [strarr k]
  (cond (== k 1)
        (map count strarr)
        :else
        (map #(reduce + %) (partition k 1 (map count strarr)))))

(defn longest-cons [strarr k]
  (cond
    (>= 0 k) ""
    (empty? strarr) ""
    :else
    (let [sums (get-sums strarr k)
          part (partition k 1 strarr)
          t (map conj part sums)]
      (println sums part t)
      (clojure.string/join (rest (first (sort-by first > t)))))))

;; Again there's a better way :(
;; I shouldn't be sad, I'm still learning. And I had the right idea,
;; just thought about counting too early!

(defn longest-cons-better [strarr k]
  (reduce #(if (> (count %2) (count %1)) %2 %1) ""
   (map #(apply str %) (partition k 1 strarr))))

;; Return century given year

(defn century [year]
  (-> year
      (/ 100)
      (java.lang.Math/ceil)
      (int)))

(century 1705)
