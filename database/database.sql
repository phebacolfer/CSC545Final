drop table food cascade constraints;
drop table recipes cascade constraints;
drop table food_recipes cascade constraints;
drop table mealPlan cascade constraints;

create table food(
    foodName varChar2(30) primary key,
    foodGroup varChar2(20),
    foodCalories number(3),
    foodSugars number(3),
    foodFat number(3),
    foodInFridge bool not null,
    constraint foodGroupCheck check(foodGroup in ('meat', 'veggie', 'dairy', 'fruit', 'grain', 'bread', 'nut', 'bean', 'condiment'))
);

create table recipes(
    recipeName varChar2(30) primary key,
    recipeCategory varChar2(25),
    recipeInstructions varChar2(2000),
    constraint recipeCategoryCheck check (recipeCategory in ('appetizer', 'main course - beef', 'main course - chicken', 'main course - pork',
    'main course - seafood', 'main course - sandwich', 'bread', 'soup', 'salad', 'side dish', 'dessert'))
);

create table food_recipes(
    recipeName varChar2(30),
    foodName varChar2(30),
    amount decimal(4, 2) not null,
    units varChar2(10) not null,
    constraint amountCheck check(amount > 0),
    constraint foodName_FK foreign key (foodName) references food(foodName) on delete cascade,
    constraint recipeName_FK foreign key (recipeName) references recipes(recipeName) on delete cascade
);
alter table food_recipes add(constraint food_recipe_PK primary key (recipeName, foodName));

create table mealPlan(
    weekDay varChar2(9),
    meal varChar2(9),
    recipeName varChar2(30),
    constraint weekDayCheck check(weekDay in ('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday')),
    constraint mealCheck check(meal in ('Breakfast', 'Lunch', 'Dinner')),
    constraint mealPlanRecipeName_FK foreign key (recipeName) references recipes(recipeName) on delete set null
);
alter table mealPlan add(constraint mealPlan_PK primary key (weekday, meal));



insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('ground beef', 'meat', 20, 0, 10, True);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('butter', 'dairy', 0, 0, 10, True);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('burger bun', 'bread', 70, 50, 50, True);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('pickle', 'veggie', 20, 0, 0, False);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('lettuce', 'veggie', 20, 2, 0, True);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('tomato', 'fruit', 30, 20, 0, False);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('onion', 'veggie', 10, 10, 0, True);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('ketchup', 'condiment', 20, 20, 2, True);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('mustard', 'condiment', 15, 0, 3, True);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('mayonaise', 'condiment', 30, 0, 20, False);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('bacon', 'meat', 50, 0, 20, True);
insert into food (foodName, foodGroup, foodCalories, foodSugars, foodFat, foodInFridge) values ('cheddar', 'dairy', 50, 0, 30, True);


insert into recipes (recipeName, recipeCategory, recipeInstructions) values ('plain burger', 'main course - sandwich', 
'form 1/4 lb ground beef into a round patty.  Cook patty in a pan at medium heat.  
Upon reaching desired temperature, remove burger from heat and place patty on the bottom of a burger bun.  Turn off stove.  Place top of 
burger bun on top of patty.  Enjoy.');

insert into recipes (recipeName, recipeCategory, recipeInstructions) values ('cheeseburger', 'main course - sandwich', 
'form 1/4 lb ground beef into a round patty.  Cook patty in a pan at medium heat.  
Upon reaching desired temperature, remove burger from heat and place patty on a burger bun.  Turn off stove.  Add a slice of cheese to the top of your patty.  
Optionally, place lettuce, tomato, pickle, and onion on top of your cheese.  
Optionally, add mayonaise, ketchup, and mustard to the top of your burger bun.  Place top of burger bun on top of cheese.  Enjoy.');

insert into recipes (recipeName, recipeCategory, recipeInstructions) values ('bacon cheeseburger', 'main course - sandwich', 
'Do you really not know how to make a bacon cheeseburger?');

--ingredients for plain burger
insert into food_recipes (recipeName, foodName, amount, units) values ('plain burger', 'ground beef', 0.25, 'lbs');
insert into food_recipes (recipeName, foodName, amount, units) values ('plain burger', 'burger bun', 1, 'count');

--ingredients for cheeseburger
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'ground beef', 0.25, 'lbs');
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'burger bun', 1, 'count');
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'pickle', 5, 'slices');
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'lettuce', 1, 'leaf');
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'tomato', 1, 'slice');
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'onion', 6, 'rings');
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'cheddar', 1, 'slice');
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'ketchup', 1, 'tbsp');
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'mustard', 0.5, 'tbsp');
insert into food_recipes (recipeName, foodName, amount, units) values ('cheeseburger', 'mayonaise', 1, 'tbsp');

