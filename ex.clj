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
    (* (math/pow 2 (- exp 1023)) (+ 1 man)))

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

(bin_to_f "0 10000000110 011001000100000000000000000000000000000000000000000")