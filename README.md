# Deploy AS
```shell
# build
docker build -t as-app .
# run
docker run --name as-app -p 53533:53533 as-app
# find the as_ip
docker inspect as-app
```

# Deploy FS
```shell
# build
docker build -t fs-app .
# run and pass the as IP address
docker run --name fs-app -p 9090:9090 fs-app --asIP=as_IP
```

# Deploy US
```shell
# build
docker build -t us-app .
# run and pass the as IP address
docker run --name us-app -p 8080:8080 us-app
```