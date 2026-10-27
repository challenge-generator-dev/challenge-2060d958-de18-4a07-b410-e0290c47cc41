# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/payments/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/payments/domain/port/AuthorizationService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `reactor.util.retry`: El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/AuthorizationClient.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/AuthorizationClient.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/LoggingAspect.java` — `org.aspectj.lang`: El import org.aspectj.lang.JoinPoint pertenece a org.aspectj.lang, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/LoggingAspect.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/adapter/AuthorizationClientTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java` — `PaymentRequest.operationNumber`: Se invoca `operationNumber` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java` — `PaymentRequest.channel`: Se invoca `channel` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java` — `PaymentRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java` — `PaymentRequest.authorizationResult`: Se invoca `authorizationResult` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java` — `PaymentRequest.createdAt`: Se invoca `createdAt` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java` — `PaymentRequest.updatedAt`: Se invoca `updatedAt` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `PaymentRequest.operationNumber`: Se invoca `operationNumber` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `PaymentRequest.channel`: Se invoca `channel` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `PaymentRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `PaymentRequest.authorizationResult`: Se invoca `authorizationResult` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AuthorizationClient.java` — `PaymentRequest.operationNumber`: Se invoca `operationNumber` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AuthorizationClient.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AuthorizationClient.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AuthorizationClient.java` — `PaymentRequest.channel`: Se invoca `channel` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/AuthorizationClient.java` — `PaymentRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `PaymentRequest.operationNumber`: Se invoca `operationNumber` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `PaymentRequest.channel`: Se invoca `channel` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `PaymentService.findByOperationNumberAndChannel`: Se invoca `findByOperationNumberAndChannel` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `PaymentService.checkServiceAvailability`: Se invoca `checkServiceAvailability` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/config/LoggingAspect.java` — `PaymentRequest.operationNumber`: Se invoca `operationNumber` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/config/LoggingAspect.java` — `PaymentRequest.channel`: Se invoca `channel` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/config/LoggingAspect.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/config/LoggingAspect.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/config/LoggingAspect.java` — `PaymentRequest.status`: Se invoca `status` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/config/LoggingAspect.java` — `PaymentRequest.authorizationResult`: Se invoca `authorizationResult` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentRequest.operationNumber`: Se invoca `operationNumber` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentRequest.channel`: Se invoca `channel` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentRequest.requestDate`: Se invoca `requestDate` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentRequest.authorizationResult`: Se invoca `authorizationResult` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Reto
- Tema: sólida experiencia en frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Integración y Configuración de Framework en Aplicación de Pagos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración Inicial del Framework — objetivo: Configurar el framework para enrutar solicitudes y controlar variables de entorno. — entregable (NO resolver): Configuración del framework que enruta solicitudes y controla variables de entorno.
- Fase 2: Implementación de Generación de Logs — objetivo: Implementar la generación de logs detallados para rastrear las solicitudes de pago. — entregable (NO resolver): Implementación de generación de logs detallados para solicitudes de pago.
- Fase 3: Conexión con Base de Datos utilizando ORM — objetivo: Conectar la aplicación con una base de datos utilizando un ORM. — entregable (NO resolver): Conexión de la aplicación con una base de datos utilizando un ORM y persistencia de solicitudes de pago.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>payments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>payments</name>
    <description>Sistema de procesamiento de pagos con tolerancia a fallos y logging estructurado</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
            <version>42.7.3</version>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Documentation -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/payments/Application.java ===
package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

@SpringBootApplication
@EnableConfigurationProperties
@EnableRetry
@EnableAsync
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(500))
                .setReadTimeout(Duration.ofMillis(1500))
                .build();
    }
    
    @Bean
    public CircuitBreakerConfig customCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .build();
    }
    
    @Bean
    public TimeLimiterConfig customTimeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(1500))
                .build();
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: payments-service
  
  profiles:
    active: dev
  
  datasource:
    url: jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:payments_db}
    username: ${DB_USER:payment_user}
    password: ${DB_PASSWORD:payment_password}
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      idle-timeout: 30000
      max-lifetime: 1800000
      connection-timeout: 30000
      pool-name: PaymentHikariPool
  
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        jdbc:
          lob.non_contextual_creation: true
        
  retry:
    max-attempts: 3
    backoff:
      initial-interval: 5000
      max-interval: 10000
      multiplier: 2.0

