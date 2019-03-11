(ns roman-numerals-test
  (:require [clojure.test :refer :all]
            [roman-numerals :refer :all]))

; Test all the core values
(are [arabic expected-roman-numerals] (= (generate arabic) expected-roman-numerals)
                                      0 ""
                                      1 "I"
                                      3 "III"
                                      4 "IV"
                                      5 "V"
                                      8 "VIII"
                                      9 "IX"
                                      10 "X"
                                      11 "XI"
                                      40 "XL"
                                      50 "L"
                                      90 "XC"
                                      99 "XCIX"
                                      100 "C"
                                      400 "CD"
                                      500 "D"
                                      900 "CM"
                                      1000 "M"
                                      1017 "MXVII"
                                      1998 "MCMXCVIII"
                                      3887 "MMMDCCCLXXXVII"
                                      3999 "MMMCMXCIX")

; Test the boundary conditions
(are [input expected] (= (generate-response input) expected)
                      0 "There is no Roman numeral for zero"
                      -1 "There are no negative Roman numerals"
                      4000 "This application does not support numbers greater than 3,999")

(deftest roman-keys-to-string
  (testing "multiple roman-keys to string"
    (is (= "XVII" (roman-keys->string [:X :V :I :I]))))
  (testing "a single roman-key"
    (is (= "V" (roman-keys->string [:v])))))