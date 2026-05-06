# One Stop Shop — CS2430 Mobile Computing
### Exercise 1 | Kotlin Jetpack Compose | Lakehead University

---

## Student Information
- **Name:** Harika Ravi
- **Student Number:** 1332239
- **Course:** CS2430 MOBILE COMPUTING
- **Instructor:** DR. SABAH MOHAMMED

---

## App Overview
One Stop Shop is a high-end retail shopping application built with
Kotlin and Jetpack Compose. The app features 15 fashion products
across men's and women's categories, a full shopping cart with
quantity controls, and a complete order summary with 13% tax
calculation. The UI is inspired by premium retail brands with a
minimal, editorial black-and-white design with gold accents.

---

## Project Structure

```
OneStopShop/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── res/
│       │   ├── values/
│       │   │   ├── strings.xml
│       │   │   └── themes.xml
│       │   └── values-night/
│       │       └── themes.xml
│       └── java/com/example/onestopshop/
│           ├── MainActivity.kt
│           ├── Navigation.kt
│           ├── data/
│           │   ├── Product.kt
│           │   └── ProductRepository.kt
│           └── ui/
│               ├── theme/
│               │   ├── Color.kt
│               │   ├── Type.kt
│               │   └── Theme.kt
│               ├── screens/
│               │   ├── SplashScreen.kt
│               │   ├── ProductListScreen.kt
│               │   └── CartScreen.kt
│               └── viewmodel/
│                   └── CartViewModel.kt
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```
---

## Key Dependencies

| Library | Version | Purpose |
|---|---|---|
| Kotlin | 2.1.0 | Programming language |
| Jetpack Compose BOM | 2024.12.01 | All Compose UI libraries |
| Material3 | via BOM | UI components and theming |
| Navigation Compose | 2.8.5 | 3-screen navigation |
| ViewModel Compose | 2.8.7 | Cart state management |
| Coil Compose | 2.7.0 | Async product image loading |
| Material Icons Extended | via BOM | Shopping bag, heart, etc. |

---

## How to Configure and Run

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17 (bundled with Android Studio)
- Android SDK 36
- Pixel 10 Pro Virtual Device (API 35 and RAM 8)

### Steps to Run
1. Clone or download the project
2. Open Android Studio → File → Open → select the OneStopShop folder
3. Wait for Gradle sync to complete
4. Select Pixel 10 Pro from the device dropdown (i used virtual device)
5. Click the green Run button or press Shift + F10
6. App launches on the emulator automatically

---

## App Features

### Screen 1 — Splash Scree (Home Page)
- Animated dark editorial background with gold radial glows
- Brand name "ONE STOP SHOP" with animated entrance
- Gold divider line and tagline
- Category pills: WOMEN, MEN, NEW IN
- Two CTA buttons: EXPLORE COLLECTION and VIEW ALL ITEMS
- Both buttons navigate to the product listing screen

### Screen 2 — Product Listing
- Filter tabs: ALL, WOMEN, MEN, NEW, SALE
- 2-column lazy grid showing 15 products
- Each card shows: product image, gender tag, name, description, price
- Sale items show original price with strikethrough in red
- Badges: NEW, SALE, BESTSELLER, EXCLUSIVE
- Live cart badge showing item count on shopping bag icon

### Screen 3 — Shopping Cart
- Full list of added items with thumbnail and name
- Quantity stepper (+ / -) per item
- Per-item line total
- Remove individual items with X button
- CLEAR ALL button
- Order summary showing:
  - Subtotal
  - Estimated Shipping (FREE)
  - Tax at 13%
  - Order Total
- PROCEED TO CHECKOUT button
- Order confirmation dialog on checkout
- Empty cart state with Continue Shopping button

---

## Architecture
- **Pattern:** MVVM (Model-View-ViewModel)
- **State:** StateFlow with collectAsState()
- **Navigation:** Navigation Compose with 3 destinations
- **Data:** In-memory product list (no database required)
- **Single Activity:** MainActivity hosts all Compose UI

---

## Screenshots
<img width="1919" height="1019" alt="Screenshot 2026-05-06 184957" src="https://github.com/user-attachments/assets/1ff74369-c479-4a29-a836-d6cedc647b89" />
<img width="1919" height="1003" alt="Screenshot 2026-05-06 185520" src="https://github.com/user-attachments/assets/92f89cce-45d5-4814-91d6-72c9593cbf27" />
<img width="1919" height="1020" alt="Screenshot 2026-05-06 185739" src="https://github.com/user-attachments/assets/616d5f59-bcfe-417a-a0e4-fc6e91e0a6a3" />
<img width="1919" height="1018" alt="Screenshot 2026-05-06 185754" src="https://github.com/user-attachments/assets/c4ec7b74-1813-41d9-8e97-defab26608b6" />



