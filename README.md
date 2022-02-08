#  Spring Boot simple API to generate password 

Simple stateless password generator API using Spring Boot.

# Deployment in Kubernetes
## Minikube
```bash
minikube start
```

To enable docker
```bash
eval $(minikube docker-env)
```

Deploy API
```bash
apiVersion: apps/v1
kind: Deployment
metadata:
  name: simple-api
spec:
  selector:
    matchLabels:
      app: simple-api
  replicas: 1
  template:
    metadata:
      labels:
        app: simple-api
    spec:
      containers:
        - name: simple-api
          image: simple-api
          imagePullPolicy: Never
          ports:
            - containerPort: 8080
          resources:
            limits:
              cpu: "750m"
              memory: "1024Mi"
            requests:
              cpu: "250m"
              memory: "256Mi"
```

# API Application build

To build
```bash
mvn clean package
docker build --tag simple-api .
```

## API Usage to send SMS
Deploy the K8S yamls
```bash

kubectl apply -f k8s/
kubectl port-forward service/sms-api 8080:8080

```

Make a GET request request to `http://127.0.0.1:8080/password?length=12` with sample payload:

```json
{"password":"Y9vyCawmTyBmK"}
```