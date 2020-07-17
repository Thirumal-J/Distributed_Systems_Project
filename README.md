# Distributed_Systems_Project

# Steps done in pulling postgres
1. docker run -p 5432:5432 --name mydbcontainer -e POSTGRES_PASSWORD=postgres -d postgres
(This will pull docker image if not exists and run it in mydbcontainer)
2. docker exec -it mydbcontainer bash
(This will open the db in bash mode)
3. su postgres
(login as postgres user)
4. psql
(opens the postgres database)
5. \l 
(list the existing databases)
6. \c <dbname>
  Eg:- \c pressuredb
  (this switches to "pressuredb" database from postgres)
7. \dt
  (This gives the tables of the db you logged in)
