BEGIN TRANSACTION;
CREATE TABLE `users` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
	`username`	TEXT UNIQUE,
	`password`	TEXT,
	`email`	TEXT
);
CREATE TABLE "showtimes" (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
	`movie_id`	INTEGER,
	`timestamp`	INTEGER
);
CREATE TABLE "reservations" (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
	`showtime_id`	INTEGER,
	`user_id`	INTEGER,
	`number_of_seats`	INTEGER
);
CREATE TABLE "movies" (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT NOT NULL UNIQUE,
	`year`	TEXT NOT NULL,
	`genre`	TEXT NOT NULL,
	`description`	TEXT NOT NULL,
	`cast`	TEXT NOT NULL,
	`director`	TEXT NOT NULL,
	`length`	INTEGER NOT NULL,
	`img`	BLOB NOT NULL,
	`imdbRating`	REAL NOT NULL,
	`imdbLink`	TEXT NOT NULL,
	`status`	INTEGER
);
COMMIT;
