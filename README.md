# WeatherApp 🌤️

Ứng dụng dự báo thời tiết hiện đại được phát triển bằng Android Kotlin với Jetpack Compose, cung cấp thông tin thời tiết chi tiết và dự báo cho các tỉnh thành tại Việt Nam.

## ✨ Tính năng

- 🌡️ **Thời tiết hiện tại**: Hiển thị nhiệt độ, độ ẩm, tốc độ gió và chỉ số chất lượng không khí
- 📅 **Dự báo 7 ngày**: Thông tin thời tiết chi tiết cho tuần tới
- 🏙️ **Tìm kiếm theo tỉnh thành**: Hỗ trợ tìm kiếm thời tiết cho các tỉnh thành Việt Nam
- 🎨 **Giao diện hiện đại**: Thiết kế Material Design 3 với Jetpack Compose
- 🌈 **Hiệu ứng động**: Tích hợp Lottie animations cho trải nghiệm người dùng tốt hơn
- 📱 **Responsive UI**: Giao diện thích ứng với các kích thước màn hình khác nhau

## 🛠️ Công nghệ sử dụng

### Framework & Ngôn ngữ
- **Kotlin** - Ngôn ngữ lập trình chính
- **Jetpack Compose** - Modern UI toolkit for Android
- **Material Design 3** - Design system

### Thư viện chính
- **Retrofit 2.9.0** - HTTP client cho API calls
- **Gson Converter** - JSON serialization/deserialization
- **Coil Compose 2.4.0** - Image loading library
- **Navigation Compose 2.9.2** - Navigation component
- **Lifecycle ViewModel Compose** - State management
- **Lottie Compose 6.3.0** - Animation library
- **ConstraintLayout Compose** - Layout management

### API & Data
- **WeatherAPI** - Nguồn dữ liệu thời tiết
- **Province API** - Dữ liệu tỉnh thành Việt Nam

## 📋 Yêu cầu hệ thống

- **Android API Level**: 24+ (Android 7.0)
- **Target SDK**: 35 (Android 15)
- **Compile SDK**: 35
- **Java Version**: 11
- **Kotlin Version**: 2.0.21

## 🚀 Cài đặt và chạy dự án

### 1. Clone repository
```bash
git clone https://github.com/nguyentuann/weather_app.git
cd WeatherApp
```

### 2. Cấu hình API Key
Tạo file `local.properties` trong thư mục root của project và thêm API key:
```properties
API_KEY=your_weather_api_key_here
```

**Lưu ý**: Bạn có thể đăng ký miễn phí API key tại [WeatherAPI.com](https://www.weatherapi.com/)

### 3. Build và chạy
```bash
./gradlew assembleDebug
```

Hoặc mở project trong Android Studio và nhấn Run.

## 📁 Cấu trúc dự án

```
app/
├── src/main/java/vn/tutorial/weatherapp/
│   ├── data/
│   │   ├── api/              # API services và Retrofit client
│   │   │   ├── WeatherAPIService.kt
│   │   │   ├── ProvinceAPIService.kt
│   │   │   └── RetrofitClient.kt
│   │   └── model/            # Data models
│   │       ├── weather/      # Weather response models
│   │       └── province/     # Province data models
│   ├── ui/
│   │   ├── screen/           # UI screens
│   │   │   ├── MainScreen.kt
│   │   │   ├── WeatherScreen.kt
│   │   │   └── MonthlyForecastScreen.kt
│   │   └── theme/            # UI theme configuration
│   ├── viewmodel/            # ViewModels for state management
│   │   ├── WeatherViewModel.kt
│   │   └── ProvinceViewModel.kt
│   ├── utils/                # Utility classes
│   │   ├── helper/           # Helper functions
│   │   └── constant/         # Constants
│   ├── navigation/           # Navigation setup
│   └── MainActivity.kt       # Main activity
```

## 🔧 Cấu hình Build

Project sử dụng Kotlin DSL cho Gradle build configuration:

- **Android Gradle Plugin**: 8.10.0
- **Kotlin**: 2.0.21
- **Compose Compiler**: Compatible với Kotlin 2.0.21
- **Min SDK**: 24
- **Target SDK**: 35

