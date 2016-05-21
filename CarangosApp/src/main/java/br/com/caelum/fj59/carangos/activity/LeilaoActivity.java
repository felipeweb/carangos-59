package br.com.caelum.fj59.carangos.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.modelo.Lance;
import br.com.caelum.fj59.carangos.tasks.BuscaLeiloesTask;
import br.com.caelum.fj59.carangos.tasks.CustomHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LeilaoActivity extends ActionBarActivity {

	private List<Lance> lancesAteMomento = new ArrayList<Lance>();
	private Calendar horarioUltimaBusca = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leilao);
		ListView lancesList = (ListView) findViewById(R.id.lances_list);
		ArrayAdapter<Lance> adapter = new ArrayAdapter<Lance>( LeilaoActivity.this, android.R.layout.simple_list_item_1, lancesAteMomento);
		lancesList.setAdapter(adapter);
		CustomHandler handler = new CustomHandler(adapter, lancesAteMomento);
		new BuscaLeiloesTask(handler, horarioUltimaBusca).executa();
	}
}
