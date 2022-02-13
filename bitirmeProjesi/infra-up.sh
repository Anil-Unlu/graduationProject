echo "Starting postgres container.."
docker run --name patika-postgres \
  -e POSTGRES_USER=root \
  -e POSTGRES_PASSWORD=password \
  -d \
  -p 5432:5432 \
  postgres

echo "Starting redis container.."
docker run --name patika-redis \
  -d \
  -e ALLOW_EMPTY_PASSWORD=yes \
  -p 6379:6379 \
  bitnami/redis:latest