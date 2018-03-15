(ns app.core
 (:gen-class)
 (:require [app.util :refer [bmi]])
 ;(:require [immutant.web :refer [run]] [compojure.core :refer [defroutes GET POST]])
 (:require [ring.middleware.params :refer [wrap-params]])
 (:require [hiccup.core :refer [html]])
 (:import [redis.clients.jedis Jedis])
 (:require [conman.core :as conman] [immutant.web :refer [run]] [compojure.api.sweet :refer [api GET POST]] [ring.util.http-response :refer [ok]]))

(def db (conman/connect! {:jdbc-url "jdbc:mysql://mail-node-alpha1.pg1.krane.9rum.cc/clojure?user=testuser&password=testpassword&serverTimezone=UTC"}))


(conman/bind-connection db "query.sql")

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

; (defn bmi [height weight]
;   (let [bmi (float (/ weight (/ (* height height) 10000)))]
;     (cond
;       (< bmi 18.5) "저체중"
;       :else "몰라")))


(def User
 {:name String
  :id Long})

(def PayLoad
    {:chatId Long
     :authorId Long
     :message String
     :nickname String
     :ldapId String
     :type Long})


(def handler
  (api
    {:swagger {:ui "/swagger-ui"
              :spec "/swagger.json"}}
     (GET "/" []
      :summary "기본.."
       (ok "ok"))
     (GET "/add/:x/:y" []
            :path-params [x :- Long
                          y :- Long]
            (ok {:result (+ x y)}))
      (POST "/hello" []
           :body [user User]
           (ok {:message (str "Hello " (:name user))}))
      (POST "/callback" []
        :body [payload PayLoad]
        (ok {:chatId (:chatId payload)
             :messages (if (= "!헬로두부" (:message payload))
                         ["두부만세"]
                         [])})
       (ok {:chatId (:chatId payload)
            :messages (if (= 1 1)
                        ["두부만세"]
                        [])})
                         )
       ))

; (defroutes handler
;   (GET "/user/:id" [id]
;     (str "x" id))
;
;   (GET "/bmi/height/:height/weight/:weight" [height weight]
;      ;(str height weight))
;     (str  (bmi (Long/parseLong height) (Long/parseLong weight))))
;
;   (GET "/y" [id]
;      id)
;
;   (GET "/msg" []
;       "home")
;
;
;   (GET "/form" []
;     (html [:form {:action "/form1" :method "GET"}]
;        [:div [:input {:type "text" :name "height"}]]
;        [:div [:input {:type "text" :name "weight"}]]
;        [:div [:input {:type "submit"}]]))
;
;   (GET "/form1" []
;         {:status 302 :headers {"Location" "/msg"} :body ""})
;      )

(defn -main [& args]
    (run #(handler %) {:host "0.0.0.0"})
    ; (let [jedis (Jedis. "localhost")]
    ;   (.set jedis "foo11" "000000001")
    ;   (.get jedis "foo11"))
    ;(run (wrap-params handler))
    ;db
    ;(println (insert-comment {:user "dubu", :content "hi"}))
    ;(println (select-comment))
    )
