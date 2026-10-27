# Integración y Configuración de Framework en Aplicación de Pagos

La empresa de pagos en línea requiere la integración de un framework en su aplicación principal para mejorar la gestión de solicitudes, el control de variables de entorno, la generación de logs y la conexión con bases de datos. El framework debe permitir el enrutamiento de solicitudes, el manejo de variables de entorno, la generación de logs detallados y la conexión con una base de datos utilizando un ORM. Los actores involucrados son el 'originador de pagos', el 'motor de autorización' y el 'sistema de liquidación'. La aplicación debe procesar un mínimo de 10 000 transacciones por hora con una latencia máxima de 200 ms y garantizar la idempotencia de las solicitudes de pago por número de operación y canal. En caso de falla del 'motor de autorización' con respuesta 5xx, el sistema debe reintentar la solicitud hasta 3 veces con un intervalo de 5 segundos entre intentos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | sólida experiencia en frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración Inicial del Framework

**Objetivo:** Configurar el framework para enrutar solicitudes y controlar variables de entorno.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configura el framework para enrutar las solicitudes de pago desde el 'originador de pagos' al'motor de autorización'.
- Implementa el control de variables de entorno para ajustar la configuración de la aplicación según el entorno de ejecución (desarrollo, pruebas, producción).

**Entregable:** Configuración del framework que enruta solicitudes y controla variables de entorno.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la estructura de la aplicación y cómo el framework puede integrarse de manera eficiente.
- Piensa en las mejores prácticas para manejar variables de entorno en diferentes entornos.

</details>

### Fase 2: Implementación de Generación de Logs

**Objetivo:** Implementar la generación de logs detallados para rastrear las solicitudes de pago.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementa la generación de logs detallados para cada solicitud de pago, incluyendo el número de operación, el canal, la fecha y hora de la solicitud, y el resultado de la autorización.
- Asegura que los logs sean persistentes y estén disponibles para análisis posterior.

**Entregable:** Implementación de generación de logs detallados para solicitudes de pago.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el formato y la estructura de los logs para asegurar que sean útiles para el análisis y la depuración.
- Piensa en cómo asegurar que los logs sean persistentes y accesibles.

</details>

### Fase 3: Conexión con Base de Datos utilizando ORM

**Objetivo:** Conectar la aplicación con una base de datos utilizando un ORM.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Configura la conexión de la aplicación con una base de datos utilizando un ORM.
- Implementa la persistencia de las solicitudes de pago en la base de datos, incluyendo el número de operación, el canal, la fecha y hora de la solicitud, y el resultado de la autorización.

**Entregable:** Conexión de la aplicación con una base de datos utilizando un ORM y persistencia de solicitudes de pago.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la estructura de la base de datos y cómo el ORM puede mapear los objetos de la aplicación a las tablas de la base de datos.
- Piensa en las mejores prácticas para asegurar que la conexión con la base de datos sea segura y eficiente.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un framework y por qué es importante en el desarrollo de aplicaciones?
- **paraQueSirve**: ¿Para qué sirve la generación de logs en una aplicación de pagos?
- **comoSeUsa**: ¿Cómo se utiliza un ORM para conectar una aplicación con una base de datos?
- **erroresComunes**: ¿Cuáles son los errores comunes al configurar un framework y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de un framework para una aplicación de pagos?

## Criterios de Evaluacion

- Configuración correcta del framework para enrutar solicitudes y controlar variables de entorno.
- Implementación de generación de logs detallados para solicitudes de pago.
- Conexión exitosa de la aplicación con una base de datos utilizando un ORM y persistencia de solicitudes de pago.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
