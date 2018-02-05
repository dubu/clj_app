(ns app.core
 (:gen-class)
 (:require [app.util :refer [bmi]])
 (:require [immutant.web :refer [run]] [compojure.core :refer [defroutes GET]]))


; (defn bmi [height weight]
;    ;; "저체중", "정상", "과체중", "비만" 문자열을 리턴하도록 구현해보세요
;     (let [w height h weight b(/ w (* h h))]
;      (print (cond (> b 100) "저체중"
;                   (> b 200) "중간"
;                   :else "나머지"))))

; (defn -main [& args]
;   (println "Hello World" (first args))
;   (println "sum "  (+ (Long/parseLong(first args)) (Long/parseLong(nth args 1))))
;   (bmi 80 170))
;   ; (bmi (Long/parseLong (first args)) (Long/parseLong (second args))))


; (defn handler [request]
;     ;{:status 200 :body (str request)}
;     ; {:status 200 :body (:uri request)}
;     {:status 200 :body (cond (= (:uri request) "/aa") "aa.page"
;                              (= (:uri request) "/bb") "bb.page"
;                               :else  (str "404" (:uri request)))})

(defroutes handler
  (GET "/user/:id" [id]
    (str "x" id))

  (GET "/y" request
    "YYYYYYYYY"))

(defn -main [& args]
    (run handler))
