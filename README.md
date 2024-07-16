# Campus Management System (Backend)
```angular2html
- ld5ehom@gmail.com
- https://www.linkedin.com/in/ld5ehom
```

-----
## Tool
- Docker : https://www.docker.com/products/docker-desktop/
- DBeaver : https://dbeaver.io/
- ERD Editor : https://erd-editor.io/
- Insomnia : https://insomnia.rest/
-----
## Reference Site
- GraphQL Java : https://www.graphql-java.com/documentation/getting-started
- resilience4j : https://resilience4j.readme.io/docs/getting-started
- Prometheus : https://github.com/vegasbrianc/prometheus
- Kubernetes : https://kubernetes.io/docs/concepts/architecture
- Kubernetes ingress-nginx : https://kubernetes.github.io/ingress-nginx/deploy/#docker-desktop
- Helm : https://helm.sh/docs/
-----
## Description
- Developed a system based on Microservices Architecture (MSA) that integrates and aggregates data from RESTful API, gRPC, and GraphQL, enabling students to view course information in real-time and register for courses.
- Implemented a Course Management system with RESTful API for course creation, management, session handling, and evaluations, along with course playback information, enrollment, and playback services using gRPC, and aggregated data from these components using GraphQL.
- Collected component metrics with Prometheus and visualized them using Grafana, while also collecting transaction data from each component using Zipkin, storing it in Elasticsearch, and visualizing it through Kibana.
- Utilized: Java, Spring Boot, MySQL, REST, gRPC, GraphQL, Redis, ElasticSearch, Grafana, Prometheus

```angular2html
Architecture Diagram:
├─ Client
│   └─ API Gateway(Spring) <─> User Management Service(Spring)
│
├─ Service Registry (Spring)
│
├─ Microservices Architecture (MSA) :
│  ├─ (Query) GraphQL
│  │   └─ Aggregation from/
│  │      ├─ (gRPC) Enrollment Service
│  │      ├─ (REST) Course Management Service
│  │      └─ (gRPC) Playback Service
│  │
│  ├─ (gRPC) Enrollment Service 
│  ├─ (REST) Course Management Service 
│  ├─ (gRPC) Playback Service 
│  └─ (RAW/MP4) Content File Management Service
│
├─ Distributed Tracing :
│  ├─ Zipkin
│  ├─ Elasticsearch
│  ├─ Logstash
│  └─ Kibana
│
└─ Monitoring : 
   ├─ Grafana
   └─ Prometheus
```
```angular2html
Service Components Definition:
- GraphQL
   └─ Aggregates data from multiple components.
- User Management Service
   └─ Manages user authentication and profile information.
- Course Management Service
   ├─ Manages course and session information.
   └─ Handles course evaluations.
- Enrollment Service 
   └─ Manages information about purchased courses and user entitlements.
- Content File Management Service
   └─ Manages course content files.
- Playback Service
   └─ Tracks user playback sessions and events.
```
-----
## Course Information Service Components Design
### - Course Management Service Design
- Create Course (POST) : `/courses`
- Update Course Information (PUT) : `/courses/{courseId}`
- Get Course Information (GET) : `/courses/{courseId}`
- Get All Courses (GET) : `/courses`

### - Course Session Management
- Add Course Session (POST) : `/courses/{courseId}/sessions`
- Update Course Session Information (PUT) : `/courses/{courseId}/sessions/{sessionId}`
- Get Course Session Information (GET) : `/courses/{courseId}/sessions/{sessionId}`
- Get All Course Sessions (GET) : `/courses/{courseId}/sessions`

### - Course Ratings
- Add Course Rating (POST) : `/courses/{courseId}/ratings`
- Update Course Rating (PUT) : `/courses/{courseId}/ratings/{ratingId}`
- Delete Course Rating (DELETE) : `/courses/{courseId}/ratings/{ratingId}`
- Get All Course Ratings (GET) : `/courses/{courseId}/ratings`

