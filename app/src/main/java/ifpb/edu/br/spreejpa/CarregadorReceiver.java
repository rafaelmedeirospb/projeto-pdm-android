package ifpb.edu.br.spreejpa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Windows 10 on 13/02/2017.
 */

public class CarregadorReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        String msg;

        switch(intent.getAction()){
            case Intent.ACTION_POWER_CONNECTED: msg = "Carregador Conectado"; break;
            case Intent.ACTION_POWER_DISCONNECTED: msg="Carregador desconectado"; break;
            default: msg="Desconhecido";
        }
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
