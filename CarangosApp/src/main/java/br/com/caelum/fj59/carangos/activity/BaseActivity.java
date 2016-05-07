package br.com.caelum.fj59.carangos.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import br.com.caelum.fj59.carangos.app.CarangosApplication;

public class BaseActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		CarangosApplication application = (CarangosApplication) getApplication();
		application.limpaTudo();
		super.onDestroy();
	}
}
