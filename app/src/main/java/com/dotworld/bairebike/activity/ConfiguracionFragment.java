package com.dotworld.bairebike.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dotworld.bairebike.R;


public class ConfiguracionFragment extends Fragment {

    Button boton = null;
    EditText uri = null;
    String url = null;
    View rootView = null;
    public final static String NOMBRE_FILE_PREFERENCIAS = "bairesbike";

    public ConfiguracionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_configuracion, container, false);
        boton = (Button)rootView.findViewById(R.id.button);
        try {
            /**
             * Este codigo refiere a recuperar la url guardada de las preferencias
             */
            SharedPreferences sp = getActivity().getSharedPreferences(NOMBRE_FILE_PREFERENCIAS,
                    Context.MODE_PRIVATE);
            String valor = sp.getString("url", "");
            uri = (EditText) rootView.findViewById(R.id.editText);
            uri.setText(valor);
        } catch (Exception e){
            Toast.makeText(getActivity(),
                    getActivity().getString(R.string.url_error_read),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                uri = (EditText) rootView.findViewById(R.id.editText);
                url = uri.getText().toString();
                if (url.length() >= 8) {
                    try {
                        /**
                         * Este codigo permite guardar el string de url en preferencias
                         * prestar atencion que debe ser tomado el get shared de el get activity
                         * y el MODE es del context
                         */
                        SharedPreferences sp = getActivity().getSharedPreferences(NOMBRE_FILE_PREFERENCIAS,
                                Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("url", url);
                        editor.commit();
                        Toast toast = Toast.makeText(getActivity(),
                                getActivity().getString(R.string.parametros_guardados),
                                Toast.LENGTH_SHORT);
                        toast.show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(),
                                getActivity().getString(R.string.url_error),
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();

                    }

                } else {
                    Toast.makeText(getActivity(),
                            getActivity().getString(R.string.url_error),
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}
