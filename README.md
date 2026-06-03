# 📚 Kütüphane Yönetim Sistemi (Library Management System)

Bu proje, Spring Boot (Backend) ve HTML5/Tailwind CSS (Frontend) kullanılarak geliştirilmiş, Basic Authentication güvenlik altyapısına sahip bir **Kütüphane Yönetim Sistemi** otomasyonudur.

## 🚀 Proje Yapısı ve Özellikler

- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA, H2 Database (Bellek içi veritabanı).
- **Frontend:** Tek sayfa (`index.html`), Tailwind CSS (Açık kaynaklı CDN).
- **Güvenlik (Maliye & Rol Yönetimi):** `STATELESS` (Oturumsuz) yapıda Basic Auth kullanılmıştır. Sistemde iki farklı rol bulunur:
  - **Öğrenci (`USER`):** Kitapları listeler ve görüntüleyebilir. (Kullanıcı adı: `ogrenci` / Şifre: `1234`)
  - **Kütüphaneci (`ADMIN`):** Kitap ekleme, silme, güncelleme ve listeleme yetkilerine sahiptir. (Kullanıcı adı: `kutuphaneci` / Şifre: `admin123`)

---

## 🛠️ Kurulum ve Çalıştırma

### 1. Backend Çalıştırılması
1. Projeyi **IntelliJ IDEA** ile açın.
2. `src/main/java/.../LibraryManagementSystemApplication.java` (Ana Sınıf) dosyasını bulun ve projeyi **Run** edin.
3. Sunucu varsayılan olarak `http://localhost:8080` portunda ayağa kalkacaktır.

### 2. Frontend Çalıştırılması
1. `index.html` dosyasını tarayıcınızda açın (Çift tıklayarak ya da bir canlı sunucu/Live Server üzerinden).
2. Giriş panelinde rolünüze göre bilgileri girerek **Sisteme Bağlan** butonuna basın:
   - Kitap eklemek/silmek için: `kutuphaneci`
   - Sadece listelemek için: `ogrenci`

---

## 🔌 API Uç Noktaları (EndPoints)

| Metot | URL | Yetki | Açıklama |
| :--- | :--- | :--- | :--- |
| **GET** | `/books` | `USER`, `ADMIN` | Tüm kitapları listeler. |
| **GET** | `/books/{id}` | `USER`, `ADMIN` | ID'ye göre kitap getirir. |
| **POST** | `/books` | `ADMIN` | Yeni kitap ekler (JSON gövdesi alır). |
| **PUT** | `/books/{id}` | `ADMIN` | Kitap bilgilerini günceller. |
| **DELETE**| `/books/{id}` | `ADMIN` | Kitap kaydını siler. |

---

## 🔒 CORS ve Güvenlik Notu
Frontend ve Backend'in farklı portlardan veya yerel dosya sisteminden (`file://`) birbiriyle sorunsuz haberleşebilmesi için `SecurityConfig.java` üzerinde global **CORS (Cross-Origin Resource Sharing)** izinleri ve `OPTIONS` ön kontrol isteklerine yönelik geçişler tanımlanmıştır.
