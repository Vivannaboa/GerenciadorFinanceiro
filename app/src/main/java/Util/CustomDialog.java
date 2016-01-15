package Util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;

import com.financa.vivan.gerenciadorfinanceiro.R;

public class CustomDialog extends Dialog {


    public CustomDialog(Context context)
    {
        super(context);

        // get this window's layout parameters so we can change the position
        WindowManager.LayoutParams params = getWindow().getAttributes();

        // change the position. 0,0 is center
        params.x = 0;
        params.y = 250;
        this.getWindow().setAttributes(params);

        // no title on this dialog
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.custom_dialog_layout);

        // get our tabHost from the xml
        TabHost tabs = (TabHost)findViewById(R.id.TabHost01);
        tabs.setup();

        // create tab 1
        TabHost.TabSpec tab1 = tabs.newTabSpec("tab1");
        tab1.setContent(R.id.listView01);
        tab1.setIndicator("List 1");
        tabs.addTab(tab1);

        // create tab 2
        TabHost.TabSpec tab2 = tabs.newTabSpec("tab2");
        tab2.setContent(R.id.listView02);
        tab2.setIndicator("List 2");
        tabs.addTab(tab2);
    }



}