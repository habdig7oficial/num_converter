(require '[clojure.string :as str] '[clojure.math :as math])

(defn int_part [num]
    (def res (atom 0))
    (dotimes [x (count num)]
        (def n (Character/getNumericValue (get num x)))
        (def exp (if (not= n 0) (int (math/pow 2 x)) 0))
        (swap! res + exp)
        (printf "2 ^ %d * %d = %d\n" x n exp))
        @res)

(defn float_part [num]
    (def res (atom 0))
    (dotimes [x (count num)]
        (def n (Character/getNumericValue (get num x)))
        (def exp (if (not= n 0) (math/pow 2 (- (+ x 1))) 0.0))
        (swap! res + exp)
        (printf "2 ^ %d * %d = %f\n" (- (+ x 1)) n exp))
        @res)

(defn formula [exp man]
    ; 2 ^ numbytes / 2
    ; double -> 2 ^ 11 / 2 - 1= 2048 / 2 - 1 = 1023
    ; float -> 2 ^ 8 / 2 - 1= 2048 / 2 - 1 = 127
    ; see if it is float or double
    (def offset (if (> exp 255) 1023 127))
    (* (math/pow 2 (- exp offset)) (+ 1 man)))

(defn bin_to_f [bin]
    (def arr (str/split bin #" "))
    (def expoent (int_part (str/reverse (get arr 1))))
    (printf "total: %d\n" expoent)
    (println "-------------------")
    (def mantissa (float_part (get arr 2)))
    (printf "res: %f\n" mantissa)
    (def res (formula expoent mantissa))
    (printf "%f\n" (if (= (get arr 0 ) "0") res (- res)))
    (println arr))

(println "1.a)")
(bin_to_f "0 01111100 01000000000000000000000")
(println "1.b)")
(bin_to_f "0 01111100 01000000000000000000000")

;(println "2.a)")
;(bin_to_f "0 10000000110 011001000100000000000000000000000000000000000000000")
;(println "2.ab")
;(bin_to_f "1 10000000101 001110010000000000000000000000000000000000000000000")
