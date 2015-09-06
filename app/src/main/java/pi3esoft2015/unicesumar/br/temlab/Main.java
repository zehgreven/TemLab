package pi3esoft2015.unicesumar.br.temlab;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pi3esoft2015.unicesumar.br.temlab.informatica.GETTER;
import pi3esoft2015.unicesumar.br.temlab.informatica.builder.ScheduleBuilder;
import pi3esoft2015.unicesumar.br.temlab.informatica.enums.Page;
import pi3esoft2015.unicesumar.br.temlab.informatica.enums.Period;

public class Main extends AppCompatActivity {

    private List<String> rawList;
    private ArrayList<String> freshList;
    Main _this = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tAcronimo = (TextView) findViewById(R.id.tAcronimo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String[] auxiliar = getResources().getStringArray(R.array.acronimo);
            //auxiliar[extras.getInt("curso")].toString()+
            final String acronimo = "esoft" + extras.getString("periodo")+"-"+
                                    extras.getString("turno").substring(0,1)+"-"+
                                    extras.getString("turma");
            tAcronimo.setText(acronimo);
        }

        ImageButton bOpenFilter = (ImageButton) findViewById(R.id.bOpenFilter);
        bOpenFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Filter.class);
                startActivity(intent);
            }
        });

        try {
            Boolean Resultado = new asyncResults().execute(Period.night).get();

            freshList = new ScheduleBuilder()
                    .build(rawList)
                    .search(tAcronimo.getText().toString());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(_this, android.R.layout.simple_list_item_1, freshList);
            ListView listView = (ListView) findViewById(R.id.listCurse);
            listView.setAdapter(arrayAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class asyncResults extends AsyncTask<Period, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Period... params) {
            try {
                rawList = new GETTER()
                        .from(Page.informatica)
                        .period(new Date(), params[0])
                        .get()
                        .asList();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