### - Database Structure
```angular2html
  Database Name: ucla_course
  Following the Database per Service pattern.
  Only tables related to courses are created in the database:
    - courses 
    - course_sessions
    - course_ratings
```
-----
## User Management and Authentication Service Design
### - User Management
- Register and Manage User (POST) : `/users`
- Get User Details (GET) : `/users/{userId}`
- Update User Profile (PUT) : `/users/{userId}`
- Get User Login and Logout History (GET) : `/users/{userId}/login-histories`
- Change Password Request (POST) : `/users/{userId}/password-change`

### - Authentication
- Issue and Renew JWT (POST) : `/auth/token`
- Verify JWT (POST) : `/auth/verify-token`
- Refresh JWT (POST) : `/auth/refresh-token`


### - Database Structure
```angular2html
  Database Name: ucla_user
  Following the Database per Service pattern.
  Only tables related to users are created in the database:
    - users 
    - user_login_histories
```
-----
## Content File Service Component Design
### - File Upload
- POST /api/sessions/{sessionId}/files

### - List Files
- GET /api/sessions/{sessionId}/files 
- Response:
```json
{
  "fileId": 1,
  "fileName": "session1_video.mp4",
  "fileType": "mp4",
  "filePath": "/files/session1/"
}
```

### - Get File Detail
- GET /api/sessions/{sessionId}/files/{fileId}
- Response:
```json
{
  "fileId": 1,
  "fileName": "session1_video.mp4",
  "fileType": "mp4",
  "filePath": "/files/session1/",
  "url": "http://example.com/files/session1_video.mp4"
}
```

### - Delete File
- DELETE /api/sessions/{sessionId}/files/{fileId}

### - File Streaming
- POST /api/sessions/{sessionId}/streams

### - Database Structure
```angular2html
  Database Name: ucla_files
  Following the Database per Service pattern.
  Only tables related to session files are created in the database:
    - session_files
```
-----

## Ucla Course Database
```angular2html
ucla-campus-mysql

mysql -u root -p (ucla)
show databases;

CREATE DATABASE ucla_course;
CREATE DATABASE ucla_user;
CREATE DATABASE ucla_files;
CREATE DATABASE ucla_playback;
CREATE DATABASE ucla_enrollment;

CREATE USER 'taewook'@'%' IDENTIFIED BY 'campus';

GRANT ALL PRIVILEGES ON ucla_course.* TO 'taewook'@'%';
GRANT ALL PRIVILEGES ON ucla_user.* TO 'taewook'@'%';
GRANT ALL PRIVILEGES ON ucla_files.* TO 'taewook'@'%';
GRANT ALL PRIVILEGES ON ucla_playback.* TO 'taewook'@'%';
GRANT ALL PRIVILEGES ON ucla_enrollment.* TO 'taewook'@'%';
```





### Start
- UclaDiscoveryApplication
- Other Application
- docker
- zipkin

### ElasticSearch
```angular2html
export ES_JAVA_HOME=$(/usr/libexec/java_home)
/opt/homebrew/Cellar/elasticsearch-full/7.17.4/bin/elasticsearch
```

### Prometheus
```angular2html
- JVM Dashboard ID : 4701
prometheus --config.file=/usr/local/etc/prometheus.yml
```

### Grafana
```
brew services start grafana
```

### Spring Cloud Gateway
```angular2html
https://github.com/spring-cloud/spring-cloud-gateway
ID : grafana.net/dashboard/import
-> Import via dashboard JSON model

https://github.com/spring-cloud/spring-cloud-gateway/blob/main/docs/src/main/asciidoc/gateway-grafana-dashboard.json
```


### Docker Image Build
- Start
```
kubectl get pods 
```

- Execute from the root directory
```
#!/bin/bash
```

