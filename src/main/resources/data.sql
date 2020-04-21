delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;
insert into Ingredient (id, name, type)
values ('FLTO', 'Pszenna', 'WRAP');
insert into Ingredient (id, name, type)
values ('COTO', 'Kukurydziana', 'WRAP');
insert into Ingredient (id, name, type)
values ('FGRA', 'Pełno ziarnista', 'WRAP');
insert into Ingredient (id, name, type)
values ('BEEF', 'Wołowina', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('PORK', 'Wieprzowina', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('CHIC', 'Kurczak', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('MIXE', 'Mieszane', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('TMTO', 'Krojone pomidory', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('LETC', 'Sałata', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('CHIL', 'Chilly', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('JALA', 'Jalapeno', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('AVOC', 'Awokado', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
values ('GOUD', 'Gouda', 'CHEESE');
insert into Ingredient (id, name, type)
values ('GORG', 'Gorgonzola', 'CHEESE');
insert into Ingredient (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
values ('SRCR', 'ŚMIETANA', 'SAUCE');
insert into Ingredient (id, name, type)
values ('SWET', 'Sweet chilly', 'SAUCE');
insert into Ingredient (id, name, type)
values ('KETC', 'Ketchup', 'SAUCE');
insert into Ingredient (id, name, type)
values ('GARL', 'Czosnkowy', 'SAUCE');