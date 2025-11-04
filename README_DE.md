# Multi-Stack Demo Anwendungen

**🌐 Language / Sprache:** [English](README.md) | **Deutsch**

---

## Übersicht
Dieses Repository enthält Demo-Anwendungen, die mit verschiedenen Technologie-Stacks erstellt wurden, um Cloud-native Entwicklungsmuster und Deployment-Strategien zu demonstrieren. Das Projekt umfasst zwei Arten von Demos, jeweils implementiert in drei verschiedenen Technologie-Stacks (Spring Boot/Java, .NET Core/C#, Node.js).

## Zielsetzung
Das Ziel dieser Demo ist es:
- **Tech-Stacks vergleichen**: Sehen Sie, wie dieselbe Anwendung in Spring Boot (Java), .NET Core und Node.js implementiert wird
- **Cloud Native Patterns**: Demonstrieren von Containerisierung, externer Konfiguration, zustandslosen und zustandsbehafteten Designs
- **Datenbank-Integration**: Cloud-native Datenbankmuster mit PostgreSQL und MySQL zeigen
- **Deployment-Flexibilität**: Mehrere Deployment-Optionen zeigen (lokal, Docker, Cloud Foundry)
- **Blue/Green Deployments**: Deployment-Strategien mit Versions- und Farbcodierung visualisieren
- **Multi-Instanz-Awareness**: UUIDs verwenden, um verschiedene Instanzen in Load-Balanced-Szenarien zu identifizieren
- **Service Binding**: Cloud Foundry Service Binding mit VCAP_SERVICES demonstrieren

## Demo-Anwendungen

### Simple-Demo (Zustandslose Anwendungen)
Grundlegende zustandslose Anwendungen, die fundamentale Cloud-native Muster präsentieren.

![Simple Demo Screenshot](assets/simple-demo.png)

#### 1. Spring Boot Demo (`simple-demo/spring-boot-demo`)
- **Framework**: Spring Boot 3.5.7
- **Sprache**: Java 21
- **Port**: 8080 (konfigurierbar über PORT env var)

#### 2. .NET Core Demo (`simple-demo/dotnet-demo`)
- **Framework**: .NET 8.0
- **Sprache**: C#
- **Port**: 8081 (konfigurierbar über PORT env var)

#### 3. Node.js + React Demo (`simple-demo/nodejs-demo`)
- **Backend**: Express.js
- **Frontend**: React
- **Port**: 8082 (konfigurierbar über PORT env var)
- **Offline Deployment**: Unterstützt Cloud Foundry Offline/Air-Gapped Deployments über `npm-packages-offline-cache`

### DB-Demo (Datenbank-gestützte Anwendungen)
Anwendungen mit PostgreSQL-Datenbankintegration, die Cloud-native Datenmuster demonstrieren.

![DB Demo Screenshot](assets/db-demo.png)

#### 1. Spring Boot DB Demo (`db-demo/spring-boot-demo`)
- **Framework**: Spring Boot 3.5.7 + Spring Data JPA
- **Sprache**: Java 21
- **Datenbank**: PostgreSQL 17
- **Port**: 8080 (konfigurierbar über PORT env var)

#### 2. .NET Core DB Demo (`db-demo/dotnet-demo`)
- **Framework**: .NET 8.0 + Entity Framework Core
- **Sprache**: C#
- **Datenbank**: PostgreSQL 17
- **Port**: 8081 (konfigurierbar über PORT env var)

#### 3. Node.js DB Demo (`db-demo/nodejs-demo`)
- **Backend**: Express.js + pg (node-postgres) + mysql2
- **Datenbank**: PostgreSQL 17 oder MySQL 8 (automatisch erkannt)
- **Port**: 8082 (konfigurierbar über PORT env var)
- **Offline Deployment**: Unterstützt Cloud Foundry Offline/Air-Gapped Deployments über `npm-packages-offline-cache`

## Features

### Simple-Demo Features

#### Web-Interface
- **Instanz-UUID**: Im Header angezeigt, um einzelne Instanzen zu identifizieren
- **Tech-Stack-Informationen**: Framework, Sprachversion, Runtime-Details
- **Versions-Badge**: Konfigurierbare Versionsnummer mit Farbcodierung zur Deployment-Visualisierung

#### REST API
- **Endpoint**: `GET /api/infos`
- **Rückgabe**: JSON mit Tech-Stack-Informationen und Instanzdetails

![API Infos Response](assets/api-infos.png)

### DB-Demo Features

#### Web-Interface
- **Instanz-UUID**: Im Header angezeigt, um einzelne Instanzen zu identifizieren
- **Tech-Stack-Informationen**: Framework, Sprachversion, Runtime-Details
- **Versions-Badge**: Konfigurierbare Versionsnummer mit Farbcodierung zur Deployment-Visualisierung
- **Pets-Tabelle**: Datenbank-gestützte Tabelle mit 8 Beispiel-Haustiereinträgen (Rasse, Geschlecht, Name, Alter, Beschreibung)

#### REST API
- **Endpoint**: `GET /api/infos` - Tech-Stack-Informationen
- **Endpoint**: `GET /api/pets` - Alle Haustiere aus der PostgreSQL-Datenbank auflisten

![API Pets Response](assets/api-pets.png)

#### Datenbank-Integration
- **PostgreSQL 17 oder MySQL 8**: Dual-Datenbank-Unterstützung mit automatischer Erkennung
- **Beispieldaten**: 8 vorbefüllte Haustiereinträge
- **Auto-Initialisierung**: Erstellt Tabelle und Daten beim ersten Start
- **Docker Compose**: Enthält PostgreSQL und pgAdmin 4 für lokale Entwicklung
- **Cloud Foundry**: Erkennt automatisch MySQL oder PostgreSQL über VCAP_SERVICES (my-demo-db)
- **Flexibles Deployment**: Wählen Sie zwischen PostgreSQL oder MySQL beim Deployment auf Cloud Foundry

## Deployment-Optionen

### Simple-Demo: Lokale Entwicklung
Jede Anwendung kann direkt auf Ihrem Rechner ausgeführt werden:
```bash
# Spring Boot
cd simple-demo/spring-boot-demo
./mvnw spring-boot:run

# .NET Core
cd simple-demo/dotnet-demo
dotnet run

# Node.js
cd simple-demo/nodejs-demo
npm install
npm start

# Hinweis: Für Cloud Foundry Offline/Air-Gapped Umgebungen,
# siehe Abschnitt "Offline Deployment" unten
```

### DB-Demo: Lokale Entwicklung mit Docker Compose
Die db-demo Apps benötigen PostgreSQL, bereitgestellt über Docker Compose:
```bash
# Spring Boot
cd db-demo/spring-boot-demo
docker-compose up

# .NET Core
cd db-demo/dotnet-demo
docker-compose up

# Node.js
cd db-demo/nodejs-demo
docker-compose up
```

Dies startet:
- Die Anwendung (Spring Boot/8080, .NET/8081, Node.js/8082)
- PostgreSQL 17 Datenbank (nur internes Netzwerk)
- pgAdmin 4 unter http://localhost:5050 (admin@demo.com / admin)

### Docker mit Cloud Native Buildpacks
Erstellen und Ausführen mit Paketo Buildpacks:
```bash
cd simple-demo/<app-name>
./build.sh
docker-compose up
```

### Cloud Foundry Deployment

#### Simple-Demo
```bash
cd simple-demo/<app-name>
cf push
```

**Für Node.js Apps in Offline/Air-Gapped Cloud Foundry Umgebungen:**
```bash
cd simple-demo/nodejs-demo
./create-offline-cache.sh  # npm-packages-offline-cache erstellen
cf push
```

#### DB-Demo (mit Datenbank-Service)

**Option 1: PostgreSQL (Standard)**
```bash
cd db-demo/<app-name>
# PostgreSQL Service erstellen, falls nicht vorhanden
./create-db-service.sh
# oder explizit:
./create-db-service.sh postgres
# Anwendung deployen
cf push
```

**Option 2: MySQL**
```bash
cd db-demo/<app-name>
# MySQL Service erstellen, falls nicht vorhanden
./create-db-service.sh mysql
# Anwendung deployen
cf push
```

Die db-demo Anwendungen erkennen und binden sich automatisch an MySQL oder PostgreSQL Service (`my-demo-db`) in Cloud Foundry über VCAP_SERVICES. Die Anwendung konfiguriert sich entsprechend basierend auf dem erkannten Datenbanktyp.

**Für Node.js Apps in Offline/Air-Gapped Cloud Foundry Umgebungen:**
```bash
cd db-demo/nodejs-demo
./create-offline-cache.sh        # npm-packages-offline-cache erstellen
./create-db-service.sh postgres  # Datenbank-Service erstellen (falls benötigt)
cf push
```

## Konfiguration
Jede Anwendung verwendet ihr natives Konfigurationsformat zum Setzen von:
- **Versionsnummer**: Zur Blue/Green Deployment-Identifikation
- **Deployment-Farbe**: Visueller Indikator (blau, grün, etc.)

Konfigurationsdateien:
- Spring Boot: `application.properties`
- .NET Core: `appsettings.json`
- Node.js: `.env` oder `config.json`

## Blue/Green Deployment Demo
1. Deployen Sie Version 1.0.0 mit Farbe "blue"
2. Greifen Sie auf die Anwendung zu und notieren Sie UUID und Farbe
3. Deployen Sie Version 2.0.0 mit Farbe "green"
4. Schalten Sie den Traffic zwischen Versionen um, um Zero-Downtime Deployment zu demonstrieren
5. Verwenden Sie die UUID, um zu verfolgen, welche Instanz Anfragen bearbeitet

## Offline/Air-Gapped Cloud Foundry Deployment

Die **Node.js Demos** unterstützen Deployment auf Cloud Foundry Umgebungen, die **keinen Zugriff auf externe npm Registries** während der Buildpack-Ausführung haben (Air-Gapped oder eingeschränkte Netzwerke).

### Offizielle Cloud Foundry Methode: npm-packages-offline-cache

Beide Node.js Demos verwenden die **offizielle Cloud Foundry Offline-Deployment-Methode**, wie in der [CF Node.js Buildpack Dokumentation](https://docs.cloudfoundry.org/buildpacks/node/index.html#vendoring) beschrieben.

#### Wie es funktioniert
1. **Offline-Cache erstellen**: Führen Sie `./create-offline-cache.sh` aus, um das `npm-packages-offline-cache/` Verzeichnis mit allen Abhängigkeiten zu erstellen
2. **Hochladen zu CF**: Der Cache, `.yarnrc` und `yarn.lock` Dateien werden mit Ihrer App hochgeladen
3. **Buildpack-Erkennung**: CF Buildpack erkennt den Offline-Cache und führt Yarn im Offline-Modus aus
4. **Kein externer Zugriff erforderlich**: Alle Abhängigkeiten werden aus dem Cache bereitgestellt

#### Quick Start
```bash
# Simple demo
cd simple-demo/nodejs-demo
./create-offline-cache.sh
cf push

# DB demo
cd db-demo/nodejs-demo
./create-offline-cache.sh
./create-db-service.sh postgres  # falls benötigt
cf push
```

#### Erstellte Dateien
- `npm-packages-offline-cache/` - Enthält alle Abhängigkeiten als .tgz Archive
- `.yarnrc` - Yarn Offline-Konfiguration
- `yarn.lock` - Dependency Lock-Datei

#### Buildpack-Ausgabe
Beim Deployen sehen Sie:
```
-----> Detected npm-packages-offline-cache directory
-----> Running yarn in offline mode
```

### Dokumentation
- **Vollständige Anleitung**: [`OFFLINE-CACHE-GUIDE.md`](OFFLINE-CACHE-GUIDE.md)
- **Übersicht & Vergleich**: [`NODEJS-OFFLINE-DEPLOYMENT-README.md`](NODEJS-OFFLINE-DEPLOYMENT-README.md)

---

## Demonstrierte Cloud Native Patterns

### Simple-Demo Patterns
- ✅ **Stateless Design**: Kein gemeinsamer Zustand zwischen Instanzen
- ✅ **External Configuration**: Version und Farbe aus Konfigurationsdateien
- ✅ **Containerization**: Cloud Native Buildpacks für Sicherheit und Konsistenz
- ✅ **API-First**: REST-Endpoints neben Web-UI
- ✅ **Health Monitoring**: Jede App stellt Health-Informationen bereit
- ✅ **Platform Portability**: Läuft lokal, auf Docker, Cloud Foundry
- ✅ **Offline Deployment** (Node.js): Air-Gapped Cloud Foundry Unterstützung über npm-packages-offline-cache

#### Health Check Endpoints
Alle Anwendungen bieten Health-Check-Endpoints für Monitoring und Orchestrierung:

![Spring Boot Actuator Health](assets/actuator-health.png)

- **Spring Boot**: `/actuator/health` - Actuator Health Endpoint
- **.NET Core**: `/health` - ASP.NET Core Health Check
- **Node.js**: `/health` - Custom Health Endpoint

### DB-Demo Patterns (Zusätzlich)
- ✅ **External Service Dependency**: Datenbank als separater, verwalteter Service
- ✅ **Service Binding**: Cloud Foundry VCAP_SERVICES für Datenbank-Credentials
- ✅ **Multi-Database Support**: Automatische Erkennung und Konfiguration für MySQL oder PostgreSQL
- ✅ **Loose Coupling**: Anwendung verwaltet nicht den Datenbank-Lebenszyklus
- ✅ **Environment-Based Configuration**: Datenbankverbindung aus Umgebungsvariablen
- ✅ **Service Discovery**: Plattform stellt Datenbankstandort und Credentials bereit
- ✅ **Database Initialization**: Automatische Schema-Erstellung beim ersten Start
- ✅ **Docker Compose for Dev**: Lokale Entwicklung mit PostgreSQL und pgAdmin
- ✅ **Offline Deployment** (Node.js): Air-Gapped Cloud Foundry Unterstützung mit Datenbanktreibern im Offline-Cache

## Architektur
```
┌─────────────────────────────────────────┐
│          Load Balancer                  │
└─────────────────────────────────────────┘
         │              │
         ▼              ▼
    ┌─────────┐    ┌─────────┐
    │ App v1  │    │ App v2  │
    │ (Blue)  │    │ (Green) │
    │ UUID: A │    │ UUID: B │
    └─────────┘    └─────────┘
```

## Erste Schritte
1. Klonen Sie dieses Repository
2. Navigieren Sie zur Demo Ihrer Wahl in `simple-demo/`
3. Folgen Sie der README in jedem Unterverzeichnis für spezifische Setup-Anweisungen
4. Starten Sie die Anwendung mit Ihrer bevorzugten Methode (lokal, Docker oder CF)
5. Greifen Sie auf die Web-UI zu oder rufen Sie den `/api/infos` Endpoint auf

## Projektstruktur
```
demo/
├── README.md (diese Datei - Englisch)
├── README_DE.md (diese Datei - Deutsch)
├── CLAUDE.md (Planungsdokument)
├── LICENSE (MIT Lizenz)
├── .gitignore
├── OFFLINE-CACHE-GUIDE.md (Node.js Offline Deployment Guide)
├── NODEJS-OFFLINE-DEPLOYMENT-README.md (Übersicht & Vergleich)
├── assets/ (Screenshots)
├── simple-demo/
│   ├── spring-boot-demo/
│   ├── dotnet-demo/
│   └── nodejs-demo/
│       └── create-offline-cache.sh (Offline-Cache-Erstellung)
└── db-demo/
    ├── spring-boot-demo/
    ├── dotnet-demo/
    └── nodejs-demo/
        └── create-offline-cache.sh (Offline-Cache-Erstellung)
```

## Dokumentation
- **Planung & Architektur**: [`CLAUDE.md`](CLAUDE.md) - Detaillierte Planung und architektonische Entscheidungen
- **Anwendungsspezifisch**: Jedes Unterverzeichnis enthält seine eigene README mit spezifischen Anweisungen
- **Node.js Offline Deployment**:
  - [`OFFLINE-CACHE-GUIDE.md`](OFFLINE-CACHE-GUIDE.md) - Vollständige Anleitung für npm-packages-offline-cache Methode
  - [`NODEJS-OFFLINE-DEPLOYMENT-README.md`](NODEJS-OFFLINE-DEPLOYMENT-README.md) - Übersicht und Methodenvergleich

## Lizenz
Dieses Projekt ist unter der MIT-Lizenz lizenziert - siehe die [LICENSE](LICENSE) Datei für Details.

Dies ist ein Demonstrationsprojekt, das speziell für **Bildungszwecke** entwickelt wurde. Fühlen Sie sich frei, es für Lernen und Lehren zu verwenden, zu modifizieren und zu teilen.

## Happy Coding
### 11/2025 - Andreas Lange
