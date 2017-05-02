package tbarn2.uw.edu.maplab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button mapTypeButton = (Button) findViewById(R.id.map_type_button);
        mapTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMapType();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions().position(new LatLng(47.6538063, -122.3077886))
                .title("Drumheller Fountain")
                .snippet("There are some duck boys in there oh boy quack quack"))
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions w = new PolylineOptions()
                .add(new LatLng(47.6540063, -122.3080886))
                .add(new LatLng(47.6535563, -122.3079886))  // North of the previous point, but at the same longitude
                .add(new LatLng(47.6538063, -122.3077886))  // Same latitude, and 30km to the west
                .add(new LatLng(47.6535563, -122.3076886))  // Same longitude, and 16km to the south
                .add(new LatLng(47.6540063, -122.3074886))
                .color(R.color.purple); // Closes the polyline.
        // Get back the mutable Polyline
        Polyline polyline = mMap.addPolyline(w);
    }

    private void changeMapType() {
        Button mapTypeButton = (Button) findViewById(R.id.map_type_button);
        int type = mMap.getMapType();
        if (type == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mapTypeButton.setText("Noraml");
        } else {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mapTypeButton.setText("Satellite");
        }
    }
}
