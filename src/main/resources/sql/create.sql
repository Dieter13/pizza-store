
insert into pizza_type (id, name, description, price)
values
(1, "Country", "sausage, cheese, mushrooms", 23),
(2, "Vegetarian", "mozarella, tomatoes, basil, herbs", 19),
(3, "Kebab pizza", "chicken, red onion, pickels", 22),
(4, "Cheese", "mozarella, feta cheese, yellow cheese", 20);


insert into pizza_size (id, name, extra_price)
values
(1, "Small", 0),
(2, "Medium", 3),
(3, "Large", 6);


insert into pizza_topping (id, name, extra_price)
values
(1, "Double cheese regular", 2),
(2, "Double cheese mozarella", 2),
(3, "Double chicken", 2),
(4, "Double crust", 2),
(5, "Garlic sauce", 2),
(6, "Tomatoe sauce", 2);
