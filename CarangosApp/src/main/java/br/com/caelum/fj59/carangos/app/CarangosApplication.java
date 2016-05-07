package br.com.caelum.fj59.carangos.app;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipeweb on 5/7/16.
 */

public class CarangosApplication extends Application {
	private List<AsyncTask<?, ?, ?>> tasks = new ArrayList<AsyncTask<?, ?, ?>>();

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
}
