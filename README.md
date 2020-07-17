# Distributed_Systems_Project

# Steps done in pulling postgres
1. docker run -p 5432:5432 --name mydbcontainer -e POSTGRES_PASSWORD=postgres -d postgres
(This will pull docker image if not exists and run it in mydbcontainer)
2. docker exec -it mydbcontainer bash
(This will open the db in bash mode)
