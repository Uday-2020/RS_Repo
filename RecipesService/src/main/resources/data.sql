

DROP TABLE IF EXISTS ingredient;
 
CREATE TABLE ingredient(
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  RECIPE_ID INT ,
  DESCRIPTION VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS recipe;

CREATE TABLE recipe (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  RECIPE_NAME VARCHAR(250) NOT NULL,
  NO_OF_PPL_SUITABLE_FOR INT NOT NULL,
  IS_VEG BOOLEAN NOT NULL, 
  COOKING_INSTRUCTIONS VARCHAR(250) DEFAULT NULL,
  CREATION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

alter table ingredient add constraint FK_recipe_Id foreign key (recipe_id) references recipe (id);
	
INSERT INTO RECIPE VALUES(2,'Chicken Beriyani',1,false,'spicy',default);
INSERT INTO RECIPE VALUES(3,'Mutton Beriyani',2,false,'spicy',default);
INSERT INTO RECIPE VALUES(4,'Fish Beriyani',3,false,'spicy',default);
