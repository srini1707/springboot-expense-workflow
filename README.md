# Expense Approval Workflow

    Expense submission and approval workflow with policy checks, role-aware actions, filtering, pagination and audit timestamps.

    ## Technology
    - Java 21
    - Spring Boot 3.4.4
    - Maven
    - Spring Web, Bean Validation, Actuator
    - In-memory storage using Java collections (no database)

    ## Important database note
    This project has **no database dependency and no database configuration**. Data is held in application memory and is lost when the process/container restarts. This is intentional for a database-free DevOps lab.

    ## Run locally
    ```bash
    mvn clean verify
    mvn spring-boot:run
    ```
    Application port: `8084`

    Health check:
    ```bash
    curl http://localhost:8084/actuator/health
    ```

    ## Docker
    ```bash
    docker build -t springboot-expense-workflow:1.0 .
    docker run --rm -p 8084:8084 springboot-expense-workflow:1.0
    ```

    ## API endpoints
    ```text
    POST   /api/v1/expenses
GET    /api/v1/expenses/{id}
GET    /api/v1/expenses?employeeId=...
PATCH  /api/v1/expenses/{id}/submit
PATCH  /api/v1/expenses/{id}/approve?approverId=...
PATCH  /api/v1/expenses/{id}/reject?approverId=...&reason=...
    ```

    ## Example requests
    ```bash
    curl -X POST http://localhost:8084/api/v1/expenses -H 'Content-Type: application/json' -d '{"employeeId":"EMP-10","category":"TRAVEL","description":"Airport transfer","amount":850.50,"expenseDate":"2026-11-20"}'
curl -X PATCH http://localhost:8084/api/v1/expenses/1/submit
curl -X PATCH 'http://localhost:8084/api/v1/expenses/1/approve?approverId=MGR-01'
    ```

    ## DevOps practice tasks
    1. Build the JAR with Maven and inspect the `target/` output.
    2. Build and run the Docker image; inspect logs and container health.
    3. Change the application port through an environment variable or JVM property.
    4. Add a GitHub Actions branch protection rule and inspect CI failures.
    5. Add structured logging and a correlation ID filter.
    6. Add API integration tests with MockMvc.
    7. Create Kubernetes Deployment and Service manifests; configure readiness and liveness probes.
    8. Add resource requests/limits and run a rolling update.
    9. Introduce a deliberate validation or business-rule bug and troubleshoot it.
    10. Document the impact of in-memory data loss during pod restart and propose a persistence strategy.

    ## Repository structure
    ```text
    src/main/java/   application, controller, service, records, exception handler
    src/main/resources/application.properties
    src/test/java/   service unit tests
    .github/workflows/ci.yml
    Dockerfile
    pom.xml
    ```
