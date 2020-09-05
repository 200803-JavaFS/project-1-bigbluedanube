--After Project 0, I made a habit of adding DROP statements at the beginning of my SQL editors.
--This is to ensure that, should the data change, 
--I am able to persist the changes and avoid the error message: 
--"TABLE users already exists".
--This is also a good way to hedge against destroying everything if someone runs the entire script at once.

DROP TABLE IF exists ers_user_roles CASCADE;
DROP TABLE IF exists ers_reimbursement_type CASCADE;
DROP TABLE IF exists ers_reimbursement_status CASCADE;
DROP TABLE IF exists ers_users CASCADE;
DROP TABLE IF exists ers_reimbursement CASCADE;

--The shortcut for 'Refresh' is F5.

--TABLE 1/5
CREATE TABLE ers_user_roles(
	ers_user_role_id SERIAL PRIMARY KEY,		--See? It's easier to distinguish between these two columns. Having them both begin with the same word is a bunch of errors waiting to happen.
	user_role VARCHAR(50) NOT NULL
);

--TABLE 2/5
CREATE TABLE ers_reimbursement_type(
	reimb_type_id SERIAL PRIMARY KEY,
	reimb_type VARCHAR(10) NOT NULL
);

--TABLE 3/5
CREATE TABLE ers_reimbursement_status(
	reimb_status_id SERIAL PRIMARY KEY,
	reimb_status VARCHAR(10) NOT NULL
);

--TABLE 4/5
CREATE TABLE ers_users(
	ers_users_id SERIAL PRIMARY KEY,
	ers_username VARCHAR(50) NOT NULL,
	ers_password VARCHAR(50) NOT NULL,
	user_first_name VARCHAR(100) NOT NULL,
	user_last_name VARCHAR(100) NOT NULL,
	user_email VARCHAR(150) NOT NULL,
	user_role_id_fk INTEGER REFERENCES ers_user_roles(ers_user_role_id) NOT NULL, --It's confusing because it SHOULD BE "user_role_id", but the table is called "ers_user_roles", also I'm staying as faithful to the project rubric as possible this time.
	UNIQUE(ers_username, user_email)
	--This is called "role_id" b/c it should be an INTEGER (i.e., 0 = financial director, 2 = employee).
	--Using numbers instead of words will make things MUCH easier. We can be extra once it works, okay?
);

--TABLE 5/5
CREATE TABLE ers_reimbursement(
	reimb_id SERIAL PRIMARY KEY,
	reimb_amount NUMERIC(7,2) NOT NULL,			--This means you must have an amount entered, even if it is $0.00.
	reimb_submitted TIMESTAMP NOT NULL,			--All applications for reimbursement are submitted at a specific time, and we want to record that.
	reimb_resolved TIMESTAMP,					--This can be NULL because the reimbursement may not have been resolved yet.
	reimb_description VARCHAR(250),				--User does not have to enter a description, but if they do, it must have fewer than 250 characters.
	reimb_author_fk INTEGER REFERENCES ers_users(ers_users_id) NOT NULL,							--Both the Reimbursement Author and the Resolver must be Users, so they reference the same Primary Key.
	reimb_resolver_fk INTEGER REFERENCES ers_users(ers_users_id) NOT NULL,						--The reimbursement may not have been resolved yet, so it's allowed to be NULL.
	reimb_status_id_fk INTEGER REFERENCES ers_reimbursement_status(reimb_status_id) NOT NULL,
	reimb_type_id_fk INTEGER REFERENCES ers_reimbursement_type(reimb_type_id) NOT NULL
	--Using numbers instead of words will make things MUCH easier. We can be extra once it works, okay?
);


INSERT INTO ers_user_roles(user_role)
VALUES('Employee'),
('Financial Manager');

INSERT INTO ers_reimbursement_status(reimb_status)
VALUES('PENDING'),
('APPROVED'),
('DENIED');

INSERT INTO ers_reimbursement_type(reimb_type)
VALUES('LODGING'),
('TRAVEL'),
('FOOD'),
('OTHER');


--DROP FUNCTION trigger_set_time() CASCADE;
   

/*INSERT INTO ers_reimbursement(reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author_fk, reimb_resolver_fk, reimb_status_id_fk, reimb_type_id_fk)
VALUES(10000.00, DEFAULT, DEFAULT, 'Guest tribute for royal progress to Winterfell.', 3, 1, 1, 1),
(10.00, DEFAULT, DEFAULT, 'Purchased a horse to travel to the Wall.', 4, 2, 1, 2),
(50000.00, DEFAULT, DEFAULT, 'Bought a bowl of brown in Flea Bottom.', 5, 1, 1, 3),
(90000.00, DEFAULT, DEFAULT, 'Payment for the breastplate stretcher.', 3, 2, 1, 4),
(5.00, DEFAULT, DEFAULT, 'Bribe for the goldcloaks to let me out of the city.', 4, 1, 1, 4);*/


--your foreign keys will link back to primary keys of type SERIAL, and the foreign keys should be type INTEGER.
--reimb_author is a foreign key of type INTEGER that relates to the user_id of type SERIAL.


--reimb_receipt BYTEA,			--This is a BLOB in our dialect of SQL. Its implementation is a stretch goal, so I'll allow it to be NULL.






