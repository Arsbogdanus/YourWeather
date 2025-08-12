import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class LocationHelper(private val activity: Activity) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

    private val locationRequest = LocationRequest.create().apply {
        priority = Priority.PRIORITY_HIGH_ACCURACY
        numUpdates = 1
        interval = 0
    }

    fun getCityName(onResult: (String) -> Unit) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
            onResult("Произошла ошибка: нет разрешения")
            return
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val location = locationResult.lastLocation
                if (location != null) {
                    try {
                        val geocoder = Geocoder(activity)
                        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        if (!addresses.isNullOrEmpty() && addresses[0].locality != null) {
                            onResult(addresses[0].locality)
                        } else {
                            onResult("Местоположение не найдено")
                        }
                    } catch (e: Exception) {
                        onResult("Ошибка геокодера")
                    }
                } else {
                    onResult("Местоположение не найдено")
                }
                fusedLocationClient.removeLocationUpdates(this)
            }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }
}
