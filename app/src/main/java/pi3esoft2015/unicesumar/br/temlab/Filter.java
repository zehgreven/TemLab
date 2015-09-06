package pi3esoft2015.unicesumar.br.temlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Filter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ImageButton bAddFilter = (ImageButton) findViewById(R.id.bAddFilter);
        bAddFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner sCurso = (Spinner) findViewById(R.id.sCurso);
                Spinner sTurno = (Spinner) findViewById(R.id.sTurno);
                Spinner sTurma = (Spinner) findViewById(R.id.sTurma);
                EditText ePeriodo = (EditText) findViewById(R.id.ePeriodo);

                Intent intent = new Intent(Filter.this, Main.class);
                intent.putExtra("curso", sCurso.getSelectedItemId());
                intent.putExtra("turno", sTurno.getSelectedItem().toString());
                intent.putExtra("turma", sTurma.getSelectedItem().toString());
                intent.putExtra("periodo", ePeriodo.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
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
