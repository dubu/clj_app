-- :name insert-comment :! :n
insert into comments (user, content)
values (:user, :content)

-- :name select-comment :? :*
select * from comments
