package com.sanky.tilesnew.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sanky.tilesnew.R;
import com.sanky.tilesnew.tiles.TileViewActivity;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;

public class Tile_map_navigation extends TileViewActivity {

    private TileView tileView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_map_navigation);

        tileView = (TileView)findViewById(R.id.sankettile);

        //tileView.stgetTileView();

        // multiple references
       // TileView tileView = getTileView();

        // size of original image at 100% mScale
       //  tileView.setSize( 2736, 2880 );
        tileView.setSize( 8192, 6186 );
       // tileView.setSize( 4015, 4057 );
        // small map, let's let it resize to 200%
        tileView.setScaleLimits( 0, 2 );


        // we're running from assets, should be fairly fast decodes, go ahead and render asap
        tileView.setShouldRenderWhilePanning( true );
        // detail levels
        tileView.addDetailLevel( 1.000f, "tiles/qbengo/1000/%d_%d.jpg");
        tileView.addDetailLevel( 0.500f, "tiles/qbengo/500/%d_%d.jpg");
        tileView.addDetailLevel( 0.250f, "tiles/qbengo/250/%d_%d.jpg");
        tileView.addDetailLevel( 0.125f, "tiles/qbengo/125/%d_%d.jpg");

        // let's use 0-1 positioning...
        tileView.defineBounds( 0, 0, 1, 1 );

        // center markers along both axes
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
    };

}
