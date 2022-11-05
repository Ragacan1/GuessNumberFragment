package com.example.guessnumberfragment.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentConfigBinding.inflate(inflater);
        binding.setJugador(new Jugador());

        binding.btnJugar.setOnClickListener(view -> {
            jugar();
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null)
            if (binding != null)
                binding.setJugador(savedInstanceState.getParcelable(Jugador.KEY));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (binding != null)
            outState.putParcelable(Jugador.KEY, binding.getJugador());
    }

    public void jugar() {
        if (!checkCampos() || !checkIntentos())
            return;
        else {
            binding.getJugador().setNombre(binding.etJugador.getText().toString());
            binding.getJugador().setnIntentos(Integer.parseInt(binding.etNumIntentos.getText().toString()));
            navegacion();
        }
    }

    /**
     * Verifica que ningún campo quede vacío
     */
    public boolean checkCampos() {
        String etJugador = binding.etJugador.getText().toString();
        String etNumIntentos = String.valueOf(binding.etNumIntentos.getText());

        if (etJugador.equals("") || etNumIntentos.equals("")) {
            Toast.makeText(getActivity(), "Todos los campos deben estar rellenos", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    /**
     * Verifica que el rango de intentos sea entre 1 y 100
     */
    public boolean checkIntentos() {
        if (Integer.parseInt(binding.etNumIntentos.getText().toString()) > 100 || Integer.parseInt(binding.etNumIntentos.getText().toString()) < 1) {
            Toast.makeText(getActivity(), "Los intentos deben estar entre 1 y 100", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    public void navegacion() {
        ConfigFragmentDirections.ActionConfigFragmentToPlayFragment action = ConfigFragmentDirections.actionConfigFragmentToPlayFragment(binding.getJugador());
        NavHostFragment.findNavController(this).navigate(action);
    }
}