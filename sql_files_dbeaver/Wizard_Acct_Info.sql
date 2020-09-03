DROP TABLE IF EXISTS wizards;
DROP TABLE IF EXISTS vaults;

--0th Normal Form

CREATE TABLE wizards (
	wizard_name VARCHAR(60)
);

INSERT INTO wizards (wizard_name)
	VALUES ('Harry Potter'),
	('Hermione Granger'),
	('Ron Weasley'),
	('Ginny Weasley'),
	('Luna Lovegood'),
	('Neville Longbottom'),
	('George Weasley'),
	('Fleur Delacour'),
	('Bill Weasley'),
	('Charlie Weasley'),
	('Molly Weasley'),
	('Arthur Weasley'),
	('Minerva McGonagall'),
	('Albus Dumbledore');


--1st Normal Form (atomic data, all records have a primary key, some might be composite keys)

DROP TABLE IF EXISTS wizards;

CREATE TABLE wizards (
	first_name VARCHAR(30),
	last_name VARCHAR(30)
);

INSERT INTO wizards (first_name, last_name)
	VALUES ('Harry', 'Potter'),
	('Hermione', 'Granger'),
	('Ron', 'Weasley'),
	('Ginny', 'Weasley'),
	('Luna', 'Lovegood'),
	('Neville', 'Longbottom'),
	('George', 'Weasley'),
	('Fleur', 'Delacour'),
	('Bill', 'Weasley'),
	('Charlie', 'Weasley'),
	('Molly', 'Weasley'),
	('Arthur', 'Weasley'),
	('Minerva', 'McGonagall'),
	('Albus', 'Dumbledore');

ALTER TABLE wizards ADD COLUMN acct_active BOOLEAN;
ALTER TABLE wizards ADD COLUMN is_employee BOOLEAN;
ALTER TABLE wizards ADD COLUMN is_admin BOOLEAN;

ALTER TABLE wizards ADD PRIMARY KEY (first_name, last_name);

INSERT INTO wizards (first_name, last_name, acct_active, is_employee, is_admin)
	VALUES 
	('Viktor', 'Krum', TRUE, FALSE, FALSE),
	('Rubeus', 'Hagrid', TRUE, FALSE, FALSE),
	('Sybill', 'Trelawney', TRUE, FALSE, FALSE),
	('Horace', 'Slughorn', TRUE, FALSE, FALSE),
	('Gringott', 'G.', TRUE, TRUE, TRUE),
	('Burgock', 'G.', TRUE, TRUE, FALSE),
	('Odbert', 'G.', TRUE, TRUE, FALSE);
	

--2nd Normal Form (having a single column primary key... and no composite keys)

DROP TABLE IF EXISTS wizards;

CREATE TABLE wizards (
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	acct_active BOOLEAN,
	is_employee BOOLEAN,
	is_admin BOOLEAN
	);


INSERT INTO wizards (first_name, last_name, acct_active, is_employee, is_admin)
	VALUES ('Harry', 'Potter', TRUE, FALSE, FALSE),
	('Hermione', 'Granger', TRUE, FALSE, FALSE),
	('Ron', 'Weasley', TRUE, FALSE, FALSE),
	('Ginny', 'Weasley', TRUE, FALSE, FALSE),
	('Luna', 'Lovegood', TRUE, FALSE, FALSE),
	('Neville', 'Longbottom', TRUE, FALSE, FALSE),
	('George', 'Weasley', TRUE, FALSE, FALSE),
	('Fleur', 'Delacour', TRUE, FALSE, FALSE),
	('Bill', 'Weasley', TRUE, FALSE, FALSE),
	('Charlie', 'Weasley', TRUE, FALSE, FALSE),
	('Molly', 'Weasley', TRUE, FALSE, FALSE),
	('Arthur', 'Weasley', TRUE, FALSE, FALSE),
	('Minerva', 'McGonagall', TRUE, FALSE, FALSE),
	('Albus', 'Dumbledore', TRUE, FALSE, FALSE),
	('Viktor', 'Krum', TRUE, FALSE, FALSE),
	('Rubeus', 'Hagrid', TRUE, FALSE, FALSE),
	('Sybill', 'Trelawney', TRUE, FALSE, FALSE),
	('Horace', 'Slughorn', TRUE, FALSE, FALSE),
	('Gringott', 'G.', TRUE, TRUE, TRUE),
	('Burgock', 'G.', TRUE, TRUE, FALSE),
	('Odbert', 'G.', TRUE, TRUE, FALSE);
	

--3rd Normal Form - no transitive dependencies allowed

DROP TABLE IF EXISTS wizards;
DROP TABLE IF EXISTS vaults;

CREATE TABLE wizards (
	wizard_id SERIAL PRIMARY KEY,
	first_name VARCHAR(30),
	last_name VARCHAR(30)
);

CREATE TABLE vaults (
	acct_active BOOLEAN,
	vault_number SERIAL PRIMARY KEY,
	balance NUMERIC(10, 2),
	is_employee BOOLEAN,
	is_admin BOOLEAN,
	owner_fk INTEGER REFERENCES wizards (wizard_id)
);

