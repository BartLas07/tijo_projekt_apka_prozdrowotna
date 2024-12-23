--liquibase formatted sql
--changeset blas:1


CREATE TABLE IF NOT EXISTS meal_Ingredient
(
    meal_ingredient_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    meal_ingredient_name VARCHAR(20) NOT NULL,
    protein DOUBLE ,
    carbohydrates DOUBLE ,
    sodium DOUBLE ,
    calories DOUBLE ,
    fats DOUBLE ,
    cholesterol DOUBLE ,
    sugar DOUBLE ,
    fiber DOUBLE
);


--liquibase formatted sql
--changeset blas:2

CREATE TABLE IF NOT EXISTS breakfast
(
    meal_ingredient_for_breakfast_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    meal_ingredient_id INTEGER NOT NULL,
    CONSTRAINT breakfast_fk
        FOREIGN KEY (meal_ingredient_id) REFERENCES meal_Ingredient(meal_ingredient_id)
            ON DELETE CASCADE
);

--liquibase formatted sql
--changeset blas:3

CREATE TABLE IF NOT EXISTS lunch
(
    meal_ingredient_for_lunch_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    meal_ingredient_id INTEGER NOT NULL,
    CONSTRAINT lunch_fk
        FOREIGN KEY (meal_ingredient_id) REFERENCES meal_Ingredient(meal_ingredient_id)
            ON DELETE CASCADE
);

--liquibase formatted sql
--changeset blas:4

CREATE TABLE IF NOT EXISTS dinner
(
    meal_ingredient_for_dinner_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    meal_ingredient_id INTEGER NOT NULL,
    CONSTRAINT dinner_fk
        FOREIGN KEY (meal_ingredient_id) REFERENCES meal_Ingredient(meal_ingredient_id)
            ON DELETE CASCADE
);

--liquibase formatted sql
--changeset blas:5

CREATE TABLE IF NOT EXISTS snacks
(
    meal_ingredient_for_snacks_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    meal_ingredient_id INTEGER NOT NULL,
    CONSTRAINT snacks_fk
        FOREIGN KEY (meal_ingredient_id) REFERENCES meal_Ingredient(meal_ingredient_id)
            ON DELETE CASCADE
);




--liquibase formatted sql
--changeset blas:9 runOnChange="true"

ALTER TABLE dinner
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

--liquibase formatted sql
--changeset blas:10 runOnChange="true"
ALTER TABLE snacks
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

--liquibase formatted sql
--changeset blas:11 runOnChange="true"
ALTER TABLE breakfast
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

--liquibase formatted sql
--changeset blas:12 runOnChange="true"
ALTER TABLE lunch
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

--liquibase formatted sql
--changeset your_name:13

ALTER TABLE breakfast ADD COLUMN meal_ingredient_quantity_in_grams INTEGER NOT NULL;
ALTER TABLE lunch ADD COLUMN meal_ingredient_quantity_in_grams INTEGER NOT NULL;
ALTER TABLE dinner ADD COLUMN meal_ingredient_quantity_in_grams INTEGER NOT NULL;
ALTER TABLE snacks ADD COLUMN meal_ingredient_quantity_in_grams INTEGER NOT NULL;

