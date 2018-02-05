(ns app.util)

(defn bmi [height weight]
   ;; "저체중", "정상", "과체중", "비만" 문자열을 리턴하도록 구현해보세요
    (let [w height h weight b(/ w (* h h))]
     (print (cond (> b 100) "저체중"
                  (> b 200) "중간"
                  :else "나머지"))))
