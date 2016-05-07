package br.com.caelum.fj59.carangos.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.adapter.PublicacaoAdapter;
import br.com.caelum.fj59.carangos.app.CarangosApplication;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.navegacao.EstadoMainActivity;
import br.com.caelum.fj59.carangos.tasks.BuscaMaisPublicacoesDelegate;
import br.com.caelum.fj59.carangos.tasks.BuscaMaisPublicacoesTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BuscaMaisPublicacoesDelegate {
	private List<Publicacao> publicacoes;
	private EstadoMainActivity estado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.publicacoes = new ArrayList<Publicacao>();
		this.estado = EstadoMainActivity.INICIO;
		this.estado.executa(this);
	}

	public List<Publicacao> getPublicacoes() {
		return this.publicacoes;
	}

	@Override
	public void lidaComRetorno(List<Publicacao> resultado) {
		this.publicacoes.clear();
		this.publicacoes.addAll(resultado);
		this.estado = EstadoMainActivity.PRIMEIRAS_PUBLICACOES_RECEBIDAS;
		this.estado.executa(this);
		MyLog.i(this.estado);
	}

	@Override
	public void lidaComErro(Exception e) {
		e.printStackTrace();
		Toast.makeText(this, "Erro ao buscar dados", Toast.LENGTH_LONG).show();
	}


	@Override
	public CarangosApplication getCarangosApplication() {
		return (CarangosApplication) getApplication();
	}

	public void alteraEstadoEExecuta(EstadoMainActivity estado) {
		MyLog.i(this.estado);
		this.estado = estado;
		this.estado.executa(this);
		MyLog.i(this.estado);

	}

	public void buscaPublicacoes() {
		new BuscaMaisPublicacoesTask(this).execute();
	}
}