--ingredients for bacon cheeseburger
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'ground beef', 0.25, 'lbs');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'burger bun', 1, 'count');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'pickle', 5, 'slices');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'lettuce', 1, 'leaf');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'tomato', 1, 'slice');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'onion', 6, 'rings');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'cheddar', 1, 'slice');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'ketchup', 1, 'tbsp');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'mustard', 0.5, 'tbsp');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'mayonaise', 1, 'tbsp');
insert into food_recipes (recipeName, foodName, amount, units) values ('bacon cheeseburger', 'bacon', 2, 'slice');


--this area is to test data integrity constraints
--do not run when creating database
/*
select * from food_recipes where recipeName = 'bacon cheeseburger';
select * from food_recipes where recipeName = 'cheeseburger';
update food set foodName = 'mayo' where foodName = 'mayonaise';
select * from food_recipes where recipeName = 'bacon cheeseburger';
select * from food_recipes where recipeName = 'cheeseburger';
update food set foodName = 'mayonaise' where foodName = 'mayo';
select * from food_recipes where recipeName = 'bacon cheeseburger';
select * from food_recipes where recipeName = 'cheeseburger';

update recipes set recipeName = 'gross burger' where recipeName = 'plain burger';
select * from food_recipes where foodName = 'ground beef';
update recipes set recipeName = 'plain burger' where recipeName = 'gross burger';
select * from food_recipes where foodName = 'ground beef';

--running delete statements may make reinsertion a pain
--it is advised to rerun database creation and insertion statements
--instead of trying to manually reinsert data
delete from recipes where recipeName = 'plain burger';
select * from food_recipes;

delete from food where foodName = 'lettuce';
select * from food_recipes;
*/

select json_object( 'food_name' VALUE foodname,
'food_group' VALUE foodgroup,
'calories' VALUE foodcalories,
'sugars' VALUE foodsugars,
'fat' value foodfat,
'in_stock' value CASE WHEN foodinfridge IS FALSE THEN 'False' ELSE 'True' END FORMAT JSON ) AS "result" from food;

select json_object('name' VALUE RECIPENAME, 'category' VALUE RECIPECATEGORY, 'instructions' VALUE RECIPEINSTRUCTIONS) AS "resu" from recipes;

select json_object('food_name' VALUE FOODNAME, 'qty' VALUE AMOUNT, 'units' value UNITS) from food_recipes WHERE RECIPENAME = 'bacon cheeseburger';
select json_object('food_name' VALUE FOODNAME, 'qty' VALUE AMOUNT, 'units' value UNITS) from food_recipes WHERE RECIPENAME = 'bacon cheeseburger';

select json_object('day' VALUE WEEKDAY, 'meal' VALUE MEAL, 'recipe' VALUE RECIPENAME) from mealPlan;

select foodname from food_recipes;

select json_object( 'food_name' VALUE foodname,
'in_stock' value CASE WHEN foodinfridge IS FALSE THEN 'False' ELSE 'True' END FORMAT JSON ) AS "result" from food;

update food set foodInFridge=TRUE where FOODNAME = 'tomato';

SELECT * from mealPlan;

insert into mealplan (weekday, meal, recipename) values ('Monday', 'Lunch', 'cheeseburger');

CREATE OR REPLACE VIEW shopping_list AS
SELECT distinct( food.foodname)
from mealplan LEFT JOIN recipes ON mealplan.RECIPENAME = recipes.RECIPENAME LEFT JOIN food_recipes ON food_recipes.recipeNAME = recipes.RECIPENAME LEFT JOIN food on food.FOODNAME = food_recipes.foodname WHERE foodInFridge = False;



select * from shopping_list;


select * from mealplan;


delete from mealplan where meal = 'Breakfast';


CREATE OR REPLACE VIEW recipe_rundown AS
select food_recipes.recipename as recipe, RECIPECATEGORY as category, food.FOODNAME as food from recipes RIGHT JOIN food_recipes ON food_recipes.recipeNAME = recipes.RECIPENAME LEFT JOIN food on food.FOODNAME = food_recipes.foodname;


select distinct(food.foodname) from mealplan LEFT JOIN recipes ON mealplan.RECIPENAME = recipes.RECIPENAME LEFT JOIN food_recipes ON food_recipes.recipeNAME = recipes.RECIPENAME LEFT JOIN food on food.FOODNAME = food_recipes.foodname WHERE foodInFridge = False;

SELECT json_object('food_name' VALUE food.foodname,
'food_group' VALUE foodgroup,
'calories' VALUE foodcalories,
'sugars' VALUE foodsugars,
'fat' value foodfat
) as "result" from mealplan LEFT JOIN recipes ON mealplan.RECIPENAME = recipes.RECIPENAME LEFT JOIN food_recipes ON food_recipes.recipeNAME = recipes.RECIPENAME LEFT JOIN food on food.FOODNAME = food_recipes.foodname WHERE foodInFridge = False;
