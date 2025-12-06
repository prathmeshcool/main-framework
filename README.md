\# 🧪 Main Test Automation Framework (SDET Portfolio Project)



This is a full-stack automation framework covering \*\*UI + API + Database\*\* testing,

designed using industry best SDET practices.



---



\## 📌 CI Status



!\[CI](https://github.com/prathmeshcool/main-framework/actions/workflows/ci.yml/badge.svg)

!\[Scheduled CI](https://github.com/prathmeshcool/main-framework/actions/workflows/ci-regression-schedule.yml/badge.svg)



---



\## 🔥 Features



| Area | Technology | What’s Covered |

|------|------------|----------------|

| UI Testing | Selenium + TestNG | POM, waits, headless, parallel runs, actions, assertions |

| API Testing | RestAssured | GET + POST validations |

| DB Verification | JDBC + SQLite | CRUD validations |

| Reports | ExtentReports | Screenshots on failure |

| CI/CD | GitHub Actions | Headless runs, artifacts upload |

| Data-Driven | JSON \& Excel | TestData for Login and more |

| Stability | RetryAnalyzer | Automatic retry for flaky failures |



---



\## 🏗 Framework Structure

src/test/java

├─ pages

├─ tests

│ ├─ LoginTest.java (UI)

│ ├─ ApiTests.java (API)

│ └─ DBTest.java (DB)

├─ utils (DriverFactory, Listeners, Data utilities)

├─ resources (Excel + JSON test data)



yaml





---



\## ▶ How to Run Locally



Run full suite:

```bash

mvn clean test

Run only smoke tests:



bash

mvn -Dgroups=smoke test

Run only regression tests:



bash



mvn -Dgroups=regression test

Chrome supports headless:



bash



mvn -Dheadless=true test

🧩 CI/CD Workflows

Workflow	Trigger	Tests

ci.yml	On Push + PR	Full Suite

ci-regression-schedule.yml	Nightly @ 02:00 IST + Manual	smoke on PR / regression nightly



💡 Reports \& screenshots are available as downloadable Artifacts in Actions.



🏁 Future Enhancements

Docker + Selenium Grid for distributed parallel execution



BrowserStack/LambdaTest cloud runs



Visual testing integration (Applitools)



📞 Contact

Created by Prathmesh — SDET in progress 🚀

