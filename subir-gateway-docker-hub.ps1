# Script para construir y subir Gateway-Service a Docker Hub
# Ejecutar: .\subir-gateway-docker-hub.ps1

Write-Host "🔐 Iniciando sesión en Docker Hub..." -ForegroundColor Cyan
docker login

Write-Host "`n🏗️ Construyendo y subiendo Gateway-Service..." -ForegroundColor Green
docker build -t jazambrano21/farmacia-gateway-service:1.0.0 ./Gateway-Service
docker tag jazambrano21/farmacia-gateway-service:1.0.0 jazambrano21/farmacia-gateway-service:latest
docker push jazambrano21/farmacia-gateway-service:1.0.0
docker push jazambrano21/farmacia-gateway-service:latest

Write-Host "`n✅ Gateway-Service ha sido subido a Docker Hub!" -ForegroundColor Green
Write-Host "🔍 Verifica en: https://hub.docker.com/u/jazambrano21" -ForegroundColor Cyan

