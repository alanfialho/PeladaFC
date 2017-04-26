package com.br.peladafc.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.br.peladafc.R;
import com.br.peladafc.view.AutenticarContaActivity;

/**
 * Created by alan on 27/03/2017.
 */

public class NotificacaoRecebedora extends BroadcastReceiver {

    private static int NOTIFICACAO_RECEBEDORA_ID = 1;

    public NotificacaoRecebedora() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //TODO: adicionar icone de notificação
        NotificacaoDados dados = (NotificacaoDados) intent.getSerializableExtra("notificacaoDados");
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setContentTitle(dados.getTitulo())
                        .setContentText(dados.getTexto());

        Intent resultIntent = new Intent(context, dados.getCallBack());
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        builder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICACAO_RECEBEDORA_ID, builder.build());
    }
}
