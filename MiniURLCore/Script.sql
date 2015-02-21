--<ScriptOptions statementTerminator=";"/>

CREATE TABLE URL_INFO (
	oid INT NOT NULL AUTO_INCREMENT,
	url VARCHAR(255) NOT NULL,
	PRIMARY KEY (oid)
)AUTO_INCREMENT=1000 ENGINE=InnoDB;


--drop table url_DETAILS;
CREATE TABLE URL_DETAILS (
	oid INT NOT NULL AUTO_INCREMENT,
	country_code VARCHAR(2) NOT NULL,
	click_count int NOT NULL,
	url_id int not null,
	PRIMARY KEY (oid),
	FOREIGN KEY (url_id) references URL_INFO (oid)
)ENGINE=InnoDB;


