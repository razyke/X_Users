SELECT * FROM users;

select users.id, First_name,Last_name,Login_name,Email,Birthday,createdTimeStamp,lastupdateTimeStamp,isActive,isAdmin,`Group`,zip,Country,City,District,Street from users JOIN adress on users.id = Adress.id WHERE First_name LIKE '%d%';

DELETE FROM adress WHERE id=4;
ALTER TABLE users AUTO_INCREMENT=6;