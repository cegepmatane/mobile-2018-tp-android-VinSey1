package ca.qc.cgmatane.informatique.todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceptionAlarme extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarme !!", Toast.LENGTH_SHORT).show();
    }
}