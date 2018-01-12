package com.example.navigationdrawerfragments;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentTwo.OnFragmentInteractionListener, FragmentOne.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private DrawerLayout drawL_base;
    private NavigationView navigationView;

    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Mostrando botón de home
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Encontrando drawer, se abre como START
        drawL_base = (DrawerLayout) findViewById(R.id.drawL_base);

        //
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        setupNavigationView();
    }

    // Método que inicializa el Listener para NavigationItemSelected y en base al item seleccionado
    // se realizan diferentes acciones
    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_books:
                        pulsandoItem("libros");
                        lanzarFragOne();
                        break;
                    case R.id.action_items:
                        pulsandoItem("items");
                        lanzarFragTwo();
                        break;
                    case R.id.action_help:
                        pulsandoItem("ayuda");
                        break;
                    case R.id.action_settings:
                        pulsandoItem("configuración");
                        break;
                }
                // Mantenemos opción seleccionada y cambiamos title de toolbar
                item.setChecked(true);
                getSupportActionBar().setTitle(item.getTitle());

                // Cualquier opción seleccionada provoca el cierre del Drawer
                drawL_base.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Se ha pulsado HOME", Toast.LENGTH_SHORT).show();
                drawL_base.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    private void pulsandoItem(String itemSeleccionado) {
        Toast.makeText(this, "Se ha pulsado " + itemSeleccionado, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // Si drawL_base es visible, cerramos. Si no, ejecutamos el código suppah
        if (drawL_base.isDrawerOpen(GravityCompat.START))
            drawL_base.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void lanzarFragOne() {
        FragmentManager fM = getSupportFragmentManager();
        FragmentTransaction fT;

        fragmentOne = (FragmentOne) fM.findFragmentByTag(FragmentOne.TAG);

        if (fragmentOne == null)
        {
            fragmentOne = FragmentOne.newInstance("1", "2");

            fT = fM.beginTransaction();
            fT.replace(R.id.flContent, fragmentOne, FragmentOne.TAG);
            fT.addToBackStack(null);
            fT.commit();
        }
    }

    private void lanzarFragTwo() {
        FragmentManager fM = getSupportFragmentManager();
        FragmentTransaction fT;

        fragmentTwo = (FragmentTwo) fM.findFragmentByTag(FragmentTwo.TAG);

        if (fragmentTwo == null)
        {
            fragmentTwo = FragmentTwo.newInstance("1", "2");

            fT = fM.beginTransaction();
            fT.replace(R.id.flContent, fragmentTwo, FragmentTwo.TAG);
            fT.addToBackStack(null);
            fT.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