--liquibase formatted sql
--changeset your_name:14
INSERT INTO meal_Ingredient (meal_ingredient_name, protein, carbohydrates, sodium, calories, fats, cholesterol, sugar, fiber) VALUES
                                                                                                                        ('Agrest', 1.3, 10.2, 1, 44, 0.6, 0, 9.1, 4.3),
                                                                                                                        ('Ananas', 0.5, 13.1, 1, 50, 0.1, 0, 10.0, 1.4),
                                                                                                                        ('Awokado', 2.0, 8.5, 7, 160, 15.0, 0, 0.7, 6.7),
                                                                                                                        ('Bagietka', 9.0, 57.5, 460, 276, 1.2, 0, 2.5, 3.4),
                                                                                                                        ('Bakłażan', 1.0, 5.7, 2, 25, 0.2, 0, 3.5, 3.0),
                                                                                                                        ('Banany', 1.1, 22.8, 1, 96, 0.3, 0, 12.2, 2.6),
                                                                                                                        ('Bataty', 1.6, 20.1, 55, 86, 0.1, 0, 4.2, 3.0),
                                                                                                                        ('Baton musli', 5.2, 64.0, 15, 380, 12.5, 0, 21.0, 6.5),
                                                                                                                        ('Boczek', 14.0, 1.5, 1840, 541, 50.0, 80, 0.0, 0.0),
                                                                                                                        ('Brzoskwinia', 0.9, 9.5, 0, 39, 0.3, 0, 8.0, 1.5),
                                                                                                                        ('Brokuł', 2.8, 6.6, 33, 34, 0.4, 0, 1.5, 3.3),
                                                                                                                        ('Bułka kajzerka', 8.0, 55.0, 490, 275, 1.3, 0, 3.0, 3.2),
                                                                                                                        ('Bułka pełnoziarnista', 10.0, 48.0, 420, 265, 3.8, 0, 3.5, 6.5),
                                                                                                                        ('Burak', 1.6, 9.6, 78, 43, 0.2, 0, 6.8, 2.8),
                                                                                                                        ('Cebula', 1.1, 9.3, 4, 40, 0.1, 0, 4.2, 1.7),
                                                                                                                        ('Chleb pełnoziarnisty', 10.5, 45.0, 380, 240, 2.7, 0, 1.5, 6.8),
                                                                                                                        ('Chleb żytni', 8.0, 48.0, 420, 250, 1.0, 0, 2.0, 5.5),
                                                                                                                        ('Cukinia', 1.2, 3.1, 8, 17, 0.3, 0, 2.5, 1.1),
                                                                                                                        ('Czosnek', 6.4, 33.1, 17, 149, 0.5, 0, 1.0, 2.1),
                                                                                                                        ('Daktyle', 2.5, 75.0, 1, 282, 0.4, 0, 66.5, 8.0),
                                                                                                                        ('Dorsz', 18.0, 0.0, 80, 82, 0.7, 50, 0.0, 0.0),
                                                                                                                        ('Dynia', 1.0, 6.5, 1, 26, 0.1, 0, 2.8, 0.5),
                                                                                                                        ('Fasola biała', 21.0, 60.0, 6, 333, 1.2, 0, 2.0, 15.0),
                                                                                                                        ('Filet z kurczaka', 23.0, 0.0, 60, 165, 3.6, 85, 0.0, 0.0),
                                                                                                                        ('Granat', 1.7, 18.7, 1, 83, 1.2, 0, 13.7, 4.0),
                                                                                                                        ('Gruszka', 0.4, 15.2, 2, 57, 0.1, 0, 9.8, 3.1),
                                                                                                                        ('Herbata', 0.0, 0.0, 2, 1, 0.0, 0, 0.0, 0.0),
                                                                                                                        ('Indyk', 29.0, 0.0, 65, 135, 0.7, 85, 0.0, 0.0),
                                                                                                                        ('Jabłko', 0.3, 14.0, 1, 52, 0.2, 0, 10.4, 2.4),
                                                                                                                        ('Jogurt naturalny', 3.5, 4.7, 36, 61, 3.3, 10, 4.7, 0.0),
                                                                                                                        ('Kalafior', 1.9, 4.3, 30, 25, 0.3, 0, 1.9, 2.0),
                                                                                                                        ('Karp', 16.0, 0.0, 55, 127, 5.6, 70, 0.0, 0.0),
                                                                                                                        ('Kiełbasa', 14.0, 2.0, 850, 300, 25.0, 70, 0.0, 0.0),
                                                                                                                        ('Kiwi', 1.1, 14.7, 3, 61, 0.5, 0, 8.9, 3.0),
                                                                                                                        ('Kukurydza', 3.3, 19.0, 2, 86, 1.2, 0, 6.3, 2.7),
                                                                                                                        ('Łosoś', 20.0, 0.0, 59, 206, 13.0, 63, 0.0, 0.0),
                                                                                                                        ('Mak', 18.0, 23.7, 16, 525, 42.0, 0, 1.7, 19.5),
                                                                                                                        ('Makaron', 13.0, 75.0, 6, 371, 1.5, 0, 2.0, 3.2),
                                                                                                                        ('Maliny', 1.2, 11.9, 1, 52, 0.6, 0, 4.4, 6.5),
                                                                                                                        ('Mandarynki', 0.8, 13.3, 2, 53, 0.3, 0, 11.2, 1.8),
                                                                                                                        ('Marchew', 0.9, 9.6, 69, 41, 0.2, 0, 4.7, 2.8);



