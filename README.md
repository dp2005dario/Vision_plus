# VisionPlus 👁️🔊

**ES** · [English below](#english)

VisionPlus es una aplicación móvil Android pensada para **personas con cierta discapacidad visual**. Permite **escanear productos** con la cámara y escuchar la información en voz alta, con **voces TTS personalizables**. Además incluye una **moneda virtual acumulable (prismas)** que se gana usando la app y se gasta en una **tienda de personalizaciones**.

> Estado: primera versión funcional de un proyecto más ambicioso: escanear productos en tiendas físicas.

## Qué hace

- 📷 **Escaneo de productos** con la cámara.
- 🔊 **Lectura en voz alta** de la información del producto.
- 🎙️ **Voces TTS personalizables** (API de ElevenLabs).
- 💎 **Moneda virtual (prismas)** que se acumula con el uso.
- 🛍️ **Tienda** para personalizar la experiencia.
- 🕘 **Escaneados recientes** para volver a consultar productos.

## Tecnologías

Java · Android Studio · Firebase · ElevenLabs API · Gradle (Kotlin DSL)

## Cómo ejecutarlo

1. Clona el repositorio:
   ```bash
   git clone https://github.com/dp2005dario/Vision_plus.git
   ```
2. Ábrelo con **Android Studio**.
3. **Configura tus propias claves** (no están incluidas en el repositorio):
   - Crea tu proyecto en [Firebase](https://console.firebase.google.com/) y coloca tu `google-services.json` en `app/`.
   - Consigue una clave de [ElevenLabs](https://elevenlabs.io/) y añádela en tu `local.properties` (archivo que no se sube a Git).
4. Sincroniza Gradle y ejecuta la app en un emulador o en un móvil Android.

## Autor

**Darío Pino Jiménez** · [GitHub](https://github.com/dp2005dario) · [LinkedIn](https://www.linkedin.com/in/dar%C3%ADo-pino-0136361b3/)

---

## English

VisionPlus is an Android mobile app designed for **people with some visual impairment**. It lets you **scan products** with the camera and hear the information read aloud, with **customizable TTS voices**. It also includes an **earnable virtual currency (prisms)** that you collect by using the app and spend in a **customization store**.

> Status: first working version of a more ambitious project: scanning products in physical stores.

### Features

- 📷 **Product scanning** with the camera.
- 🔊 **Read-aloud** product information.
- 🎙️ **Customizable TTS voices** (ElevenLabs API).
- 💎 **Virtual currency (prisms)** earned through use.
- 🛍️ **Store** to personalize the experience.
- 🕘 **Recent scans** to look products up again.

### Tech stack

Java · Android Studio · Firebase · ElevenLabs API · Gradle (Kotlin DSL)

### How to run

1. Clone the repository:
   ```bash
   git clone https://github.com/dp2005dario/Vision_plus.git
   ```
2. Open it with **Android Studio**.
3. **Set up your own keys** (they are not included in the repository):
   - Create your project in [Firebase](https://console.firebase.google.com/) and put your `google-services.json` in `app/`.
   - Get an [ElevenLabs](https://elevenlabs.io/) key and add it to your `local.properties` (not tracked by Git).
4. Sync Gradle and run the app on an emulator or an Android device.

### Author

**Darío Pino Jiménez** · [GitHub](https://github.com/dp2005dario) · [LinkedIn](https://www.linkedin.com/in/dar%C3%ADo-pino-0136361b3/)
