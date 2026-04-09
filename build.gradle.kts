// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
  //  alias(libs.plugins.android.application) apply false
//}
plugins {
    // Usamos 'alias' para que use la versión que ya tiene IntelliJ configurada
    alias(libs.plugins.android.application) apply false
    // El plugin de Google Services
    id("com.google.gms.google-services") version "4.4.1" apply false
}