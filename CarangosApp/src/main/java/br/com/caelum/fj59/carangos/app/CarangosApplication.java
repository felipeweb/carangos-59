package br.com.caelum.fj59.carangos.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.tasks.RegistraAparelhoTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipeweb on 5/7/16.
 */

public class CarangosApplication extends Application {
	private List<AsyncTask<?, ?, ?>> tasks = new ArrayList<AsyncTask<?, ?, ?>>();
	private List<Publicacao> publicacoes = new ArrayList<Publicacao>();
	private static final String REGISTRADO = "registradoNoGcm";
	private static final String ID_DO_REGISTRO = "idDoRegistro";
	private SharedPreferences preferences;

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void limpaTudo() {
		for (AsyncTask task : this.tasks) {
			task.cancel(true);
		}
		this.tasks.clear();
	}

	public void registra(AsyncTask<?, ?, ?> task) {
		tasks.add(task);
	}

	public void desregistra(AsyncTask<?, ?, ?> task) {
		tasks.remove(task);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		preferences = getSharedPreferences("configs", Activity.MODE_PRIVATE);
		registraNoGcm();
	}

	public void registraNoGcm() {
		if (!usuarioRegistrado()) {
			new RegistraAparelhoTask(this).execute();
		} else {
			MyLog.i("Aparelho já cadastrado! Seu id é: " + preferences.getString(ID_DO_REGISTRO, null));
		}
	}

	private boolean usuarioRegistrado() {
		return preferences.getBoolean(REGISTRADO, false);
	}

	public void lidaComRespostaDoRegistroNoServidor(String registro) {
		if (registro != null) {
			SharedPreferences.Editor editor = preferences.edit();
			editor.putBoolean(REGISTRADO, true);
			editor.putString(ID_DO_REGISTRO, registro);
			editor.commit();
		}
	}

}
