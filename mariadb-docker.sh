docker run \
  --name ldf-ktor-mariadb \
  -p 3306:3306 \
  -v ldf-ktor-mariadb-data:/var/lib/mysql \
  -e MARIADB_ROOT_PASSWORD=ktor-root-pass \
  -e MARIADB_USER=ktor-user \
  -e MARIADB_PASSWORD=ktor-pass \
  -e MARIADB_DATABASE=ldf-ktor \
  -d mariadb:latest