--fk stands for "foreign key", and always references the primary key of another table.

INSERT INTO wizards (first_name, last_name)
	VALUES ('Harry', 'Potter'),
	('Hermione', 'Granger'),
	('Ron', 'Weasley'),
	('Ginny', 'Weasley'),
	('Luna', 'Lovegood'),
	('Neville', 'Longbottom'),
	('George', 'Weasley'),
	('Fleur', 'Delacour'),
	('Bill', 'Weasley'),
	('Charlie', 'Weasley'),
	('Molly', 'Weasley'),
	('Arthur', 'Weasley'),
	('Minerva', 'McGonagall'),
	('Albus', 'Dumbledore'),
	('Viktor', 'Krum'),
	('Rubeus', 'Hagrid'),
	('Sybill', 'Trelawney'),
	('Horace', 'Slughorn'),
	('Gringott', 'G.'),
	('Burgock', 'G.'),
	('Odbert', 'G.');

INSERT INTO vaults (acct_active, balance, is_employee, is_admin)
	VALUES (TRUE, 7000000, FALSE, FALSE),
	(TRUE, 125000, FALSE, FALSE),
	(TRUE, 2500, FALSE, FALSE),
	(TRUE, 1500, FALSE, FALSE),
	(TRUE, 300000, FALSE, FALSE),
	(TRUE, 6000000, FALSE, FALSE),
	(TRUE, 500000, FALSE, FALSE),
	(TRUE, 3500000, TRUE, FALSE),
	(TRUE, 33000, TRUE, FALSE),
	(TRUE, 20000, FALSE, FALSE),
	(TRUE, 7000, FALSE, FALSE),
	(TRUE, 8000, FALSE, FALSE),
	(TRUE, 10000000, FALSE, FALSE),
	(TRUE, 10000000, FALSE, FALSE),
	(TRUE, 200000, FALSE, FALSE),
	(TRUE, 0, FALSE, FALSE),
	(TRUE, 333333, FALSE, FALSE),
	(TRUE, 666000, FALSE, FALSE),
	(TRUE, 70000, TRUE, TRUE),
	(TRUE, 66700, TRUE, FALSE),
	(TRUE, 66600, TRUE, FALSE);
	

--Joins

--SELECT * FROM wizards JOIN vaults ON vaults.owner_fk = wizard_id;

--SELECT * FROM wizards RIGHT JOIN vaults ON vaults.owner_fk = wizard_id;

SELECT * FROM wizards LEFT OUTER JOIN vaults ON vaults.owner_fk = wizard_id;

SELECT * FROM wizards FULL JOIN vaults ON vaults.owner_fk = wizard_id;

--INSERT INTO wizards (first_name, last_name, wizard_wand_wood, wizard_wand_core, wizard_patronus)
	VALUES ('Harry', 'Potter', 'Holly', 'Phoenix Feather', 'Stag'),
	('Hermione', 'Granger', 'Vinewood', 'Dragon Heartstring', 'Otter'),
	('Ron', 'Weasley', 'Willow', 'Unicorn Hair', 'Jack Russell Terrier'),
	('Ginny', 'Weasley', 'Yew', 'Dragon Heartstring', 'Horse'),
	('Luna', 'Lovegood', 'Rosewood', 'Phoenix Feather', 'Hare'),
	('Neville', 'Longbottom', 'Cherry', 'Unicorn Hair', 'Lion'),
	('George', 'Weasley', 'Spruce', 'Dragon Heartstring', 'Magpie'),
	('Fleur', 'Delacour', 'Rosewood', 'Veela Hair', 'Swan'),
	('Bill', 'Weasley', 'Cypress', 'Dragon Heartstring', 'Wolf'),
	('Charlie', 'Weasley', 'Ash', 'Unicorn Hair', 'Dragon'),
	('Molly', 'Weasley', 'Cedar', 'Dragon Heartstring', 'Bear'),
	('Arthur', 'Weasley', 'Ebony', 'Unicorn Hair', 'Weasel'),
	('Minerva', 'McGonagall', 'Fir', 'Dragon Heartstring', 'Cat'),
	('Albus', 'Dumbledore', 'Elder', 'Thestral Tail Hair', 'Phoenix'),
	('Viktor', 'Krum', 'Hornbeam', 'Dragon Heartstring', 'Wolf'),
	('Rubeus', 'Hagrid', 'English Oak', 'Dragon Heartstring', 'Hipogriff'),
	('Sybill', 'Trelawney', 'Hazel', 'Unicorn Hair', 'Raven'),
	('Horace', 'Slughorn', 'Cedar', 'Dragon Heartstring', 'Beta Fish'),
	('Gringott', 'G.', NULL, NULL, NULL),
	('Burgock', 'G.', NULL, NULL, NULL),
	('Odbert', 'G.', NULL, NULL, NULL);

	wizard_wand_wood VARCHAR(30), Perhaps in an expanded version of this application, I'll put these back.
	wizard_wand_core VARCHAR (30), They're all on a Gringotts Google Doc, so the information won't be completely lost.
	wizard_patronus VARCHAR(30), but for now, these need to go so I can make a trimmed-down version that actually works.
*/

