package br.com.caelum.fj59.carangos.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import br.com.caelum.fj59.carangos.infra.MyLog;

/**
 * Created by felipeweb on 5/21/16.
 */

public class GCMBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		MyLog.i("Chegou a mensagem do GCM!");
	}
}
