# 🇲🇬 MalaGasy Spoken English — App d'Apprentissage Orale (A2 ➔ B2)

Une application Android moderne développée en **Kotlin** et **Jetpack Compose** (Material 3), conçue pour aider les locuteurs malgaches à maîtriser l'anglais américain parlé grâce au système de révision espacée (SRS), un laboratoire de prononciation vocale et un suivi de progression complet.

---

## 📋 Table des Matières

1. [Prérequis Système & Logiciels](#1-prérequis-système--logiciels)
2. [Récupération du Code Source (GitHub / ZIP)](#2-récupération-du-code-source-github--zip)
3. [Configuration & Installation des Packages (Gradle)](#3-configuration--installation-des-packages-gradle)
4. [Exécution en Local (Émulateur ou Appareil Physique)](#4-exécution-en-local-émulateur-ou-appareil-physique)
5. [Génération & Exportation du Fichier APK](#5-génération--exportation-du-fichier-apk)
   - [Méthode A : APK Debug (Pour tests immédiats)](#méthode-a--apk-debug-pour-tests-immédiats)
   - [Méthode B : APK Release Signé (Pour distribution)](#méthode-b--apk-release-signé-pour-distribution)
6. [Installation du fichier APK sur Téléphone](#6-installation-du-fichier-apk-sur-téléphone)
7. [Dépannage des Erreurs Fréquentes](#7-dépannage-des-erreurs-fréquentes)

---

## 1. 🛠️ Prérequis Système & Logiciels

Avant de pouvoir exécuter le projet en local, installez les outils suivants sur votre ordinateur (Windows, macOS ou Linux) :

1. **Git** : Pour cloner le projet depuis GitHub.
   - Téléchargement : [git-scm.com](https://git-scm.com/downloads)
2. **Android Studio** (Version *Ladybug*, *Jellyfish* ou plus récente) :
   - Téléchargement : [developer.android.com/studio](https://developer.android.com/studio)
   - *Android Studio inclut automatiquement le JDK Java 17 et les outils SDK Android nécessaires.*
3. **Android SDK Manager** (intégré à Android Studio) :
   - Android SDK API Level **34** ou **35**
   - Android SDK Build-Tools **34.0.0+**
   - Android Emulator (si vous testez sur ordinateur)

---

## 2. 📥 Récupération du Code Source (GitHub / ZIP)

Vous pouvez récupérer le projet de deux manières :

### Option A : Via Git & GitHub (Recommandé)
Ouvrez un terminal ou invite de commande sur votre ordinateur et lancez :
```bash
# 1. Cloner le projet depuis votre dépôt GitHub
git clone https://github.com/votre-utilisateur/votre-repo-malagasy-english.git

# 2. Entrer dans le dossier du projet
cd votre-repo-malagasy-english
```

### Option B : Via l'export ZIP de Google AI Studio
1. Dans Google AI Studio, cliquez sur le bouton **Export / Settings** en haut de l'écran.
2. Choisissez **Download ZIP**.
3. Extrayez l'archive ZIP sur votre ordinateur dans un dossier de votre choix (ex: `C:\Projets\MalagasySpokenEnglish`).

---

## 3. ⚙️ Configuration & Installation des Packages (Gradle)

Contrairement aux projets Web (qui utilisent `npm install`), les projets Android utilisent **Gradle** pour gérer et installer automatiquement les dépendances et bibliothèques (Jetpack Compose, Room, KSP, Material 3, Navigation, etc.).

### Procédure d'installation des dépendances :

1. Lancez **Android Studio**.
2. Sur l'écran d'accueil, cliquez sur **Open** (ou *File > Open*).
3. Sélectionnez le dossier racine du projet (là où se trouve le fichier `settings.gradle.kts`).
4. **Synchronisation automatique Gradle** :
   - Dès l'ouverture, Android Studio télécharge automatiquement tous les packages et dépendances spécifiés dans `gradle/libs.versions.toml` et `app/build.gradle.kts`.
   - Vous verrez la barre de progression en bas d'Android Studio : *"Gradle Build Model"* / *"Downloading dependencies"*.
5. **En cas de problème de synchronisation** :
   - Dans le menu supérieur, cliquez sur **File > Sync Project with Gradle Files**.
   - Ou exécutez la commande suivante dans le terminal d'Android Studio :
     ```bash
     # Sur Linux / macOS
     ./gradlew build --refresh-dependencies

     # Sur Windows (Command Prompt / PowerShell)
     gradlew.bat build --refresh-dependencies
     ```

---

## 📱 4. Exécution en Local (Émulateur ou Appareil Physique)

### Option 1 : Tester sur un Émulateur Android (AVD)
1. Dans Android Studio, ouvrez le **Device Manager** (icône de téléphone dans la barre latérale droite).
2. Cliquez sur **Create Virtual Device** (ex: *Pixel 8* avec API 34).
3. Cliquez sur le bouton **Play ▶️** à côté de votre émulateur pour le démarrer.
4. Une fois l'émulateur ouvert, cliquez sur le bouton vert **Run 'app' ▶️** dans la barre d'outils supérieure d'Android Studio (ou raccourci `Shift + F10`).

### Option 2 : Tester sur un Téléphone Android Physique (USB)
1. Sur votre smartphone Android, activez les **Options pour développeurs** (*Paramètres > À propos du téléphone > Taper 7 fois sur "Numéro de build"*).
2. Dans *Paramètres > Options pour développeurs*, activez le **Débogage USB**.
3. Branchez votre téléphone à l'ordinateur via un câble USB.
4. Autorisez le débogage USB sur l'écran du téléphone si demandé.
5. Dans Android Studio, sélectionnez votre téléphone physique dans la liste déroulante des appareils (en haut) et cliquez sur **Run 'app' ▶️**.

---

## 📦 5. Génération & Exportation du Fichier APK

L'exportation du fichier APK vous permet d'installer l'application sur n'importe quel téléphone Android sans passer par Google Play.

### Méthode A : APK Debug (Pour tests immédiats)

#### 1. Via l'interface Android Studio (Facile) :
1. Dans le menu du haut, cliquez sur **Build** > **Build Bundle(s) / APK(s)** > **Build APK(s)**.
2. Android Studio va compiler l'application (cela prend 1 à 2 minutes).
3. Une bulle de notification apparaît en bas à droite : **"Build APK(s): APK(s) generated successfully"**.
4. Cliquez sur le lien bleu **locate** dans cette notification. Cela ouvrira le dossier contenant votre fichier APK.

#### 2. Via le Terminal :
```bash
# Sur Linux / macOS
./gradlew assembleDebug

# Sur Windows
gradlew.bat assembleDebug
```

📁 **Chemin du fichier APK Debug généré :**
```text
<dossier_du_projet>/app/build/outputs/apk/debug/app-debug.apk
```

---

### Méthode B : APK Release Signé (Pour distribution officielle)

Pour produire un fichier APK optimisé, plus léger et signé avec une clé de sécurité :

1. Dans le menu d'Android Studio, allez dans **Build** > **Generate Signed Bundle / APK...**
2. Cochez **APK** puis cliquez sur **Next**.
3. **Configuration du Key Store (Clé de signature)** :
   - Si vous n'avez pas encore de clé : cliquez sur **Create new...**, choisissez un emplacement (ex: `my-release-key.jks`), saisissez un mot de passe et vos informations d'auteur, puis validez.
   - Si vous avez déjà une clé : cliquez sur **Choose existing...** et sélectionnez votre fichier `.jks`.
4. Renseignez :
   - *Key store password*
   - *Key alias*
   - *Key password*
5. Cliquez sur **Next**.
6. Sélectionnez la variante **release**.
7. Cochez les cases de signature si demandées, puis cliquez sur **Create**.

📁 **Chemin du fichier APK Release généré :**
```text
<dossier_du_projet>/app/release/app-release.apk
```

---

## 📲 6. Installation du fichier APK sur Téléphone

Une fois le fichier `app-debug.apk` ou `app-release.apk` généré :

1. **Transférez le fichier APK sur votre téléphone** :
   - Par câble USB, WhatsApp, Telegram, Google Drive, ou e-mail.
2. **Installez l'APK sur Android** :
   - Sur votre téléphone, ouvrez votre gestionnaire de fichiers et appuyez sur le fichier `.apk`.
   - Si Android bloque l'installation, autorisez *"Installation depuis des sources inconnues"* (ou *"Autoriser à partir de cette source"*).
3. **Lancez l'application** :
   - L'icône de l'application **MalaGasy Spoken English** apparaîtra sur votre écran d'accueil !

---

## ❓ 7. Dépannage des Erreurs Fréquentes

### 1. `SDK location not found`
- **Solution** : Créez un fichier nommé `local.properties` à la racine du projet et ajoutez le chemin vers votre SDK Android :
  - Windows : `sdk.dir=C\:\\Users\\VotreNom\\AppData\\Local\\Android\\Sdk`
  - macOS : `sdk.dir=/Users/VotreNom/Library/Android/sdk`
  - Linux : `sdk.dir=/home/VotreNom/Android/Sdk`

### 2. `Unsupported Java Version` ou `KSP incompatibility`
- **Solution** : Vérifiez que vous utilisez Java 17 dans Android Studio (*File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK* -> Sélectionner **JDK 17**).

### 3. Permission du microphone refusée sur l'application
- **Solution** : Le laboratoire de prononciation utilise la reconnaissance vocale. Lors de la première ouverture du Laboratoire de Prononciation, acceptez l'autorisation d'accès au microphone lorsqu'elle est demandée à l'écran.

---

## 🌟 Fonctionnalités Implémentées dans l'App

- 🔄 **Système SRS (Révision Espacée)** : Algorithme d'intervalle pour la mémorisation long terme.
- ❤️ **Système de Favoris** : Bouton cœur interactif avec mise à jour immédiate dans la base de données SQLite/Room.
- 🎙️ **Laboratoire de Prononciation (SpeechRecognizer & TTS)** : Analyse vocale mot par mot et note de précision en %.
- 📊 **Statistiques & Progression** : Graphiques, séries de jours (*streaks*) et suivi des cartes révisées.
