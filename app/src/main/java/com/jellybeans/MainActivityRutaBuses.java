package com.jellybeans;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityRutaBuses extends AppCompatActivity {
    GridView gridview;
    String[] busesNames = {"Bus Plaza Avaroa","Bus Alto Obrajes","Bus Irpavi","Bus Cota Cota",
            "Bus Achumani","Bus Miraflores"};
    int[] busesImagenes = {R.drawable.busavaroa,R.drawable.busaltoobrajes,R.drawable.busirpavi,
            R.drawable.buscotacota,R.drawable.busachumani,R.drawable.busmiraflores};
    int[] horarioBuses = {R.drawable.horariobusavaroa,R.drawable.horariobusaltoobrajes,R.drawable.horariobusirpavi,
            R.drawable.horariobuscotacota,R.drawable.horariobusachumani,R.drawable.horariobusmiraflores};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        gridview = findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridview.setAdapter(customAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",busesNames[i]);
                intent.putExtra("image",horarioBuses[i]);
                startActivity(intent);

            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return busesImagenes.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewl = getLayoutInflater().inflate(R.layout.row_data,null);

            TextView name = viewl.findViewById(R.id.fruits);
            ImageView image = viewl.findViewById(R.id.images);

            name.setText(busesNames[i]);
            image.setImageResource(busesImagenes[i]);
            return viewl;
        }
    }
}
