# Expense Workflow

Expense submission, approval/rejection, filtering and category reports

## Business functionality
Draft-to-submitted-to-approved/rejected workflow; owner-scoped reads; date/category filtering; pageable queries.

## Technology and engineering practices
- Java 21 and Spring Boot 3.4
- REST controllers, DTO validation and service/repository separation
- Spring Data JPA with PostgreSQL
- Transactional business operations and optimistic locking on core records
- Actuator health/metrics endpoints
- Maven build and GitHub Actions CI
- Multi-stage Docker image running as a non-root user
- Docker Compose with PostgreSQL health check
- Kubernetes Deployment/Service with probes, resources and Secret references

## Run
```bash
docker compose up --build
```
Service port: `8084`. Health: `http://localhost:8084/actuator/health`

Local run: configure PostgreSQL connection in environment, then `mvn spring-boot:run`.
Test: `mvn clean verify`.

## API outline
See controller classes for exact request contracts.
- `POST /api/v1/expenses` create draft
- `GET /api/v1/expenses/{id}?ownerId=10` retrieve owner expense
- `POST /api/v1/expenses/{id}/submit?ownerId=10` submit
- `POST /api/v1/expenses/{id}/decision?approve=true` approve/reject
- `GET /api/v1/expenses?ownerId=10&from=2026-01-01&to=2026-12-31` search

## Suggested intermediate/advanced DevOps labs
1. Add unit and integration tests for business rules, not only application context.
2. Add JaCoCo coverage gates and publish test reports.
3. Add Flyway migrations; replace `ddl-auto: update` before production.
4. Add Testcontainers-based PostgreSQL integration tests.
5. Build immutable Docker tags using Git SHA; push to Docker Hub/ECR/GHCR.
6. Add Trivy scanning and generate an SBOM.
7. Deploy to Kubernetes; configure DB Secret and PostgreSQL service separately.
8. Add HPA, resource tuning, structured logs, metrics and distributed tracing.
9. Add OpenAPI documentation, authentication and role-based authorization.

## Training disclaimer
These are substantial learning starters, not production-ready systems. Identity IDs are simplified; authentication/authorization, payment/provider integrations, robust audit security and full integration test coverage are left as deliberate extension exercises. Review and run `mvn clean verify` before using them in class.
