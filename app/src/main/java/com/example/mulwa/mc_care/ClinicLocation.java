package com.example.mulwa.mc_care;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mulwa.mc_care.Adapters.DividerItemDecoration;
import com.example.mulwa.mc_care.Adapters.FacilityAdapter;
import com.example.mulwa.mc_care.Pojo.Clinics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class ClinicLocation extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleClient;
    private double mlatitude, mlongitude;
    private LocationRequest mlocationRequest;
    private Toolbar toolbar;
    private int Zoom =15;
    ArrayList<Clinics> clinic = new ArrayList<>();
    private HashMap<Marker, Clinics> mMarkersHashMap;
    private MapStyleOptions style;

    private View view;
    private BottomSheetBehavior bottomSheetBehavior;
    private RecyclerView MrecyclerView;
    private FacilityAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_location);
        buildGoogleAPIClient();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMarkersHashMap = new HashMap<Marker, Clinics>();

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Clinics Near you");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        view = findViewById(R.id.comment_buttom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(view);
        bottomSheetBehavior.setPeekHeight(100);
        MrecyclerView = (RecyclerView) findViewById(R.id.facility_list);
        MrecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        MrecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL ));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleClient.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
       LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleClient, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // Sets the retro style via raw resource JSON.
        style = MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle_retro);
        getClinics();
        mMap.setMapStyle(style);

        LatLng area = new LatLng(-1.093085, 37.018233);
        Log.d("latitude", "this lati"+String.valueOf(mlatitude));

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location);
        mMap.addMarker(new MarkerOptions().position(area).title("mylocation").icon(icon));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(area));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(area, Zoom));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);

       //Try to obtain the map from the SupportMapFragment.

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        fetchCurrentLocation();

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getApplicationContext(),"connection  suspended",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(),"connection failed",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLocationChanged(Location location) {
        mlatitude = location.getLatitude();
        mlongitude = location.getLongitude();

        Toast.makeText(getApplicationContext(),"current location"+location.getLatitude(),Toast.LENGTH_LONG).show();
        Log.d("location","this Onlocationchanged"+location.getLatitude());



    }
    protected synchronized void buildGoogleAPIClient() {
        mGoogleClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
//        mGoogleClient.connect();

    }
    public void fetchCurrentLocation(){
        mlocationRequest = LocationRequest.create();
        mlocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mlocationRequest.setInterval(2000);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleClient, mlocationRequest,this );


    }
    private void getClinics(){

        clinic.add(new Clinics("Jommo Kenyata Hospital","discription about the hospital","private","0707-200-3014",-1.102324, 37.013546));
        clinic.add(new Clinics("Mater Hospital","description","private","0707200315",-1.101916, 37.014372));
        clinic.add(new Clinics("Juja Hospital","description","public","0707200314",-1.089119, 37.021311));
        clinic.add(new Clinics("Thika Hospital","Description","public","0707200314",-1.089119, 37.021311));
        clinic.add(new Clinics("Thika level 5 hospital","Description","public","0707200314",-1.041614,37.0769057));
        clinic.add(new Clinics("Ruiru sub-district hospital","Description","public"," 0726845142",-1.1445471,36.9536907));
        clinic.add(new Clinics("Mt sinai hospital","Description","private"," 0726845142",-1.0521784,37.0958498));

        clinic.add(new Clinics("Getrudes childrens hospital","Description","private","0207206000",-1.2556832,36.8296996));
        clinic.add(new Clinics(" Guru nanak hospital","Description","private","0207206000",-1.2695758,36.8301559));

        clinic.add(new Clinics("Aga khan hospital","Description","private","0207206000",-1.2616306,36.8221593));
        clinic.add(new Clinics("Mama lucy kibaki hospital","Description","private","0207206000",-1.2739894,36.8964713));

        clinic.add(new Clinics("Aga khan hospital","Description","private","0207206000",-1.2616306,36.8221593));
        clinic.add(new Clinics("Gatundu district hospital","Description","private","0207206000",-1.0146951,36.9050489));
        clinic.add(new Clinics("Kenyatta national hospital","Description","private","0207206000",-1.3021863,36.8043931));

        setMarkers(clinic);
        setRecyclerView(clinic,getApplicationContext());

    }
    private void setMarkers(ArrayList<Clinics> clinicMarker){
        for (int i=0; i< clinicMarker.size(); i++){
            BitmapDescriptor hospital_icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_public_hospital); ;

            if(clinicMarker.get(i).getClinicType() == "private"){
                hospital_icon  = BitmapDescriptorFactory.fromResource(R.drawable.ic_private_hospital);

            }
            LatLng clinicArea = new LatLng(clinicMarker.get(i).getLatitude(),clinicMarker.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(clinicArea).title(clinicMarker.get(i).getClinicName()).icon(hospital_icon));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clinicArea,Zoom));

            // Create user marker with custom icon and other options
//

        }
    }
    private void setRecyclerView(ArrayList<Clinics> facilities, Context context){
        adapter = new FacilityAdapter(facilities,context);
        MrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }



}
