# Deploy AS
```shell
# build
docker build -t as-app .
# run
docker run --name as-app -p 53533:53533 as-app
```

# Deploy FS
```shell
# build
docker build -t fs-app .
# run
docker run --name fs-app -p 9090:9090 fs-app
```

# Deploy US
```shell
# build
docker build -t us-app .
# run 
docker run --name us-app -p 8080:8080 us-app
```