server:
  port: ${SERVER_PORT:8080}
  servlet:
    context-path: /api/payments

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
    instances:
      authorizationService:
        baseConfig: default
        waitDurationInOpenState: 10s
  
  retry:
    configs:
      default:
        maxRetryAttempts: 3
        waitDuration: 5s
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
        retryExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
    instances:
      authorizationService:
        baseConfig: default

logging:
  level:
    root: INFO
    com.pragma.payments: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.web: DEBUG
    org.springframework.retry: DEBUG
    io.github.resilience4j: DEBUG
  
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  
  file:
    name: ${LOG_FILE:logs/payments-service.log}
    max-size: 10MB
    max-history: 7

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/PaymentRequest.java ===
package com.pragma.payments.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentRequest(
    @NotBlank(message = "El número de operación no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z0-9-]{1,50}$", message = "El número de operación debe ser alfanumérico y tener entre 1 y 50 caracteres")
    String operationNumber,
    
    @NotBlank(message = "El canal no puede estar vacío")
    @Pattern(regexp = "^[A-Z]{2,10}$", message = "El canal debe ser una cadena de 2 a 10 caracteres en mayúsculas")
    String channel,
    
    @NotNull(message = "La fecha y hora no pueden ser nulas")
    LocalDateTime requestDateTime,
    
    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser positivo")
    BigDecimal amount,
    
    @NotBlank(message = "La moneda no puede estar vacía")
    @Pattern(regexp = "^[A-Z]{3}$", message = "La moneda debe ser un código de 3 letras en mayúsculas")
    String currency,
    
    String merchantId,
    
    @NotBlank(message = "El número de tarjeta no puede estar vacío")
    @Pattern(regexp = "^\\d{13,19}$", message = "El número de tarjeta debe tener entre 13 y 19 dígitos")
    String cardNumber,
    
    String authorizationResult,
    
    UUID idempotencyKey
) {
    public PaymentRequest {
        if (operationNumber == null || operationNumber.isBlank()) {
            throw new IllegalArgumentException("El número de operación no puede ser nulo o vacío");
        }
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("El canal no puede ser nulo o vacío");
        }
        if (requestDateTime == null) {
            throw new IllegalArgumentException("La fecha y hora no pueden ser nulas");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("La moneda no puede ser nula o vacía");
        }
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("El número de tarjeta no puede ser nulo o vacío");
        }
        if (idempotencyKey == null) {
            idempotencyKey = UUID.randomUUID();
        }
    }
    
    public PaymentRequest withAuthorizationResult(String result) {
        return new PaymentRequest(
            this.operationNumber,
            this.channel,
            this.requestDateTime,
            this.amount,
            this.currency,
            this.merchantId,
            this.cardNumber,
            result,
            this.idempotencyKey
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/PaymentRepository.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.PaymentRequest;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    PaymentRequest save(PaymentRequest paymentRequest);
    
    Optional<PaymentRequest> findByOperationNumberAndChannel(String operationNumber, String channel);
    
    Optional<PaymentRequest> findByIdempotencyKey(UUID idempotencyKey);
    
    boolean existsByOperationNumberAndChannel(String operationNumber, String channel);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/AuthorizationService.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.PaymentRequest;
import reactor.core.publisher.Mono;

public interface AuthorizationService {
    Mono<String> authorizePayment(PaymentRequest paymentRequest);
    
    Mono<Boolean> isAvailable();
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.PaymentRequest;
import com.pragma.payments.domain.port.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentJpaRepository implements PaymentRepository {

    private final PaymentRequestJpaRepository jpaRepository;

    public PaymentJpaRepository(PaymentRequestJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PaymentRequest save(PaymentRequest paymentRequest) {
        PaymentRequestEntity entity = toEntity(paymentRequest);
        PaymentRequestEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<PaymentRequest> findByOperationNumberAndChannel(String operationNumber, String channel) {
        return jpaRepository.findByOperationNumberAndChannel(operationNumber, channel)
                .map(this::toDomain);
    }

    @Override
    public Optional<PaymentRequest> findByIdempotencyKey(UUID idempotencyKey) {
        return jpaRepository.findByIdempotencyKey(idempotencyKey)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByOperationNumberAndChannel(String operationNumber, String channel) {
        return jpaRepository.existsByOperationNumberAndChannel(operationNumber, channel);
    }

    private PaymentRequestEntity toEntity(PaymentRequest domain) {
        PaymentRequestEntity entity = new PaymentRequestEntity();
        entity.setOperationNumber(domain.operationNumber());
        entity.setChannel(domain.channel());
        entity.setAmount(domain.amount());
        entity.setCurrency(domain.currency());
        entity.setIdempotencyKey(domain.idempotencyKey());
        entity.setAuthorizationResult(domain.authorizationResult());
        entity.setCreatedAt(domain.createdAt());
        entity.setUpdatedAt(domain.updatedAt());
        return entity;
    }

    private PaymentRequest toDomain(PaymentRequestEntity entity) {
        return new PaymentRequest(
                entity.getOperationNumber(),
                entity.getChannel(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getIdempotencyKey(),
                entity.getAuthorizationResult(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public interface PaymentRequestJpaRepository extends JpaRepository<PaymentRequestEntity, Long> {
        Optional<PaymentRequestEntity> findByOperationNumberAndChannel(String operationNumber, String channel);

        Optional<PaymentRequestEntity> findByIdempotencyKey(UUID idempotencyKey);

        boolean existsByOperationNumberAndChannel(String operationNumber, String channel);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/PaymentRequestEntity.java ===
package com.pragma.payments.infrastructure.adapter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_requests")
public class PaymentRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_number", nullable = false)
    private String operationNumber;

    @Column(nullable = false)
    private String channel;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "idempotency_key", nullable = false)
    private UUID idempotencyKey;

    @Column(name = "authorization_result")
    private String authorizationResult;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(UUID idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public String getAuthorizationResult() {
        return authorizationResult;
    }

    public void setAuthorizationResult(String authorizationResult) {
        this.authorizationResult = authorizationResult;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/PaymentService.java ===
package com.pragma.payments.application;

import com.pragma.payments.domain.model.PaymentRequest;
import com.pragma.payments.domain.port.AuthorizationService;
import com.pragma.payments.domain.port.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final long RETRY_INTERVAL_SECONDS = 5;

    private final PaymentRepository paymentRepository;
    private final AuthorizationService authorizationService;

    public PaymentService(PaymentRepository paymentRepository, AuthorizationService authorizationService) {
        this.paymentRepository = paymentRepository;
        this.authorizationService = authorizationService;
    }

    public Mono<PaymentRequest> processPayment(PaymentRequest request) {
        log.info("Iniciando procesamiento de pago para operationNumber={}, channel={}, amount={} {}",
                request.operationNumber(), request.channel(), request.amount(), request.currency());

        return checkIdempotency(request.idempotencyKey())
                .flatMap(exists -> {
                    if (exists) {
                        log.warn("Solicitud duplicada detectada para idempotencyKey={}", request.idempotencyKey());
                        return paymentRepository.findByIdempotencyKey(request.idempotencyKey())
                                .map(Mono::just)
                                .orElseGet(() -> Mono.error(new IllegalStateException("Inconsistencia en idempotencia")));
                    }
                    return processWithRetry(request);
                });
    }

    private Mono<Boolean> checkIdempotency(UUID idempotencyKey) {
        return Mono.fromCallable(() -> paymentRepository.findByIdempotencyKey(idempotencyKey).isPresent())
                .onErrorResume(e -> {
                    log.error("Error al verificar idempotencia: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    private Mono<PaymentRequest> processWithRetry(PaymentRequest request) {
        return authorizationService.authorizePayment(request)
                .retryWhen(Retry.backoff(MAX_RETRY_ATTEMPTS, Duration.ofSeconds(RETRY_INTERVAL_SECONDS))
                        .filter(throwable -> isRetryableError(throwable))
                        .doBeforeRetry(retrySignal -> {
                            log.warn("Reintento {} de {} para operationNumber={}",
                                    retrySignal.totalRetries() + 1,
                                    MAX_RETRY_ATTEMPTS,
                                    request.operationNumber());
                        }))
                .flatMap(authResult -> {
                    PaymentRequest updatedRequest = request.withAuthorizationResult(authResult);
                    return savePayment(updatedRequest);
                })
                .onErrorResume(e -> {
                    log.error("Error final en procesamiento de pago para operationNumber={}: {}",
                            request.operationNumber(), e.getMessage());
                    return Mono.error(e);
                });
    }

    private boolean isRetryableError(Throwable throwable) {
        String message = throwable.getMessage();
        if (message == null) {
            return false;
        }
        return message.contains("5") || 
               message.contains("500") || 
               message.contains("502") || 
               message.contains("503") ||
               message.contains("504") ||
               message.toLowerCase().contains("server error");
    }

    private Mono<PaymentRequest> savePayment(PaymentRequest request) {
        PaymentRequest requestToSave = new PaymentRequest(
                request.operationNumber(),
                request.channel(),
                request.amount(),
                request.currency(),
                request.idempotencyKey(),
                request.authorizationResult(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return Mono.fromCallable(() -> paymentRepository.save(requestToSave))
                .doOnSuccess(saved -> log.info("Pago procesado exitosamente para operationNumber={}, resultado={}",
                        saved.operationNumber(), saved.authorizationResult()))
                .doOnError(e -> log.error("Error al guardar pago: {}", e.getMessage()));
    }

    public Mono<PaymentRequest> getPaymentByOperationNumber(String operationNumber, String channel) {
        return Mono.fromCallable(() -> paymentRepository.findByOperationNumberAndChannel(operationNumber, channel))
                .flatMap(opt -> opt.map(Mono::just).orElseGet(() -> Mono.empty()));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/AuthorizationClient.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.PaymentRequest;
import com.pragma.payments.domain.port.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorizationClient implements AuthorizationService {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationClient.class);
    private static final String AUTHORIZATION_ENDPOINT = "http://localhost:8081/api/authorization/authorize";
    private static final String HEALTH_ENDPOINT = "http://localhost:8081/api/authorization/health";

    private final RestTemplate restTemplate;

    public AuthorizationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Mono<String> authorizePayment(PaymentRequest paymentRequest) {
        log.info("Enviando solicitud de autorización para operationNumber={}, amount={} {}",
                paymentRequest.operationNumber(), paymentRequest.amount(), paymentRequest.currency());

        return Mono.fromCallable(() -> {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("operationNumber", paymentRequest.operationNumber());
                requestBody.put("channel", paymentRequest.channel());
                requestBody.put("amount", paymentRequest.amount().toPlainString());
                requestBody.put("currency", paymentRequest.currency());
                requestBody.put("idempotencyKey", paymentRequest.idempotencyKey().toString());

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

                var response = restTemplate.postForEntity(
                        AUTHORIZATION_ENDPOINT,
                        request,
                        AuthorizationResponse.class
                );

                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    String result = response.getBody().getResult();
                    log.info("Autorización exitosa para operationNumber={}, resultado={}",
                            paymentRequest.operationNumber(), result);
                    return result;
                }

                log.error("Autorización fallida para operationNumber={}, status={}",
                        paymentRequest.operationNumber(), response.getStatusCode());
                throw new AuthorizationException("Autorización fallida con status: " + response.getStatusCode());

            } catch (Exception e) {
                log.error("Error en comunicación con motor de autorización para operationNumber={}: {}",
                        paymentRequest.operationNumber(), e.getMessage());
                throw new AuthorizationException("Error de comunicación con motor de autorización: " + e.getMessage(), e);
            }
        });
    }

    @Override
    public Mono<Boolean> isAvailable() {
        return Mono.fromCallable(() -> {
            try {
                var response = restTemplate.getForEntity(HEALTH_ENDPOINT, HealthResponse.class);
                boolean available = response.getStatusCode().is2xxSuccessful() &&
                        response.getBody() != null &&
                        "UP".equals(response.getBody().getStatus());
                log.debug("Estado del motor de autorización: {}", available ? "DISPONIBLE" : "NO DISPONIBLE");
                return available;
            } catch (Exception e) {
                log.warn("Motor de autorización no disponible: {}", e.getMessage());
                return false;
            }
        });
    }

    private static class AuthorizationResponse {
        private String result;
        private String message;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private static class HealthResponse {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class AuthorizationException extends RuntimeException {
        public AuthorizationException(String message) {
            super(message);
        }

        public AuthorizationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/ResilienceConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreakerConfig circuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
    }

    @Bean
    public RateLimiterConfig rateLimiterConfig() {
        return RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(100)
                .timeoutDuration(Duration.ofMillis(500))
                .build();
    }

    @Bean
    public RetryConfig retryConfig() {
        return RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(5))
                .retryExceptions(Exception.class)
                .build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java ===
package com.pragma.payments.infrastructure.controller;

import com.pragma.payments.application.PaymentService;
import com.pragma.payments.domain.model.PaymentRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Mono<ResponseEntity<PaymentRequest>> processPayment(
            @Valid @RequestBody PaymentRequest request) {
        
        logger.info("Recibida solicitud de pago - OperationNumber: {}, Channel: {}",
                request.operationNumber(), request.channel());
        
        return paymentService.processPayment(request)
                .map(payment -> ResponseEntity.status(HttpStatus.OK).body(payment))
                .onErrorResume(Exception.class, error -> {
                    logger.error("Error procesando pago: {}", error.getMessage(), error);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping("/{operationNumber}")
    public Mono<ResponseEntity<PaymentRequest>> getPaymentByOperationNumber(
            @PathVariable String operationNumber,
            @RequestParam String channel) {
        
        logger.info("Consultando pago - OperationNumber: {}, Channel: {}", operationNumber, channel);
        
        return paymentService.findByOperationNumberAndChannel(operationNumber, channel)
                .map(payment -> ResponseEntity.ok(payment))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<String>> healthCheck() {
        return paymentService.checkServiceAvailability()
                .flatMap(available -> {
                    if (Boolean.TRUE.equals(available)) {
                        return Mono.just(ResponseEntity.ok("OK"));
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
                });
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/LoggingAspect.java ===
package com.pragma.payments.infrastructure.config;



import com.pragma.payments.application.PaymentService;
import com.pragma.payments.infrastructure.controller.PaymentController;
import com.pragma.payments.domain.model.PaymentRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Before("execution(* com.pragma.payments.infrastructure.controller.PaymentController.*(..))")
    public void logBeforePaymentRequest(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        logger.info("INICIO DE SOLICITUD - Método: {}", methodName);
        
        for (Object arg : args) {
            if (arg instanceof PaymentRequest request) {
                logger.info("DATOS SOLICITUD - Número de operación: {}, Canal: {}, Monto: {}, Moneda: {}",
                        request.operationNumber(),
                        request.channel(),
                        request.amount(),
                        request.currency());
            } else if (arg instanceof String) {
                logger.debug("Parámetro: {}", arg);
            }
        }
    }

    @AfterReturning(pointcut = "execution(* com.pragma.payments.infrastructure.controller.PaymentController.*(..))",
                    returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        
        logger.info("RESULTADO EXITOSO - Método: {}, Timestamp: {}, Resultado: {}",
                methodName, timestamp, result);
        
        if (result instanceof PaymentRequest request) {
            logger.info("RESPUESTA PROCESADA - Número de operación: {}, Estado: {}, Autorización: {}",
                    request.operationNumber(),
                    request.status(),
                    request.authorizationResult());
        }
    }

    @AfterThrowing(pointcut = "execution(* com.pragma.payments.infrastructure.controller.PaymentController.*(..))",
                   throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        
        logger.error("ERROR EN SOLICITUD - Método: {}, Timestamp: {}, Tipo de error: {}, Mensaje: {}",
                methodName, timestamp, exception.getClass().getSimpleName(), exception.getMessage());
        
        logger.error("Stack trace completo:", exception);
    }

    @Before("execution(* com.pragma.payments.application.PaymentService.*(..))")
    public void logServiceLayer(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        logger.debug("EJECUTANDO SERVICIO - Clase: {}, Método: {}, Argumentos: {}",
                className, methodName, Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.pragma.payments.application.PaymentService.*(..))",
                    returning = "result")
    public void logServiceLayerComplete(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        logger.debug("SERVICIO COMPLETADO - Clase: {}, Método: {}, Resultado tipo: {}",
                className, methodName, result != null ? result.getClass().getSimpleName() : "void");
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/application/PaymentServiceTest.java ===
package com.pragma.payments.application;

import com.pragma.payments.domain.model.PaymentRequest;
import com.pragma.payments.domain.port.AuthorizationService;
import com.pragma.payments.domain.port.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
@DisplayName("PaymentService - Tests de lógica de reintentos y procesamiento de pagos")
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private PaymentService paymentService;

    private PaymentRequest paymentRequest;
    private static final String OPERATION_NUMBER = "OP-2024-001";
    private static final String CHANNEL = "WEB";
    private static final String IDEMPOTENCY_KEY = "idem-key-123";

    @BeforeEach
    void setUp() {
        paymentRequest = new PaymentRequest(
            OPERATION_NUMBER,
            CHANNEL,
            new BigDecimal("1000.00"),
            "USD",
            IDEMPOTENCY_KEY,
            LocalDateTime.now(),
            null
        );
    }

    @Test
    @DisplayName("Debe procesar pago exitosamente en primer intento cuando el servicio de autorización responde OK")
    void debeProcesarPagoExitosamenteEnPrimerIntento() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(Mono.just("AUTHORIZED"));
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "AUTHORIZED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(authorizationService, times(1)).authorizePayment(any(PaymentRequest.class));
        verify(paymentRepository, times(1)).save(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe reintentar hasta 3 veces cuando el servicio de autorización retorna error 5xx")
    void debeReintentarHasta3VecesEnError5xx() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(
                Mono.error(new RuntimeException("500 Internal Server Error")),
                Mono.error(new RuntimeException("503 Service Unavailable")),
                Mono.just("AUTHORIZED")
            );
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "AUTHORIZED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(authorizationService, times(3)).authorizePayment(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe fallar después de 3 reintentos cuando el servicio de autorización sigue fallando")
    void debeFallarDespuesDe3Reintentos() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(
                Mono.error(new RuntimeException("500 Internal Server Error")),
                Mono.error(new RuntimeException("503 Service Unavailable")),
                Mono.error(new RuntimeException("502 Bad Gateway")),
                Mono.error(new RuntimeException("504 Gateway Timeout"))
            );
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectError(RuntimeException.class)
            .verify();

        verify(authorizationService, times(3)).authorizePayment(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar pago por idempotencia si ya existe operación con mismo número y canal")
    void debeRechazarPagoPorIdempotencia() {
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(true);
        when(paymentRepository.findByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(Optional.of(paymentRequest.withAuthorizationResult("AUTHORIZED")));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "AUTHORIZED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(authorizationService, never()).authorizePayment(any(PaymentRequest.class));
        verify(paymentRepository, never()).save(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe verificar idempotencia por clave antes de procesar")
    void debeVerificarIdempotenciaPorClave() {
        String anotherIdempotencyKey = UUID.randomUUID().toString();
        PaymentRequest duplicateRequest = new PaymentRequest(
            OPERATION_NUMBER,
            CHANNEL,
            new BigDecimal("1000.00"),
            "USD",
            anotherIdempotencyKey,
            LocalDateTime.now(),
            null
        );

        when(paymentRepository.findByIdempotencyKey(UUID.fromString(anotherIdempotencyKey)))
            .thenReturn(Optional.of(paymentRequest.withAuthorizationResult("AUTHORIZED")));

        Mono<PaymentRequest> result = paymentService.processPayment(duplicateRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "AUTHORIZED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(paymentRepository, never()).existsByOperationNumberAndChannel(anyString(), anyString());
    }

    @Test
    @DisplayName("Debe guardar pago con resultado de autorización exitoso")
    void debeGuardarPagoConResultadoExitoso() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(Mono.just("AUTHORIZED"));
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> {
                PaymentRequest saved = invocation.getArgument(0);
                return new PaymentRequest(
                    saved.operationNumber(),
                    saved.channel(),
                    saved.amount(),
                    saved.currency(),
                    saved.idempotencyKey(),
                    saved.requestDate(),
                    saved.authorizationResult()
                );
            });

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> payment.authorizationResult() != null)
            .verifyComplete();

        verify(paymentRepository).save(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe manejar error de autorización con resultado DECLINED")
    void debeManejarErrorAutorizacion() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(Mono.just("DECLINED"));
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "DECLINED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(paymentRepository).save(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe verificar disponibilidad del servicio de autorización antes de procesar")
    void debeVerificarDisponibilidadDelServicio() {
        when(authorizationService.isAvailable())
            .thenReturn(Mono.just(true));
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(Mono.just("AUTHORIZED"));
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextCount(1)
            .verifyComplete();

        verify(authorizationService).isAvailable();
    }

    @Test
    @DisplayName("Debe rechazar cuando el servicio de autorización no está disponible")
    void debeRechazarCuandoServicioNoDisponible() {
        when(authorizationService.isAvailable())
            .thenReturn(Mono.just(false));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("Authorization service unavailable"))
            .verify();

        verify(authorizationService, never()).authorizePayment(any(PaymentRequest.class));
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/adapter/AuthorizationClientTest.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.PaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
@DisplayName("AuthorizationClient - Tests de comunicación con el motor de autorización")
class AuthorizationClientTest {

    @Mock
    private RestTemplate restTemplate;

    private AuthorizationClient authorizationClient;
    private PaymentRequest paymentRequest;
    private static final String AUTHORIZATION_URL = "http://authorization-service:8080/api/authorize";
    private static final String HEALTH_URL = "http://authorization-service:8080/api/health";

    @BeforeEach
    void setUp() {
        authorizationClient = new AuthorizationClient(restTemplate, AUTHORIZATION_URL);
        paymentRequest = new PaymentRequest(
            "OP-2024-001",
            "WEB",
            new BigDecimal("1000.00"),
            "USD",
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            null
        );
    }

    @Test
    @DisplayName("Debe retornar AUTHORIZED cuando el servicio responde 200 OK")
    void debeRetornarAuthorizedCuandoRespuesta200() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("AUTHORIZED", HttpStatus.OK));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectNext("AUTHORIZED")
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar DECLINED cuando el servicio responde 400 Bad Request")
    void debeRetornarDeclinedCuandoRespuesta400() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("DECLINED", HttpStatus.BAD_REQUEST));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectNext("DECLINED")
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el servicio responde 500 Internal Server Error")
    void debeLanzarExcepcionCuandoRespuesta500() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("500"))
            .verify();
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el servicio responde 503 Service Unavailable")
    void debeLanzarExcepcionCuandoRespuesta503() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("Service Unavailable", HttpStatus.SERVICE_UNAVAILABLE));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("503"))
            .verify();
    }

    @Test
    @DisplayName("Debe retornar true cuando el servicio de salud responde 200 OK")
    void debeRetornarTrueCuandoHealth200() {
        when(restTemplate.getForEntity((eq(HEALTH_URL)), eq(String.class)))
            .thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        Mono<Boolean> result = authorizationClient.isAvailable();

        StepVerifier.create(result)
            .expectNext(true)
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar false cuando el servicio de salud responde 503")
    void debeRetornarFalseCuandoHealth503() {
        when(restTemplate.getForEntity(eq(HEALTH_URL), eq(String.class)))
            .thenReturn(new ResponseEntity<>("UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE));

        Mono<Boolean> result = authorizationClient.isAvailable();

        StepVerifier.create(result)
            .expectNext(false)
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el servicio de salud no responde")
    void debeLanzarExcepcionCuandoHealthNoResponde() {
        when(restTemplate.getForEntity(eq(HEALTH_URL), eq(String.class)))
            .thenThrow(new RuntimeException("Connection refused"));

        Mono<Boolean> result = authorizationClient.isAvailable();

        StepVerifier.create(result)
            .expectError(RuntimeException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe mapear correctamente los datos del PaymentRequest al enviar al servicio de autorización")
    void debeMapearDatosCorrectamente() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("AUTHORIZED", HttpStatus.OK));

        PaymentRequest request = new PaymentRequest(
            "OP-TEST-999",
            "MOBILE",
            new BigDecimal("5000.00"),
            "EUR",
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            null
        );

        Mono<String> result = authorizationClient.authorizePayment(request);

        StepVerifier.create(result)
            .expectNext("AUTHORIZED")
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe manejar respuesta con código 401 Unauthorized")
    void debeManejar401Unauthorized() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("401"))
            .verify();
    }

    @Test
    @DisplayName("Debe manejar respuesta con código 403 Forbidden")
    void debeManejar403Forbidden() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("403"))
            .verify();
    }
}
```