- Build Docker images for each module
```
docker build -t ucla-discovery:latest -f ucla-discovery/Dockerfile .
docker build -t ucla-gateway:latest -f ucla-gateway/Dockerfile .

docker build -t ucla-course-service:latest -f ucla-course-service/Dockerfile .
docker build -t ucla-file-manage-service:latest -f ucla-file-manage-service/Dockerfile .
docker build -t ucla-user-service:latest -f ucla-user-service/Dockerfile .

docker build -t ucla-enrollment-service:latest -f ucla-enrollment-service/Dockerfile .
docker build -t ucla-playback-service:latest -f ucla-playback-service/Dockerfile .
docker build -t ucla-graphql:latest -f ucla-graphql/Dockerfile .

docker image list 
```

### build-docker-image.sh
```
chmod 755 ./build-docker-image.sh
./build-docker-image.sh
```

### Ports
```angular2html
ucla-discovery = 8000:8000
ucla-gateway = 9000:8080
ucla-graphql = 8080:8080
ucla-course-service = 8081:8080
ucla-enrollment-service = 8082:8080
ucla-file-manage-service = 8083:8080
ucla-playback-service = 8084:8080
ucla-user-service = 8085:8080
```

### Kubernetes
```angular2html
kubectl
kubectl cluster-info
kubectl get all 

kubectl edit pod [pod-name]
kubectl logs [pod-name]

kubectl get namespaces
kubectl create namespace [namespace-name]

kubectl config view
kubectl config current-context
kubectl config use-context [context-name]
```

### Kubernetes Dashboard
```angular2html
- install
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.4.0/aio/deploy/recommended.yaml

kubectl get deployment -n kubernetes-dashboard

-proxy
kubectl proxy

http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/login

```

### Kubernetes service-account
```angular2html
-- service-account.yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: dashboard-admin
  namespace: kubernetes-dashboard
```
```angular2html
kubectl apply -f service-account.yaml
```
```angular2html
-- cluster-role-binding.yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: dashboard-admin
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: dashboard-admin
  namespace: kubernetes-dashboard
```
```angular2html
kubectl apply -f cluster-role-binding.yaml
```
```angular2html
kubectl -n kubernetes-dashboard create token dashboard-admin
```

### Kubernetes ingress-nginx
```angular2html
helm upgrade --install ingress-nginx ingress-nginx \
  --repo https://kubernetes.github.io/ingress-nginx \
  --namespace ingress-nginx --create-namespace
```
```angular2html
teo@TaeWookui-Macmini infrastructure-k8s % cd ucla-ingress
teo@TaeWookui-Macmini ucla-ingress % kubectl apply -f ingress.yaml
teo@TaeWookui-Macmini ucla-ingress % kubectl get ingress

```

### Helm
```angular2html
helm install [RELEASE_NAME] [CHART]
helm list
helm status [RELEASE_NAME]
helm upgrade [RELEASE_NAME] [CHART]
helm rollback [RELEASE_NAME] [REVISION]
helm uninstall [RELEASE_NAME]
helm search repo [KEYWORD]
helm repo add [REPO_NAME] [URL]
helm repo update
helm template [RELEASE_NAME] [CHART]
helm show chart [CHART]
```

```angular2html
Chart_name/
├── Chart.yaml
├── values.yaml
├── templates/
│   ├── deployment.yaml
│   ├── service.yaml
│   └── ingress.yaml 
└── .helmignore
```
```angular2html
cd..
ls
teo@TaeWookui-Macmini infrastructure-k8s % cd ucla-course-service
teo@TaeWookui-Macmini ucla-course-service % helm install ucla-course-service .
```
```angular2html
kubectl get services
kubectl get deployments
kubectl get deployments ucla-course-service
```
```angular2html
helm upgrade ucla-course-service .
```
```angular2html
kubectl proxy
http://localhost:8001/api/v1/namespaces/default/services/ucla-discovery:8000/proxy/
ctrl + c
```

