package br.com.caelum.fj59.carangos.activity;

import android.os.Bundle;
import android.widget.Toast;
import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.app.CarangosApplication;
import br.com.caelum.fj59.carangos.evento.EventoPublicacoesRecebidas;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.navegacao.EstadoMainActivity;
import br.com.caelum.fj59.carangos.tasks.BuscaMaisPublicacoesDelegate;
import br.com.caelum.fj59.carangos.tasks.BuscaMaisPublicacoesTask;

import java.util.List;

public class MainActivity extends BaseActivity implements BuscaMaisPublicacoesDelegate {
	private EstadoMainActivity estado;
	private static final String ESTADO_ATUAL = "ESTADO_ATUAL";
	private EventoPublicacoesRecebidas evento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.estado = EstadoMainActivity.INICIO;
		this.evento = EventoPublicacoesRecebidas.registraObservador(this);
	}


	@Override
	public void lidaComRetorno(List<Publicacao> resultado) {
		CarangosApplication application = getCarangosApplication();
		List<Publicacao> publicacoes = application.getPublicacoes();
		publicacoes.clear();
		publicacoes.addAll(resultado);
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
		new BuscaMaisPublicacoesTask(getCarangosApplication()).execute();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		MyLog.i("SALVANDO ESTADO!");
		outState.putSerializable(ESTADO_ATUAL, this.estado);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		MyLog.i("RESTAURANDO ESTADO!");
		this.estado = (EstadoMainActivity) savedInstanceState.getSerializable(ESTADO_ATUAL);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MyLog.i("EXECUTANDO ESTADO: " + this.estado);
		this.estado.executa(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.evento.desregistra(getCarangosApplication());
	}
}
