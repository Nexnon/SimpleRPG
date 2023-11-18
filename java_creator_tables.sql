CREATE TABLE player (
    player_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Создаем таблицу item
CREATE TABLE item (
    item_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE inventory (
	inventory_cell_id SERIAL PRIMARY KEY,
    player_id INTEGER REFERENCES player(player_id) NOT NULL,
	row_position INTEGER NOT NULL,
	column_position INTEGER NOT NULL,
	item_id INTEGER REFERENCES item(item_id)
);

-- Создаем таблицу armor, наследующуюся от item
CREATE TABLE armor (
    armor_id SERIAL PRIMARY KEY,
    defense INTEGER NOT NULL,
    item_id INTEGER REFERENCES item(item_id) UNIQUE
);

-- Создаем таблицу weapon, наследующуюся от item
CREATE TABLE weapon (
    weapon_id SERIAL PRIMARY KEY,
    damage INTEGER NOT NULL,
    item_id INTEGER REFERENCES item(item_id) UNIQUE
);

CREATE TABLE skill (
    skill_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE skilltree (
    skill_tree_id SERIAL PRIMARY KEY,
	skill_id INTEGER REFERENCES skill(skill_id),
	player_id INTEGER REFERENCES player(player_id) NOT NULL,
	is_researched BOOL NOT NULL
);

CREATE TABLE balance (
    balance_id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES player(player_id) NOT NULL,
    name VARCHAR(255) NOT NULL,
	value INTEGER NOT NULL
);

CREATE TABLE equipment (
    equipment_id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES player(player_id) NOT NULL,
	armor_id INTEGER REFERENCES armor(armor_id),
	weapon_id INTEGER REFERENCES weapon(weapon_id)
);
