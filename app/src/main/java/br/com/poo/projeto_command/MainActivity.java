package br.com.poo.projeto_command;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;


import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;

import br.com.poo.projeto_command.domain.Car;
import br.com.poo.projeto_command.domain.ControleRemoto;
import br.com.poo.projeto_command.domain.GarageDoorCloseCommand;
import br.com.poo.projeto_command.domain.GarageDoorOpenCommand;
import br.com.poo.projeto_command.domain.LightOffComand;
import br.com.poo.projeto_command.domain.LightOnComand;
import br.com.poo.projeto_command.domain.MacroCommand;
import br.com.poo.projeto_command.domain.Status;
import br.com.poo.projeto_command.domain.StereoOffCommand;
import br.com.poo.projeto_command.domain.StereoOnCommand;
import br.com.poo.projeto_command.domain.TvOffCommand;
import br.com.poo.projeto_command.domain.TvOnCommand;
import br.com.poo.projeto_command.fragment.CarFragment;
import br.com.poo.projeto_command.interfaces.Command;
import br.com.poo.projeto_command.models.GarageDoor;
import br.com.poo.projeto_command.models.Light;
import br.com.poo.projeto_command.models.Stereo;
import br.com.poo.projeto_command.models.TV;

public class MainActivity extends ActionBarActivity {
    private static String TAG = "LOG";
    private Toolbar mToolbar;
    private Drawer.Result navigationDrawerLeft;
    private int mPositionClicked;

    int slot;
    ControleRemoto controleRemoto;



    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(MainActivity.this, "onCheckedChanged: " + (b ? "true" : "false"), Toast.LENGTH_SHORT).show();
            if (b) {
                controleRemoto.onButtonWasPushed(slot);
            } else {
                controleRemoto.offButtonWasPushed(slot);
            }
//            else if (tipo.equals("Desfazer")){
//                controleRemoto.undoButtonWasPushed();
//            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Controle Remoto");
        setSupportActionBar(mToolbar);

        initialClasses();
        controleRemoto = new ControleRemoto();


        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);*/


//        // FRAGMENT
//        CarFragment frag = (CarFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
//        if(frag == null) {
//            frag = new CarFragment();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
//            ft.commit();
//        }

        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(true)
                    /*.withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                        @Override
                        public boolean onNavigationClickListener(View view) {
                            return false;
                        }
                    })*/
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        for (int count = 0, tam = navigationDrawerLeft.getDrawerItems().size(); count < tam; count++) {
                            if (count == mPositionClicked && mPositionClicked <= 3) {
                                PrimaryDrawerItem aux = (PrimaryDrawerItem) navigationDrawerLeft.getDrawerItems().get(count);
                                aux.setIcon(getResources().getDrawable( getCorretcDrawerIcon( count, false ) ));
                                break;
                            }
                        }

                        if(i <= 3){
                            ((PrimaryDrawerItem) iDrawerItem).setIcon(getResources().getDrawable( getCorretcDrawerIcon( i, true ) ));
                        }

                        mPositionClicked = i;
                        navigationDrawerLeft.getAdapter().notifyDataSetChanged();
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(MainActivity.this, "onItemLongClick: " + i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        navigationDrawerLeft.addItem(new SectionDrawerItem().withName("Controle Remoto"));
        navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("Luz").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
        navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("Garagem").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
        navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("Radio").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
        navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("TV").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
        navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("Modo Festa").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
        navigationDrawerLeft.addItem( new SectionDrawerItem().withName("Tarefas"));
        navigationDrawerLeft.addItem( new SectionDrawerItem().withName("Adicionar Tarefa"));
    }


    private int getCorretcDrawerIcon(int position, boolean isSelecetd){
        switch(position){
            case 0:
                return( isSelecetd ? R.drawable.car_selected_1 : R.drawable.car_1 );
            case 1:
                return( isSelecetd ? R.drawable.car_selected_2 : R.drawable.car_2 );
            case 2:
                return( isSelecetd ? R.drawable.car_selected_3 : R.drawable.car_3 );
            case 3:
                return( isSelecetd ? R.drawable.car_selected_4 : R.drawable.car_4 );
        }
        return(0);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if(id == R.id.action_second_activity){
//            startActivity(new Intent(this, SecondActivity.class));
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



    public List<Car> getSetCarList(int qtd){
        String[] models = new String[]{"Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        String[] brands = new String[]{"Lamborghini", " bugatti", "Chevrolet", "Pagani", "Porsche", "BMW", "Aston Martin", "Ford", "Chevrolet", "Cadillac"};
        int[] photos = new int[]{R.drawable.gallardo, R.drawable.vyron, R.drawable.corvette, R.drawable.paganni_zonda, R.drawable.porsche_911, R.drawable.bmw_720, R.drawable.db77, R.drawable.mustang, R.drawable.camaro, R.drawable.ct6};
        List<Car> listAux = new ArrayList<>();

        for(int i = 0; i < qtd; i++){
            Car c = new Car( models[i % models.length], brands[ i % brands.length ], photos[i % models.length] );
            listAux.add(c);
        }
        return(listAux);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = navigationDrawerLeft.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if(navigationDrawerLeft.isDrawerOpen()){
            navigationDrawerLeft.closeDrawer();
        }
        else{
            super.onBackPressed();
        }
    }

    private void initialClasses(){
        Status s = Status.getInstance();

        s.addMensagem("Iniciando Sistema\n");

        ControleRemoto remote = new ControleRemoto();

        Light light = new Light();
        GarageDoor garage = new GarageDoor();
        Stereo stereo = new Stereo();
        TV tv = new TV();

        LightOnComand lightOn = new LightOnComand(light);
        LightOffComand lightOff = new LightOffComand(light);

        GarageDoorOpenCommand garageDoorOpen = new GarageDoorOpenCommand(garage);
        GarageDoorCloseCommand garageDoorClose = new GarageDoorCloseCommand(garage);

        StereoOnCommand stereoOn = new StereoOnCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);

        TvOnCommand tvOn = new TvOnCommand(tv);
        TvOffCommand tvOff = new TvOffCommand(tv);

        Command[] partyOn = {lightOff, garageDoorClose, stereoOn, tvOn};
        Command[] partyOff = {lightOn, garageDoorOpen, stereoOff, tvOff};

        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);

        remote.setCommand(0, lightOn, lightOff, "Luz");
        remote.setCommand(1, garageDoorOpen, garageDoorClose, "Garagem");
        remote.setCommand(2, stereoOn, stereoOff, "Radio");
        remote.setCommand(3, tvOn, tvOff, "TV");
        remote.setCommand(4, partyOnMacro, partyOffMacro, "Modo Festa");
    }
}

