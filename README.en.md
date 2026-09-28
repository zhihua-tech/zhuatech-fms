[简体中文](README.md) | **English**

# ZhuaTech Financial Management System

> A source-available enterprise project by [ZhuaTech](https://www.zhuatech.cn/) for enterprise finance, planning, settlement, and risk control.

ZhuaTech Financial Management System provides a practical, self-hosted foundation for enterprise finance, planning, settlement, and risk control. It is designed for finance teams, business controllers, approvers, and operations managers, with clear business records, controlled workflows, operational visibility, and auditable actions.

This repository is intended for learning, technical evaluation, and non-commercial collaboration. The included implementation, tests, database resources, and container configuration provide a reproducible starting point for further enterprise adaptation.

**Search topics:** enterprise financial management system, self-hosted financial management system, Java Spring Boot enterprise software, digital transformation.

## Product Scope

- **Primary users:** Finance teams, business controllers, approvers, and operations managers.
- **Deployment model:** Self-hosted, with container-based local deployment where supported.
- **Governance baseline:** Role-aware operations, validation, approval boundaries, exception handling, and auditability.
- **Production boundary:** Review security, identity, backup, observability, capacity, and compliance controls before production use.

## Capability Map

- **User** — Manage user with ownership, validation, and explicit lifecycle states.
- **Cash Account** — Coordinate cash account through controlled workflows and approval gates.
- **Receivable** — Track receivable metrics, exceptions, deadlines, and follow-up actions.
- **Payable** — Preserve payable evidence in searchable, traceable operational history.
- **Expense Claim** — Expose expense claim in role-aware user and administration workspaces.
- **Budget** — Connect budget to external systems through configurable integration boundaries.

## Technology Baseline

**Technology stack:** Java 21 · Spring Boot · Vue 3 · Vite · MySQL 8 · Docker Compose

### Repository Layout

- `backend/` — Java backend, domain services, APIs, validation, and automated tests
- `frontend/` — responsive user and administration interfaces
- `deploy/` — deployment and operations resources
- `docs/` — architecture, operations, screenshots, and supporting documentation
- `compose.yaml` — local multi-service orchestration

## Run Locally

```bash
docker compose up -d --build
```

- Review `compose.yaml` before changing published ports, storage paths, or production credentials.

## Verification

Run the checks supported by this repository before changing or deploying it:

```bash
cd backend && mvn test
cd frontend && npm ci && npm run build
```

## Interface Preview

### Fms Finance Cockpit

![Fms Finance Cockpit](docs/images/fms-finance-cockpit.png)

### Fms Treasury Center

![Fms Treasury Center](docs/images/fms-treasury-center.png)

### Fms Settlement Ledger

![Fms Settlement Ledger](docs/images/fms-settlement-ledger.png)

### Fms Budget Expense

![Fms Budget Expense](docs/images/fms-budget-expense.png)

## Security and Production Readiness

- Never commit real passwords, API keys, tokens, certificates, customer data, or production connection strings.
- Replace all local demonstration credentials and secrets before deployment.
- Apply least privilege, tenant isolation, backup and restore drills, monitoring, rate limiting, and vulnerability management.
- Please report security issues privately through the contact channels below instead of publishing sensitive details.

## Usage and Commercial Licensing

Copyright © 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd.

This project is a publicly available source edition intended solely for personal learning, technical research, and non-commercial communication. Commercial use, paid delivery, resale, hosted commercial services, and commercial derivative distribution require prior written authorization from the copyright holder.

Third-party dependencies remain subject to their respective licenses. Review the repository `LICENSE` and `NOTICE` files before use.

## Commercial Licensing and Enterprise Services

For commercial licensing, private deployment, enterprise customization, software outsourcing, implementation services, FDE outsourcing, OPC technical support, or AI transformation consulting, contact ZhuaTech:

- Email: [han@zhuatech.cn](mailto:han@zhuatech.cn)
- Email: [jack@zhuatech.cn](mailto:jack@zhuatech.cn)
- [WhatsApp: +86 17521234993](https://wa.me/8617521234993)
- Website: [https://www.zhuatech.cn/](https://www.zhuatech.cn/)

## About ZhuaTech

[ZhuaTech](https://www.zhuatech.cn/) is operated by Shanghai Rujing Zhihua Information Technology Co., Ltd. We support small and medium-sized enterprises with digital transformation, AI adoption, enterprise software implementation, custom development, software project outsourcing, FDE services, OPC integration, and long-term technical support.
