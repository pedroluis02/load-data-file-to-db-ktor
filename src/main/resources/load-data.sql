LOAD DATA LOCAL
INFILE ?
INTO TABLE customer
COLUMNS TERMINATED BY ','
IGNORE 1 LINES
(
id,
code,
name,
last_name,
company,
city,
country,
phone,
phone2,
email,
subscription_date,
website
)