--liquibase formatted sql
--changeset your_name:15
INSERT INTO meal_Ingredient (meal_ingredient_name, protein, carbohydrates, sodium, calories, fats, cholesterol, sugar, fiber) VALUES
                                                                                                                        ('Migdały', 21.0, 21.6, 1, 579, 49.4, 0, 4.4, 12.5),
                                                                                                                        ('Morele', 1.4, 11.1, 1, 48, 0.4, 0, 9.2, 2.0),
                                                                                                                        ('Ogórek', 0.7, 3.6, 2, 16, 0.1, 0, 1.7, 0.5),
                                                                                                                        ('Orzechy włoskie', 15.0, 13.7, 2, 654, 65.0, 0, 2.6, 6.7),
                                                                                                                        ('Papryka', 0.9, 6.0, 1, 26, 0.3, 0, 4.2, 1.7),
                                                                                                                        ('Pieczarka', 3.1, 3.3, 5, 22, 0.3, 0, 0.6, 1.0),
                                                                                                                        ('Pierogi ruskie', 7.0, 34.0, 670, 230, 7.0, 15, 2.0, 2.0),
                                                                                                                        ('Pistacje', 21.0, 28.0, 5, 560, 45.8, 0, 7.7, 10.6),
                                                                                                                        ('Pomarańcza', 0.9, 11.8, 1, 47, 0.1, 0, 9.3, 2.4),
                                                                                                                        ('Pomidory', 0.9, 3.9, 5, 18, 0.2, 0, 2.6, 1.2),
                                                                                                                        ('Porzeczka czarna', 1.4, 15.4, 2, 63, 0.4, 0, 8.0, 5.2),
                                                                                                                        ('Porzeczka czerwona', 1.4, 13.8, 2, 56, 0.2, 0, 7.4, 4.3),
                                                                                                                        ('Ryż biały', 7.1, 79.0, 5, 364, 0.6, 0, 0.1, 1.3),
                                                                                                                        ('Ryż brązowy', 7.5, 77.2, 5, 370, 2.7, 0, 0.9, 3.5),
                                                                                                                        ('Sałata', 1.4, 2.9, 28, 15, 0.2, 0, 0.8, 1.3),
                                                                                                                        ('Ser Cheddar', 25.0, 1.3, 621, 403, 33.0, 105, 0.5, 0.0),
                                                                                                                        ('Ser Feta', 14.0, 4.1, 1116, 264, 21.0, 89, 4.1, 0.0),
                                                                                                                        ('Ser Gouda', 25.0, 2.2, 819, 356, 27.0, 114, 0.0, 0.0),
                                                                                                                        ('Ser Mozzarella', 22.0, 2.2, 373, 280, 17.0, 54, 0.0, 0.0),
                                                                                                                        ('Ser Parmezan', 35.8, 3.2, 1529, 431, 28.4, 88, 0.9, 0.0),
                                                                                                                        ('Ser Ricotta', 11.3, 3.0, 105, 174, 13.0, 51, 0.3, 0.0),
                                                                                                                        ('Ser Twaróg', 19.8, 3.3, 55, 99, 0.5, 17, 3.3, 0.0),
                                                                                                                        ('Słonecznik', 21.0, 20.0, 9, 584, 51.5, 0, 2.6, 8.6),
                                                                                                                        ('Soczewica', 9.0, 20.0, 6, 116, 0.4, 0, 1.8, 8.0),
                                                                                                                        ('Sok pomarańczowy', 0.7, 10.4, 1, 45, 0.2, 0, 8.4, 0.2),
                                                                                                                        ('Sok jabłkowy', 0.1, 11.3, 4, 46, 0.1, 0, 10.0, 0.2),
                                                                                                                        ('Spaghetti', 13.0, 75.0, 6, 371, 1.5, 0, 2.0, 3.2),
                                                                                                                        ('Szpinak', 2.9, 3.6, 24, 23, 0.4, 0, 0.4, 2.2),
                                                                                                                        ('Tofu', 8.1, 1.9, 12, 76, 4.8, 0, 0.9, 0.3),
                                                                                                                        ('Truskawki', 0.7, 7.7, 1, 32, 0.3, 0, 4.9, 2.0),
                                                                                                                        ('Tuńczyk', 29.0, 0.0, 37, 130, 1.0, 44, 0.0, 0.0),
                                                                                                                        ('Twarożek', 11.0, 3.3, 55, 99, 0.5, 17, 3.3, 0.0),
                                                                                                                        ('Wafle ryżowe', 7.5, 80.0, 50, 387, 2.8, 0, 0.5, 3.2),
                                                                                                                        ('Winogrona', 0.6, 18.1, 2, 69, 0.2, 0, 15.5, 0.9),
                                                                                                                        ('Ziemniaki', 2.0, 17.0, 6, 77, 0.1, 0, 0.8, 2.2),
                                                                                                                        ('Żurawina', 0.4, 12.2, 2, 46, 0.1, 0, 4.0, 3.6);



--liquibase formatted sql
--changeset blas:16

CREATE TABLE IF NOT EXISTS user_details
(
    user_details_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    calorie_pool INTEGER,
    recommended_hydration DOUBLE,
    bmi DOUBLE
);