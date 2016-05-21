package br.com.caelum.fj59.carangos.tasks;

import android.os.Message;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.webservice.WebClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by felipeweb on 5/21/16.
 */
public class BuscaLeiloesTask extends TimerTask {
	private Calendar horarioUltimaBusca;
	private CustomHandler handler;

	public BuscaLeiloesTask(CustomHandler handler, Calendar horarioUltimaBusca) {
		this.horarioUltimaBusca = horarioUltimaBusca;
		this.handler = handler;

	}

	@Override
	public void run() {
		MyLog.i("Efetuando nova busca!");
		WebClient webClient = new WebClient("leilao/leilaoid54635/" +
				new SimpleDateFormat("ddMMyy-HHmmss") .format(horarioUltimaBusca.getTime()));
		String json = webClient.get();
		MyLog.i("Lances recebidos: "+json);
		Message message = handler.obtainMessage();
		message.obj = json;
		this.handler.sendMessage(message);
		this.horarioUltimaBusca = Calendar.getInstance();
	}

	public void executa() {
		Timer timer = new Timer();
		timer.schedule(this, 0, 30 * 1000);
	}
}

