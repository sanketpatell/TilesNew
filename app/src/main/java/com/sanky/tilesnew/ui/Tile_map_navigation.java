package com.sanky.tilesnew.ui;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.sanky.tilesnew.R;
import com.sanky.tilesnew.tiles.TileViewActivity;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;

import java.util.List;

public class Tile_map_navigation extends TileViewActivity {
    private static boolean isRouteShowed = false;
    private static boolean isCompassSupported = true;
    private TileView tileView;
    private ImageButton changeFromToView;
    private ImageButton clearFromToRouteView;
    private ImageButton twoDimensionalCodeView;

    private AutoCompleteTextView editFrom;
    private AutoCompleteTextView editTo;
    private ImageButton editFromClear;
    private ImageButton editToClear;
    private CompoundButton compassToggle;


    Intent intent;

    private static final String FROM_POI = "fromPoi";
    private static final String TO_POI = "toPoi";
    private static final String FROM_POI_ID = "fromPoiId";
    private static final String TO_POI_ID = "toPoiId";
    private static final String FROM_POI_NODE = "fromPoiNode";
    private static final String TO_POI_NODE = "toPoiNode";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_map_navigation);

        tileView = (TileView) findViewById(R.id.sankettile);

        twoDimensionalCodeView = (ImageButton) findViewById(R.id.two_dimensional_code_view);
        twoDimensionalCodeView.setOnClickListener(this);

        changeFromToView = (ImageButton) findViewById(R.id.change_from_to_view);
        changeFromToView.setOnClickListener(this);

        clearFromToRouteView = (ImageButton) findViewById(R.id.clear_from_to_route);
        clearFromToRouteView.setOnClickListener(this);


        compassToggle = (ToggleButton) findViewById(R.id.venue_compass_button);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> magneticSensorList = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        List<Sensor> accelerationSensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        //不支持指南针功能
        //if(0 == magneticSensorList.size() || 0 == accelerationSensorList.size()){
        isCompassSupported = false;
        compassToggle.setVisibility(View.GONE);
        //}
        //else{
        //	isCompassSupported = true;
        //	compassToggle.setVisibility(View.VISIBLE);
        //    compassToggle.setOnCheckedChangeListener(onToggleCompass);
        //}


        //tileView.stgetTileView();

        // multiple references
        // TileView tileView = getTileView();

        // size of original image at 100% mScale
        //  tileView.setSize( 2736, 2880 );
        tileView.setSize(8192, 6186);
        // tileView.setSize( 4015, 4057 );
        // small map, let's let it resize to 200%
        tileView.setScaleLimits(0, 1);


        // we're running from assets, should be fairly fast decodes, go ahead and render asap
        tileView.setShouldRenderWhilePanning(true);
        // detail levels
        tileView.addDetailLevel(1.000f, "tiles/qbengo/1000/0%d_0%d.jpg");
        tileView.addDetailLevel(0.500f, "tiles/qbengo/500/0%d_0%d.jpg");
        tileView.addDetailLevel(0.250f, "tiles/qbengo/250/%d_%d.jpg");
        tileView.addDetailLevel(0.125f, "tiles/qbengo/125/%d_%d.jpg");

        // let's use 0-1 positioning...
        tileView.defineBounds(0, 0, 1, 1);

     /*   // center markers along both axes
        tileView.setMarkerAnchorPoints( -0.5f, -0.5f );

        // add a marker listener
        tileView.setMarkerTapListener( mMarkerTapListener );

        // add some pins...
        addPin( 0.25, 0.25 );
        addPin( 0.25, 0.75 );
        addPin( 0.75, 0.25 );
        addPin( 0.75, 0.75 );
        addPin( 0.50, 0.50 );

        // mScale it down to manageable size
        tileView.setScale( 0.5f );

        // center the frame
        frameTo( 0.5, 0.5 );

    }

    private void addPin( double x, double y ) {
        ImageView imageView = new ImageView( this );
        imageView.setImageResource( R.drawable.push_pin );
        getTileView().addMarker( imageView, x, y, null, null );
    }

    private MarkerLayout.MarkerTapListener mMarkerTapListener = new MarkerLayout.MarkerTapListener() {
        @Override
        public void onMarkerTap( View v, int x, int y ) {
            Toast.makeText( getApplicationContext(), "You tapped a pin", Toast.LENGTH_LONG ).show();
        }
    };*/

        @Override
        protected void onNewIntent (Intent intent){
            super.onNewIntent(intent);
            this.intent = intent;

            //TODO 搜索POI处理
        }
    private void onMapChanged(Intent intent) {
        if (intent.hasExtra(FROM_POI) && editFrom != null) {
            from = (Suggest) intent.getSerializableExtra(FROM_POI);
            editFrom.setText(from.getTitle());
        }
        if (intent.hasExtra(TO_POI) && editTo != null) {
            to = (Suggest) intent.getSerializableExtra(TO_POI);
            editTo.setText(to.getTitle());
        }

        final boolean compassAllowed = (maps != null && maps.getCount() == 1);//true
        mapFragment.setCompassEnabled(isCompassSupported && compassAllowed && compassToggle.isChecked());
        compassToggle.setVisibility(isCompassSupported && compassAllowed ? View.VISIBLE : View.GONE);

        long fromId = intent.getLongExtra(FROM_POI_ID, 0);
        if (fromId > 0) {
            long toId = intent.getLongExtra(TO_POI_ID, 0);
            long fromNode = intent.getLongExtra(FROM_POI_NODE, 0);
            long toNode = intent.getLongExtra(TO_POI_NODE, 0);
            mapFragment.setInitNode(fromId, toId, fromNode, toNode);
            isRouteShowed = true;
            editFrom.setFocusable(false);
            editTo.setFocusable(false);
            editFrom.setFocusableInTouchMode(false);
            editTo.setFocusableInTouchMode(false);
            editFromClear.setVisibility(View.GONE);
            editToClear.setVisibility(View.GONE);
            twoDimensionalCodeView.setVisibility(View.GONE);
        }
        if(intent.hasExtra(POIFields.ID)){
            Uri uri = Uri.withAppendedPath(intent.getData(), "poi").buildUpon()
                    .appendPath(intent.getIntExtra(POIFields.ID, 0) + "").build();
            mapFragment.setPOIUri(uri, (long) intent.getIntExtra(POIFields.ID, 0));
        }
    }

    }
}
