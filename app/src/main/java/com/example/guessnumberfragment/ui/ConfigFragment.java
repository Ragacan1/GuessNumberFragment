package com.example.guessnumberfragment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.guessnumberfragment.R;
import com.example.guessnumberfragment.databinding.FragmentConfigBinding;
import com.example.guessnumberfragment.data.Jugador;


public class ConfigFragment extends Fragment {

    private FragmentConfigBinding binding;

    public ConfigFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentConfigBinding.inflate(inflater);
        binding.setMessage(new Jugador());

        binding.btnJugar.setOnClickListener(view -> {
            //OPCION 1: QUE SE PASA UN BUNDLE
            Bundle bundle= new Bundle();
            bundle.putParcelable(Jugador.KEY, binding.getMessage());
            NavHostFragment.findNavController(this).navigate(R.id.playFragment, bundle);
            //OPCION 2: SE PASA UN OBJETO MESSAGE

            //ConfigFragmentDirections.ActionFragmentAToFragmentB action=FragmentADirections.actionFragmentAToFragmentB(binding.getMessage());
            //NavHostFragment.findNavController(this).navigate(action);
        });

        return binding.getRoot();

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    /*public void jugar(View view) {
        if (!checkCampos() || !checkIntentos())
            return;
        else {
            ((MainActivity) getApplication()).setJugador(binding.etJugador.getText().toString(), Integer.parseInt(binding.etNumIntentos.getText().toString()));
            Intent intent = new Intent(this, PlayFragment.class);
            startActivity(intent);
            finish();
        }
    }*/

    /**
     * Verifica que ningún campo quede vacío
     */
    public boolean checkCampos() {
        String etJugador = binding.etJugador.getText().toString();
        String etNumIntentos = String.valueOf(binding.etNumIntentos.getText());

        if (etJugador.equals("") || etNumIntentos.equals("")) {
            //Toast.makeText(this, "Todos los campos deben estar rellenos", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    /**
     * Verifica que el rango de intentos sea entre 1 y 100
     */
    public boolean checkIntentos() {
        String etNumIntentos = String.valueOf(binding.etNumIntentos.getText());

        if (Integer.parseInt(binding.etNumIntentos.getText().toString()) > 100 || Integer.parseInt(binding.etNumIntentos.getText().toString()) < 1) {
            //Toast.makeText(this, "Los intentos deben estar entre 1 y 100", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

}